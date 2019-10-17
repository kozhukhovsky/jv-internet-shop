package me.kozhukhovsky.internetshop.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import me.kozhukhovsky.internetshop.lib.annotation.Inject;
import me.kozhukhovsky.internetshop.model.User;
import me.kozhukhovsky.internetshop.service.UserService;

@WebServlet("/servlet/admin/users")
public class GetAllUsersController extends HttpServlet {
    @Inject
    private static UserService userService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        List<User> users = userService.getAll();
        req.setAttribute("users", users);

        req.getRequestDispatcher("/WEB-INF/views/users.jsp").forward(req, resp);
    }
}
