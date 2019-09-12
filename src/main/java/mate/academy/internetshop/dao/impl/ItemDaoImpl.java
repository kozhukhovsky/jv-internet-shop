package mate.academy.internetshop.dao.impl;

import java.util.NoSuchElementException;
import mate.academy.internetshop.dao.ItemDao;
import mate.academy.internetshop.db.Storage;
import mate.academy.internetshop.lib.Dao;
import mate.academy.internetshop.model.Item;

@Dao
public class ItemDaoImpl implements ItemDao {
    @Override
    public Item create(Item item) {
        Storage.items.add(item);
        return item;
    }

    @Override
    public Item get(Long id) {
        return Storage.items
            .stream()
            .filter(item -> item.getId().equals(id))
            .findFirst()
            .orElseThrow(() -> new NoSuchElementException("Can't find item with id " + id));
    }

    @Override
    public Item update(Item item) {
        for (int i = 0; i < Storage.items.size(); i++) {
            if (Storage.items.get(i).getId().equals(item.getId())) {
                Storage.items.set(i, item);
                return item;
            }
        }
        throw new NoSuchElementException();
    }

    @Override
    public void deleteById(Long id) {
        Storage.items.removeIf(item -> item.getId().equals(id));
    }

    @Override
    public void deleteByItem(Item item) {
        Storage.items.removeIf(sourceItem -> sourceItem.getId().equals(item.getId()));
    }
}
