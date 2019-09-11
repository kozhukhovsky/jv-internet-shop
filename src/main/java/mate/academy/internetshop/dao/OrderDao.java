package mate.academy.internetshop.dao;

import mate.academy.internetshop.model.Bucket;
import mate.academy.internetshop.model.Order;

public interface OrderDao {
    Order create(Order order);

    Order get(Long id);

    Order update(Order order);

    void deleteById(Long id);

    void deleteByOrder(Order order);
}
