package com.videochat.videochatuser.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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

    private String duration;

    private LocalDate date;
}
