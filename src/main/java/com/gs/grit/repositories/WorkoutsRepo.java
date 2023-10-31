package com.gs.grit.repositories;

import com.gs.grit.entities.Workouts;
import org.hibernate.jdbc.Work;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WorkoutsRepo extends JpaRepository<Workouts, Integer> {

    @Query(value = "SELECT * FROM workouts WHERE workout_id IN :workoutIds", nativeQuery = true)
    List<Workouts> findByWorkoutIds(@Param("workoutIds") List<Integer> workoutIds);
}
