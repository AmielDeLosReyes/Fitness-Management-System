package com.gs.grit.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class UserWorkoutsId implements Serializable {
    @Column(name = "user_id")
    private Integer user_id;
    @Column(name = "workout_id")
    private Integer workout_id;

    public UserWorkoutsId(Integer user_id, Integer workout_id) {
        this.user_id = user_id;
        this.workout_id = workout_id;
    }

    public UserWorkoutsId() {
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getWorkout_id() {
        return workout_id;
    }

    public void setWorkout_id(Integer workout_id) {
        this.workout_id = workout_id;
    }
}
