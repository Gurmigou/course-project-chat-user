package com.videochat.videochatuser.controller;

import com.videochat.videochatuser.model.dto.LoginDto;
import com.videochat.videochatuser.model.dto.RegistrationDto;
import com.videochat.videochatuser.model.dto.Response;
import com.videochat.videochatuser.model.dto.TokenDto;
import com.videochat.videochatuser.service.SecurityService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/security")
@CrossOrigin("*")
public class SecurityController {
    private final SecurityService userService;

    @Autowired
    public SecurityController(SecurityService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUsingEmail(@RequestBody LoginDto loginDto) {
        try {
            TokenDto tokenDto = userService.loginUser(loginDto);
            return ResponseEntity.ok(tokenDto);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity
                    .badRequest()
                    .body(new Response(false, e.getMessage()));
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody @Valid RegistrationDto registrationDto) {
        try {
            System.out.println("In registerUser");
            userService.registerUser(registrationDto);
            return ResponseEntity.ok(new Response(true, "You have been successfully registered"));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity
                    .badRequest()
                    .body(new Response(false, e.getMessage()));
        }
    }

    @PostMapping("/update-token")
    public ResponseEntity<?> authenticateUsingJwtToken(Principal principal) {
        try {
            TokenDto tokenDto = userService.generateToken(principal.getName());
            return ResponseEntity.ok(tokenDto);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity
                    .badRequest()
                    .body(new Response(false, "Jwt token is invalid: " + e.getMessage()));
        }
    }
}