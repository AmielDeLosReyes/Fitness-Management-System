package com.gs.grit.controllers;

public class ProgramData {
    private Integer programId;
    private String programName;
    private String status; // Add the status property

    public ProgramData(Integer programId, String programName, String status) {
        this.programId = programId;
        this.programName = programName;
        this.status = status; // Set the status property
    }


    // Constructors, getters, and setters

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

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
