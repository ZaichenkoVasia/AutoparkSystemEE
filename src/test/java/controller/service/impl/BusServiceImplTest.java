//package controller.service.impl;
//
//import controller.exception.ServiceLayerRuntimeException;
//import domain.Bus;
//import model.dao.impl.BusDAOImpl;
//import model.exception.DatabaseRuntimeException;
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//
//import static org.junit.Assert.*;
//import static org.mockito.Mockito.*;
//
//public class BusServiceImplTest {
//
//    private BusDAOImpl busDAO;
//    private BusServiceImpl busService;
//
//    @Before
//    public void setUp() throws Exception {
//        busDAO = mock(BusDAOImpl.class);
//        busService = new BusServiceImpl(busDAO);
//    }
//
//    @After
//    public void tearDown() throws Exception {
//    }
//
//    @Test
//    public void findFreeBuses() throws DatabaseRuntimeException, ServiceLayerRuntimeException {
//        when(busDAO.findFreeBuses()).thenReturn(Arrays.asList(new Bus.BusBuilder().setId(1).createBus()));
//        assertEquals(1, busService.findFreeBuses().size());
//        verify(busDAO, atLeast(1)).findFreeBuses();
//    }
////
////    @Test
////    public void countBusesOnRouteById() throws DatabaseRuntimeException, ServiceLayerRuntimeException {
////        when(busDAO.countBusesOnRouteById(anyInt(), any())).thenReturn(5);
////        assertEquals(new Integer(5), busService.countBusesOnRouteById(anyInt(), any()));
////        verify(busDAO, atLeast(1)).countBusesOnRouteById(anyInt(), any());
////    }
//
//    @Test
//    public void findBusesByIdRoute() throws DatabaseRuntimeException, ServiceLayerRuntimeException {
//        when(busDAO.findBusesByIdRoute(anyInt())).thenReturn(new ArrayList<>());
//        assertEquals(0, busService.findBusesByIdRoute(anyInt()).size());
//        verify(busDAO, atLeast(1)).findBusesByIdRoute(anyInt());
//    }
//
//    @Test
//    public void isElementValid() {
//    }
//}