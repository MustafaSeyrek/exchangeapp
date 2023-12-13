package com.seyrek.exchangeapp.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String type;

    int userId;

    int shareId;

    int count;

    @Column(scale = 2)
    float price;
}
