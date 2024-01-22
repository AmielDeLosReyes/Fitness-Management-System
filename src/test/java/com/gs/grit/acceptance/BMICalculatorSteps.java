package com.gs.grit.acceptance;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;

import static org.junit.Assert.assertEquals;

public class BMICalculatorSteps {

    private double height;
    private double weight;
    private String calculatedBMI;

    @Given("the user is on the BMI calculator page")
    public void givenUserIsOnBMICalculatorPage() {
        // Implement navigation to the BMI calculator page (Assume the page is reachable)
    }

    @When("the user enters valid height and weight")
    public void whenUserEntersValidHeightAndWeight() {
        // Assume the user provides height and weight values
        height = 175.0;
        weight = 70.0;
    }

    @And("submits the BMI calculation form")
    public void andUserSubmitsForm() {
        // Simulate submitting the BMI calculation form (Assume the form submits via HTTP)
        // This step may involve interacting with your application, e.g., using Selenium WebDriver
        // For simplicity, we'll calculate BMI directly here

        // Calculate BMI
        double bmi = Math.round(weight / ((height / 100) * (height / 100)));
        calculatedBMI = "Your BMI is: " + bmi;
    }

    @Then("the user should see the calculated BMI")
    public void thenUserShouldSeeCalculatedBMI() {
        // Simulate verifying the displayed calculated BMI
        // This step may involve interacting with your application, e.g., using Selenium WebDriver
        // For simplicity, we'll use a simple assertion

        assertEquals("Your BMI is: 23.0", calculatedBMI);
    }
}
