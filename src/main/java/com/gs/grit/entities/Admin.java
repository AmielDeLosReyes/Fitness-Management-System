package com.gs.grit.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "admin")
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer admin_id;

    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;

    public Integer getadmin_id() {
        return admin_id;
    }

    public void setadmin_id(Integer admin_id) {
        this.admin_id = admin_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Admin() {

    }
}

