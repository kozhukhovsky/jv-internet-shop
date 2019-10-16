package me.kozhukhovsky.internetshop.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import me.kozhukhovsky.internetshop.exceptions.RegistrationException;
import me.kozhukhovsky.internetshop.lib.annotation.Inject;
import me.kozhukhovsky.internetshop.model.Bucket;
import me.kozhukhovsky.internetshop.model.Role;
import me.kozhukhovsky.internetshop.model.User;
import me.kozhukhovsky.internetshop.service.BucketService;
import me.kozhukhovsky.internetshop.service.RoleService;
import me.kozhukhovsky.internetshop.service.UserService;
import org.apache.log4j.Logger;

@WebServlet("/inject")
public class InjectDataController extends HttpServlet {
    private static Logger logger = Logger.getLogger(InjectDataController.class);

    @Inject
    private static UserService userService;

    @Inject
    private static BucketService bucketService;

    @Inject
    private static RoleService roleService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Role adminRole = roleService.getByName("ADMIN");
        Role userRole = roleService.getByName("USER");

        User root = new User();
        root.setName("Anton");
        root.setLogin("root");
        root.setPassword("123");
        root.addRole(adminRole);
        root.addRole(userRole);
        try {
            userService.create(root);
        } catch (RegistrationException e) {
            logger.error("Can't inject user", e);
        }
        Bucket rootsBucket = new Bucket(root);
        bucketService.create(rootsBucket);

        User user = new User();
        user.setName("Jack");
        user.setLogin("user");
        user.setPassword("123");
        user.addRole(userRole);
        try {
            userService.create(user);
        } catch (RegistrationException e) {
            logger.error("Can't inject user", e);
        }
        Bucket usersBucket = new Bucket(user);
        bucketService.create(usersBucket);

        User admin = new User();
        admin.setName("Mark");
        admin.setLogin("admin");
        admin.setPassword("123");
        admin.addRole(adminRole);
        try {
            userService.create(admin);
        } catch (RegistrationException e) {
            logger.error("Can't inject user", e);
        }
        Bucket adminsBucket = new Bucket(admin);
        bucketService.create(adminsBucket);

        resp.sendRedirect(req.getContextPath() + "/");
    }
}
