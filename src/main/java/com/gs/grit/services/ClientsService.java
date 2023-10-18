//package com.gs.grit.services;
//
//import com.gs.grit.entities.Clients;
//import com.gs.grit.repositories.ClientsRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//@ComponentScan(basePackages = "com.gs.grit.repositories")
//public class ClientsService {
//
//    public final ClientsRepository clientsRepository;
//
//    @Autowired
//    public ClientsService(ClientsRepository clientsRepository) {
//        this.clientsRepository = clientsRepository;
//    }
//
//    public List<Clients> getAllClients(){
//        return (List<Clients>) clientsRepository.findAll();
//    }
//
//    public Clients saveClient(Clients clients){
//        return clientsRepository.save(clients);
//    }
//
//}