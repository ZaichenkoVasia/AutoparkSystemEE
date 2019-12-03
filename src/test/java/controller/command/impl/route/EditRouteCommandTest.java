package controller.command.impl.route;

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
public class EditRouteCommandTest {
    private static final Route ROUTE = getRoute();

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse responce;

    @Mock
    private RouteService service;

    @InjectMocks
    private EditRouteCommand command;

    @After
    public void resetMock() {
        reset(request, responce, service);
    }

    @Test
    public void executeShouldEditRoute() {
        when(request.getParameter(anyString())).thenReturn("1");
        when(service.findElementById(any(Integer.class))).thenReturn(ROUTE);

        String expected = "WEB-INF/jsp/editing_pages/add_route.jsp";
        String actual = command.execute(request, responce);

        assertThat(expected, is(actual));
        verify(request).setAttribute(anyString(), any());
    }

    private static Route getRoute() {
        return Route.builder()
                .withId(1)
                .withStatus("free")
                .build();
    }
}
