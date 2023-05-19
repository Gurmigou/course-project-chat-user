package com.videochat.videochatuser.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private Gender myGender;

    @Enumerated(EnumType.STRING)
    private Gender genderPreference;

    private String interests;

    @OneToMany(mappedBy = "firstPeer")
    private List<Talk> talksAsFirstPeer;

    @OneToMany(mappedBy = "secondPeer")
    private List<Talk> talksAsSecondPeer;

    public List<Talk> getTalks() {
        var arrayList = new ArrayList<Talk>();
        arrayList.addAll(talksAsFirstPeer);
        arrayList.addAll(talksAsSecondPeer);
        return arrayList;
    }

    public void setInterests(List<Interests> interests) {
        this.interests = interests.stream()
                .map(Interests::getName)
                .collect(Collectors.joining(","));
    }

    public List<Interests> getInterests() {
        return Stream.of(interests.split(","))
                .map(Interests::fromString)
                .collect(Collectors.toList());
    }
}
