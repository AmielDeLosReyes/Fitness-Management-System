package com.gs.grit.integration;

import com.gs.grit.controllers.EnrollController;
import com.gs.grit.entities.User;
import com.gs.grit.repositories.UserProgramsRepo;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import jakarta.servlet.http.HttpSession;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebMvcTest(EnrollController.class)
class EnrollControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserProgramsRepo userProgramsRepo;

    @MockBean
    private JavaMailSender mailSender;

    @InjectMocks
    private EnrollController enrollController;

    @Test
    void testEnroll_Success() throws Exception {
        // Mock HttpSession and User
        HttpSession httpSession = mockMvc.perform(MockMvcRequestBuilders.get("/userLogin"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getRequest().getSession();

        User mockUser = new User();
        mockUser.setUser_id(1);
        mockUser.setFirst_name("John");
        mockUser.setLast_name("Doe");
        mockUser.setEmail("john.doe@example.com");
        httpSession.setAttribute("user", mockUser);

        // Mock behavior of userProgramsRepo and mailSender
        when(userProgramsRepo.findMaxUserProgramId()).thenReturn(1);
        when(userProgramsRepo.findProgramNameByProgramId(any())).thenReturn("Test Program");
        when(userProgramsRepo.insertUserProgram(any(), any(), any(), any())).thenReturn(1);

        // Perform a mock HTTP POST request to /enrol
        mockMvc.perform(MockMvcRequestBuilders.post("/enrol")
                        .param("programId", "1")
                        .session(httpSession))
                .andExpect(MockMvcResultMatchers.status().isOk())  // Expect HTTP 200 OK
                .andExpect(MockMvcResultMatchers.model().attributeExists("enrollmentSuccess"))  // Expect model attribute
                .andExpect(MockMvcResultMatchers.view().name("success"));  // Expect view name "success"
    }

}

