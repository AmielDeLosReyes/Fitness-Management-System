package com.gs.grit.restcontrollers;

import com.gs.grit.entities.UserPrograms;
import com.gs.grit.repositories.UserProgramsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class API {

    @Autowired
    UserProgramsRepo userProgramsRepo;

    @GetMapping("/userPrograms")
    public List<UserPrograms> getUserPrograms(){

        return userProgramsRepo.findByUserId(3);
    }
}
