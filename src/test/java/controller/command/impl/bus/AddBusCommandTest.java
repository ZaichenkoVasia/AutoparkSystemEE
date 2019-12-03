package controller.command.impl.bus;

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
import static org.mockito.Mockito.reset;

@RunWith(MockitoJUnitRunner.class)
public class AddBusCommandTest {
    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse responce;

    @InjectMocks
    private AddBusCommand command;

    @After
    public void resetMock() {
        reset(request, responce);
    }

    @Test
    public void executeShouldReturnAddBusPage() {
        String expected = "WEB-INF/jsp/editing_pages/add_bus.jsp";
        String actual = command.execute(request, responce);

        assertThat(expected, is(actual));
    }
}
