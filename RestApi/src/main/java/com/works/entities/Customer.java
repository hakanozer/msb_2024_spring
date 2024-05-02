package com.works.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cid;

    @Size(min = 3, max = 100)
    @NotEmpty
    @NotNull
    @Column(unique = true, length = 100)
    private String username;

    @NotEmpty
    @NotNull
    private String password;

    @Size(min = 2, max = 100)
    @NotEmpty
    @NotNull
    @Column(length = 100)
    private String name;

}
