package mate.academy.internetshop.model;

import java.util.List;
import mate.academy.internetshop.lib.IdGenerator;

public class Order {
    private final Long id;
    private List<Item> items;
    private Long userId;

    public Order() {
        id = IdGenerator.getNextOrderId();
    }

    public Long getId() {
        return id;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
