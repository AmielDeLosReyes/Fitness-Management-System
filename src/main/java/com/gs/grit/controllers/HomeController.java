package com.gs.grit.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

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
}
