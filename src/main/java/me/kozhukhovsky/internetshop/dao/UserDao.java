package me.kozhukhovsky.internetshop.dao;

import java.util.List;
import java.util.Optional;
import me.kozhukhovsky.internetshop.exceptions.AuthenticationException;
import me.kozhukhovsky.internetshop.exceptions.RegistrationException;
import me.kozhukhovsky.internetshop.model.User;

public interface UserDao {
    User create(User user) throws RegistrationException;

    User get(Long id);

    User update(User user);

    User deleteById(Long id);

    List<User> getAll();

    User login(String login, String password) throws AuthenticationException;

    Optional<User> getByToken(String token);

    User getByLogin(String login) throws AuthenticationException;
}
