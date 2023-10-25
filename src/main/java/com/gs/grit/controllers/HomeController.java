package com.gs.grit.controllers;

import com.gs.grit.entities.Clients;
import com.gs.grit.entities.Users;
import com.gs.grit.repositories.ClientsRepository;
import com.gs.grit.repositories.EmailsRepo;
import com.gs.grit.repositories.RegistrationsRepository;
import com.gs.grit.repositories.UsersRepo;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
    private UsersRepo usersRepo;

    @Autowired
    private EmailsRepo emailsRepo;

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

    @GetMapping("/contact")
    public String contact() {
        return "contact";
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

    @GetMapping("/signup")
    public String signup(){
        return "signup";
    }

    @GetMapping("/clientForm")
    public String clientSignUp() {
        return "clientRegistrationForm";
    }

//    @GetMapping("/admin/profile")
//    public String adminPage() {
//        return "admin/profile";
//    }

    @GetMapping("/email")
    public String emailPage() {
        return "email";
    }

    @GetMapping("/login")
    public String login(){
        return "signup";
    }

    @PostMapping("admin/authenticate")
    public String auth(@RequestParam String username,
                       @RequestParam String password,
                       Model model,
                       HttpSession httpSession){
        Users user = usersRepo.findByUsername(username);
        Users users = (Users) httpSession.getAttribute("user");

        // if successful
        if (user != null && user.getPassword().equals(password)) {
            List<Clients> clients = clientsRepository.findAll();

            long clientCount = clientsRepository.count();

            model.addAttribute("clients", clients);
            model.addAttribute("clientCount", clientCount);
            httpSession.setAttribute("user", user);


            return "admin/dashboard";
        } else {
            // Handle authentication failure (e.g., show an error message)
            return "redirect:../404";
        }

    }

    @GetMapping("/admin/dashboard")
    public String adminDashboard(Model model, HttpSession httpSession){
        Users user = (Users) httpSession.getAttribute("user");
        if (user != null) {
            // User is authenticated, perform secured operations
            List<Clients> clients = clientsRepository.findAll();

            long clientCount = clientsRepository.count();

            model.addAttribute("clients", clients);
            model.addAttribute("clientCount", clientCount);
            return "admin/dashboard";
        }else {
            return "redirect:../signup";
        }
    }

    @GetMapping("/admin/blank")
    public String blank(HttpSession httpSession){
        Users user = (Users) httpSession.getAttribute("user");
        if (user != null) {
            return "admin/blank";
        }else {
            return "redirect:../404";
        }
    }

    @GetMapping("/admin/logout")
    public String logout(HttpSession httpSession) {
        // Invalidate the user's session
        httpSession.invalidate();
        return "redirect:../login"; // Redirect to the login page after logout
    }
}
