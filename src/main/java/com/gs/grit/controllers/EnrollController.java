package com.gs.grit.controllers;

import com.gs.grit.entities.User;
import com.gs.grit.entities.UserPrograms;
import com.gs.grit.repositories.UserProgramsRepo;
import com.gs.grit.repositories.UserRepo;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class EnrollController {

    @Autowired
    private UserProgramsRepo userProgramsRepo;

    @Autowired
    private JavaMailSender mailSender;

    @Transactional
    @PostMapping("/enrol")
    public String enrol(HttpSession httpSession, Model model, @RequestParam("programId") int programId) {

        User user = (User) httpSession.getAttribute("user");

        if (user != null) {
            int userId = user.getUser_id();
            String status = "PENDING"; // Set the enrollment status
            String firstName = user.getFirst_name();
            String lastName = user.getLast_name();


            try {
                // Insert the new program enrollment for the user
                userProgramsRepo.insertUserProgram(userId, programId, status);
            } catch (Exception e) {
                // Handle the exception
                e.printStackTrace(); // You can log the exception for debugging

                // You can also return an error page or a message to the user
                model.addAttribute("errorMessage", "Error enrolling in the program. Please try again later.");
                return "404"; // Create an error page in your templates
            }

//            SimpleMailMessage simpleMailMessage1 = new SimpleMailMessage();
//
//            UserPrograms userPrograms = new UserPrograms();
//            int programID = userPrograms.getProgram_id();
//
//            String subject1 = "NEW FITNESS CLIENT REGISTERED!";
//            String message1 = firstName + " " + lastName + " wants to enrol in program number " + programID;
//
//            simpleMailMessage1.setTo("gritdominate@gmail.com");
//            simpleMailMessage1.setSubject(subject1);
//            simpleMailMessage1.setText(message1);
//
//            mailSender.send(simpleMailMessage1);
        }

        // Handle the case where the user is not logged in
        // You might want to return an error page or redirect to a login page
        return "redirect:/userLogin"; // Change this to your login page URL
    }


}
