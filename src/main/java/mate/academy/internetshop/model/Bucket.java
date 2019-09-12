package mate.academy.internetshop.model;

import java.util.ArrayList;
import java.util.List;
import mate.academy.internetshop.lib.IdGenerator;

public class Bucket {
    private final Long id;
    private final Long userId;
    private final List<Item> items;

    public Bucket(Long userId) {
        id = IdGenerator.getNextBucketId();
        items = new ArrayList<>();
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
