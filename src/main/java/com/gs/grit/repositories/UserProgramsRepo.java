package com.gs.grit.repositories;

import com.gs.grit.entities.Programs;
import com.gs.grit.entities.UserPrograms;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserProgramsRepo extends JpaRepository<UserPrograms, Integer> {

    @Query(value = "SELECT * FROM userPrograms WHERE user_id = :userId", nativeQuery = true)
    List<UserPrograms> findByUserId(@Param("userId") int userId);

    @Query(value = "SELECT up.program_id, p.program_name " +
            "FROM userPrograms up " +
            "INNER JOIN programs p ON up.program_id = p.program_id " +
            "WHERE up.user_id = :userId", nativeQuery = true)
    List<Object[]> findProgramDataByUserId(@Param("userId") int userId);

}
