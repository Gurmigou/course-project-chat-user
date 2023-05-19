package com.videochat.videochatuser.model.entity;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum Interests {
    SPORT("Sport"),
    MUSIC("Music"),
    GAMES("Games"),
    MOVIES("Movies"),
    BOOKS("Books"),
    ART("Art"),
    FOOD("Food"),
    TRAVEL("Travel"),
    FASHION("Fashion"),
    SCIENCE("Science"),
    TECHNOLOGY("Technology"),
    NATURE("Nature"),
    ANIMALS("Animals"),
    HISTORY("History"),
    POLITICS("Politics");

    @JsonValue
    private final String name;

    Interests(String name) {
        this.name = name;
    }

    public static Interests fromString(String name) {
        for (Interests interest : Interests.values()) {
            if (interest.name.equalsIgnoreCase(name)) {
                return interest;
            }
        }
        throw new IllegalArgumentException("No enum constant with name " + name);
    }
}
