package com.gs.grit.integration;

// Import statements

import com.gs.grit.controllers.BMIController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BMIControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private BMIController bmiController;

    @Test
    public void testCalculateBMI() {
        // Input values for the BMI calculation
        double height = 175.0;
        double weight = 70.0;

        // Expected response after BMI calculation
        String expectedResponse = "Your BMI is: 23.0";

        // Simulate an HTTP POST request to calculateBMI and get the actual response
        String actualResponse = restTemplate.postForObject("/calculateBMI?height={height}&weight={weight}", null, String.class, height, weight);

        // Check if the actual response matches the expected result
        assertEquals(expectedResponse, actualResponse);
    }
}
