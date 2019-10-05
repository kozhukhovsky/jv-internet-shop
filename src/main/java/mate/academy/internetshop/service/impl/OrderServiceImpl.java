package mate.academy.internetshop.service.impl;

import java.util.ArrayList;
import java.util.List;
import mate.academy.internetshop.dao.OrderDao;
import mate.academy.internetshop.dao.UserDao;
import mate.academy.internetshop.lib.annotation.Inject;
import mate.academy.internetshop.lib.annotation.Service;
import mate.academy.internetshop.model.Order;
import mate.academy.internetshop.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {
    @Inject
    private static OrderDao orderDao;
    @Inject
    private static UserDao userDao;

    @Override
    public Order create(Order order) {
        return orderDao.create(order);
    }

    @Override
    public Order get(Long id) {
        return orderDao.get(id);
    }

    @Override
    public Order update(Order order) {
        return orderDao.update(order);
    }

    @Override
    public Order deleteById(Long id) {
        return orderDao.deleteById(id);
    }

    @Override
    public Order completeOrder(List items, Long userId) {
        Order order = new Order(new ArrayList<>(items), userId);
        orderDao.create(order);
        userDao.get(userId).getOrders().add(order);
        return order;
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
