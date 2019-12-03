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

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class EditBusCommandTest {

    private static final Bus BUS = getBus();

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse responce;

    @Mock
    private BusService service;

    @InjectMocks
    private EditBusCommand command;

    @After
    public void resetMock() {
        reset(request, responce, service);
    }

    @Test
    public void executeShouldEditBus() {
        when(request.getParameter(anyString())).thenReturn("1");
        when(service.findElementById(any(Integer.class))).thenReturn(BUS);

        String expected = "WEB-INF/jsp/editing_pages/add_bus.jsp";
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
