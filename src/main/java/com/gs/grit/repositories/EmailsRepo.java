package com.gs.grit.repositories;

import com.gs.grit.entities.Emails;
import com.gs.grit.entities.Registrations;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmailsRepo extends JpaRepository<Emails, Integer> {

    Emails findByEmail(String email);

}
