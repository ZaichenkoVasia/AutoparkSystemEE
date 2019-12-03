package controller.command.impl.user;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class LogOutCommandTest {
    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse responce;

    @Mock
    private HttpSession session;

    @InjectMocks
    private LogOutCommand command;

    @After
    public void resetMock() {
        reset(request, responce, session);
    }

    @Test
    public void executeShouldReturnSuccessfulLoginPage() {
        when(request.getSession()).thenReturn(session);
        String expected = "index.jsp";
        String actual = command.execute(request, responce);

        assertEquals(expected, actual);
    }
}
