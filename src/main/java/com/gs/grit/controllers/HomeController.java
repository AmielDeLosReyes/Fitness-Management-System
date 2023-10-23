package com.gs.grit.controllers;

import com.gs.grit.entities.Clients;
import com.gs.grit.repositories.ClientsRepository;
import com.gs.grit.repositories.EmailsRepo;
import com.gs.grit.repositories.RegistrationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private ClientsRepository clientsRepository;

    @Autowired
    private RegistrationsRepository registrationsRepository;

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


    @GetMapping("/admin/dashboard")
    public String adminDashboard(Model model){
        List<Clients> clients = clientsRepository.findAll();

        long clientCount = clientsRepository.count();

        model.addAttribute("clients", clients);
        model.addAttribute("clientCount", clientCount);
        return "admin/dashboard";
    }

    @GetMapping("/admin/clients")
    public String clients(){
        return "admin/clients";
    }

    @GetMapping("/admin/blank")
    public String blank(){
        return "admin/blank";
    }
}
