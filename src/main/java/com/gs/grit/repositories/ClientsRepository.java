package com.gs.grit.repositories;

import com.gs.grit.entities.Clients;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientsRepository extends JpaRepository<Clients, Integer>{
    Clients findByEmail(String email);
}