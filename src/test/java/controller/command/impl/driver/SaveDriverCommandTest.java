package controller.command.impl.driver;

import controller.exception.WrongInputDataRuntimeException;
import controller.util.collectors.impl.DriverDataCollector;
import model.domain.Driver;
import model.domain.User;
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
public class SaveDriverCommandTest {
    private static final Driver DRIVER = getDriver();

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse responce;

    @Mock
    private BusStationService service;

    @Mock
    private DriverDataCollector collector;

    @InjectMocks
    private SaveDriverCommand command;

    @After
    public void resetMock() {
        reset(request, responce, service, collector);
    }

    @Test
    public void executeShouldSaveDriver() {
        when(request.getParameter(anyString())).thenReturn("1");
        when(collector.retrieveData(request)).thenReturn(DRIVER);
        when(service.saveDriver(any(Driver.class), any(User.class), anyString(), anyString())).thenReturn(true);

        String expected = "index.jsp";
        String actual = command.execute(request, responce);

        assertThat(expected, is(actual));
        verify(request).setAttribute(anyString(), any());
    }

    @Test
    public void executeShouldNotSaveDriver() {
        when(request.getParameter(anyString())).thenReturn("-1");
        when(collector.retrieveData(request)).thenThrow(WrongInputDataRuntimeException.class);

        String expected = "WEB-INF/jsp/editing_pages/add_driver.jsp";
        String actual = command.execute(request, responce);

        assertThat(expected, is(actual));
    }

    private static Driver getDriver() {
        return Driver.builder()
                .withId(1)
                .withName("name")
                .withSurname("surname")
                .withUser(User.builder()
                        .withId(1)
                        .build())
                .build();
    }
}
