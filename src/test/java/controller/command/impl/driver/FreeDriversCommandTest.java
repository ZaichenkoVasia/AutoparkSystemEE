package controller.command.impl.driver;

import model.domain.Driver;
import model.domain.User;
import model.service.DriverService;
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
public class FreeDriversCommandTest {
    private static final Driver DRIVER = getDriver();

    private static final List<Driver> DRIVERS = Collections.singletonList(DRIVER);

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse responce;

    @Mock
    private DriverService service;

    @InjectMocks
    private FreeDriversCommand command;

    @After
    public void resetMock() {
        reset(request, responce, service);
    }

    @Test
    public void executeShouldReturnFreeDriversPage() {
        when(request.getParameter(anyString())).thenReturn("1");
        when(service.findFreeDrivers()).thenReturn(DRIVERS);

        String expected = "WEB-INF/jsp/admin/free_drivers.jsp";
        String actual = command.execute(request, responce);

        assertThat(expected, is(actual));
        verify(request, times(2)).setAttribute(anyString(), any());
    }

    @Test
    public void executeShouldNotReturnFreeDriversPage() {
        when(request.getParameter(anyString())).thenReturn("1");
        when(service.findFreeDrivers()).thenReturn(Collections.emptyList());

        String expected = "index.jsp";
        String actual = command.execute(request, responce);

        assertThat(expected, is(actual));
        verify(request).setAttribute(anyString(), any());
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
