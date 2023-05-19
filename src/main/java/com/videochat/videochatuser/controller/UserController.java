package com.videochat.videochatuser.controller;

import com.videochat.videochatuser.model.dto.Response;
import com.videochat.videochatuser.model.dto.UserDto;
import com.videochat.videochatuser.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/user")
@CrossOrigin("*")
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
            e.printStackTrace();
            return ResponseEntity
                    .badRequest()
                    .body(new Response(false, e.getMessage()));
        }
    }

    @PutMapping
    public ResponseEntity<?> updateUserInfo(Principal principal, @RequestBody UserDto userDto) {
        try {
            var userInfo = userService.updateUser(principal.getName(), userDto);
            return ResponseEntity.ok(userInfo);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity
                    .badRequest()
                    .body(new Response(false, e.getMessage()));
        }
    }
}
