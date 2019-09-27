package mate.academy.internetshop.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mate.academy.internetshop.lib.annotation.Inject;
import mate.academy.internetshop.model.Bucket;
import mate.academy.internetshop.model.Role;
import mate.academy.internetshop.model.User;
import mate.academy.internetshop.service.BucketService;
import mate.academy.internetshop.service.UserService;

@WebServlet("/inject")
public class InjectDataController extends HttpServlet {
    @Inject
    private static UserService userService;

    @Inject
    private static BucketService bucketService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        User user = new User();
        user.setName("Jack");
        user.setLogin("user");
        user.setPassword("123");
        user.addRole(Role.of("USER"));
        userService.create(user);
        Bucket usersBucket = new Bucket(user.getId());
        bucketService.create(usersBucket);

        User admin = new User();
        admin.setName("Mark");
        admin.setLogin("admin");
        admin.setPassword("123");
        admin.addRole(Role.of("ADMIN"));
        userService.create(admin);
        Bucket adminsBucket = new Bucket(admin.getId());
        bucketService.create(adminsBucket);

        resp.sendRedirect(req.getContextPath() + "/");
    }
}
