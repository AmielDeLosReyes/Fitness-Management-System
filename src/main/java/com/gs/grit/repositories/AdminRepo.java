package com.gs.grit.repositories;

import com.gs.grit.entities.Admin;
import org.springframework.data.repository.CrudRepository;

public interface AdminRepo extends CrudRepository<Admin, Integer> {
    Admin findByUsername(String username);
}
