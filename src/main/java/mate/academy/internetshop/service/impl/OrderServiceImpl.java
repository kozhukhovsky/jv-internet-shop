package mate.academy.internetshop.service.impl;

import java.util.List;
import mate.academy.internetshop.dao.OrderDao;
import mate.academy.internetshop.dao.UserDao;
import mate.academy.internetshop.factory.Factory;
import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.lib.Service;
import mate.academy.internetshop.model.Order;
import mate.academy.internetshop.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {
    @Inject
    private OrderDao orderDao = Factory.getOrderDao();
    @Inject
    private UserDao userDao = Factory.getUserDao();

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
    public Order deleteByOrder(Order order) {
        return orderDao.deleteByOrder(order);
    }

    @Override
    public Order completeOrder(List items, Long userId) {
        Order order = new Order(items, userId);
        orderDao.create(order);
        userDao.get(userId).getOrders().add(order);
        return order;
    }


}
