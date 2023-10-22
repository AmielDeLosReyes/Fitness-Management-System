package com.gs.grit.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "emails")
public class Emails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "email_id")
    private Integer email_id;

    @Column(name = "email")
    private String email;

    public Integer getEmail_id() {
        return email_id;
    }

    public Emails(Integer email_id, String email) {
        this.email_id = email_id;
        this.email = email;
    }

    public void setEmail_id(Integer email_id) {
        this.email_id = email_id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Emails(){
    }

}
