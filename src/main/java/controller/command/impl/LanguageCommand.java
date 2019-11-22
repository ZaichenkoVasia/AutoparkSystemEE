package controller.command.impl;

import controller.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LanguageCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        String language = request.getParameter("lang");
        if (language.equals("en")){
            session.setAttribute("lang", "en_US");
        }else if (language.equals("ua")){
            session.setAttribute("lang","uk_UA");
        }else {
            session.setAttribute("lang", "ru_RU");
        }
        request.setAttribute("message", "language.changed");
        return "index.jsp";
    }
}
