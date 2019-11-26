package controller.command.impl;

import controller.command.Command;
import controller.exception.ServiceLayerRuntimeException;
import controller.service.*;
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
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceLayerRuntimeException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null){
            return "WEB-INF/jsp/login.jsp";
        }
        if (user.getRole().equals("admin")){
            Admin admin = adminService.getAdminByUserId(user.getId());
            request.setAttribute("admin", admin);
            return "WEB-INF/jsp/admin/account.jsp";
        }else {
            Driver driver = busStationService.getDriverAccountDataByUserId(user.getId());
            request.setAttribute("driver", driver);
            return "WEB-INF/jsp/driver/account.jsp";
        }
    }
}
