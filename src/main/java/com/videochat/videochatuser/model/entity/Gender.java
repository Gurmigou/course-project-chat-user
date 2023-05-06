package com.videochat.videochatuser.model.entity;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum Gender {
    MALE("Male"),
    FEMALE("Female"),
    I_DONT_KNOW("I don't know");

    @JsonValue
    private final String name;

    Gender(String name) {
        this.name = name;
    }
}
