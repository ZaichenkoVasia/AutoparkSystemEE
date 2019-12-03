package controller.command.impl.menu;

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
public class ContactsCommandTest {
    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse responce;

    @InjectMocks
    private ContactsCommand command;

    @After
    public void resetMock() {
        reset(request, responce);
    }

    @Test
    public void executeShouldReturnContactsPage() {
        String expected = "WEB-INF/jsp/contacts.jsp";
        String actual = command.execute(request, responce);

        assertThat(expected, is(actual));
    }
}
