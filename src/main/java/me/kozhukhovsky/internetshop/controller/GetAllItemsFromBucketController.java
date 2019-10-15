package me.kozhukhovsky.internetshop.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import me.kozhukhovsky.internetshop.lib.annotation.Inject;
import me.kozhukhovsky.internetshop.model.Bucket;
import me.kozhukhovsky.internetshop.model.Item;
import me.kozhukhovsky.internetshop.service.BucketService;

@WebServlet("/servlet/bucket")
public class GetAllItemsFromBucketController extends HttpServlet {
    @Inject
    private static BucketService bucketService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Long userId = (Long) req.getSession(true).getAttribute("userId");
        Bucket bucket = bucketService.get(userId);
        List<Item> items = bucket.getItems();
        req.setAttribute("items", items);
        req.setAttribute("bucket_id", bucket.getId());

        req.getRequestDispatcher("/WEB-INF/views/bucket.jsp").forward(req, resp);
    }
}
