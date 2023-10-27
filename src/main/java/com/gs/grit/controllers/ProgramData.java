package com.gs.grit.controllers;

public class ProgramData {
    private Integer programId;
    private String programName;

    // Constructors, getters, and setters

    public ProgramData() {
    }

    public ProgramData(Integer programId, String programName) {
        this.programId = programId;
        this.programName = programName;
    }

    public Integer getProgramId() {
        return programId;
    }

    public void setProgramId(Integer programId) {
        this.programId = programId;
    }

    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }
}
