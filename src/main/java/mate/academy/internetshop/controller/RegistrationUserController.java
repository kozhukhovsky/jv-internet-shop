package mate.academy.internetshop.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import mate.academy.internetshop.exceptions.RegistrationException;
import mate.academy.internetshop.lib.annotation.Inject;
import mate.academy.internetshop.model.Role;
import mate.academy.internetshop.model.User;
import mate.academy.internetshop.service.RoleService;
import mate.academy.internetshop.service.UserService;
import mate.academy.internetshop.util.HashUtil;
import org.apache.log4j.Logger;

@WebServlet("/registration")
public class RegistrationUserController extends HttpServlet {
    private static Logger logger = Logger.getLogger(RegistrationUserController.class);

    @Inject
    private static UserService userService;
    @Inject
    private static RoleService roleService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/registration.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        User user = new User();
        user.setName(req.getParameter("user_name"));
        user.setLogin(req.getParameter("user_login"));
        user.setPassword(req.getParameter("user_password"));
        Role role = roleService.getByName("USER");
        user.addRole(role);
        try {
            userService.create(user);
        } catch (RegistrationException e) {
            req.setAttribute("errorMsg", "Account already exists. Try Sign In. ");
            req.getRequestDispatcher("/WEB-INF/views/registration.jsp").forward(req, resp);
            logger.error(e);
        }
        HttpSession session = req.getSession(true);
        session.setAttribute("userId", user.getId());
        Cookie cookie = new Cookie("MATE", user.getToken());
        resp.addCookie(cookie);

        resp.sendRedirect(req.getContextPath() + "/servlet/createBucket");
    }
}
