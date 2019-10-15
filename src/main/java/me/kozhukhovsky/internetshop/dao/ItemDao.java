package me.kozhukhovsky.internetshop.dao;

import java.util.List;
import me.kozhukhovsky.internetshop.model.Item;

public interface ItemDao {
    Item create(Item item);

    Item get(Long id);

    Item update(Item item);

    Item deleteById(Long id);

    List<Item> getAll();
}
