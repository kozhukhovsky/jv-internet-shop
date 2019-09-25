package mate.academy.internetshop.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mate.academy.internetshop.lib.annotation.Inject;
import mate.academy.internetshop.service.UserService;

@WebServlet("/servlet/deleteOrder")
public class DeleteOrderController extends HttpServlet {
    @Inject
    private static UserService userService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Long userId = (Long) req.getSession(true).getAttribute("userId");
        Long orderId = Long.valueOf(req.getParameter("order_id"));
        userService.deleteOrder(userId, orderId);

        resp.sendRedirect(req.getContextPath() + "/servlet/orders");
    }
}
