package com.example.exp4.Entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private int currentID;
    private String name;
    private String telephone;
    private String email;
    private String qq;
    private String address;

    public User(String name, String phone, String email, String qq, String address) {
        this.name = name;
        this.telephone = phone;
        this.email = email;
        this.qq = qq;
        this.address = address;
    }

    public User() {

    }
}