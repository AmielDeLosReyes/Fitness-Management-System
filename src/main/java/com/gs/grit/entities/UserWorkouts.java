package com.gs.grit.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import com.gs.grit.entities.UserWorkoutsId;

@Entity
@Table(name = "user_workout")
public class UserWorkouts implements Serializable {

    @EmbeddedId
    private UserWorkoutsId id;

    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "workout_id", insertable = false, updatable = false)
    private Workouts workout;


    public UserWorkouts(User user, Workouts workout) {
        this.user = user;
        this.workout = workout;
    }

    public UserWorkouts() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Workouts getWorkout() {
        return workout;
    }

    public void setWorkout(Workouts workout) {
        this.workout = workout;
    }
}
