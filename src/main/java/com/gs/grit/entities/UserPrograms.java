package com.gs.grit.entities;

import jakarta.persistence.*;

@Entity
@Table(name="userPrograms")
public class UserPrograms {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_program_id")
    private Integer user_program_id;

    @Column(name = "user_id")
    private Integer user_id;

    @Column(name="program_id")
    private Integer program_id;

    @Column(name="status")
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public UserPrograms() {
    }

    public Integer getUser_program_id() {
        return user_program_id;
    }

    public void setUser_program_id(Integer user_program_id) {
        this.user_program_id = user_program_id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getProgram_id() {
        return program_id;
    }

    public void setProgram_id(Integer program_id) {
        this.program_id = program_id;
    }
}
