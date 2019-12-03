package controller.command.impl.user;

import controller.util.collectors.impl.UserDataCollector;
import model.domain.User;
import model.service.UserService;
import model.service.encoder.EncoderPassword;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class LoginCommandTest {

    private static final User USER = getUser();

    private static final User ENCODED_USER = getEncodedUser();

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse responce;

    @Mock
    private UserService service;

    @Mock
    private UserDataCollector collector;

    @Mock
    private EncoderPassword encoderPassword;

    @Mock
    private HttpSession session;

    @InjectMocks
    private LoginCommand command;

    @After
    public void resetMock() {
        reset(request, responce, service, collector, encoderPassword, session);
    }

    @Test
    public void executeShouldLoginUserWithCorrectPassword() {
        when(request.getSession()).thenReturn(session);
        when(collector.retrieveData(request)).thenReturn(USER);
        when(service.findUserByLoginData(any(User.class))).thenReturn(ENCODED_USER);
        when(encoderPassword.encode(anyString())).thenReturn("21232f297a57a5a743894ae4a801fc3");

        String expected = "index.jsp";
        String actual = command.execute(request, responce);

        assertThat(expected, is(actual));
    }

    @Test
    public void executeShouldNotLoginUserWithWrongPassword() {
        when(request.getSession()).thenReturn(session);
        when(collector.retrieveData(request)).thenReturn(USER);
        when(service.findUserByLoginData(any(User.class))).thenReturn(ENCODED_USER);
        when(encoderPassword.encode(anyString())).thenReturn("WrongPassword");

        String expected = "WEB-INF/jsp/login.jsp";
        String actual = command.execute(request, responce);

        assertThat(expected, is(actual));
    }

    private static User getUser() {
        return User.builder()
                .withId(1)
                .withLogin("user.login")
                .withPassword("admin")
                .build();
    }

    private static User getEncodedUser() {
        return User.builder()
                .withId(1)
                .withLogin("user.login")
                .withPassword("21232f297a57a5a743894ae4a801fc3")
                .build();
    }
}
