package controller.command.impl.bus;

import model.service.BusStationService;
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
public class AppointBusCommandTest {

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse responce;

    @Mock
    private BusStationService service;

    @InjectMocks
    private AppointBusCommand command;

    @After
    public void resetMock() {
        reset(request, responce, service);
    }

    @Test
    public void executeShouldAppointBus() {
        when(request.getParameter(anyString())).thenReturn("1");
        when(service.appointBusToRoute(any(Integer.class), any(Integer.class))).thenReturn(true);

        String expected = "index.jsp";
        String actual = command.execute(request, responce);

        assertThat(expected, is(actual));
        verify(request).setAttribute(anyString(), any());
    }
}
