package com.gs.grit.acceptance;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ClientFormControllerSteps {

    private MockMvc mockMvc; // Initialize MockMvc
    private ModelAndView modelAndView; // Initialize ModelAndView

    // Add any necessary fields or dependencies

    @Given("the user is on the client form page")
    public void givenUserIsOnClientFormPage() {
        // Implement navigation to the client form page
        // In this case, you can simulate the HTTP request to the client form page using MockMvc
        try {
            mockMvc.perform(MockMvcRequestBuilders.get("/clientForm"))
                    .andExpect(status().isOk()); // Assuming a successful request
        } catch (Exception e) {
            e.printStackTrace(); // Handle exceptions if needed
        }
    }

    @When("the user enters valid client information")
    public void whenUserEntersValidClientInformation() {
        // Implement entering valid client information in the form
        // For example, you can use MockMvc to simulate form submission
        try {
            mockMvc.perform(MockMvcRequestBuilders.post("/clientFormComplete")
                                    .param("height", "170")
                                    .param("weight", "70")
                                    .param("age", "25")
                            // Add more parameters as needed
                    )
                    .andExpect(status().isOk()); // Assuming a successful form submission
        } catch (Exception e) {
            e.printStackTrace(); // Handle exceptions if needed
        }
    }

    @And("submits the client form")
    public void andUserSubmitsClientForm() {
        // Implement submitting the client form
        // This step may be covered in the "whenUserEntersValidClientInformation" step
    }

    @Then("the user should see the success page")
    public void thenUserShouldSeeSuccessPage() {
        // Implement verification of the displayed success page
        // You can use MockMvcResultMatchers to verify the expected result
        try {
            mockMvc.perform(MockMvcRequestBuilders.get("/successClientForm"))
                    .andExpect(status().isOk())
                    .andExpect(MockMvcResultMatchers.view().name("successClientForm")); // Assuming the view name is "successClientForm"
        } catch (Exception e) {
            e.printStackTrace(); // Handle exceptions if needed
        }
    }
}
