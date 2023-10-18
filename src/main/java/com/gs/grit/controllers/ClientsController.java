//package com.gs.grit.controllers;
//
//import com.gs.grit.entities.Clients;
//import com.gs.grit.services.ClientsService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/clients")
//@ComponentScan(basePackages = "com.gs.grit.services")
//public class ClientsController {
//    public final ClientsService clientsService;
//
//    @Autowired
//    public ClientsController(ClientsService clientsService) {
//        this.clientsService = clientsService;
//    }
//
//    @GetMapping
//    public List<Clients> getAllClients() {
//        return clientsService.getAllClients();
//    }
//
//    @PostMapping
//    public Clients createClient(@RequestBody Clients clients) {
//        return clientsService.saveClient(clients);
//    }
//}
