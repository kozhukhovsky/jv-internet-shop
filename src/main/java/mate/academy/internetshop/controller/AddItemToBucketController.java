package mate.academy.internetshop.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mate.academy.internetshop.lib.annotation.Inject;
import mate.academy.internetshop.model.Bucket;
import mate.academy.internetshop.model.Item;
import mate.academy.internetshop.service.BucketService;
import mate.academy.internetshop.service.ItemService;

@WebServlet("/addItemToBucket")
public class AddItemToBucketController extends HttpServlet {
    private static final long TEMP_USER_ID = 0L;

    @Inject
    private static BucketService bucketService;

    @Inject
    private static ItemService itemService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Bucket bucket = bucketService.get(TEMP_USER_ID);
        Long itemId = Long.valueOf(req.getParameter("item_id"));
        Item item = itemService.get(itemId);
        bucket.getItems().add(item);

        resp.sendRedirect(req.getContextPath() + "/items");
    }
}
