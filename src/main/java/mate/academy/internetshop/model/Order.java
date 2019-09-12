package mate.academy.internetshop.model;

import java.util.List;
import mate.academy.internetshop.lib.IdGenerator;

public class Order {
    private final Long id;
    private final Long userId;
    private final List<Item> items;

    public Order(List<Item> items, Long userId) {
        id = IdGenerator.getNextOrderId();
        this.items = items;
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public List<Item> getItems() {
        return items;
    }

    public Long getUserId() {
        return userId;
    }
}
