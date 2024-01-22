package com.gs.grit.integration;

import com.gs.grit.controllers.ClientFormController;
import com.gs.grit.entities.User;
import com.gs.grit.repositories.ClientsRepository;
import com.gs.grit.repositories.UserRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

// Specify the test environment and configure MockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class ClientFormControllerIntegrationTest {

    // Autowire MockMvc for simulating HTTP requests
    @Autowired
    private MockMvc mockMvc;

    // Autowire UserRepo for mocking user repository
    @Autowired
    private UserRepo userRepo;

    // Mock ClientsRepository for mocking clients repository
    @MockBean
    private ClientsRepository clientsRepository;

    // Test method for the clientFormComplete endpoint
    @Test
    public void testClientFormComplete() throws Exception {
        // Arrange
        User mockUser = new User();
        mockUser.setUser_id(1);
        mockUser.setFirst_name("John");
        mockUser.setLast_name("Doe");
        mockUser.setEmail("john.doe@example.com");

        // Mock behavior when findByEmail is called
        when(userRepo.findByEmail("john.doe@example.com")).thenReturn(mockUser);

        // Act
        ResultActions resultActions = mockMvc.perform(post("/clientFormComplete")
                        .param("height", "175")
                        .param("weight", "70")
                // Add more parameters if needed
        );

        // Assert
        resultActions.andExpect(status().isOk())
                // Add more assertions as needed
                // For example, you can check the response content or perform additional verifications
                .andExpect(jsonPath("$.userId").value(1))
                .andExpect(jsonPath("$.message").value("Client form completed successfully"));
    }
}
