package controller.command.impl.route;

import controller.exception.WrongInputDataRuntimeException;
import controller.util.collectors.impl.RouteDataCollector;
import model.domain.Route;
import model.service.RouteService;
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
public class SaveRouteCommandTest {
    private static final Route ROUTE = getRoute();

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse responce;

    @Mock
    private RouteService service;

    @Mock
    private RouteDataCollector collector;

    @InjectMocks
    private SaveRouteCommand command;

    @After
    public void resetMock() {
        reset(request, responce, service, collector);
    }

    @Test
    public void executeShouldSaveNewRoute() {
        when(request.getParameter(anyString())).thenReturn(null);
        when(collector.retrieveData(request)).thenReturn(ROUTE);

        String expected = "index.jsp";
        String actual = command.execute(request, responce);

        assertThat(expected, is(actual));
        verify(service).insertElement(any(Route.class));
        verify(request).setAttribute(anyString(), any());
    }


    @Test
    public void executeShouldSaveOldRoute() {
        when(request.getParameter(anyString())).thenReturn("1");
        when(collector.retrieveData(request)).thenReturn(ROUTE);

        String expected = "index.jsp";
        String actual = command.execute(request, responce);

        assertThat(expected, is(actual));
        verify(service).updateElement(any(Route.class));
        verify(request).setAttribute(anyString(), any());
    }

    @Test
    public void executeShouldNotSaveDriver() {
        when(request.getParameter(anyString())).thenReturn("-1");
        when(collector.retrieveData(request)).thenThrow(WrongInputDataRuntimeException.class);

        String expected = "WEB-INF/jsp/editing_pages/add_route.jsp";
        String actual = command.execute(request, responce);

        assertThat(expected, is(actual));
    }

    private static Route getRoute() {
        return Route.builder()
                .withId(1)
                .withStatus("free")
                .build();
    }
}
