package com.gs.grit.controllers;

import com.gs.grit.entities.Clients;
import com.gs.grit.entities.User;
import com.gs.grit.repositories.UserRepo;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.gs.grit.entities.Clients;
import com.gs.grit.entities.User;
import com.gs.grit.repositories.ClientsRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Controller
public class ClientFormController {

    private final ClientsRepository ClientsRepository;

    public ClientFormController(ClientsRepository ClientsRepository) {
        this.ClientsRepository = ClientsRepository;
    }

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private UserRepo userRepo;

    @PostMapping("/clientFormComplete")
    public String clientFormComplete(HttpSession httpSession,
                                     Model model,
                                     @RequestParam String height,
                                     @RequestParam String weight,
                                     @RequestParam String age,
                                     @RequestParam String sex,
                                     @RequestParam String fitnessRoutine,
                                     @RequestParam String diet,
                                     @RequestParam String injuries,
                                     @RequestParam String struggles,
                                     @RequestParam String whyStart,
                                     @RequestParam String driven,
                                     @RequestParam String gymAccess,
                                     @RequestParam String otherInfo) {

        User user = (User) httpSession.getAttribute("user");
        model.addAttribute("user", user);

        if (user != null) {
            Integer userId = user.getUser_id();
            String firstName = user.getFirst_name();
            String lastName = user.getLast_name();

            Clients clients = new Clients();
            clients.setFirstName(firstName);
            clients.setLastName(lastName);
            clients.setHeight(height);
            clients.setWeight(weight);
            clients.setAge(age);
            clients.setSex(sex);
            clients.setFitnessRoutine(fitnessRoutine);
            clients.setDiet(diet);
            clients.setInjuries(injuries);
            clients.setStruggles(struggles);
            clients.setWhyStart(whyStart);
            clients.setDriven(driven);
            clients.setGymAccess(gymAccess);
            clients.setOtherInfo(otherInfo);

            // Save the client information to the database
            ClientsRepository.save(clients);

            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            String subject = "Thank You For Filling Up The Form!";

            String message = "We have gathered your information and will personalize the workout program based on the information you provided!\n\nExpect your workout program in a couple hours.\n\nThank you!\nGrit Dominate Team";
            simpleMailMessage.setTo(user.getEmail());
            simpleMailMessage.setSubject(subject);
            simpleMailMessage.setText(message);

            mailSender.send(simpleMailMessage);

            SimpleMailMessage simpleMailMessage1 = new SimpleMailMessage();
            String subject1 = "A Client Has Filled Out The Client Information Form!";

            String message1 = "Here are the information to help you personalize the workout program:"
                    + "\n\nFirst Name: " + firstName
                    + "\nLast Name: " + lastName
                    + "\nHeight: " + height
                    + "\nWeight: " + weight
                    + "\nAge: " + age
                    + "\nSex: " + sex
                    + "\nFitness Routine: " + fitnessRoutine
                    + "\nDiet: " + diet
                    + "\nInjuries: " + injuries
                    + "\nStruggles: " + struggles
                    + "\nWhy Start: " + whyStart
                    + "\nDriven: " + driven
                    + "\nGym Access: " + gymAccess
                    + "\nOther Information: " + otherInfo
                    + "\n\nPlease create the workout program in the next couple hours because the client is expecting it.\n\nThank you!\nGrit Dominate Team";

            simpleMailMessage1.setSubject(subject1);
            simpleMailMessage1.setTo("gritdominate@gmail.com");
            simpleMailMessage1.setText(message1);

            mailSender.send(simpleMailMessage1);

            return "successClientForm";
        }

        // return to login page
        return "redirect:/userLogin";
    }

    @PostMapping("/authenticateClient")
    public String userAuth(@RequestParam String email,
                           @RequestParam String password,
                           Model model,
                           HttpSession httpSession) {
        // Retrieve the user from the repository based on the provided email
        User user = userRepo.findByEmail(email);
        if (user != null && user.getPassword().equals(password)) {
            httpSession.setAttribute("user", user);
            // Debug statement
            System.out.println("User logged in: " + user.getFirst_name());
            return "redirect:/clientForm";
        } else {
            // Authentication failed, return to the login page
            return "redirect:/clientLogin";
        }
    }

    private static final java.util.logging.Logger LOGGER = Logger.getLogger(HomeController.class.getName());
    @GetMapping("/clientLogin")
    public String clientLogin(HttpSession httpSession, Model model) {
        User user = (User) httpSession.getAttribute("user");
        if (user != null) {

            LOGGER.info("User is logged in: " + user.getFirst_name());
            return "redirect:/clientForm";
        }
        return "clientLogin";
    }

}

