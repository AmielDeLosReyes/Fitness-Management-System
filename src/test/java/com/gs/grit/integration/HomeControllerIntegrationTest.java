package com.gs.grit.integration;

import com.gs.grit.controllers.HomeController;
import com.gs.grit.entities.Admin;
import com.gs.grit.entities.User;
import com.gs.grit.repositories.AdminRepo;
import com.gs.grit.repositories.UserRepo;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.Mockito.when;

@WebMvcTest(HomeController.class)
class HomeControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AdminRepo adminRepo;

    @MockBean
    private UserRepo userRepo;

    @InjectMocks
    private HomeController homeController;

    @Test
    void testAdminAuthenticate_Success() throws Exception {
        // Mock behavior of adminRepo
        Admin mockAdmin = new Admin();
        mockAdmin.setUsername("admin");
        mockAdmin.setPassword("password");
        when(adminRepo.findByUsername("admin")).thenReturn(mockAdmin);

        // Perform a mock HTTP POST request to /admin/authenticate
        mockMvc.perform(MockMvcRequestBuilders.post("/admin/authenticate")
                        .param("username", "admin")
                        .param("password", "password"))
                .andExpect(MockMvcResultMatchers.status().isOk())  // Expect HTTP 200 OK
                .andExpect(MockMvcResultMatchers.view().name("admin/dashboard"))  // Expect view name "admin/dashboard"
                .andExpect(MockMvcResultMatchers.model().attributeExists("users", "userCount"));  // Expect model attributes
    }

    @Test
    void testUserLogin_Success() throws Exception {
        // Mock HttpSession and User
        MockHttpSession httpSession = new MockHttpSession();

        User mockUser = new User();
        mockUser.setUser_id(1);
        mockUser.setFirst_name("John");
        mockUser.setLast_name("Doe");
        mockUser.setEmail("john.doe@example.com");
        httpSession.setAttribute("user", mockUser);

        // Mock behavior of userRepo
        when(userRepo.findByEmail("john.doe@example.com")).thenReturn(mockUser);

        // Perform a mock HTTP GET request to /userLogin
        mockMvc.perform(MockMvcRequestBuilders.get("/userLogin").session(httpSession))
                .andExpect(MockMvcResultMatchers.status().isOk())  // Expect HTTP 200 OK
                .andExpect(MockMvcResultMatchers.view().name("userHome"))  // Expect view name "userHome"
                .andExpect(MockMvcResultMatchers.model().attributeExists("welcomeMessage", "userPrograms"));  // Expect model attributes
    }

}

