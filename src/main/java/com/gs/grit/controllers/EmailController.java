package com.gs.grit.controllers;

import com.gs.grit.entities.Clients;
import com.gs.grit.entities.Emails;
import com.gs.grit.entities.Registrations;
import com.gs.grit.repositories.ClientsRepository;
import com.gs.grit.repositories.EmailsRepo;
import com.gs.grit.repositories.RegistrationsRepository;
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

    @Autowired
    private ClientsRepository clientsRepository;

    @Autowired
    private RegistrationsRepository registrationsRepository;

    @Autowired
    private EmailsRepo emailsRepo;


    @PostMapping("/newsletterSignup")
    public String emailSub(@RequestParam String email,
                           Model model){

        if(emailsRepo.findByEmail(email) != null){
            // handle duplicate email
            return "redirect:/404email";
        }

        Emails emails = new Emails();
        emails.setEmail(email);

        emailsRepo.save(emails);

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

        String subject = "Welcome to Grit Dominate Newletter! Let's Begin Your Journey To Fitness Excellence!";

        String message = "This is a test email for newsletter of a client";
        simpleMailMessage.setTo(email);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(message);

        mailSender.send(simpleMailMessage);

        return "success";
    }

    @PostMapping("/registerNow")
    public String register(
            @RequestParam String firstName,
            @RequestParam String lastName,
            @RequestParam String email,
            @RequestParam String phoneNumber,
            @RequestParam String comment,
            Model model
    ) {
        // if registration already exists
        if(registrationsRepository.findByEmail(email) != null){
            // handle duplicate email
            return "redirect:/404email";
        }

        Registrations registrations = new Registrations();
        registrations.setFirst_name(firstName);
        registrations.setLast_name(lastName);
        registrations.setEmail(email);
        registrations.setPhone_number(phoneNumber);
        registrations.setComment(comment);

        registrationsRepository.save(registrations);

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

        String subject = "Welcome to Grit Dominate, " + firstName + "! Let's Begin Your Journey To Fitness Excellence!";

        String message = "This is a test email for registration of a client";
        simpleMailMessage.setTo(email);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(message);

        mailSender.send(simpleMailMessage);

        model.addAttribute("firstName", firstName);
        model.addAttribute("lastName", lastName);
        model.addAttribute("email", email);
        model.addAttribute("phoneNumber", phoneNumber);
        model.addAttribute("comment", comment);

        return "success";
    }

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

        // if client already exists
        if(clientsRepository.findByEmail(email) != null){
            // handle duplicate email
            return "redirect:/404email";
        }

        Clients clients = new Clients();
        clients.setFirst_name(firstName);
        clients.setLast_name(lastName);
        clients.setEmail(email);
        clients.setPhone_number(phoneNumber);
        clients.setHeight(height);
        clients.setWeight(weight);
        clients.setAge(age);
        clients.setSex(sex);
        clients.setExperience_level(explainExperience);
        clients.setFitness_routine(fitnessRoutine);
        clients.setCurrent_diet(diet);
        clients.setInjuries(injuries);
        clients.setStruggles(struggles);
        clients.setWhy_start_journey(whyStart);
        clients.setDriven(driven);
        clients.setOther(otherInfo);

        clientsRepository.save(clients);

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