package com.gs.grit.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "programs")
public class Programs {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer program_id;

    @Column(name="program_name")
    private String program_name;

    @Column(name="program_desc")
    private String program_desc;

    public Programs() {
    }

    public Integer getProgram_id() {
        return program_id;
    }

    public void setProgram_id(Integer program_id) {
        this.program_id = program_id;
    }

    public String getProgram_name() {
        return program_name;
    }

    public void setProgram_name(String program_name) {
        this.program_name = program_name;
    }

    public String getProgram_desc() {
        return program_desc;
    }

    public void setProgram_desc(String program_desc) {
        this.program_desc = program_desc;
    }
}
