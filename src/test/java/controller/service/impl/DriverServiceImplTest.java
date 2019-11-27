//package controller.service.impl;
//
//import controller.exception.ServiceLayerRuntimeException;
//import domain.Driver;
//import model.dao.impl.DriverDAOImpl;
//import model.exception.DatabaseRuntimeException;
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//
//import java.util.Arrays;
//
//import static org.junit.Assert.assertEquals;
//import static org.mockito.Matchers.any;
//import static org.mockito.Matchers.anyInt;
//import static org.mockito.Mockito.*;
//
//public class DriverServiceImplTest {
//
//    private DriverDAOImpl driverDAO;
//    private DriverServiceImpl driverService;
//
//    @Before
//    public void setUp() throws Exception {
//        driverDAO = mock(DriverDAOImpl.class);
//        driverService = new DriverServiceImpl(driverDAO);
//    }
//
//    @After
//    public void tearDown() throws Exception {
//    }
//
////    @Test
////    public void findDriverByUserId() throws DatabaseRuntimeException, ServiceLayerRuntimeException {
////        when(driverDAO.findDriverByUserId(anyInt(), any())).thenReturn(new Driver.DriverBuilder().setName("User").createDriver());
////        assertEquals("User", driverService.findDriverByUserId(anyInt(), any()).getName());
////        verify(driverDAO, atLeast(1)).findDriverByUserId(anyInt(), any());
////    }
//
//    @Test
//    public void findFreeDrivers() throws DatabaseRuntimeException, ServiceLayerRuntimeException {
//        when(driverDAO.findFreeDrivers()).thenReturn(Arrays.asList(new Driver(), new Driver()));
//        assertEquals(2, driverService.findFreeDrivers().size());
//        verify(driverDAO, atLeast(1)).findFreeDrivers();
//    }
//
//    @Test
//    public void isElementValid() {
//    }
//}