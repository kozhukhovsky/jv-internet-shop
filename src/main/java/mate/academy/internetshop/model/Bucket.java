package mate.academy.internetshop.model;

import java.util.ArrayList;
import java.util.List;
import mate.academy.internetshop.lib.IdGenerator;

public class Bucket {
    private Long id;
    private Long userId;
    private List<Item> items;

    public Bucket(Long userId) {
        id = IdGenerator.getNextBucketId();
        items = new ArrayList<>();
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
