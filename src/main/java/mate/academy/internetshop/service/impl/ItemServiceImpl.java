package mate.academy.internetshop.service.impl;

import mate.academy.internetshop.dao.ItemDao;
import mate.academy.internetshop.factory.Factory;
import mate.academy.internetshop.lib.annotation.Inject;
import mate.academy.internetshop.lib.annotation.Service;
import mate.academy.internetshop.model.Item;
import mate.academy.internetshop.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService {
    @Inject
    private ItemDao itemDao;

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
    public Item deleteByItem(Item item) {
        return itemDao.deleteByItem(item);
    }
}
