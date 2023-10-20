package com.gs.grit.repositories;

import com.gs.grit.entities.Registrations;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegistrationsRepository extends JpaRepository<Registrations, Integer> {
    Registrations findByEmail(String email);
}
