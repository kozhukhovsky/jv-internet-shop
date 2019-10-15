package me.kozhukhovsky.internetshop.service;

import java.util.List;
import me.kozhukhovsky.internetshop.model.Item;

public interface ItemService {
    Item create(Item item);

    Item get(Long id);

    Item update(Item item);

    Item deleteById(Long id);

    List<Item> getAll();
}
