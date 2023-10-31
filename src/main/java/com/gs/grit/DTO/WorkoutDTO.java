package com.gs.grit.DTO;

public class WorkoutDTO {

    private Integer program_id;
    private Integer workout_id;
    private String workout_name;

    public Integer getProgram_id() {
        return program_id;
    }

    public void setProgram_id(Integer program_id) {
        this.program_id = program_id;
    }

// Getters and setters


    public WorkoutDTO(Integer program_id, Integer workout_id, String workout_name) {
        this.program_id = program_id;
        this.workout_id = workout_id;
        this.workout_name = workout_name;
    }

    public WorkoutDTO() {
    }

    public Integer getWorkout_id() {
        return workout_id;
    }

    public void setWorkout_id(Integer workout_id) {
        this.workout_id = workout_id;
    }

    public String getWorkout_name() {
        return workout_name;
    }

    public void setWorkout_name(String workout_name) {
        this.workout_name = workout_name;
    }
}