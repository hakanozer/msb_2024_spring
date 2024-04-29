package com.works.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cid;

    @Column(unique = true, length = 100)
    private String username;

    private String password;

    @Column(length = 100)
    private String name;
}
