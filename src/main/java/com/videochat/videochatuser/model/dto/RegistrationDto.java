package com.videochat.videochatuser.model.dto;

import com.videochat.videochatuser.model.entity.Gender;
import com.videochat.videochatuser.model.entity.Interests;
import lombok.Data;

import java.util.List;

@Data
public class RegistrationDto {
    private String username;
    private String password;
    private Gender myGender;
    private Gender genderPreference;
    private List<Interests> interests;
}
