package com.gs.grit.controllers;

import com.gs.grit.entities.Admin;
import com.gs.grit.entities.Clients;
import com.gs.grit.entities.Admin;
import com.gs.grit.entities.User;
import com.gs.grit.repositories.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private ClientsRepository clientsRepository;

    @Autowired
    private RegistrationsRepository registrationsRepository;

    @Autowired
    private AdminRepo adminRepo;

    @Autowired
    private EmailsRepo emailsRepo;

    @Autowired
    private UserRepo userRepo;

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/404")
    public String notFound() {
        return "404";
    }

    @GetMapping("/404email")
    public String emailDuplicate() {
        return "404email";
    }

    @GetMapping("/about-us")
    public String aboutUs() {
        return "about-us";
    }

    @GetMapping("/blog")
    public String blog() {
        return "blog";
    }

    @GetMapping("/blog-details")
    public String blogDetails() {
        return "blog-details";
    }

    @GetMapping("/blog-details2")
    public String blogDetails2() {
        return "blog-details2";
    }

    @GetMapping("/blog-details3")
    public String blogDetails3() {
        return "blog-details3";
    }

    @GetMapping("/blog-details4")
    public String blogDetails4() {
        return "blog-details4";
    }

    @GetMapping("/blog-details5")
    public String blogDetails5() {
        return "blog-details5";
    }

    @GetMapping("/bmi-calculator")
    public String bmiCalculator() {
        return "bmi-calculator";
    }

    @GetMapping("/class-details")
    public String classDetails() {
        return "class-details";
    }

    @GetMapping("/class-timetable")
    public String classTimetable() {
        return "class-timetable";
    }

    @GetMapping("/userRegistration")
    public String userRegistration() {
        return "userRegistration";
    }

    @GetMapping("/gallery")
    public String gallery() {
        return "gallery";
    }

    @GetMapping("/main")
    public String mainPage() {
        return "main";
    }

    @GetMapping("/services")
    public String services() {
        return "services";
    }


    @GetMapping("/team")
    public String team() {
        return "team";
    }

    @GetMapping("/registration")
    public String register(){
        return "contact";
    }

    @GetMapping("/adminLogin")
    public String signup(){
        return "adminLogin";
    }

    @GetMapping("/clientForm")
    public String clientSignUp() {
        return "clientRegistrationForm";
    }

//    @GetMapping("/admin/profile")
//    public String adminPage() {
//        return "admin/profile";
//    }

    @GetMapping("/userHome")
    public String userHome(HttpSession httpSession, Model model) {
        User us = (User) httpSession.getAttribute("u");

        if (us != null) {
            String welcomeMessage = "Welcome to your homepage, " + us.getFirst_name() + "!";
            String firstName = us.getFirst_name();

            String m = firstName + "'s Programs";


            model.addAttribute("welcomeMessage", welcomeMessage);
            model.addAttribute("m", m);
            model.addAttribute("firstName", firstName);
            return "userHome";
        }

        return "userLogin";
    }

    @GetMapping("/test")
    public String test() {
        return "test";
    }

    @GetMapping("/email")
    public String emailPage() {
        return "email";
    }

    @GetMapping("/userLogin")
    public String userLogin(HttpSession httpSession, Model model){
        User user = (User) httpSession.getAttribute("user");

        if(user != null) {
            String welcomeMessage = "Welcome to your homepage, " + user.getFirst_name() + "!";
            String firstName = user.getFirst_name();

            String m = firstName + "'s Programs";


            model.addAttribute("welcomeMessage", welcomeMessage);
            model.addAttribute("m", m);
            model.addAttribute("firstName", firstName);
            return "userHome";
        }

        return "userLogin";
    }

    @PostMapping("/admin/authenticate")
    public String auth(@RequestParam String username,
                       @RequestParam String password,
                       Model model,
                       HttpSession httpSession) {
        // Retrieve the admin user from the repository based on the provided username
        Admin admin = adminRepo.findByUsername(username);

        if (admin != null && admin.getPassword().equals(password)) {
            // Authentication is successful
            // You can load additional data and set it in the model if needed
            List<User> users = userRepo.findAll();
            long userCount = userRepo.count();

            model.addAttribute("users", users);
            model.addAttribute("userCount", userCount);

            // Store the authenticated admin in the session
            httpSession.setAttribute("admin", admin);

            return "admin/dashboard";
        } else {
            // Authentication failed, show an error message or redirect to the login page
            model.addAttribute("error", "Invalid username or password"); // Add an error message to display in the view
            return "redirect:/adminLogin"; // Redirect to the login page
        }
    }


    @PostMapping("authenticate")
    public String userAuth(@RequestParam String email,
                           @RequestParam String password,
                           Model model,
                           HttpSession httpSession) {
        // You should retrieve the user from the repository based on the provided email
        User user = userRepo.findByEmail(email);

        if (user != null && user.getPassword().equals(password)) {
            // Authentication is successful, store the user in the session
            String welcomeMessage = "Welcome to your homepage, " + user.getFirst_name() + "!";
            String firstName = user.getFirst_name();

            String m = firstName + "'s Programs";

            model.addAttribute("welcomeMessage", welcomeMessage);
            model.addAttribute("m", m);
            model.addAttribute("firstName", firstName);
            httpSession.setAttribute("user", user);

            return "userHome";
        } else {
            // Authentication failed, return to the login page
            return "userLogin";
        }
    }


    @GetMapping("/admin/dashboard")
    public String adminDashboard(Model model, HttpSession httpSession){
        Admin admin = (Admin) httpSession.getAttribute("admin");
        if (admin != null) {
            // You can load additional data and set it in the model if needed
            List<User> users = userRepo.findAll();
            long userCount = userRepo.count();

            model.addAttribute("users", users);
            model.addAttribute("userCount", userCount);

            // Store the authenticated admin in the session
            httpSession.setAttribute("admin", admin);

            return "admin/dashboard";
        } else {
            return "redirect:../signup";
        }
    }

    @GetMapping("/admin/blank")
    public String blank(HttpSession httpSession){
        Admin admin = (Admin) httpSession.getAttribute("admin");
        if (admin != null) {
            return "admin/blank";
        }else {
            return "redirect:../404";
        }
    }

    @GetMapping("/admin/logout")
    public String logout(HttpSession httpSession) {
        // Invalidate the user's session
        httpSession.invalidate();
        return "redirect:../"; // Redirect to the login page after logout
    }

    @GetMapping("userLogout")
    public String userLogout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "userLogin"; // Redirect to the login page
    }

}
