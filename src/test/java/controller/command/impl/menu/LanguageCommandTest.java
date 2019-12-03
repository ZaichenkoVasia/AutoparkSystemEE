package controller.command.impl.menu;

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
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class LanguageCommandTest {
    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse responce;


    @Mock
    private HttpSession session;

    @InjectMocks
    private LanguageCommand command;

    @After
    public void resetMock() {
        reset(request, responce, session);
    }

    @Test
    public void executeShouldReturnIndexPageWithRussianLanguage() {
        when(request.getSession()).thenReturn(session);
        when(request.getParameter(anyString())).thenReturn("ru_RU");

        String expected = "index.jsp";
        String actual = command.execute(request, responce);

        assertThat(expected, is(actual));
        verify(session).setAttribute(anyString(), any());
        verify(request).setAttribute(anyString(), any());
    }

    @Test
    public void executeShouldReturnIndexPageWithUkranianLanguage() {
        when(request.getSession()).thenReturn(session);
        when(request.getParameter(anyString())).thenReturn("uk_UA");

        String expected = "index.jsp";
        String actual = command.execute(request, responce);

        assertThat(expected, is(actual));
        verify(session).setAttribute(anyString(), any());
        verify(request).setAttribute(anyString(), any());
    }

    @Test
    public void executeShouldReturnIndexPageWithEnglishLanguage() {
        when(request.getSession()).thenReturn(session);
        when(request.getParameter(anyString())).thenReturn("en_US");

        String expected = "index.jsp";
        String actual = command.execute(request, responce);

        assertThat(expected, is(actual));
        verify(session).setAttribute(anyString(), any());
        verify(request).setAttribute(anyString(), any());
    }
}
