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
public class DeleteBusCommandTest {

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse responce;

    @Mock
    private BusStationService service;

    @InjectMocks
    private DeleteBusCommand command;

    @After
    public void resetMock() {
        reset(request, responce, service);
    }

    @Test
    public void executeShouldDeleteBus() {
        when(request.getParameter(anyString())).thenReturn("1");

        String expected = "index.jsp";
        String actual = command.execute(request, responce);

        assertThat(expected, is(actual));
        verify(service).deleteBus(any(Integer.class));
        verify(request).setAttribute(anyString(), any());
    }
}
