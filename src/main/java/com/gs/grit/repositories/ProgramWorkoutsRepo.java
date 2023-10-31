package com.gs.grit.repositories;

import com.gs.grit.DTO.WorkoutDTO;
import com.gs.grit.entities.ProgramWorkouts;
import jakarta.persistence.Tuple;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProgramWorkoutsRepo extends JpaRepository<ProgramWorkouts, Integer> {
    // Define a custom query to retrieve workout IDs and names associated with a specific program
//    @Query("SELECT new com.gs.grit.DTO.WorkoutDTO(w.workout_id, w.workout_name) FROM ProgramWorkouts pw JOIN pw.workouts w WHERE pw.program_id = :programId")
//    List<WorkoutDTO> findWorkoutsByProgramId(@Param("programId") Integer programId);

//    @Query(value = "SELECT pw.program_id, w.workout_id, w.workout_name FROM program_workouts pw INNER JOIN pw.workouts w WHERE pw.program_id = :program_id", nativeQuery = true)
//    List<Integer> findWorkoutsByProgramId(@Param("program_id") Integer program_id);

    @Query(value = "SELECT pw.program_id, w.workout_id, w.workout_name, w.workout_location FROM program_workouts pw INNER JOIN workouts w ON pw.workout_id = w.workout_id WHERE pw.program_id = :program_id", nativeQuery = true)
    List<Tuple> findWorkoutsByProgramId(@Param("program_id") Integer program_id);

}
