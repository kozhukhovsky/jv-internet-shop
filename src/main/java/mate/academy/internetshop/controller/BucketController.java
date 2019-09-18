package mate.academy.internetshop.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mate.academy.internetshop.lib.annotation.Inject;
import mate.academy.internetshop.model.Bucket;
import mate.academy.internetshop.model.Item;
import mate.academy.internetshop.service.BucketService;

@WebServlet("/bucket")
public class BucketController extends HttpServlet {
    private static final long TEMP_USER_ID = 0L;

    @Inject
    private static BucketService bucketService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Bucket bucket = bucketService.get(TEMP_USER_ID);
        List<Item> items = bucketService.getAllItems(bucket.getId());
        req.setAttribute("items", items);
        req.setAttribute("bucket_id", bucket.getId());

        req.getRequestDispatcher("WEB-INF/views/bucket.jsp").forward(req, resp);
    }
}
