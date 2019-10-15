package me.kozhukhovsky.internetshop.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import me.kozhukhovsky.internetshop.lib.annotation.Inject;
import me.kozhukhovsky.internetshop.model.Bucket;
import me.kozhukhovsky.internetshop.service.BucketService;
import me.kozhukhovsky.internetshop.service.OrderService;

@WebServlet("/servlet/completeOrder")
public class CompleteOrderController extends HttpServlet {
    @Inject
    private static OrderService orderService;
    @Inject
    private static BucketService bucketService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Long userId = (Long) req.getSession(true).getAttribute("userId");
        Bucket bucket = bucketService.get(userId);
        orderService.completeOrder(bucket.getItems(), userId);
        bucketService.clear(bucket.getId());

        resp.sendRedirect(req.getContextPath() + "/servlet/orders");
    }
}
