package controller.command.impl.user;

import controller.exception.WrongInputDataRuntimeException;
import controller.util.collectors.impl.AdminDataCollector;
import model.domain.Admin;
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

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SaveAdminCommandTest {
    private static final Admin ADMIN = getAdmin();

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse responce;

    @Mock
    private BusStationService service;

    @Mock
    private AdminDataCollector collector;

    @InjectMocks
    private SaveAdminCommand command;

    @After
    public void resetMock() {
        reset(request, responce, service, collector);
    }

    @Test
    public void executeShouldSaveAdmin() {
        when(request.getParameter(anyString())).thenReturn("1");
        when(collector.retrieveData(request)).thenReturn(ADMIN);
        when(service.saveAdmin(any(Admin.class), any(User.class), anyString(), anyString())).thenReturn(true);

        String expected = "index.jsp";
        String actual = command.execute(request, responce);

        assertEquals(expected, actual);
    }

    @Test
    public void executeShouldNotSaveAdmin() {
        when(request.getParameter(anyString())).thenReturn("-1");
        when(collector.retrieveData(request)).thenThrow(WrongInputDataRuntimeException.class);

        String expected = "WEB-INF/jsp/admin/account.jsp";
        String actual = command.execute(request, responce);

        assertEquals(expected, actual);
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
                        .build())
                .build();
    }
}
