package mate.academy.internetshop.service;

import java.util.List;
import java.util.Optional;
import mate.academy.internetshop.exceptions.AuthenticationException;
import mate.academy.internetshop.model.Order;
import mate.academy.internetshop.model.User;

public interface UserService {
    User create(User user);

    User get(Long id);

    User update(User user);

    User deleteById(Long id);

    User deleteByUser(User user);

    List<User> getAll();

    List<Order> getOrders(Long userId);

    Order deleteOrder(Long userId, Long orderId);

    User login(String login, String password) throws AuthenticationException;

    Optional<User> getByToken(String token);

}
