package mate.academy.internetshop.model;

import java.util.List;
import mate.academy.internetshop.lib.IdGenerator;

public class User {
    private final Long id;
    private List<Order> orders;
    private Long bucketId;

    public User() {
        id = IdGenerator.getNextUserId();
    }

    public Long getId() {
        return id;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public Long getBucketId() {
        return bucketId;
    }

    public void setBucketId(Long bucketId) {
        this.bucketId = bucketId;
    }
}
