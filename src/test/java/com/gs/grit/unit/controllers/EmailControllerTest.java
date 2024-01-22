import com.gs.grit.controllers.EmailController;
import com.gs.grit.entities.Emails;
import com.gs.grit.entities.User;
import com.gs.grit.repositories.EmailsRepo;
import com.gs.grit.repositories.UserRepo;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebMvcTest(EmailController.class)
class EmailControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private JavaMailSender mailSender;

    @MockBean
    private EmailsRepo emailsRepo;

    @MockBean
    private UserRepo userRepo;

    @InjectMocks
    private EmailController emailController;

    @Test
    void testEmailSub_Success() throws Exception {
        // Mock the behavior of the repository methods
        when(emailsRepo.findByEmail(any())).thenReturn(null);

        // Perform a mock HTTP POST request to /newsletterSignup
        mockMvc.perform(MockMvcRequestBuilders.post("/newsletterSignup")
                        .param("email", "test@example.com"))
                .andExpect(MockMvcResultMatchers.status().isOk())  // Expect HTTP 200 OK
                .andExpect(MockMvcResultMatchers.view().name("success"));  // Expect view name "success"
    }

    @Test
    void testEmailSub_DuplicateEmail() throws Exception {
        // Mock the behavior of the repository methods to simulate a duplicate email
        when(emailsRepo.findByEmail(any())).thenReturn(new Emails());

        // Perform a mock HTTP POST request to /newsletterSignup
        mockMvc.perform(MockMvcRequestBuilders.post("/newsletterSignup")
                        .param("email", "duplicate@example.com"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())  // Expect HTTP 3xx redirection
                .andExpect(MockMvcResultMatchers.view().name("redirect:/404email"));  // Expect redirect to /404email
    }

    @Test
    void testRegister_Success() throws Exception {
        // Mock the behavior of the repository methods
        when(userRepo.findByEmail(any())).thenReturn(null);

        // Perform a mock HTTP POST request to /registerNow
        mockMvc.perform(MockMvcRequestBuilders.post("/registerNow")
                        .param("firstName", "John")
                        .param("lastName", "Doe")
                        .param("email", "john.doe@example.com")
                        .param("phoneNumber", "123456789")
                        .param("password", "password"))
                .andExpect(MockMvcResultMatchers.status().isOk())  // Expect HTTP 200 OK
                .andExpect(MockMvcResultMatchers.view().name("success"));  // Expect view name "success"
    }

    @Test
    void testRegister_DuplicateEmail() throws Exception {
        // Mock the behavior of the repository methods to simulate a duplicate email
        when(userRepo.findByEmail(any())).thenReturn(new User());

        // Perform a mock HTTP POST request to /registerNow
        mockMvc.perform(MockMvcRequestBuilders.post("/registerNow")
                        .param("firstName", "Duplicate")
                        .param("lastName", "User")
                        .param("email", "duplicate@example.com")
                        .param("phoneNumber", "987654321")
                        .param("password", "password"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())  // Expect HTTP 3xx redirection
                .andExpect(MockMvcResultMatchers.view().name("redirect:/404email"));  // Expect redirect to /404email
    }
}
