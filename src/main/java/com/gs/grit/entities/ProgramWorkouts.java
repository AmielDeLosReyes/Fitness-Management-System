package com.gs.grit.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "program_workouts")
public class ProgramWorkouts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer program_workout_id;

    @ManyToOne
    @JoinColumn(name = "program_id")
    private Programs programs;

    @ManyToOne
    @JoinColumn(name = "workout_id")
    private Workouts workouts;

    // Constructors, getters, and setters
    public ProgramWorkouts(Integer program_workout_id, Programs programs, Workouts workouts) {
        this.program_workout_id = program_workout_id;
        this.programs = programs;
        this.workouts = workouts;
    }

    public ProgramWorkouts() {
    }

    public Integer getProgram_workout_id() {
        return program_workout_id;
    }

    public void setProgram_workout_id(Integer program_workout_id) {
        this.program_workout_id = program_workout_id;
    }

    public Programs getPrograms() {
        return programs;
    }

    public void setPrograms(Programs programs) {
        this.programs = programs;
    }

    public Workouts getWorkouts() {
        return workouts;
    }

    public void setWorkouts(Workouts workouts) {
        this.workouts = workouts;
    }
}

