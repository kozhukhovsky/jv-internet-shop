package me.kozhukhovsky.internetshop.service.impl;

import java.util.List;
import java.util.Optional;
import me.kozhukhovsky.internetshop.dao.UserDao;
import me.kozhukhovsky.internetshop.exceptions.AuthenticationException;
import me.kozhukhovsky.internetshop.exceptions.RegistrationException;
import me.kozhukhovsky.internetshop.lib.annotation.Inject;
import me.kozhukhovsky.internetshop.lib.annotation.Service;
import me.kozhukhovsky.internetshop.model.User;
import me.kozhukhovsky.internetshop.service.UserService;
import me.kozhukhovsky.internetshop.util.HashUtil;

@Service
public class UserServiceImpl implements UserService {
    @Inject
    private static UserDao userDao;

    @Override
    public User create(User user) throws RegistrationException {
        String password = user.getPassword();
        byte[] salt = HashUtil.getSalt();
        String hashedPassword = HashUtil.hashPassword(password, salt);
        user.setSalt(salt);
        user.setPassword(hashedPassword);
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
    public User login(String login, String password) throws AuthenticationException {
        User user = userDao.getByLogin(login);
        byte[] salt = user.getSalt();
        String hashedPassword = HashUtil.hashPassword(password, salt);
        return userDao.login(login, hashedPassword);
    }

    @Override
    public List<User> getAll() {
        return userDao.getAll();
    }

    @Override
    public Optional<User> getByToken(String token) {
        return userDao.getByToken(token);
    }
}
