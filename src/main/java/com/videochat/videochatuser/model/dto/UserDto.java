package com.videochat.videochatuser.model.dto;

import com.videochat.videochatuser.model.entity.Gender;
import com.videochat.videochatuser.model.entity.Interests;

import java.util.List;

public record UserDto(String username, Gender myGender, Gender preferredGender, List<Interests> interests) {
}
