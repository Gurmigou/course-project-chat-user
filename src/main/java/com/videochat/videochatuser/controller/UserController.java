package com.videochat.videochatuser.controller;

import com.videochat.videochatuser.model.dto.Response;
import com.videochat.videochatuser.model.dto.UserDto;
import com.videochat.videochatuser.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<?> getUserInfo(@RequestParam String username) {
        try {
            var userInfo = userService.getUserInfo(username);
            return ResponseEntity.ok(userInfo);
        } catch (Exception e) {
            return ResponseEntity
                    .badRequest()
                    .body(new Response(false, e.getMessage()));
        }
    }

    @PutMapping
    public ResponseEntity<?> updateUserInfo(@RequestBody UserDto userDto) {
        try {
            var userInfo = userService.updateUser(userDto);
            return ResponseEntity.ok(userInfo);
        } catch (Exception e) {
            return ResponseEntity
                    .badRequest()
                    .body(new Response(false, e.getMessage()));
        }
    }
}
