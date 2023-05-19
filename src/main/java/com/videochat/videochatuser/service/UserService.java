package com.videochat.videochatuser.service;

import com.videochat.videochatuser.model.dto.UserDto;
import com.videochat.videochatuser.model.entity.User;
import com.videochat.videochatuser.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDto getUserInfo(String username) {
        var user = findUserByUsername(username);
        return mapToUserDto(user);
    }

    protected User findUserByUsername(String username) {
        return userRepository
                .findByUsername(username)
                .orElseThrow(() -> new IllegalStateException("User" + username + " not found"));
    }

    @Transactional
    public UserDto updateUser(String oldName, UserDto userDto) {
        var user = findUserByUsername(oldName);
        user.setUsername(userDto.username());
        user.setMyGender(userDto.myGender());
        user.setGenderPreference(userDto.preferredGender());
        user.setInterests(userDto.interests());
        var updatedUser = userRepository.save(user);
        return mapToUserDto(updatedUser);
    }

    private UserDto mapToUserDto(User user) {
        return new UserDto(
                user.getUsername(),
                user.getMyGender(),
                user.getGenderPreference(),
                user.getInterests()
        );
    }
}
