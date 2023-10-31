package com.gs.grit.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "workouts")
public class Workouts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer workout_id;

    @Column(name = "workout_name")
    private String workout_name;

    @Column(name = "workout_location")
    private String workout_location;

    public Workouts(){

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

    public String getWorkout_location() {
        return workout_location;
    }

    public void setWorkout_location(String workout_location) {
        this.workout_location = workout_location;
    }
}
