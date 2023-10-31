package com.gs.grit.restcontrollers;


import com.gs.grit.entities.ProgramWorkouts;
import com.gs.grit.entities.UserPrograms;
import com.gs.grit.entities.Workouts;
import com.gs.grit.repositories.ProgramWorkoutsRepo;
import com.gs.grit.repositories.UserProgramsRepo;
import com.gs.grit.repositories.WorkoutsRepo;
import jakarta.persistence.Tuple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.gs.grit.DTO.WorkoutDTO; // Import the WorkoutDTO class


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class API {

    @Autowired
    UserProgramsRepo userProgramsRepo;

    @Autowired
    WorkoutsRepo workoutsRepo;

    @Autowired
    private ProgramWorkoutsRepo programWorkoutsRepo;


    @GetMapping("/userPrograms")
    public List<UserPrograms> getUserPrograms(){

        return userProgramsRepo.findByUserId(3);
    }

    @GetMapping("/getWorkoutIds")
    public List<Workouts> getWorkoutsByIds(@RequestParam List<Integer> workoutIds) {
        return workoutsRepo.findByWorkoutIds(workoutIds);
    }

    @GetMapping("/byProgram/{programId}")
    public List<Map<String, Object>> getWorkoutsByProgramId(@PathVariable Integer programId) {
        List<Tuple> workoutTuples = programWorkoutsRepo.findWorkoutsByProgramId(programId);
        List<Map<String, Object>> workouts = new ArrayList<>();

        for (Tuple tuple : workoutTuples) {
            Map<String, Object> workout = new HashMap<>();
            workout.put("program_id", tuple.get(0, Integer.class));
            workout.put("workout_id", tuple.get(1, Integer.class));
            workout.put("workout_name", tuple.get(2, String.class));
            workouts.add(workout);
        }

        return workouts;
    }

}
