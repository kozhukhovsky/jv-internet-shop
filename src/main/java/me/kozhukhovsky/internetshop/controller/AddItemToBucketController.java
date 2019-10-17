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
import me.kozhukhovsky.internetshop.service.ItemService;

@WebServlet("/servlet/addItemToBucket")
public class AddItemToBucketController extends HttpServlet {
    @Inject
    private static BucketService bucketService;

    @Inject
    private static ItemService itemService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Long userId = (Long) req.getSession(true).getAttribute("userId");
        Long itemId = Long.valueOf(req.getParameter("item_id"));
        Bucket bucket = bucketService.get(userId);
        bucketService.addItem(bucket.getId(), itemId);

        resp.sendRedirect(req.getContextPath() + "/servlet/items");
    }
}
