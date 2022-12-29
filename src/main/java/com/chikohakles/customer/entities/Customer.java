package com.chikohakles.customer.entities;

import java.io.Serializable;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

//buat bikin getId, getNama, dll.
import lombok.Getter;
//buat bikin setId, getNama, dll.
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
