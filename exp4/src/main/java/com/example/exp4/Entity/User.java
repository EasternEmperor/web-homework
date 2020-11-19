package com.example.exp4.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {
    private static int ID;
    private int currentID;
    private String name;
    private String telephone;
    private String email;
    private String qq;
    private String address;

    public User(String name, String phone, String email, String qq, String address) {
        this.currentID = ID;
        ID++;
        this.name = name;
        this.telephone = phone;
        this.email = email;
        this.qq = qq;
        this.address = address;
    }

    public User(int id){
        this.ID = id;
        this.currentID = id;
    }

    public User() {
        this.currentID = ID;
    }
}
