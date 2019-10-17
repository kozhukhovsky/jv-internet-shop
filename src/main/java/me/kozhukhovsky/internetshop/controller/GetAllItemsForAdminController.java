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
import me.kozhukhovsky.internetshop.service.ItemService;

@WebServlet("/servlet/admin/items")
public class GetAllItemsForAdminController extends HttpServlet {
    @Inject
    private static ItemService itemService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        List<Item> items = itemService.getAll();
        req.setAttribute("items", items);

        req.getRequestDispatcher("/WEB-INF/views/editItems.jsp").forward(req, resp);
    }
}
