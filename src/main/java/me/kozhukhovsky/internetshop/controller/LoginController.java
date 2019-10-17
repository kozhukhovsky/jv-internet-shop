package me.kozhukhovsky.internetshop.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import me.kozhukhovsky.internetshop.exceptions.AuthenticationException;
import me.kozhukhovsky.internetshop.lib.annotation.Inject;
import me.kozhukhovsky.internetshop.model.Role;
import me.kozhukhovsky.internetshop.model.User;
import me.kozhukhovsky.internetshop.service.UserService;
import org.apache.log4j.Logger;

@WebServlet("/login")
public class LoginController extends HttpServlet {
    private static final Logger logger = Logger.getLogger(LoginController.class);

    @Inject
    private static UserService userService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String login = req.getParameter("user_login");
        String password = req.getParameter("user_password");
        try {
            User user = userService.login(login, password);
            HttpSession session = req.getSession(true);
            session.setAttribute("name", user.getName());
            session.setAttribute("userId", user.getId());
            session.setAttribute("login", "true");
            for (Role role : user.getRoles()) {
                if (role.getName().equals("ADMIN")) {
                    session.setAttribute("admin", "true");
                } else {
                    session.setAttribute("user", "true");
                }
            }
            Cookie cookie = new Cookie("MATE", user.getToken());
            resp.addCookie(cookie);
            resp.sendRedirect(req.getContextPath() + "/");
        } catch (AuthenticationException e) {
            logger.error(e);
            req.setAttribute("error", "true");
            req.setAttribute("errorMsg", "Incorrect login or password. Try again.");
            req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
        }
    }
}
