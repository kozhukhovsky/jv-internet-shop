package me.kozhukhovsky.internetshop.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import me.kozhukhovsky.internetshop.lib.annotation.Inject;
import me.kozhukhovsky.internetshop.model.Item;
import me.kozhukhovsky.internetshop.model.Order;
import me.kozhukhovsky.internetshop.service.OrderService;

@WebServlet("/servlet/itemsFromOrder")
public class GetAllItemsFromOrderController extends HttpServlet {
    @Inject
    private static OrderService orderService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Long orderId = Long.valueOf(req.getParameter("order_id"));
        Order order = orderService.get(orderId);
        List<Item> items = order.getItems();
        req.setAttribute("items", items);

        req.getRequestDispatcher("/WEB-INF/views/itemsFromBucket.jsp").forward(req, resp);
    }
}
