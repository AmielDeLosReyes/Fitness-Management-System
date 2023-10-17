package com.gs.grit.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BMIController {

//    @PostMapping("/bmiCalculate")
//    public String bmiCalculate(@RequestParam double height,
//                               @RequestParam double weight,
//                               @RequestParam double age,
//                               @RequestParam String sex,
//                               Model model){
//        double bmi = weight / ((height/100) * (height/100));
//        String BMI = Double.toString(bmi);
//
//        model.addAttribute("height", height);
//        model.addAttribute("weight", weight);
//        model.addAttribute("age", age);
//        model.addAttribute("sex", sex);
//
//        model.addAttribute("bmi", bmi);
//        return "Your BMI is" + BMI;
//    }
    @PostMapping("/calculateBMI")
    @ResponseBody
    public String calculateBMI(@RequestParam("height") double height, @RequestParam("weight") double weight) {
        // Calculate BMI

        double bmi = Math.round(weight / ((height/100) * (height/100)));


        return "Your BMI is: " + bmi;
    }
}
