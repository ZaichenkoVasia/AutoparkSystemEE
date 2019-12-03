package controller.command.impl.bus;

import controller.exception.WrongInputDataRuntimeException;
import controller.util.collectors.impl.BusDataCollector;
import model.domain.Bus;
import model.domain.Route;
import model.domain.Schedule;
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
public class SaveBusCommandTest {
    private static final Bus BUS = getBus();

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse responce;

    @Mock
    private BusStationService service;

    @Mock
    private BusDataCollector collector;

    @InjectMocks
    private SaveBusCommand command;

    @After
    public void resetMock() {
        reset(request, responce, service, collector);
    }

    @Test
    public void executeShouldSaveBus() {
        when(request.getParameter(anyString())).thenReturn("1");
        when(collector.retrieveData(request)).thenReturn(BUS);
        when(service.saveBus(any(Bus.class), any(Schedule.class), anyString(), anyString())).thenReturn(true);

        String expected = "index.jsp";
        String actual = command.execute(request, responce);

        assertThat(expected, is(actual));
        verify(request).setAttribute(anyString(), any());
    }

    @Test
    public void executeShouldNotSaveBus() {
        when(request.getParameter(anyString())).thenReturn("-1");
        when(collector.retrieveData(request)).thenThrow(WrongInputDataRuntimeException.class);

        String expected = "WEB-INF/jsp/editing_pages/add_bus.jsp";
        String actual = command.execute(request, responce);

        assertThat(expected, is(actual));
    }

    private static Bus getBus() {
        return Bus.builder()
                .withId(1)
                .withModel("model")
                .withRoute(Route.builder()
                        .withId(1)
                        .build())
                .withSchedule(Schedule.builder()
                        .withId(1)
                        .build())
                .build();
    }
}
