package com.gs.grit.repositories;

import com.gs.grit.entities.Clients;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClientsRepository extends JpaRepository<Clients, Integer> {
    List<Clients> findByFirstName(String firstName);
}

