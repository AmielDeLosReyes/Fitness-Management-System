package com.gs.grit.controllers;

import com.gs.grit.entities.Clients;
import com.gs.grit.repositories.ClientsRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ClientsController {
    private final ClientsRepository clientsRepository;

    public ClientsController(ClientsRepository clientsRepository) {
        this.clientsRepository = clientsRepository;
    }

    @GetMapping("/clients")
    public List<Clients> getAllClients() {
        return clientsRepository.findAll();
    }

}
