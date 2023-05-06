package com.videochat.videochatuser.model.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDate;

@Entity
@Getter
public class Talk {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "first_peer_id")
    private User firstPeer;

    @ManyToOne
    @JoinColumn(name = "second_peer_id")
    private User secondPeer;

    private Long duration;

    private LocalDate date;
}
