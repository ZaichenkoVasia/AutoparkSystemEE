package controller.command.impl.bus;

import model.domain.Admin;
import model.domain.Driver;
import model.domain.User;
import model.service.AdminService;
import model.service.BusStationService;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AccountCommandTest {
    private static final Admin ADMIN = getAdmin();

    private static final Driver DRIVER = getDriver();

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse responce;

    @Mock
    private BusStationService service;

    @Mock
    private AdminService adminService;

    @Mock
    private HttpSession session;

    @InjectMocks
    private AccountCommand command;

    @After
    public void resetMock() {
        reset(request, responce, service, adminService, session);
    }

    @Test
    public void executeShouldReturnAccountAdminPage() {
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute(anyString())).thenReturn(ADMIN.getUser());
        when(adminService.findAdminByUserId(any(Integer.class))).thenReturn(ADMIN);

        String expected = "WEB-INF/jsp/admin/account.jsp";
        String actual = command.execute(request, responce);

        assertThat(expected, is(actual));
        verify(request).setAttribute(anyString(), any());
    }

    @Test
    public void executeShouldReturnAccountDriverPage() {
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute(anyString())).thenReturn(DRIVER.getUser());
        when(service.findDriverAccountDataByUserId(any(Integer.class))).thenReturn(DRIVER);

        String expected = "WEB-INF/jsp/driver/account.jsp";
        String actual = command.execute(request, responce);

        assertThat(expected, is(actual));
        verify(request).setAttribute(anyString(), any());
    }

    @Test
    public void executeShouldNotReturnAccountPage() {
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute(anyString())).thenReturn(null);

        String expected = "WEB-INF/jsp/login.jsp";
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
                        .withRole("driver")
                        .build())
                .build();
    }

    private static Admin getAdmin() {
        return Admin.builder()
                .withId(1)
                .withName("admin.name")
                .withSurname("admin.surname")
                .withDegree("admin.degree")
                .withUser(User.builder()
                        .withId(1)
                        .withLogin("user.login")
                        .withPassword("user.password")
                        .withRole("admin")
                        .build())
                .build();
    }
}

