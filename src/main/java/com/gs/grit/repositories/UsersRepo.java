package com.gs.grit.repositories;

import com.gs.grit.entities.Users;
import org.springframework.data.repository.CrudRepository;

public interface UsersRepo extends CrudRepository<Users, Integer> {
    Users findByUsername(String username);
}
