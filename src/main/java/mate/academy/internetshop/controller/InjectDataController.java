package mate.academy.internetshop.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mate.academy.internetshop.exceptions.RegistrationException;
import mate.academy.internetshop.lib.annotation.Inject;
import mate.academy.internetshop.model.Bucket;
import mate.academy.internetshop.model.Role;
import mate.academy.internetshop.model.User;
import mate.academy.internetshop.service.BucketService;
import mate.academy.internetshop.service.RoleService;
import mate.academy.internetshop.service.UserService;
import mate.academy.internetshop.util.HashUtil;

@WebServlet("/inject")
public class InjectDataController extends HttpServlet {
    @Inject
    private static UserService userService;

    @Inject
    private static BucketService bucketService;

    @Inject
    private static RoleService roleService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Role userRole = roleService.getByName("USER");
        User user = new User();
        user.setName("Jack");
        user.setLogin("user");
        byte[] saltForUser = HashUtil.getSalt();
        user.setSalt(saltForUser);
        String userPassword = HashUtil.hashPassword("123", saltForUser);
        user.setPassword(userPassword);
        user.addRole(userRole);
        try {
            userService.create(user);
        } catch (RegistrationException e) {
            e.printStackTrace();
        }
        Bucket usersBucket = new Bucket(user.getId());
        bucketService.create(usersBucket);

        Role adminRole = roleService.getByName("ADMIN");
        User admin = new User();
        admin.setName("Mark");
        admin.setLogin("admin");
        byte[] saltForAdmin = HashUtil.getSalt();
        admin.setSalt(saltForAdmin);
        String adminPassword = HashUtil.hashPassword("123", saltForAdmin);
        admin.setPassword(adminPassword);
        admin.addRole(adminRole);
        try {
            userService.create(admin);
        } catch (RegistrationException e) {
            e.printStackTrace();
        }
        Bucket adminsBucket = new Bucket(admin.getId());
        bucketService.create(adminsBucket);

        resp.sendRedirect(req.getContextPath() + "/");
    }
}
