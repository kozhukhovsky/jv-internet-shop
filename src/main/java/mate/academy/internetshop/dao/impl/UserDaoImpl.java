package mate.academy.internetshop.dao.impl;

import java.util.NoSuchElementException;
import mate.academy.internetshop.dao.UserDao;
import mate.academy.internetshop.db.Storage;
import mate.academy.internetshop.lib.annotation.Dao;
import mate.academy.internetshop.model.User;

@Dao
public class UserDaoImpl implements UserDao {
    @Override
    public User create(User user) {
        Storage.users.add(user);
        return user;
    }

    @Override
    public User get(Long id) {
        return Storage.users
            .stream()
            .filter(user -> user.getId().equals(id))
            .findFirst()
            .orElseThrow(() -> new NoSuchElementException("Can't find user with id " + id));
    }

    @Override
    public User update(User user) {
        for (int i = 0; i < Storage.users.size(); i++) {
            if (Storage.users.get(i).getId().equals(user.getId())) {
                Storage.users.set(i, user);
                return user;
            }
        }
        throw new NoSuchElementException();
    }

    @Override
    public User deleteById(Long id) {
        User deletedUser = get(id);
        Storage.users.removeIf(user -> user.getId().equals(id));
        return deletedUser;
    }

    @Override
    public User deleteByUser(User user) {
        Storage.users.removeIf(sourceUser -> sourceUser.getId().equals(user.getId()));
        return user;
    }
}
