package mate.academy.internetshop.service.impl;

import java.util.List;
import mate.academy.internetshop.dao.UserDao;
import mate.academy.internetshop.lib.annotation.Inject;
import mate.academy.internetshop.lib.annotation.Service;
import mate.academy.internetshop.model.Order;
import mate.academy.internetshop.model.User;
import mate.academy.internetshop.service.UserService;

@Service
public class UserServiceImpl implements UserService {
    @Inject
    private static UserDao userDao;

    @Override
    public User create(User user) {
        return userDao.create(user);
    }

    @Override
    public User get(Long id) {
        return userDao.get(id);
    }

    @Override
    public User update(User user) {
        return userDao.update(user);
    }

    @Override
    public User deleteById(Long id) {
        return userDao.deleteById(id);
    }

    @Override
    public User deleteByUser(User user) {
        return userDao.deleteByUser(user);
    }

    @Override
    public List<Order> getOrders(Long userId) {
        return userDao.get(userId).getOrders();
    }

    @Override
    public Order deleteOrder(Long userId, Long orderId) {
        List<Order> orders = userDao.get(userId).getOrders();
        Order deletedOrder = orders.get(orderId.intValue());
        orders.remove(deletedOrder);
        return deletedOrder;
    }

    @Override
    public List<User> getAll() {
        return userDao.getAll();
    }
}
