package mate.academy.internetshop;

import mate.academy.internetshop.lib.annotation.Inject;
import mate.academy.internetshop.model.Bucket;
import mate.academy.internetshop.model.Item;
import mate.academy.internetshop.model.Order;
import mate.academy.internetshop.model.User;
import mate.academy.internetshop.service.BucketService;
import mate.academy.internetshop.service.ItemService;
import mate.academy.internetshop.service.OrderService;
import mate.academy.internetshop.service.UserService;

public class Main {
    @Inject
    private BucketService bucketService;
    @Inject
    private ItemService itemService;
    @Inject
    private UserService userService;
    @Inject
    private OrderService orderService;

    public static void main(String[] args) {
        Main main = new Main();
        main.testCorrectOrdersComplete();
    }

    private void testCorrectOrdersComplete() {
        Item phone = new Item("iPhone", 1000.0);
        Item notebook = new Item("MacBook", 2500.0);
        Item pad = new Item("iPad", 1200.0);
        itemService.create(phone);
        itemService.create(notebook);
        itemService.create(pad);

        User user = new User();
        userService.create(user);

        Bucket bucket = new Bucket(user.getId());
        bucketService.create(bucket);
        bucketService.addItem(bucket.getId(), phone.getId());
        bucketService.addItem(bucket.getId(), notebook.getId());
        bucketService.addItem(bucket.getId(), pad.getId());

        orderService.completeOrder(bucket.getItems(), bucket.getUserId());

        bucketService.clear(bucket.getId());

        Item phone2 = new Item("Pixel", 900.0);
        Item notebook2 = new Item("ChromeBook", 1100.0);
        Item pad2 = new Item("ChromePad", 700.0);
        itemService.create(phone2);
        itemService.create(notebook2);
        itemService.create(pad2);
        bucketService.addItem(bucket.getId(), pad2.getId());
        bucketService.addItem(bucket.getId(), notebook2.getId());
        bucketService.addItem(bucket.getId(), phone2.getId());
        orderService.completeOrder(bucket.getItems(), bucket.getUserId());

        for (Order order : user.getOrders()) {
            for (Item item : order.getItems()) {
                System.out.println(item.getName());
            }
            System.out.println("===============");
        }
    }
}
