package me.kozhukhovsky.internetshop.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import me.kozhukhovsky.internetshop.lib.annotation.Inject;
import me.kozhukhovsky.internetshop.service.UserService;

@WebServlet("/")
public class IndexController extends HttpServlet {
    @Inject
    private static UserService userService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        if (userService.getAll().size() > 0) {
            req.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(req, resp);
        } else {
            resp.sendRedirect(req.getContextPath() + "/inject");
        }
    }
}
