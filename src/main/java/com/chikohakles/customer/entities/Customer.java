package com.chikohakles.customer.entities;

import java.io.Serializable;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Customer implements Serializable{
    @Id
    private Integer id;
    private String nama;
    private String alamat;
    private String email;
    private String telepon;
}
