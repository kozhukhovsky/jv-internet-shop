package mate.academy.internetshop.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mate.academy.internetshop.lib.annotation.Inject;
import mate.academy.internetshop.service.UserService;

@WebServlet("/deleteOrder")
public class DeleteOrderController extends HttpServlet {
    private static final long TEMP_USER_ID = 0L;
    @Inject
    private static UserService userService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Long orderId = Long.valueOf(req.getParameter("order_id"));
        userService.deleteOrder(TEMP_USER_ID, orderId);

        resp.sendRedirect(req.getContextPath() + "/orders");
    }
}
