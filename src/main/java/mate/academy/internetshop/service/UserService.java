package mate.academy.internetshop.service;

import mate.academy.internetshop.model.User;

public interface UserService {
    User create(User user);

    User get(Long id);

    User update(User user);

    void deleteById(Long id);

    void deleteByUser(User user);
}
