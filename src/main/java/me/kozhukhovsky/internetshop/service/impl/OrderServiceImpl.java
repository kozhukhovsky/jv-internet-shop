package me.kozhukhovsky.internetshop.service.impl;

import java.util.ArrayList;
import java.util.List;
import me.kozhukhovsky.internetshop.dao.OrderDao;
import me.kozhukhovsky.internetshop.dao.UserDao;
import me.kozhukhovsky.internetshop.lib.annotation.Inject;
import me.kozhukhovsky.internetshop.lib.annotation.Service;
import me.kozhukhovsky.internetshop.model.Order;
import me.kozhukhovsky.internetshop.model.User;
import me.kozhukhovsky.internetshop.service.OrderService;

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
        User user = userDao.get(userId);
        Order order = new Order(new ArrayList<>(items), user);
        orderDao.create(order);
        return order;
    }

    @Override
    public List<Order> getOrdersByUserId(Long userId) {
        return orderDao.getOrdersByUserId(userId);
    }
}
