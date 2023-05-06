package com.videochat.videochatuser.service;

import com.videochat.videochatuser.model.dto.LoginDto;
import com.videochat.videochatuser.model.dto.RegistrationDto;
import com.videochat.videochatuser.model.dto.TokenDto;
import com.videochat.videochatuser.model.entity.User;
import com.videochat.videochatuser.repository.UserRepository;
import com.videochat.videochatuser.security.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SecurityService {
    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public SecurityService(UserRepository userRepository, JwtProvider jwtProvider, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.jwtProvider = jwtProvider;
        this.passwordEncoder = passwordEncoder;
    }

    public User registerUser(RegistrationDto registrationDto) {
        if (isUserExistsByLogin(registrationDto.getUsername())) {
            throw new IllegalStateException(String.format(
                    "User with username %s already exists", registrationDto.getUsername()));
        }

        User user = createNewUser(registrationDto);
        return saveUser(user);
    }

    public TokenDto loginUser(LoginDto loginDto) {
        User user = findUserByUsername(loginDto.getUsername())
                .orElseThrow(() -> new IllegalStateException(
                        "User with email " + loginDto.getUsername() + " does not exist."));

        if (!passwordEncoder.matches(loginDto.getPassword(), user.getPassword()))
            throw new IllegalStateException("Passwords don't match");

        String jwtToken = jwtProvider.generateToken(user.getUsername());
        return new TokenDto(jwtToken);
    }

    public TokenDto generateToken(String email) {
        return new TokenDto(jwtProvider.generateToken(email));
    }

    private Optional<User> findUserByUsername(String email) {
        return userRepository.findByUsername(email);
    }

    private boolean isUserExistsByLogin(String email) {
        return findUserByUsername(email).isPresent();
    }

    private User saveUser(User user) {
        return userRepository.save(user);
    }

    private User createNewUser(RegistrationDto registrationDto) {
        var newUser = User.builder()
                .username(registrationDto.getUsername())
                .password(encodePassword(registrationDto.getPassword()))
                .myGender(registrationDto.getMyGender())
                .genderPreference(registrationDto.getGenderPreference())
                .build();
        newUser.setInterests(registrationDto.getInterests());
        return newUser;
    }

    private String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }
}
