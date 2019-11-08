package service.bus;

import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import project.model.dao.BusDao;
import project.model.domain.Bus;
import project.model.entity.BusEntity;
import project.model.exception.InvalidCreationRuntimeException;
import project.model.service.impl.BusServiceImpl;
import project.model.service.mapper.BusMapper;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class BusServiceImplTest {
    private static final Bus bus = Bus.builder().withId(1).build();
    private static final List<BusEntity> entities = Arrays.asList(
            BusEntity.builder().withId(1).build(),
            BusEntity.builder().withId(2).build());
    private static final List<Bus> buses = Arrays.asList(bus, bus);

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Mock
    private BusDao busDao;

    @Mock
    private BusMapper mapper;

    @InjectMocks
    private BusServiceImpl service;

    @After
    public void resetMock() {
        reset(busDao, mapper);
    }

    @Test
    public void shouldCreateBus() {
        when(mapper.mapBusToBusEntity(any(Bus.class))).thenReturn(entities.get(1));
        when(busDao.save(any(BusEntity.class))).thenReturn(true);

        assertTrue(service.createBus(bus));
    }

    @Test
    public void shouldThrowInvalidEntityCreationWithNullBus() {
        exception.expect(InvalidCreationRuntimeException.class);
        exception.expectMessage("bus is not valid");

        service.createBus(null);
    }

    @Test
    public void shouldShowAllBuss() {
        when(busDao.findAll(1,2)).thenReturn(entities);
        when(mapper.mapBusEntityToBus(any(BusEntity.class))).thenReturn(bus);

        List<Bus> actual = service.findAll(1,2);

        assertEquals(buses, actual);
    }

    @Test
    public void shouldReturnEmptyList() {
        when(busDao.findAll(1,2)).thenReturn(Collections.emptyList());

        List<Bus> actual = service.findAll(1,2);

        assertEquals(Collections.emptyList(), actual);
    }
}