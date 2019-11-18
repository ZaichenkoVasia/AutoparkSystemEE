package controller.command.impl;

import controller.command.Command;
import controller.constants.FrontConstants;
import controller.context.ApplicationContextInjector;
import controller.exception.ServiceLayerException;
import controller.service.*;
import controller.service.impl.BusStationServiceImpl;
import controller.constants.PathJSP;
import domain.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AccountCommand implements Command {

    private BusStationService busStationService;
    private AdminService adminService;

    public AccountCommand(BusStationService busStationService, AdminService adminService) {
        this.busStationService = busStationService;
        this.adminService = adminService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceLayerException{
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(FrontConstants.USER);
        if (user == null){
            return PathJSP.LOGIN_PAGE;
        }
        if (user.getRole().equals(FrontConstants.ADMIN)){
            Admin admin = adminService.getAdminByUserId(user.getId());
            request.setAttribute(FrontConstants.ADMIN, admin);
            return PathJSP.ADMIN_ACCOUNT_PAGE;
        }else {
            Driver driver = busStationService.getDriverAccountDataByUserId(user.getId());
            request.setAttribute(FrontConstants.DRIVER, driver);
            return PathJSP.DRIVER_ACCOUNT_PAGE;
        }
    }
}
