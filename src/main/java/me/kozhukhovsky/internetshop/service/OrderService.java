package me.kozhukhovsky.internetshop.service;

import java.util.List;
import me.kozhukhovsky.internetshop.model.Order;

public interface OrderService {
    Order create(Order order);

    Order get(Long id);

    Order update(Order order);

    Order deleteById(Long id);

    Order completeOrder(List items, Long userId);

    List<Order> getOrdersByUserId(Long userId);
}
