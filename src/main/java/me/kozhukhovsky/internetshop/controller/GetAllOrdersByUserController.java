package me.kozhukhovsky.internetshop.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import me.kozhukhovsky.internetshop.lib.annotation.Inject;
import me.kozhukhovsky.internetshop.model.Order;
import me.kozhukhovsky.internetshop.service.OrderService;

@WebServlet("/servlet/orders")
public class GetAllOrdersByUserController extends HttpServlet {
    @Inject
    private static OrderService orderService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Long userId = (Long) req.getSession(true).getAttribute("userId");
        List<Order> orders = orderService.getOrdersByUserId(userId);
        req.setAttribute("orders", orders);

        req.getRequestDispatcher("/WEB-INF/views/orders.jsp").forward(req, resp);
    }
}
