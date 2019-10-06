package mate.academy.internetshop.service.impl;

import java.util.List;
import java.util.Optional;
import mate.academy.internetshop.dao.UserDao;
import mate.academy.internetshop.exceptions.AuthenticationException;
import mate.academy.internetshop.exceptions.RegistrationException;
import mate.academy.internetshop.lib.annotation.Inject;
import mate.academy.internetshop.lib.annotation.Service;
import mate.academy.internetshop.model.User;
import mate.academy.internetshop.service.UserService;
import mate.academy.internetshop.util.HashUtil;

@Service
public class UserServiceImpl implements UserService {
    @Inject
    private static UserDao userDao;

    @Override
    public User create(User user) throws RegistrationException {
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
