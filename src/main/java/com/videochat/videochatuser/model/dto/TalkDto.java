package com.videochat.videochatuser.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TalkDto {
    private String firstPeerName;
    private String secondPeerName;
    private Long duration;
    private LocalDate date;
}


