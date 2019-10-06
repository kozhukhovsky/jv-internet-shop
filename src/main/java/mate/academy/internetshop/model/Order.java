package mate.academy.internetshop.model;

import java.util.List;

public class Order {
    private Long id;
    private Long userId;
    private List<Item> items;

    public Order() {
    }

    public Order(List<Item> items, Long userId) {
        this.items = items;
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
