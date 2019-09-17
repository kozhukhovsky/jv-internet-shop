package mate.academy.internetshop.model;

import java.util.List;
import mate.academy.internetshop.lib.IdGenerator;

public class Order {
    private Long id;
    private Long userId;
    private List<Item> items;

    public Order(List<Item> items, Long userId) {
        id = IdGenerator.getNextOrderId();
        this.items = items;
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
