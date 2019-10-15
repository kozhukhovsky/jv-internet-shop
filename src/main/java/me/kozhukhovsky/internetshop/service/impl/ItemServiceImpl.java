package me.kozhukhovsky.internetshop.service.impl;

import java.util.List;
import me.kozhukhovsky.internetshop.dao.ItemDao;
import me.kozhukhovsky.internetshop.lib.annotation.Inject;
import me.kozhukhovsky.internetshop.lib.annotation.Service;
import me.kozhukhovsky.internetshop.model.Item;
import me.kozhukhovsky.internetshop.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService {
    @Inject
    private static ItemDao itemDao;

    @Override
    public Item create(Item item) {
        return itemDao.create(item);
    }

    @Override
    public Item get(Long id) {
        return itemDao.get(id);
    }

    @Override
    public Item update(Item item) {
        return itemDao.update(item);
    }

    @Override
    public Item deleteById(Long id) {
        return itemDao.deleteById(id);
    }

    @Override
    public List<Item> getAll() {
        return itemDao.getAll();
    }
}
