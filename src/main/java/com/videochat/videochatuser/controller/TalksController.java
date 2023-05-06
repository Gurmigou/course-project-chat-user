package com.videochat.videochatuser.controller;

import com.videochat.videochatuser.model.dto.Response;
import com.videochat.videochatuser.model.dto.TalkDto;
import com.videochat.videochatuser.service.TalksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/talk")
public class TalksController {
    private final TalksService talksService;

    @Autowired
    public TalksController(TalksService talksService) {
        this.talksService = talksService;
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllTalksForUser(@RequestParam String username) {
        try {
            var talks = talksService.getAllTalksForUser(username);
            return ResponseEntity.ok(talks);
        } catch (Exception e) {
            return ResponseEntity
                    .badRequest()
                    .body(new Response(false, e.getMessage()));
        }
    }

    public ResponseEntity<?> saveTalk(@RequestBody TalkDto talkDto) {
        try {
            talksService.saveTalk(talkDto);
            return ResponseEntity.ok(new Response(true, "Talk saved"));
        } catch (Exception e) {
            return ResponseEntity
                    .badRequest()
                    .body(new Response(false, e.getMessage()));
        }
    }
}
