package controller.command.impl.bus;

import model.domain.Bus;
import model.domain.Route;
import model.service.BusService;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class BusesInfoCommandTest {
    private static final Bus BUS = getBus();

    private static final List<Bus> BUSES = Collections.singletonList(BUS);

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse responce;

    @Mock
    private BusService service;

    @InjectMocks
    private BusesInfoCommand command;

    @After
    public void resetMock() {
        reset(request, responce, service);
    }

    @Test
    public void executeShouldReturnBusInfoPage() {
        when(request.getParameter(anyString())).thenReturn("1");
        when(service.findBusesByIdRoute(any(Integer.class))).thenReturn(BUSES);

        String expected = "WEB-INF/jsp/admin/buses_on_route.jsp";
        String actual = command.execute(request, responce);

        assertThat(expected, is(actual));
        verify(request, times(2)).setAttribute(anyString(), any());
    }

    @Test
    public void executeShouldNotReturnBusInfoPage() {
        when(request.getParameter(anyString())).thenReturn("1");
        when(service.findBusesByIdRoute(any(Integer.class))).thenReturn(Collections.emptyList());

        String expected = "index.jsp";
        String actual = command.execute(request, responce);

        assertThat(expected, is(actual));
        verify(request).setAttribute(anyString(), any());
    }

    private static Bus getBus() {
        return Bus.builder()
                .withId(1)
                .withModel("model")
                .withRoute(Route.builder()
                        .withId(1)
                        .build())
                .build();
    }
}
