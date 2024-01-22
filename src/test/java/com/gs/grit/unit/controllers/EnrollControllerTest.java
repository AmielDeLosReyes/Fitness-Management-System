import com.gs.grit.controllers.EnrollController;
import com.gs.grit.entities.User;
import com.gs.grit.repositories.UserProgramsRepo;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class EnrollControllerTest {

    @InjectMocks
    private EnrollController enrollController;

    @Mock
    private UserProgramsRepo userProgramsRepo;

    @Mock
    private HttpSession httpSession;

    @Mock
    private Model model;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testEnroll_Success() {
        // Mock the session to return a user
        User user = new User();
        when(httpSession.getAttribute("user")).thenReturn(user);

        // Mock the behavior of userProgramsRepo
        when(userProgramsRepo.findMaxUserProgramId()).thenReturn(1);
        when(userProgramsRepo.findProgramNameByProgramId(anyInt())).thenReturn("Test Program");

        // Mock the behavior of userProgramsRepo.insertUserProgram
        when(userProgramsRepo.insertUserProgram(anyInt(), anyInt(), anyInt(), anyString())).thenReturn(1);

        // Mock the behavior of mailSender.send
        doNothing().when(enrollController.getMailSender()).send(any());

        // Perform the enrollment
        String result = enrollController.enrol(httpSession, model, 1);

        // Assert the result or check model attributes if needed
        // Assert.assertEquals("success", result);
        // Assert.assertTrue((boolean) model.getAttribute("enrollmentSuccess"));
    }

    @Test
    void testEnroll_UserNotLoggedIn() {
        // Mock the session to return null (user not logged in)
        when(httpSession.getAttribute("user")).thenReturn(null);

        // Perform the enrollment
        String result = enrollController.enrol(httpSession, model, 1);

        // Assert the result or check model attributes if needed
        // Assert.assertEquals("redirect:/userLogin", result);
    }
}
