package com.example.aiet_server.domain.user.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Entity
@RequiredArgsConstructor
@Table(name = "user")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;
    @Column(nullable = false)
    private String id;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String name;
    @Column
    private int height;
    @Column
    private int weight;
    @Column
    private String birth;
    @Column
    private char gender;

    public UserEntity(String id, String password, String name) {
        this.id = id;
        this.password = password;
        this.name = name;
    }
}
