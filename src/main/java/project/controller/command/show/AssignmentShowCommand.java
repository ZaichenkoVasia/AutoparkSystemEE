package project.controller.command.show;

import project.controller.command.Command;
import project.model.domain.Assignment;
import project.model.service.AssignmentService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class AssignmentShowCommand implements Command {
    private AssignmentService assignmentService;

    public AssignmentShowCommand(AssignmentService assignmentService) {
        this.assignmentService = assignmentService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        int currentPage = Integer.valueOf(request.getParameter("currentPage"));
        int recordsPerPage = Integer.valueOf(request.getParameter("recordsPerPage"));

        List<Assignment> assignments = assignmentService.findAll(currentPage, recordsPerPage);

        request.setAttribute("assignments", assignments);

        int rows = assignmentService.getNumberOfRows();

        int nOfPages = rows / recordsPerPage;

        if (nOfPages % recordsPerPage > 0) {
            nOfPages++;
        }

        request.setAttribute("noOfPages", nOfPages);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("recordsPerPage", recordsPerPage);
        final String command = request.getParameter("commandShow");
        request.setAttribute("showAssignments", command);

        return "listAssignments.jsp";
    }
}
