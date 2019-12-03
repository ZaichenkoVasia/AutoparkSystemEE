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

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class DriverInfoCommandTest {
    private static final Driver DRIVER = getDriver();

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse responce;

    @Mock
    private DriverService service;

    @InjectMocks
    private DriverInfoCommand command;

    @After
    public void resetMock() {
        reset(request, responce, service);
    }

    @Test
    public void executeShouldReturnDriverInfoPage() {
        when(request.getParameter(anyString())).thenReturn("1");
        when(service.findDriverByBusId(any(Integer.class))).thenReturn(DRIVER);

        String expected = "WEB-INF/jsp/editing_pages/add_driver.jsp";
        String actual = command.execute(request, responce);

        assertThat(expected, is(actual));
        verify(request).setAttribute(anyString(), any());
    }

    @Test
    public void executeShouldNotReturnDriverInfoPage() {
        when(request.getParameter(anyString())).thenReturn("1");
        when(service.findDriverByBusId(any(Integer.class))).thenReturn(null);

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
