package controller.command.impl.user;

import model.service.UserService;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class LoginPageCommandTest {
    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse responce;

    @Mock
    private UserService service;

    @InjectMocks
    private LoginPageCommand command;

    @After
    public void resetMock() {
        reset(request, responce, service);
    }

    @Test
    public void executeShouldReturnSuccessfulLoginPage() {
        when(request.getParameter(anyString())).thenReturn("admin");
        String expected = "WEB-INF/jsp/login.jsp";
        String actual = command.execute(request, responce);

        assertEquals(expected, actual);
    }
}
