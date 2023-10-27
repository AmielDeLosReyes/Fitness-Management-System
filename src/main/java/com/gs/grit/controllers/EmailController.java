package com.gs.grit.controllers;

import com.gs.grit.entities.*;
import com.gs.grit.repositories.ClientsRepository;
import com.gs.grit.repositories.EmailsRepo;
import com.gs.grit.repositories.UserRepo;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

@Controller
public class EmailController {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private ClientsRepository clientsRepository;

//    @Autowired
//    private userRepo userRepo;
    
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private EmailsRepo emailsRepo;

    private ExecutorService threadPool;

    public void EmailSender(int numThreads) {
        threadPool = Executors.newFixedThreadPool(numThreads);
    }

    public void shutdown() {
        threadPool.shutdown();
    }

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

        String subject = "Welcome to Grit Dominate Newsletter! Let's Begin Your Journey To Fitness Excellence!";

        String message = "This is a test email for newsletter of a client";
        simpleMailMessage.setTo(email);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(message);

//        mailSender.send(simpleMailMessage);

        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
            String htmlMsg = "<!DOCTYPE html><html><head> <style>/* Add your email styles here */body{font-family: Arial, sans-serif;}.container{max-width: 600px;margin: 0 auto;padding: 20px;}.header{background-color: #333;color: #fff;padding: 20px 0;text-align: center;}.header h1{font-size: 24px;}.content{padding: 20px;}.footer{background-color: #333;color: #fff;text-align: center;padding: 10px 0;}.footer p{font-size: 14px;}.btn{display: inline-block;padding: 10px 20px;background-color: #ff6600;color: #fff;text-decoration: none;border-radius: 4px;}.logo{text-align: center;}</style></head><body><div class=\"container\"> <div class=\"header\"> <h1>Thank You for Signing Up!</h1> </div><div class=\"content\"> <div class=\"logo\"> <img src=\"img/picemail.png\" alt=\"Grit Dominate Logo\" width=\"200\" height=\"auto\"> </div><p>Hi there,</p><p>Thank you for subscribing to the Grit Dominate newsletter. We're excited to have you as part of our fitness community.</p><p>Stay tuned for the latest updates, fitness tips, and exclusive offers that will help you dominate your fitness goals.</p><p>Should you have any questions or need assistance, feel free to reach out to us at <a href=\"mailto:gritdominate@gmail.com\">gritdominate@gmail.com</a> or <a href=\"sms:+639566501830\">+63 956 650 1830</a>.</p><p>Keep pushing your limits and remember, greatness is achieved through grit and determination!</p><p>Best regards,</p><p>The Grit Dominate Team</p></div><div class=\"footer\"> <p>&copy; 2023 Grit Dominate. All rights reserved.</p></div></div></body></html>";
//mimeMessage.setContent(htmlMsg, "text/html"); /** Use this or below line **/
            helper.setText(htmlMsg, true); // Use this or above line.
            helper.setTo(email);
            helper.setSubject(subject);
            helper.setFrom("gritdominate@gmail.com");
            mailSender.send(mimeMessage);
        } catch (MessagingException ex) {
            ex.getStackTrace();

        }
        return "success";
    }

    @PostMapping("/registerNow")
    public String register(
            @RequestParam String firstName,
            @RequestParam String lastName,
            @RequestParam String email,
            @RequestParam String phoneNumber,
            @RequestParam String password,
            Model model,
            HttpSession httpSession
    ) {
        // if registration already exists
        if(userRepo.findByEmail(email) != null){
            // handle duplicate email
            return "redirect:/404email";
        }

        User users = new User();
        users.setFirst_name(firstName);
        users.setLast_name(lastName);
        users.setEmail(email);
        users.setPhone_number(phoneNumber);
        users.setPassword(password);


        Date d1 = new Date();

        SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH);
        String formattedDate = dateFormat.format(d1);

        users.setRegistration_date(formattedDate);

        userRepo.save(users);

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

        String subject = "Welcome to Grit Dominate, " + firstName + "! Let's Begin Your Journey To Fitness Excellence!";

        // send first email
//        mailSender.send(simpleMailMessage);

        // second email
        SimpleMailMessage simpleMailMessage1 = new SimpleMailMessage();

        String subject1 = "NEW CLIENT REGISTERED!";
        String message1 = "A new client has registered to Grit Dominate. Here are the details:\n\nFirst Name: " + firstName + "\nLast Name: " + lastName + "\nEmail: " + email + "\nPhone Number: " + phoneNumber + "\nRegistration Date: " + formattedDate + "\n\n***THIS IS AN AUTOMATED EMAIL. DO NOT REPLY***";

        simpleMailMessage1.setTo("gritdominate@gmail.com");
        simpleMailMessage1.setSubject(subject1);
        simpleMailMessage1.setText(message1);

        // send second email
        mailSender.send(simpleMailMessage1);

        model.addAttribute("firstName", firstName);
        model.addAttribute("lastName", lastName);
        model.addAttribute("email", email);
        model.addAttribute("phoneNumber", phoneNumber);
        model.addAttribute("registrationDate", formattedDate);

        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
            String htmlMsg = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\"><html lang=\"zxx\" xmlns:th=\"http://www.thymeleaf.org\"><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"><meta name=\"viewport\" content=\"width=device-width;initial-scale=1;maximum-scale=1;\"><meta name=\"viewport\" content=\"width=600,initial-scale=2.3,user-scalable=no\"><link href=\"https://fonts.googleapis.com/css?family=Titillium+Web\" rel=\"stylesheet\"><link href=\"https://fonts.googleapis.com/css?family=Source+Sans+Pro\" rel=\"stylesheet\"><link href=\"//maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css\" rel=\"stylesheet\"><title>Welcome To Grit Dominate!</title><style type=\"text/css\">body,html{-webkit-font-smoothing:antialiased;width:100%;padding:0;margin:0}@media only screen and (max-width:640px){.header-main{font-size:22px!important}.main-section-header{font-size:28px!important}.show{display:block!important}.hide{display:none!important}.align-center{text-align:center!important}.main-image img{width:440px!important;height:auto!important}.container590{width:440px!important}.half-container{width:220px!important}.main-button{width:220px!important}.section-img img{width:320px!important;height:auto!important}}@media only screen and (max-width:479px){.header-main{font-size:20px!important}.main-section-header{font-size:26px!important}.container590{width:280px!important}.container590{width:280px!important}.half-container{width:130px!important}.section-img img{width:280px!important;height:auto!important}}</style></head><body leftmargin=\"0\" topmargin=\"0\" marginwidth=\"0\" marginheight=\"0\"><table style=\"display:none!important\"><tr><td><div style=\"overflow:hidden;display:none;font-size:1px;color:#fff;line-height:1px;font-family:Arial;maxheight:0;max-width:0;opacity:0\">This is where your fitness journey starts! We are thrilled to have you on-board!</div></td></tr></table><table border=\"0\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" bgcolor=\"5c9085\"><tr><td align=\"center\" style=\"background-image:url(img/emailpic.png);background-size:cover;background-position:top center;background-repeat:no-repeat\" background=\"http://i.imgur.com/ymD1KR0.jpg\"><table border=\"0\" align=\"center\" width=\"590\" cellpadding=\"0\" cellspacing=\"0\" class=\"container590\"><tr><td height=\"25\" style=\"font-size:25px;line-height:25px\">&nbsp;</td></tr><tr><td><table border=\"0\" align=\"left\" cellpadding=\"0\" cellspacing=\"0\" style=\"border-collapse:collapse;mso-table-lspace:0;mso-table-rspace:0\" class=\"container590\"><tr><td align=\"center\"><table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\"><tr><td align=\"center\"><a href=\"#\" style=\"display:block;border-style:none!important;border:0!important\"><img width=\"200\" border=\"0\" style=\"display:block;width:200px\" src=\"img/logoemail.png\" alt=\"Logo\"></a></td></tr></table></td></tr></table><table border=\"0\" align=\"left\" width=\"5\" cellpadding=\"0\" cellspacing=\"0\" style=\"border-collapse:collapse;mso-table-lspace:0;mso-table-rspace:0\" class=\"container590\"><tr><td width=\"5\" height=\"10\" style=\"font-size:10px;line-height:10px\">&nbsp;</td></tr></table><table border=\"0\" align=\"right\" cellpadding=\"0\" width=\"360\" cellspacing=\"0\" style=\"border-collapse:collapse;mso-table-lspace:0;mso-table-rspace:0\" class=\"hide\"><tr><td align=\"center\" class=\"outlook-font\" style=\"color:#ddefff;font-size:14px;font-family:'Titillium Web',Helvetica Neue,Calibri,sans-serif;line-height:24px\"><div style=\"line-height:24px\"></div></td></tr></table></td></tr><tr class=\"hide\"><td height=\"35\" style=\"font-size:35px;line-height:35px\">&nbsp;</td></tr><tr><td height=\"50\" style=\"font-size:50px;line-height:50px\">&nbsp;</td></tr><tr><td align=\"center\"><table border=\"0\" width=\"500\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" style=\"border-collapse:collapse;mso-table-lspace:0;mso-table-rspace:0\" class=\"container590\"><tr><td align=\"center\" style=\"color:#ff5f15;font-size:45px;font-family:Oswald,sans-serif;line-height:35px;font-weight:1000\" class=\"main-section-header outlook-font\"><div style=\"line-height:35px\">Welcome to Grit Dominate, " + firstName +"!</div></td></tr><tr><td height=\"20\" style=\"font-size:20px;line-height:20px\">&nbsp;</td></tr><tr><td align=\"center\" class=\"outlook-font\" style=\"color:#eaf5ff;font-size:15px;font-family:'Titillium Web',Helvetica Neue,Calibri,sans-serif;line-height:24px\"><div style=\"line-height:24px\">This is where your fitness journey begins! We're thrilled to have you on board.</div></td></tr></table></td></tr><tr><td height=\"60\" style=\"font-size:60px;line-height:60px\">&nbsp;</td></tr></table></td></tr></table><table border=\"0\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" bgcolor=\"ffffff\"><tbody><tr><td height=\"50\" style=\"font-size:50px;line-height:50px\">&nbsp;</td></tr><tr><td align=\"center\"><table border=\"0\" align=\"center\" width=\"590\" cellpadding=\"0\" cellspacing=\"0\" class=\"container590\"><tbody><tr><td><table border=\"0\" width=\"590\" align=\"left\" cellpadding=\"0\" cellspacing=\"0\" style=\"border-collapse:collapse;mso-table-lspace:0;mso-table-rspace:0\" class=\"container590\"><tbody><tr><td align=\"center\" class=\"section-img\"><a href=\"#\" style=\"border-style:none!important;border:0!important\"><img src=\"img/picemail.png\" style=\"display:block;width:590px\" width=\"590\" border=\"0\" alt=\"\"></a></td></tr><tr><td height=\"20\" style=\"font-size:20px;line-height:20px\">&nbsp;</td></tr><tr><td align=\"left\" style=\"color:#212a2e;font-size:18px;font-family:'Source Sans Pro',Helvetica,Calibri,sans-serif;font-weight:500;line-height:24px\" class=\"align-center outlook-font\">Here's what you can expect:</td></tr><tr><td height=\"15\" style=\"font-size:15px;line-height:15px\">&nbsp;</td></tr><tr><td align=\"left\" style=\"color:#838383;font-family:'Source Sans Pro',Helvetica,Calibri,sans-serif;line-height:20px;font-size:14px\" class=\"align-center outlook-font\"><div style=\"line-height:20px\"><p>1. Personalized Workouts - Tailored exercise routines designed to help you reach your fitness goals effectively.</p><p>2. Nutrition Guidance - Expert advice on nutrition to fuel your body and enhance your performance.</p><p>3. Supportive Community - Join a community of like-minded individuals who motivate and inspire each other on their fitness journeys.</p><p>4. Exclusive Content - Access premium content and resources to help you dominate your fitness routine and achieve your best results.</p></div></td></tr><tr><td height=\"15\" style=\"font-size:15px;line-height:15px\">&nbsp;</td></tr><tr><td align=\"left\" style=\"color:#212a2e;font-size:18px;font-family:'Source Sans Pro',Helvetica,Calibri,sans-serif;font-weight:500;line-height:24px\" class=\"align-center outlook-font\">To start training with us:</td></tr><tr><td height=\"15\" style=\"font-size:15px;line-height:15px\">&nbsp;</td></tr><tr><td align=\"left\" style=\"color:#838383;font-family:'Source Sans Pro',Helvetica,Calibri,sans-serif;line-height:20px;font-size:14px\" class=\"align-center outlook-font\"><div style=\"line-height:20px\"><p><b>1. Login to your account and enroll to your desired program: <a href=\"http://localhost:8080/userLogin\">Grit Dominate Client Form</b></a></p><p>2. Follow Our Facebook Page: <a href=\"https://m.facebook.com/grit.dominate\">Grit Dominate Facebook Page</a></p><p>3. Follow Our Instagram Page: <a href=\"https://www.instagram.com/grit.dominate/\">Grit Dominate Instagram Page</a></p><p>4. Follow Our TikTok Page: <a href=\"https://www.tiktok.com/@grit.dominate?lang=en\">Grit Dominate TikTok Page</a></p><p>5. Subscribe To Our Youtube Channel: <a href=\"https://www.youtube.com/channel/UCUYuMr4KH_-w4cTMOZZp8CA\">Grit Dominate Youtube Channel</a></p></div></td></tr><tr><td align=\"left\"><table border=\"0\" align=\"left\" cellpadding=\"0\" cellspacing=\"0\" class=\"container590\"><tbody><tr><td align=\"center\"><table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\"><tbody></tbody></table></td></tr></tbody></table></td></tr></tbody></table></td></tr></tbody></table></td></tr></tbody></table><table border=\"0\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" bgcolor=\"ffffff\"><tbody><tr><td height=\"50\" style=\"font-size:50px;line-height:50px\">&nbsp;</td></tr><tr><td align=\"center\"><table border=\"0\" align=\"center\" width=\"590\" cellpadding=\"0\" cellspacing=\"0\" class=\"container590\"><tbody><tr><td><table border=\"0\" width=\"264\" align=\"left\" cellpadding=\"0\" cellspacing=\"0\" style=\"border-collapse:collapse;mso-table-lspace:0;mso-table-rspace:0\" class=\"container590\"><tbody><tr><td align=\"center\" class=\"section-img\"><a href=\"#\" style=\"border-style:none!important;border:0!important\"><img src=\"img/pic2email.png\" style=\"display:block;width:264px\" width=\"264\" border=\"0\" alt=\"\"></a></td></tr><tr><td height=\"20\" style=\"font-size:20px;line-height:20px\">&nbsp;</td></tr><tr><td align=\"left\" style=\"color:#212a2e;font-size:18px;font-family:'Source Sans Pro',Helvetica,Calibri,sans-serif;font-weight:500;line-height:24px\" class=\"align-center outlook-font\">Results-Driven Training</td></tr><tr><td height=\"15\" style=\"font-size:15px;line-height:15px\">&nbsp;</td></tr><tr><td align=\"left\" style=\"color:#838383;font-family:'Source Sans Pro',Helvetica,Calibri,sans-serif;line-height:20px;font-size:14px\" class=\"align-center outlook-font\"><div style=\"line-height:20px\">Experience the power of our results-driven workouts that have helped countless individuals achieve their fitness goals. Our evidence-based approach ensures that every minute you invest in your fitness with Grit Dominate brings you closer to your desired results.</div></td></tr><tr><td height=\"15\" style=\"font-size:15px;line-height:15px\">&nbsp;</td></tr><tr><td align=\"left\"><table border=\"0\" align=\"left\" cellpadding=\"0\" cellspacing=\"0\" class=\"container590\"><tbody><tr><td align=\"center\"><table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\"><tbody><tr><td align=\"center\" class=\"outlook-font\" style=\"color:#5c9085;font-family:'Source Sans Pro',Helvetica,Calibri,sans-serif;line-height:22px\"><div style=\"line-height:22px\"><a href=\"http://localhost:8080/services\" style=\"color:#ff5f15;text-decoration:none;font-weight:1000\">Read more</a></div></td></tr></tbody></table></td></tr></tbody></table></td></tr></tbody></table><table border=\"0\" width=\"5\" align=\"left\" cellpadding=\"0\" cellspacing=\"0\" style=\"border-collapse:collapse;mso-table-lspace:0;mso-table-rspace:0\" class=\"container590\"><tbody><tr><td width=\"5\" height=\"50\" style=\"font-size:50px;line-height:50px\"></td></tr></tbody></table><table border=\"0\" width=\"262\" align=\"right\" cellpadding=\"0\" cellspacing=\"0\" style=\"border-collapse:collapse;mso-table-lspace:0;mso-table-rspace:0\" class=\"container590\"><tbody><tr><td align=\"center\" class=\"section-img\"><a href=\"#\" style=\"border-style:none!important;border:0!important\"><img src=\"img/pic3email.png\" style=\"display:block;width:264px\" width=\"264\" border=\"0\" alt=\"\"></a></td></tr><tr><td height=\"20\" style=\"font-size:20px;line-height:20px\">&nbsp;</td></tr><tr><td align=\"left\" style=\"color:#212a2e;font-size:18px;font-family:'Source Sans Pro',Helvetica,Calibri,sans-serif;font-weight:500;line-height:24px\" class=\"align-center outlook-font\"><div style=\"line-height:24px\">Personal Coaching</div></td></tr><tr><td height=\"15\" style=\"font-size:15px;line-height:15px\">&nbsp;</td></tr><tr><td align=\"left\" style=\"color:#838383;font-family:'Source Sans Pro',Helvetica,Calibri,sans-serif;line-height:20px;font-size:14px\" class=\"align-center outlook-font\"><div style=\"line-height:20px\">At Grit Dominate, you're not just another client; you're part of a community where personal coaching is at the heart of your fitness journey. Our knowledgeable trainers will provide you with one-on-one support, guidance, and motivation, ensuring your success and helping you reach your full potential.</div></td></tr><tr><td height=\"15\" style=\"font-size:15px;line-height:15px\">&nbsp;</td></tr><tr><td align=\"left\"><table border=\"0\" align=\"left\" cellpadding=\"0\" cellspacing=\"0\" class=\"container590\"><tbody><tr><td align=\"center\"><table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\"><tbody><tr><td align=\"center\" class=\"outlook-font\" style=\"color:#5c9085;font-family:'Source Sans Pro',Helvetica,Calibri,sans-serif;line-height:22px\"><div style=\"line-height:22px\"><a href=\"http://localhost:8080/services\" style=\"color:#ff5f15;text-decoration:none;font-weight:1000\">Read more</a></div></td></tr></tbody></table></td></tr></tbody></table></td></tr></tbody></table></td></tr></tbody></table></td></tr></tbody></table><table border=\"0\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" bgcolor=\"ffffff\"><tbody><tr><td align=\"center\"><table border=\"0\" align=\"center\" width=\"590\" cellpadding=\"0\" cellspacing=\"0\" class=\"container590\"><tbody><tr><td><table border=\"0\" width=\"590\" align=\"left\" cellpadding=\"0\" cellspacing=\"0\" style=\"border-collapse:collapse;mso-table-lspace:0;mso-table-rspace:0\" class=\"container590\"><tbody><tr><td align=\"left\" style=\"color:#838383;font-family:'Source Sans Pro',Helvetica,Calibri,sans-serif;line-height:20px;font-size:14px\" class=\"align-center outlook-font\"><div style=\"line-height:20px\"><p>Unleash your inner Beast. Get Big, Stay Consistent, and Dominate. \uD83E\uDD8D</p><b><p>See you at the top,</p><p>Amiel De Los Reyes</p><p>Grit Dominate Founder</p></b></div></td></tr><tr><td align=\"left\"><table border=\"0\" align=\"left\" cellpadding=\"0\" cellspacing=\"0\" class=\"container590\"><tbody><tr><td align=\"center\"><table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\"><tbody></tbody></table></td></tr></tbody></table></td></tr></tbody></table></td></tr></tbody></table></td></tr></tbody></table><table border=\"0\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" bgcolor=\"f8f9fa\"><tr><td height=\"25\" style=\"font-size:25px;line-height:25px\">&nbsp;</td></tr><tr><td align=\"center\"><table border=\"0\" align=\"center\" width=\"590\" cellpadding=\"0\" cellspacing=\"0\" class=\"container590\"><tr><td align=\"center\" style=\"color:#9c9c9c;font-size:13px;font-family:'Titillium Web',Helvetica Neue,Calibri,sans-serif;line-height:24px\" class=\"outlook-font align-center\"><i class=\"fa fa-location-arrow\"></i>&nbsp;Manila, Philippines<br><i class=\"fa fa-phone\"></i>&nbsp;<a href=\"sms:+639566501830\">+63 956 650 1830</a><br><i class=\"fa fa-envelope-o\"></i>&nbsp;<a href=\"mailto:gritdominate@gmail.com\">gritdominate@gmail.com</a></td></tr><tr><td height=\"30\" style=\"font-size:30px;line-height:30px\">&nbsp;</td></tr><tr><td align=\"center\" style=\"color:#9c9c9c;font-size:13px;font-family:'Titillium Web',Helvetica Neue,Calibri,sans-serif;line-height:24px\" class=\"outlook-font align-center\">You're receiving this email because you've signed up to receive updates from Grit Dominate.<br>Copyright ©2023 | Grit Dominate Limited | All rights reserved</td></tr></table></td></tr><tr><td height=\"25\" style=\"font-size:25px;line-height:25px\">&nbsp;</td></tr></table></body></html>";
//mimeMessage.setContent(htmlMsg, "text/html"); /** Use this or below line **/
            helper.setText(htmlMsg, true); // Use this or above line.
            helper.setTo(email);
            helper.setSubject(subject);
            helper.setFrom("gritdominate@gmail.com");
            mailSender.send(mimeMessage);
        } catch (MessagingException ex) {
            ex.getStackTrace();

        }

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

        Date d1 = new Date();

        SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH);
        String formattedDate = dateFormat.format(d1);

        clients.setRegistration_date(formattedDate);

        clients.setRegistration_date(formattedDate);

        clientsRepository.save(clients);

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

        String subject = "Welcome to Grit Dominate, " + firstName + "! Let's Begin Your Journey To Fitness Excellence!";

        String message = "This is a test email for registration of a client";
        simpleMailMessage.setTo(email);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(message);

//        mailSender.send(simpleMailMessage);

        SimpleMailMessage simpleMailMessage1 = new SimpleMailMessage();

        String subject1 = "NEW FITNESS CLIENT REGISTERED!";
        String message1 = "A new client has registered to Grit Dominate. Here are the details:\n\nFirst Name: " + firstName + "\nLast Name: " + lastName + "\nEmail: " + email + "\nPhone Number: " + phoneNumber + "\nHeight: " + height + "\nWeight: " + weight + "\nAge: " + age + "\nSex: " + sex + "\nExperience: " + explainExperience + "\nFitness Routine: " + fitnessRoutine + "\nDiet: " + diet + "\nInjuries: " + injuries + "\nStruggles: " + struggles + "\nWhy Start: " + whyStart + "\nDriven: " + driven + "\nOther Info: " + otherInfo + "***THIS IS AN AUTOMATED EMAIL. DO NOT REPLY***";

        simpleMailMessage1.setTo("gritdominate@gmail.com");
        simpleMailMessage1.setSubject(subject1);
        simpleMailMessage1.setText(message1);

        mailSender.send(simpleMailMessage1);

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

        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
            String htmlMsg = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\"><html lang=\"zxx\" xmlns:th=\"http://www.thymeleaf.org\"><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"/><meta name=\"viewport\" content=\"width=device-width; initial-scale=1.0; maximum-scale=1.0;\"/><meta name=\"viewport\" content=\"width=600,initial-scale=2.3,user-scalable=no\"><link href=\"https://fonts.googleapis.com/css?family=Titillium+Web\" rel=\"stylesheet\"><link href=\"https://fonts.googleapis.com/css?family=Source+Sans+Pro\" rel=\"stylesheet\"><link href=\"//maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css\" rel=\"stylesheet\"><title>Welcome To Grit Dominate!</title><style type=\"text/css\">html,body{-webkit-font-smoothing: antialiased; width: 100%; padding: 0; margin: 0;}@media only screen and (max-width: 640px){/*------ top header ------ */ .header-main{font-size: 22px !important;}.main-section-header{font-size: 28px !important;}.show{display: block !important;}.hide{display: none !important;}.align-center{text-align: center !important;}.main-image img{width: 440px !important;height: auto !important;}.container590{width: 440px !important;}.half-container{width: 220px !important;}.main-button{width: 220px !important;}.section-img img{width: 320px !important;height: auto !important;}}@media only screen and (max-width: 479px){.header-main{font-size: 20px !important;}.main-section-header{font-size: 26px !important;}.container590{width: 280px !important;}.container590{width: 280px !important;}.half-container{width: 130px !important;}.section-img img{width: 280px !important;height: auto !important;}}</style></head><body leftmargin=\"0\" topmargin=\"0\" marginwidth=\"0\" marginheight=\"0\"><table style=\"display:none!important;\"><tr><td><div style=\"overflow:hidden;display:none;font-size:1px;color:#ffffff;line-height:1px;font-family:Arial;maxheight:0px;max-width:0px;opacity:0;\">This is where your fitness journey starts! We are thrilled to have you on-board!</div></td></tr></table><table border=\"0\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" bgcolor=\"5c9085\"><tr><td align=\"center\" style=\"background-image: url(img/emailpic.png); background-size: cover; background-position: top center; background-repeat: no-repeat;\" background=\"http://i.imgur.com/ymD1KR0.jpg\"><table border=\"0\" align=\"center\" width=\"590\" cellpadding=\"0\" cellspacing=\"0\" class=\"container590\"><tr><td height=\"25\" style=\"font-size: 25px; line-height: 25px;\">&nbsp;</td></tr><tr><td><table border=\"0\" align=\"left\" cellpadding=\"0\" cellspacing=\"0\" style=\"border-collapse:collapse; mso-table-lspace:0pt; mso-table-rspace:0pt;\" class=\"container590\"><tr><td align=\"center\"><table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\"><tr><td align=\"center\"><a href=\"#\" style=\"display: block; border-style: none !important; border: 0 !important;\"><img width=\"200\" border=\"0\" style=\"display: block; width: 200px;\" src=\"img/logoemail.png\" alt=\"Logo\"/></a></td></tr></table></td></tr></table><table border=\"0\" align=\"left\" width=\"5\" cellpadding=\"0\" cellspacing=\"0\" style=\"border-collapse:collapse; mso-table-lspace:0pt; mso-table-rspace:0pt;\" class=\"container590\"><tr><td width=\"5\" height=\"10\" style=\"font-size: 10px; line-height: 10px;\">&nbsp;</td></tr></table><table border=\"0\" align=\"right\" cellpadding=\"0\" width=\"360\" cellspacing=\"0\" style=\"border-collapse:collapse; mso-table-lspace:0pt; mso-table-rspace:0pt;\" class=\"hide\"><tr><td align=\"center\" class=\"outlook-font\" style=\"color: #ddefff; font-size: 14px; font-family: 'Titillium Web', Helvetica Neue, Calibri, sans-serif; line-height: 24px;\"><div style=\" line-height: 24px;\"></div></td></tr></table></td></tr><tr class=\"hide\"><td height=\"35\" style=\"font-size: 35px; line-height: 35px;\">&nbsp;</td></tr><tr><td height=\"50\" style=\"font-size: 50px; line-height: 50px;\">&nbsp;</td></tr><tr><td align=\"center\"><table border=\"0\" width=\"500\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" style=\"border-collapse:collapse; mso-table-lspace:0pt; mso-table-rspace:0pt;\" class=\"container590\"><tr><td align=\"center\" style=\"color: #FF5F15; font-size: 45px; font-family: 'Oswald', sans-serif; line-height: 35px; font-weight: 1000;\" class=\"main-section-header outlook-font\"><div style=\"line-height: 35px\">Welcome to Grit Dominate, " + firstName + "</div></td></tr><tr><td height=\"20\" style=\"font-size: 20px; line-height: 20px;\">&nbsp;</td></tr><tr><td align=\"center\" class=\"outlook-font\" style=\"color: #eaf5ff; font-size: 15px; font-family: 'Titillium Web', Helvetica Neue, Calibri, sans-serif; line-height: 24px;\"><div style=\"line-height: 24px\">This is where your fitness journey begins! We're thrilled to have you on board.</div></td></tr></table></td></tr><tr><td height=\"60\" style=\"font-size: 60px; line-height: 60px;\">&nbsp;</td></tr></table></td></tr></table><table border=\"0\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" bgcolor=\"ffffff\"><tbody><tr><td height=\"50\" style=\"font-size: 50px; line-height: 50px;\">&nbsp;</td></tr><tr><td align=\"center\"><table border=\"0\" align=\"center\" width=\"590\" cellpadding=\"0\" cellspacing=\"0\" class=\"container590\"><tbody><tr><td><table border=\"0\" width=\"590\" align=\"left\" cellpadding=\"0\" cellspacing=\"0\" style=\"border-collapse:collapse; mso-table-lspace:0pt; mso-table-rspace:0pt;\" class=\"container590\"><tbody><tr><td align=\"center\" class=\"section-img\"><a href=\"#\" style=\" border-style: none !important; border: 0 !important;\"><img src=\"img/picemail.png\" style=\"display: block; width: 590px;\" width=\"590\" border=\"0\" alt=\"\"></a></td></tr><tr><td height=\"20\" style=\"font-size: 20px; line-height: 20px;\">&nbsp;</td></tr><tr><td align=\"left\" style=\"color: #212a2e; font-size: 18px; font-family: 'Source Sans Pro', Helvetica, Calibri, sans-serif; font-weight: 500; line-height: 24px;\" class=\"align-center outlook-font\">Dear " + firstName + ",</td></tr><tr><td height=\"15\" style=\"font-size: 15px; line-height: 15px;\">&nbsp;</td></tr><tr><td align=\"left\" style=\"color: #838383; font-family: 'Source Sans Pro', Helvetica, Calibri, sans-serif; line-height: 20px;font-size:14px;\" class=\"align-center outlook-font\"><div style=\"line-height: 20px\"><p>Congratulations on taking the first bold step towards transforming your life with Grit Dominate! We recognize your commitment and determination, and we're honored to be your partner on this incredible journey.</p><p>Your decision to join Grit Dominate is a powerful statement of your dedication to achieving your fitness goals. We understand that you're serious about making a change, and we're equally committed to helping you get there.</p></div></td></tr><tr><td height=\"15\" style=\"font-size: 15px; line-height: 15px;\">&nbsp;</td></tr><tr><td align=\"left\" style=\"color: #212a2e; font-size: 18px; font-family: 'Source Sans Pro', Helvetica, Calibri, sans-serif; font-weight: 500; line-height: 24px;\" class=\"align-center outlook-font\">At Grit Dominate, we offer three exceptional programs, feel free to check them out by clicking your preferred program:</td></tr><tr><td height=\"15\" style=\"font-size: 15px; line-height: 15px;\">&nbsp;</td></tr><tr><td align=\"left\" style=\"color: #838383; font-family: 'Source Sans Pro', Helvetica, Calibri, sans-serif; line-height: 20px;font-size:14px;\" class=\"align-center outlook-font\"><div style=\"line-height: 20px\"><b><p>1. <a href=\"http://localhost:8080/services\" onclick=\"scrollToElement('destination');\" target=\"_blank\">Monthly Personalized Ready-To-Go Program:</a></b> Tailored to your unique fitness goals, our monthly program ensures you receive ongoing guidance, personalized workouts, and support on your journey to success.</p><b><p>2. <a href=\"http://localhost:8080/services\" onclick=\"scrollToElement('destination');\" target=\"_blank\">3-Month Ready-to-Go Jumpstart:</a></b> A comprehensive program designed for those who prefer to make a one-time payment, giving you access to our full suite of services for three months.</p><b><p>3. <a href=\"http://localhost:8080/services\" onclick=\"scrollToElement('destination');\" target=\"_blank\">1-on-1 In-Person Fit Journey Program:</a></b> Elevate your fitness journey with one-on-one sessions personally with me. You'll receive dedicated coaching and attention to help you dominate your goals.</p><script>function scrollToElement(elementId){var newWindow=window.open(\"http://localhost:8080/services\");newWindow.addEventListener(\"DOMContentLoaded\", function (){var element=newWindow.document.getElementById(elementId);element.scrollIntoView({behavior: 'smooth'});});}</script></div></td></tr><tr><td align=\"left\"><table border=\"0\" align=\"left\" cellpadding=\"0\" cellspacing=\"0\" class=\"container590\"><tbody><tr><td align=\"center\"><table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\"><tbody></tbody></table></td></tr></tbody></table></td></tr></tbody></table></td></tr></tbody></table></td></tr></tbody></table><table border=\"0\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" bgcolor=\"ffffff\"><tbody><tr><td height=\"50\" style=\"font-size: 50px; line-height: 50px;\">&nbsp;</td></tr><tr><td align=\"center\"><table border=\"0\" align=\"center\" width=\"590\" cellpadding=\"0\" cellspacing=\"0\" class=\"container590\"><tbody><tr><td><table border=\"0\" width=\"264\" align=\"left\" cellpadding=\"0\" cellspacing=\"0\" style=\"border-collapse:collapse; mso-table-lspace:0pt; mso-table-rspace:0pt;\" class=\"container590\"><tbody><tr><td align=\"center\" class=\"section-img\"><a href=\"#\" style=\" border-style: none !important; border: 0 !important;\"><img src=\"img/pic2email.png\" style=\"display: block; width: 264px;\" width=\"264\" border=\"0\" alt=\"\"></a></td></tr><tr><td height=\"20\" style=\"font-size: 20px; line-height: 20px;\">&nbsp;</td></tr><tr><td align=\"left\" style=\"color: #212a2e; font-size: 18px; font-family: 'Source Sans Pro', Helvetica, Calibri, sans-serif; font-weight: 500; line-height: 24px;\" class=\"align-center outlook-font\">Results-Driven Training</td></tr><tr><td height=\"15\" style=\"font-size: 15px; line-height: 15px;\">&nbsp;</td></tr><tr><td align=\"left\" style=\"color: #838383; font-family: 'Source Sans Pro', Helvetica, Calibri, sans-serif; line-height: 20px;font-size:14px;\" class=\"align-center outlook-font\"><div style=\"line-height: 20px\">Experience the power of our results-driven workouts that have helped countless individuals achieve their fitness goals. Our evidence-based approach ensures that every minute you invest in your fitness with Grit Dominate brings you closer to your desired results.</div></td></tr><tr><td height=\"15\" style=\"font-size: 15px; line-height: 15px;\">&nbsp;</td></tr><tr><td align=\"left\"><table border=\"0\" align=\"left\" cellpadding=\"0\" cellspacing=\"0\" class=\"container590\"><tbody><tr><td align=\"center\"><table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\"><tbody><tr><td align=\"center\" class=\"outlook-font\" style=\"color: #5c9085; font-family: 'Source Sans Pro', Helvetica, Calibri, sans-serif; line-height: 22px;\"><div style=\"line-height: 22px;\"><a href=\"http://localhost:8080/services\" style=\"color: #FF5F15; text-decoration: none; font-weight: 1000;\">Read more</a></div></td></tr></tbody></table></td></tr></tbody></table></td></tr></tbody></table><table border=\"0\" width=\"5\" align=\"left\" cellpadding=\"0\" cellspacing=\"0\" style=\"border-collapse:collapse; mso-table-lspace:0pt; mso-table-rspace:0pt;\" class=\"container590\"><tbody><tr><td width=\"5\" height=\"50\" style=\"font-size: 50px; line-height: 50px;\"></td></tr></tbody></table><table border=\"0\" width=\"262\" align=\"right\" cellpadding=\"0\" cellspacing=\"0\" style=\"border-collapse:collapse; mso-table-lspace:0pt; mso-table-rspace:0pt;\" class=\"container590\"><tbody><tr><td align=\"center\" class=\"section-img\"><a href=\"#\" style=\" border-style: none !important; border: 0 !important;\"><img src=\"img/pic3email.png\" style=\"display: block; width: 264px;\" width=\"264\" border=\"0\" alt=\"\"></a></td></tr><tr><td height=\"20\" style=\"font-size: 20px; line-height: 20px;\">&nbsp;</td></tr><tr><td align=\"left\" style=\"color: #212a2e; font-size: 18px; font-family: 'Source Sans Pro', Helvetica, Calibri, sans-serif; font-weight: 500; line-height: 24px;\" class=\"align-center outlook-font\"><div style=\"line-height: 24px\">Personal Coaching</div></td></tr><tr><td height=\"15\" style=\"font-size: 15px; line-height: 15px;\">&nbsp;</td></tr><tr><td align=\"left\" style=\"color: #838383; font-family: 'Source Sans Pro', Helvetica, Calibri, sans-serif; line-height: 20px;font-size:14px;\" class=\"align-center outlook-font\"><div style=\"line-height: 20px\">At Grit Dominate, you're not just another client; you're part of a community where personal coaching is at the heart of your fitness journey. Our knowledgeable trainers will provide you with one-on-one support, guidance, and motivation, ensuring your success and helping you reach your full potential.</div></td></tr><tr><td height=\"15\" style=\"font-size: 15px; line-height: 15px;\">&nbsp;</td></tr><tr><td align=\"left\"><table border=\"0\" align=\"left\" cellpadding=\"0\" cellspacing=\"0\" class=\"container590\"><tbody><tr><td align=\"center\"><table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\"><tbody><tr><td align=\"center\" class=\"outlook-font\" style=\"color: #5c9085; font-family: 'Source Sans Pro', Helvetica, Calibri, sans-serif; line-height: 22px;\"><div style=\"line-height: 22px;\"><a href=\"#\" style=\"color: #FF5F15; text-decoration: none; font-weight: 1000;\">Read more</a></div></td></tr></tbody></table></td></tr></tbody></table></td></tr></tbody></table></td></tr></tbody></table></td></tr></tbody></table><table border=\"0\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" bgcolor=\"ffffff\"><tbody><tr><td align=\"center\"><table border=\"0\" align=\"center\" width=\"590\" cellpadding=\"0\" cellspacing=\"0\" class=\"container590\"><tbody><tr><td><table border=\"0\" width=\"590\" align=\"left\" cellpadding=\"0\" cellspacing=\"0\" style=\"border-collapse:collapse; mso-table-lspace:0pt; mso-table-rspace:0pt;\" class=\"container590\"><tbody><tr><td align=\"left\" style=\"color: #838383; font-family: 'Source Sans Pro', Helvetica, Calibri, sans-serif; line-height: 20px;font-size:14px;\" class=\"align-center outlook-font\"><div style=\"line-height: 20px\"><p>We believe in you, and we're here to help you break through any barriers that stand in your way. Should you ever need guidance, encouragement, or have questions about our programs, our team at <a href=\"mailto:gritdominate@gmail.com\"> gritdominate@gmail.com</a> and <a href=\"sms:+639566501830\">+63 956 650 1830</a> is always here to assist you. </p><p>Once again, welcome to the Grit Dominate family. Your fitness transformation begins now, and we can't wait to witness your achievements. I will personally contact you after this registration.</p><p>Your journey is our mission, and together, we will dominate! \uD83E\uDD8D</p><b><p>Best Regards,</p><p>Amiel De Los Reyes</p><p>Grit Dominate Founder</p></b></div></td></tr><tr><td align=\"left\"><table border=\"0\" align=\"left\" cellpadding=\"0\" cellspacing=\"0\" class=\"container590\"><tbody><tr><td align=\"center\"><table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\"><tbody></tbody></table></td></tr></tbody></table></td></tr></tbody></table></td></tr></tbody></table></td></tr></tbody></table><table border=\"0\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" bgcolor=\"f8f9fa\"><tr><td height=\"25\" style=\"font-size: 25px; line-height: 25px;\">&nbsp;</td></tr><tr><td align=\"center\"><table border=\"0\" align=\"center\" width=\"590\" cellpadding=\"0\" cellspacing=\"0\" class=\"container590\"><tr><td align=\"center\" style=\"color: #9c9c9c; font-size: 13px; font-family: 'Titillium Web', Helvetica Neue, Calibri, sans-serif; line-height: 24px;\" class=\"outlook-font align-center\"><i class=\"fa fa-location-arrow\"></i> &nbsp;Manila, Philippines</br><i class=\"fa fa-phone\"></i> &nbsp;<a href=\"sms:+639566501830\">+63 956 650 1830</a></br><i class=\"fa fa-envelope-o\"></i> &nbsp; <a href=\"mailto:gritdominate@gmail.com\"> gritdominate@gmail.com</a></td></tr><tr><td height=\"30\" style=\"font-size: 30px; line-height: 30px;\">&nbsp;</td></tr><tr><td align=\"center\" style=\"color: #9c9c9c; font-size: 13px; font-family: 'Titillium Web', Helvetica Neue, Calibri, sans-serif; line-height: 24px;\" class=\"outlook-font align-center\">You're receiving this email because you've signed up to receive updates from Grit Dominate.</br>Copyright ©2023 | Grit Dominate Limited | All rights reserved</td></tr></table></td></tr><tr><td height=\"25\" style=\"font-size: 25px; line-height: 25px;\">&nbsp;</td></tr></table></body></html>";
//mimeMessage.setContent(htmlMsg, "text/html"); /** Use this or below line **/
            helper.setText(htmlMsg, true); // Use this or above line.
            helper.setTo(email);
            helper.setSubject(subject);
            helper.setFrom("gritdominate@gmail.com");
            mailSender.send(mimeMessage);
        } catch (MessagingException ex) {
            ex.getStackTrace();

        }
        return "success";
    }

}