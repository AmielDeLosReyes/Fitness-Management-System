package com.gs.grit.repositories;

import com.gs.grit.entities.Programs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProgramsRepo extends JpaRepository<Programs, Integer> {

}
