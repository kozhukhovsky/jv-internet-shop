package me.kozhukhovsky.internetshop.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import me.kozhukhovsky.internetshop.lib.annotation.Inject;
import me.kozhukhovsky.internetshop.model.Bucket;
import me.kozhukhovsky.internetshop.model.User;
import me.kozhukhovsky.internetshop.service.BucketService;
import me.kozhukhovsky.internetshop.service.UserService;

@WebServlet("/servlet/createBucket")
public class CreateBucketController extends HttpServlet {
    @Inject
    private static BucketService bucketService;

    @Inject
    private static UserService userService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Long userId = (Long) req.getSession(true).getAttribute("userId");
        User user = userService.get(userId);
        Bucket bucket = new Bucket(user);
        bucketService.create(bucket);

        resp.sendRedirect(req.getContextPath() + "/");
    }
}
