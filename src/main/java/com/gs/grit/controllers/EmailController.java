package com.gs.grit.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class EmailController {

    @Autowired
    private JavaMailSender mailSender;

    @PostMapping("/sendEmail")
    public String sendEmail(
            @RequestParam String firstName,
            @RequestParam String lastName,
            @RequestParam String height,
            @RequestParam String weight,
            @RequestParam String age,
            @RequestParam String sex,
            @RequestParam String explainExperience,
            @RequestParam String fitnessRoutine,
            @RequestParam String diet,
            @RequestParam String injuries,
            @RequestParam String struggles,
            @RequestParam String whyStart,
            @RequestParam String driven,
            @RequestParam String email,
            @RequestParam String phoneNumber,
            @RequestParam String otherInfo,
            Model model) {

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

        String subject = "Welcome to Grit Dominate, " + firstName + "! Let's Begin Your Journey To Fitness Excellence!";

        String message = "This is a test email for registration of a client";
        simpleMailMessage.setTo(email);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(message);

        mailSender.send(simpleMailMessage);

        model.addAttribute("message", "Email sent successfully!");
        model.addAttribute("firstName", firstName);
        model.addAttribute("lastName", lastName);
        model.addAttribute("height", height);
        model.addAttribute("weight", weight);
        model.addAttribute("age", age);
        model.addAttribute("sex", sex);
        model.addAttribute("explainExperience", explainExperience);
        model.addAttribute("fitnessRoutine", fitnessRoutine);
        model.addAttribute("diet", diet);
        model.addAttribute("injuries", injuries);
        model.addAttribute("struggles", struggles);
        model.addAttribute("whyStart", whyStart);
        model.addAttribute("driven", driven);
        model.addAttribute("email", email);
        model.addAttribute("phoneNumber", phoneNumber);
        model.addAttribute("otherInfo", otherInfo);
        model.addAttribute("message", "Email sent successfully!");
        return "success";
    }
}