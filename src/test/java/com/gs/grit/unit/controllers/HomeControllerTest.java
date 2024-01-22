import com.gs.grit.controllers.HomeController;
import com.gs.grit.entities.ProgramData;
import com.gs.grit.entities.User;
import com.gs.grit.repositories.UserProgramsRepo;
import com.gs.grit.repositories.UserRepo;
import jakarta.persistence.Tuple;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// WebMvcTest annotation focuses on testing the HomeController and its MVC components.
@WebMvcTest(HomeController.class)
public class HomeControllerTest {

    // Injected MockMvc instance to perform HTTP requests and validate responses
    @Autowired
    private MockMvc mockMvc;

    // Mocking the UserRepo for repository interaction
    @Mock
    private UserRepo userRepo;

    // Mocking the UserProgramsRepo for repository interaction
    @Mock
    private UserProgramsRepo userProgramsRepo;

    // Injecting mocks into the HomeController
    @InjectMocks
    private HomeController homeController;

    // Test case for the home page
    @Test
    public void testHomePage() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(model().attributeExists("user"));
    }

    // Test case for the about-us page
    @Test
    public void testAboutUsPage() throws Exception {
        mockMvc.perform(get("/about-us"))
                .andExpect(status().isOk())
                .andExpect(view().name("about-us"))
                .andExpect(model().attributeExists("user"));
    }

    // Test case for the userLogin page
    @Test
    public void testUserLoginPage() throws Exception {
        mockMvc.perform(get("/userLogin"))
                .andExpect(status().isOk())
                .andExpect(view().name("userLogin"))
                .andExpect(model().attributeExists("user"));
    }

    // Test case for the admin dashboard page
    @Test
    public void testAdminDashboardPage() throws Exception {
        mockMvc.perform(get("/admin/dashboard"))
                .andExpect(status().isOk())
                .andExpect(view().name("admin/dashboard"));
    }

    // Test case for the clientWorkout page
    @Test
    public void testClientWorkoutPage() throws Exception {
        // Mock user session
        User mockUser = new User();
        mockUser.setFirst_name("John");
        mockUser.setLast_name("Doe");
        when(userRepo.findByEmail("john@example.com")).thenReturn(mockUser);

        // Mock userProgramsRepo
        List<Object[]> userProgramData = new ArrayList<>();
        Object[] programData = {1, "Program 1", "ACTIVE"};
        userProgramData.add(programData);
        when(userProgramsRepo.findProgramDataByUserId(1)).thenReturn(userProgramData);

        mockMvc.perform(get("/clientWorkout")
                        .param("programId", "1")
                        .param("userId", "1")
                        .sessionAttr("user", mockUser))
                .andExpect(status().isOk())
                .andExpect(view().name("clients/clientWorkout"))
                .andExpect(model().attributeExists("user", "workouts", "programName"));
    }

    // Test case for the email page
    @Test
    public void testEmailPage() throws Exception {
        mockMvc.perform(get("/email"))
                .andExpect(status().isOk())
                .andExpect(view().name("email"));
    }

    // Test case for admin authentication success
    @Test
    public void testAdminAuthenticationSuccess() throws Exception {
        mockMvc.perform(post("/admin/authenticate")
                        .param("username", "admin")
                        .param("password", "admin123"))
                .andExpect(status().isOk())
                .andExpect(view().name("admin/dashboard"))
                .andExpect(model().attributeExists("users", "userCount"));
    }

    // Test case for admin authentication failure
    @Test
    public void testAdminAuthenticationFailure() throws Exception {
        mockMvc.perform(post("/admin/authenticate")
                        .param("username", "invalidAdmin")
                        .param("password", "invalidPassword"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/adminLogin"))
                .andExpect(flash().attribute("error", "Invalid username or password"));
    }

    // Test case for user authentication success
    @Test
    public void testUserAuthenticationSuccess() throws Exception {
        mockMvc.perform(post("/authenticate")
                        .param("email", "user@example.com")
                        .param("password", "user123"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/userLogin"))
                .andExpect(session().attributeExists("user"));
    }

    // Test case for user authentication failure
    @Test
    public void testUserAuthenticationFailure() throws Exception {
        mockMvc.perform(post("/authenticate")
                        .param("email", "invalidUser@example.com")
                        .param("password", "invalidPassword"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/userHome"));
    }

    // Test case for the not found page
    @Test
    public void testNotFoundPage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/404"))
                .andExpect(status().isOk())
                .andExpect(view().name("404"));
    }

    // Test case for the email duplicate page
    @Test
    public void testEmailDuplicatePage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/404email"))
                .andExpect(status().isOk())
                .andExpect(view().name("404email"));
    }

    // Test case for the blog page
    @Test
    public void testBlogPage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/blog"))
                .andExpect(status().isOk())
                .andExpect(view().name("blog"));
    }

    // Test case for the BMI calculator page
    @Test
    public void testBmiCalculatorPage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/bmi-calculator"))
                .andExpect(status().isOk())
                .andExpect(view().name("bmi-calculator"));
    }

    // Test case for the class details page
    @Test
    public void testClassDetailsPage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/class-details"))
                .andExpect(status().isOk())
                .andExpect(view().name("class-details"));
    }

    // Test case for the class timetable page
    @Test
    public void testClassTimetablePage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/class-timetable"))
                .andExpect(status().isOk())
                .andExpect(view().name("class-timetable"));
    }

    // Test case for the user registration page
    @Test
    public void testUserRegistrationPage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/userRegistration"))
                .andExpect(status().isOk())
                .andExpect(view().name("userRegistration"));
    }

    // Test case for the gallery page
    @Test
    public void testGalleryPage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/gallery"))
                .andExpect(status().isOk())
                .andExpect(view().name("gallery"));
    }

    // Test case for the main page
    @Test
    public void testMainPage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/main"))
                .andExpect(status().isOk())
                .andExpect(view().name("main"));
    }

    // Test case for the services page
    @Test
    public void testServicesPage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/services"))
                .andExpect(status().isOk())
                .andExpect(view().name("services"));
    }

    // Test case for the team page
    @Test
    public void testTeamPage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/team"))
                .andExpect(status().isOk())
                .andExpect(view().name("team"));
    }

    // Test case for the registration page
    @Test
    public void testRegistrationPage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/registration"))
                .andExpect(status().isOk())
                .andExpect(view().name("contact"));
    }

    // Test case for the successClientForm page
    @Test
    public void testSuccessClientFormPage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/successClientForm"))
                .andExpect(status().isOk())
                .andExpect(view().name("successClientForm"));
    }
}

