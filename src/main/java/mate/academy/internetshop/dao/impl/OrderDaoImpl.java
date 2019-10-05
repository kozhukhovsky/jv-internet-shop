package mate.academy.internetshop.dao.impl;

import java.util.List;
import java.util.NoSuchElementException;
import mate.academy.internetshop.dao.OrderDao;
import mate.academy.internetshop.db.Storage;
import mate.academy.internetshop.lib.annotation.Dao;
import mate.academy.internetshop.model.Order;

@Dao
public class OrderDaoImpl implements OrderDao {
    @Override
    public Order create(Order order) {
        Storage.orders.add(order);
        return order;
    }

    @Override
    public Order get(Long id) {
        return Storage.orders
            .stream()
            .filter(order -> order.getId().equals(id))
            .findFirst()
            .orElseThrow(() -> new NoSuchElementException("Can't find order with id " + id));
    }

    @Override
    public Order update(Order order) {
        for (int i = 0; i < Storage.orders.size(); i++) {
            if (Storage.orders.get(i).getId().equals(order.getId())) {
                Storage.orders.set(i, order);
                return order;
            }
        }
        throw new NoSuchElementException();
    }

    @Override
    public Order deleteById(Long id) {
        Order deletedOrder = get(id);
        Storage.orders.removeIf(order -> order.getId().equals(id));
        return deletedOrder;
    }

    @Override
    public List<Order> getOrders(Long userId) {
        return null;
    }

    @Override
    public Order deleteOrder(Long userId, Long orderId) {
        return null;
    }
}
