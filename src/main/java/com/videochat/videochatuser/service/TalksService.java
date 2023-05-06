package com.videochat.videochatuser.service;

import com.videochat.videochatuser.model.dto.TalkDto;
import com.videochat.videochatuser.model.entity.Talk;
import com.videochat.videochatuser.repository.TalkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TalksService {
    private final TalkRepository talkRepository;
    private final UserService userService;

    @Autowired
    public TalksService(TalkRepository talkRepository, UserService userService) {
        this.talkRepository = talkRepository;
        this.userService = userService;
    }

    public List<TalkDto> getAllTalksForUser(String username) {
        return talkRepository.findAllWhereUsernameIsPeer(username)
                .stream()
                .map(talk -> mapToTalkDto(talk, username))
                .toList();
    }

    @Transactional
    public void saveTalk(TalkDto talkDto) {
        var talk = new Talk();
        talk.setDuration(talkDto.getDuration());
        talk.setDate(talkDto.getDate());

        var firstPeer = userService.findUserByUsername(talkDto.getFirstPeerName());
        var secondPeer = userService.findUserByUsername(talkDto.getSecondPeerName());

        talk.setFirstPeer(firstPeer);
        talk.setSecondPeer(secondPeer);

        talkRepository.save(talk);
    }

    private TalkDto mapToTalkDto(Talk talk, String username) {
        var talkDto = new TalkDto();
        talkDto.setDuration(talk.getDuration());
        talkDto.setDate(talk.getDate());

        var firstPeer = talk.getFirstPeer();
        var secondPeer = talk.getSecondPeer();

        if (firstPeer.getUsername().equals(username)) {
            talkDto.setFirstPeerName(firstPeer.getUsername());
            talkDto.setSecondPeerName(secondPeer.getUsername());
        } else {
            talkDto.setFirstPeerName(secondPeer.getUsername());
            talkDto.setSecondPeerName(firstPeer.getUsername());
        }
        return talkDto;
    }
}
