package mate.academy.internetshop.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mate.academy.internetshop.lib.annotation.Inject;
import mate.academy.internetshop.model.User;
import mate.academy.internetshop.service.UserService;

@WebServlet("/registration")
public class RegistrationUserController extends HttpServlet {
    @Inject
    private static UserService userService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/views/registration.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        User newUser = new User();
        newUser.setName(req.getParameter("user_name"));
        newUser.setLogin(req.getParameter("user_login"));
        newUser.setPassword(req.getParameter("user_password"));
        userService.create(newUser);

        resp.sendRedirect(req.getContextPath() + "/createBucket?user_id=" + newUser.getId());
    }
}
