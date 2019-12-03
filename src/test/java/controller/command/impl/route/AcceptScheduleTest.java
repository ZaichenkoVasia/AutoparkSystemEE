package controller.command.impl.route;

import model.service.DriverService;
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
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AcceptScheduleTest {
    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse responce;

    @InjectMocks
    private AcceptSchedule command;

    @Mock
    private DriverService service;

    @After
    public void resetMock() {
        reset(request, responce);
    }

    @Test
    public void executeShouldAcceptSchedule() {
        when(request.getParameter(anyString())).thenReturn("1").thenReturn("new");
        String expected = "index.jsp";
        String actual = command.execute(request, responce);

        assertThat(expected, is(actual));
        verify(request).setAttribute(anyString(), any());
        verify(service).setStatusWork(any(Integer.class));
    }

    @Test
    public void executeShouldNotAcceptSchedule() {
        when(request.getParameter(anyString())).thenReturn("1").thenReturn("notNew");
        String expected = "index.jsp";
        String actual = command.execute(request, responce);

        assertThat(expected, is(actual));
        verify(request).setAttribute(anyString(), any());
        verify(service, never()).setStatusWork(any(Integer.class));
    }
}


