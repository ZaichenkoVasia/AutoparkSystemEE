package controller.command.impl.bus;

import controller.command.Command;
import controller.exception.ServiceLayerRuntimeException;
import model.domain.Admin;
import model.domain.Driver;
import model.domain.User;
import model.service.AdminService;
import model.service.BusStationService;

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
        if (user == null) {
            return "WEB-INF/jsp/login.jsp";
        }
        if (user.getRole().equals("admin")) {
            Admin admin = adminService.findAdminByUserId(user.getId());
            request.setAttribute("admin", admin);
            return "WEB-INF/jsp/admin/account.jsp";
        } else {
            Driver driver = busStationService.findDriverAccountDataByUserId(user.getId());
            request.setAttribute("driver", driver);
            return "WEB-INF/jsp/driver/account.jsp";
        }
    }
}
