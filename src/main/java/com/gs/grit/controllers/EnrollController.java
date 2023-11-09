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

    private Integer getNextUserProgramId() {
        // Query the database to find the maximum user_program_id
        Integer maxUserProgramId = userProgramsRepo.findMaxUserProgramId();

        // Increment the maximum value to get the next unique user_program_id
        return (maxUserProgramId != null) ? maxUserProgramId + 1 : 1;
    }


    @Transactional
    @PostMapping("/enrol")
    public String enrol(HttpSession httpSession, Model model, @RequestParam("programId") int programId) {

        User user = (User) httpSession.getAttribute("user");

        if (user != null) {
            int userId = user.getUser_id();
            String status = "PENDING"; // Set the enrollment status
            String firstName = user.getFirst_name();
            String lastName = user.getLast_name();
            String email = user.getEmail();


            try {

                // Manually find the next available user_program_id
                Integer userProgramId = getNextUserProgramId();

                // Insert the new program enrollment for the user
                userProgramsRepo.insertUserProgram(userProgramId, userId, programId, status);

                // Fetch the program name based on the programId
                String programName = userProgramsRepo.findProgramNameByProgramId(programId);

                // After successfully enrolling the user
                model.addAttribute("enrollmentSuccess", true);

                // Create a SimpleMailMessage for the enrollment notification
                SimpleMailMessage enrollmentMessage = new SimpleMailMessage();
                enrollmentMessage.setTo("gritdominate@gmail.com"); // Replace with the recipient's email
                enrollmentMessage.setSubject("New Enrollment Request!");
                enrollmentMessage.setText(firstName + " " + lastName + " wants to enroll in " + programName + "\n\nProvide him/her a personalized program and change the status to ACTIVE once done.\n\n Thank you!\nGrit Dominate Team.");

                // Store the enrollmentSuccess status in the session or request attributes
                httpSession.setAttribute("enrollmentSuccess", true);

                // Send the email
                mailSender.send(enrollmentMessage);

                // Create a SimpleMailMessage for the user
                SimpleMailMessage enrollmentMessage1 = new SimpleMailMessage();
                enrollmentMessage1.setTo(email);
                enrollmentMessage1.setSubject("Thank You For Enrolling To The " + programName + "!");
                enrollmentMessage1.setText("Hi " + firstName + ",\n\nPlease reply to this email by filling out the questions below:\n\n"
                        + "1. What is your current fitness routine:\n"
                        + "2. Current diet:\n"
                        + "3. What are your current struggles:\n"
                        + "4. Why do you want to start this journey:\n"
                        + "5. Do you consider yourself a driven person:\n"
                        + "6. Do you have gym access or you’re leaning towards home workouts:\n"
                        + "7. Other information that you want me to know:\n\n"
                        + "You can answer these questions briefly. This will help the team personalize the program for you!\n\n"
                        + "Looking forward to hearing back from you!\n\n"
                        + "Grit Dominate Team");

                // Send the email to the user
                mailSender.send(enrollmentMessage1);
            } catch (Exception e) {
                // Handle the exception
                e.printStackTrace(); // You can log the exception for debugging

                // You can also return an error page or a message to the user
                model.addAttribute("errorMessage", "Error enrolling in the program. Please try again later.");
                return "404"; // Create an error page in your templates
            }
        }


        // Handle the case where the user is not logged in
        // You might want to return an error page or redirect to a login page
        return "redirect:/userLogin"; // Change this to your login page URL
    }


}

// AJAX handling way
//package com.gs.grit.controllers;
//
//import com.gs.grit.entities.User;
//import com.gs.grit.entities.UserPrograms;
//import com.gs.grit.repositories.UserProgramsRepo;
//import com.gs.grit.repositories.UserRepo;
//import jakarta.servlet.http.HttpSession;
//import jakarta.transaction.Transactional;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@Controller
//public class EnrollController {
//
//    @Autowired
//    private UserProgramsRepo userProgramsRepo;
//
//    @Autowired
//    private JavaMailSender mailSender;
//
//    @Transactional
//    @PostMapping("/enrol")
//    @ResponseBody
//    public Map<String, Object> enroll(HttpSession httpSession, Model model, @RequestParam("programId") int programId) {
//        Map<String, Object> response = new HashMap<>();
//
//        User user = (User) httpSession.getAttribute("user");
//
//        if (user != null) {
//            int userId = user.getUser_id();
//            String status = "PENDING"; // Set the enrollment status
//            String firstName = user.getFirst_name();
//            String lastName = user.getLast_name();
//
//            try {
//                // Insert the new program enrollment for the user
//                userProgramsRepo.insertUserProgram(userId, programId, status);
//
//                // Fetch the program name based on the programId
//                String programName = userProgramsRepo.findProgramNameByProgramId(programId);
//
//                // Set success response
//                response.put("success", true);
//                response.put("message", "Enrollment request sent successfully");
//
//                // Create a SimpleMailMessage for the enrollment notification
//                SimpleMailMessage enrollmentMessage = new SimpleMailMessage();
//                enrollmentMessage.setTo("gritdominate@gmail.com"); // Replace with the recipient's email
//                enrollmentMessage.setSubject("New Enrollment Request!");
//                enrollmentMessage.setText(firstName + " " + lastName + " wants to enroll in " + programName + "\n\nProvide him/her a personalized program and change the status to ACTIVE once done.\n\n Thank you!\nGrit Dominate Team.");
//
//                // Send the email
//                mailSender.send(enrollmentMessage);
//            } catch (Exception e) {
//                // Handle the exception
//                e.printStackTrace(); // You can log the exception for debugging
//
//                // Set error response
//                response.put("success", false);
//                response.put("message", "Error enrolling in the program. Please try again later.");
//            }
//        } else {
//            // Handle the case where the user is not logged in
//            // You might want to return an error response or redirect to a login page
//            response.put("success", false);
//            response.put("message", "User not logged in. Please log in to enroll.");
//        }
//
//        return response;
//    }
//}
