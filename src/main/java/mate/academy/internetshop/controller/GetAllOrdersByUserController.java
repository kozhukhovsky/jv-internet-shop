package mate.academy.internetshop.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mate.academy.internetshop.lib.annotation.Inject;
import mate.academy.internetshop.model.Order;
import mate.academy.internetshop.service.UserService;

@WebServlet("/servlet/orders")
public class GetAllOrdersByUserController extends HttpServlet {
    @Inject
    private static UserService userService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Long userId = (Long) req.getSession(true).getAttribute("userId");
        List<Order> orders = userService.getOrders(userId);
        req.setAttribute("orders", orders);

        req.getRequestDispatcher("/WEB-INF/views/orders.jsp").forward(req, resp);
    }
}
