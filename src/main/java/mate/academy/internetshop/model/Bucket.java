package mate.academy.internetshop.model;

import java.util.List;
import mate.academy.internetshop.lib.IdGenerator;

public class Bucket {
    private final Long id;
    private List<Item> items;
    private Long orderId;

    public Bucket() {
        id = IdGenerator.getNextBucketId();
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

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
}
