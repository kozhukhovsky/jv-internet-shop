package me.kozhukhovsky.internetshop.dao;

import java.util.List;
import me.kozhukhovsky.internetshop.model.Order;

public interface OrderDao {
    Order create(Order order);

    Order get(Long id);

    Order update(Order order);

    Order deleteById(Long id);

    List<Order> getOrdersByUserId(Long userId);
}
