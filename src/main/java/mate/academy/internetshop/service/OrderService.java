package mate.academy.internetshop.service;

import mate.academy.internetshop.model.Order;

public interface OrderService {
    Order create(Order order);

    Order get(Long id);

    Order update(Order order);

    void deleteById(Long id);

    void deleteByOrder(Order order);
}
