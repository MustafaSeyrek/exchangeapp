package com.seyrek.exchangeapp.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class Share {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String code;

    @Column(scale = 2)
    float rate;

    int createdId;

    Date createdDate;

    int updatedId;

    Date updatedDate;

}
