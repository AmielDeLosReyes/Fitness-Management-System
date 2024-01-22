package com.gs.grit.unit.controllers;


import com.gs.grit.controllers.ClientFormController;
import com.gs.grit.entities.Clients;
import com.gs.grit.repositories.ClientsRepository;
import com.gs.grit.repositories.UserRepo;
import com.gs.grit.entities.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.when;
// Specify the test environment and configure MockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class ClientFormControllerTest {

    // Autowire MockMvc for simulating HTTP requests
    @Autowired
    private MockMvc mockMvc;

    // Autowire TestRestTemplate for simulating HTTP requests
    @Autowired
    private TestRestTemplate restTemplate;

    // Autowire UserRepo for mocking user repository
    @Autowired
    private UserRepo userRepo;

    // Mock ClientsRepository for mocking clients repository
    @MockBean
    private ClientsRepository clientsRepository;

    // Test method for the clientFormComplete endpoint
    @Test
    public void testClientFormComplete() throws Exception {
        // Mock user data
        User user = new User();
        user.setUser_id(1);
        user.setFirst_name("John");
        user.setLast_name("Doe");
        user.setEmail("john.doe@example.com");

        // Mock behavior when findByEmail is called
        when(userRepo.findByEmail("john.doe@example.com")).thenReturn(user);

        // Simulate an HTTP POST request to clientFormComplete with specified parameters
        ResultActions resultActions = mockMvc.perform(post("/clientFormComplete")
                        .param("height", "175")
                        .param("weight", "70")
                // ... (other parameters)
        );

        // Verify that the status is OK
        resultActions.andExpect(status().isOk());
    }
}