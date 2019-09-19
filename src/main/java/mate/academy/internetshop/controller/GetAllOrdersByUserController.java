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

@WebServlet("/orders")
public class GetAllOrdersByUserController extends HttpServlet {
    private static final long TEMP_USER_ID = 0L;
    @Inject
    private static UserService userService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        List<Order> orders = userService.getOrders(TEMP_USER_ID);
        req.setAttribute("orders", orders);

        req.getRequestDispatcher("WEB-INF/views/orders.jsp").forward(req, resp);
    }
}
