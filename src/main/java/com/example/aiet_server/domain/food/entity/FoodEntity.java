package com.example.aiet_server.domain.food.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "status_attribute")
public class FoodEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idx;


}
