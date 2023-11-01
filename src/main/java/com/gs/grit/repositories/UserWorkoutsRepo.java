package com.gs.grit.repositories;

import com.gs.grit.entities.UserWorkouts;
import com.gs.grit.entities.UserWorkoutsId;
import com.gs.grit.entities.Workouts;
import jakarta.persistence.Tuple;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserWorkoutsRepo extends JpaRepository<UserWorkouts, UserWorkoutsId> {

    @Query(value = "SELECT w.* FROM workouts w " +
            "JOIN user_workout uw ON w.workout_id = uw.workout_id " +
            "WHERE uw.user_id = :user_id", nativeQuery = true)
    List<Workouts> findWorkoutsByUserId(@Param("user_id") Integer user_id);

    @Query(value = "SELECT up.user_id, pw.program_id, p.program_name, w.workout_name, w.workout_location FROM userPrograms up " +
            "INNER JOIN program_workouts pw ON up.program_id = pw.program_id " +
            "INNER JOIN programs p ON up.program_id = p.program_id " +
            "INNER JOIN workouts w ON pw.workout_id = w.workout_id " +
            "WHERE user_id = :userId", nativeQuery = true)
    List<Tuple> findWorkoutsByUserProgram(@Param("userId") Integer userId);

    @Query(value = "SELECT DISTINCT p.program_id, w.workout_id, w.workout_name, w.workout_location " +
            "FROM program_workouts pw " +
            "JOIN programs p ON pw.program_id = p.program_id " +
            "JOIN workouts w ON pw.workout_id = w.workout_id " +
            "JOIN userPrograms up ON up.program_id = p.program_id " +
            "WHERE p.program_id = :programId AND up.user_id = :userId", nativeQuery = true)
    List<Tuple> findWorkouts(@Param("programId") Integer programId, @Param("userId") Integer userId);
}
