package mate.academy.internetshop.model;

import java.util.ArrayList;
import java.util.List;
import mate.academy.internetshop.lib.IdGenerator;

public class User {
    private final Long id;
    private final List<Order> orders;

    public User() {
        id = IdGenerator.getNextUserId();
        orders = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public List<Order> getOrders() {
        return orders;
    }

}
