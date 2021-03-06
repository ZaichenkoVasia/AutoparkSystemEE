package controller.command.impl.user;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class LoginPageCommandTest {
    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse responce;

    @InjectMocks
    private LoginPageCommand command;

    @After
    public void resetMock() {
        reset(request, responce);
    }

    @Test
    public void executeShouldReturnSuccessfulLoginPage() {
        when(request.getParameter(anyString())).thenReturn("admin");
        String expected = "WEB-INF/jsp/login.jsp";
        String actual = command.execute(request, responce);

        assertThat(expected, is(actual));
    }
}
