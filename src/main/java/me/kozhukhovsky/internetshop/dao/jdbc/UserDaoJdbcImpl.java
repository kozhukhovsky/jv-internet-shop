package me.kozhukhovsky.internetshop.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import me.kozhukhovsky.internetshop.dao.UserDao;
import me.kozhukhovsky.internetshop.exceptions.AuthenticationException;
import me.kozhukhovsky.internetshop.exceptions.RegistrationException;
import me.kozhukhovsky.internetshop.lib.annotation.Dao;
import me.kozhukhovsky.internetshop.model.Role;
import me.kozhukhovsky.internetshop.model.User;
import org.apache.log4j.Logger;

@Dao
public class UserDaoJdbcImpl extends AbstractDao<User> implements UserDao {
    private static Logger logger = Logger.getLogger(UserDaoJdbcImpl.class);

    private static final String SQL_INSERT_USER =
            "INSERT INTO user (name, login, password, token, salt) "
                    + "VALUES (?, ?, ?, ?, ?)";
    private static final String SQL_INSERT_ROLE_TO_USER = "INSERT INTO user_role VALUES(?, ?)";
    private static final String SQL_GET_USER_BY_ID = "SELECT * FROM user WHERE id=?";
    private static final String SQL_GET_ROLES_BY_USER_ID = "SELECT r.id, r.name FROM role r "
            + "INNER JOIN user_role ur ON r.id = ur.role_id WHERE user_id=?";
    private static final String SQL_UPDATE_USER = "UPDATE user SET name=?, login=?, password=?"
            + "WHERE id=?";
    private static final String SQL_DELETE_USER = "DELETE FROM user WHERE id=?";
    private static final String SQL_GET_ALL_USERS = "SELECT * FROM user";
    private static final String SQL_GET_USER_BY_LOGIN_AND_PASSWORD = "SELECT * FROM user "
            + "WHERE login=? AND password=?";
    private static final String SQL_GET_USER_BY_TOKEN = "SELECT * FROM user WHERE token=?";
    private static final String SQL_GET_USER_BY_LOGIN = "SELECT * FROM user WHERE login=?";

    public UserDaoJdbcImpl(Connection connection) {
        super(connection);
    }

    @Override
    public User create(User user) throws RegistrationException {
        try (PreparedStatement statement = connection.prepareStatement(SQL_INSERT_USER,
                PreparedStatement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getLogin());
            statement.setString(3, user.getPassword());
            statement.setString(4, user.getToken());
            statement.setBytes(5, user.getSalt());
            statement.executeUpdate();

            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                Long userId = generatedKeys.getLong(1);
                user.setId(userId);
            } else {
                throw new SQLException("Creating user failed, no ID obtained.");
            }

            Set<Role> roles = user.getRoles();
            for (Role role : roles) {
                setupRole(user.getId(), role.getId());
            }

        } catch (SQLException e) {
            logger.error("Can't create user id=" + user.getId(), e);
            throw new RegistrationException("User already exist.");
        }
        return user;
    }

    private void setupRole(Long userId, Long roleId) {
        try (PreparedStatement statement = connection.prepareStatement(SQL_INSERT_ROLE_TO_USER)) {
            statement.setLong(1, userId);
            statement.setLong(2, roleId);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Can't setup role id=" + roleId + "into user id=" + userId, e);
        }
    }

    @Override
    public User get(Long id) {
        try (PreparedStatement statement = connection.prepareStatement(SQL_GET_USER_BY_ID)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return getUser(resultSet);
            }
        } catch (SQLException e) {
            logger.error("Can't get user by id=" + id, e);
        }
        return null;
    }

    private User getUser(ResultSet resultSet) throws SQLException {
        User user = new User();
        Long userId = resultSet.getLong("id");
        String name = resultSet.getString("name");
        String login = resultSet.getString("login");
        String password = resultSet.getString("password");
        String token = resultSet.getString("token");
        byte[] salt = resultSet.getBytes("salt");
        Set<Role> roles = getRolesByUserId(userId);
        user.setId(userId);
        user.setName(name);
        user.setLogin(login);
        user.setPassword(password);
        user.setToken(token);
        user.setSalt(salt);
        user.setRoles(roles);
        return user;
    }

    private Set<Role> getRolesByUserId(Long id) {
        Set<Role> roles = new HashSet<>();
        try (PreparedStatement statement = connection.prepareStatement(SQL_GET_ROLES_BY_USER_ID)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Role role = new Role();
                Long roleId = resultSet.getLong("id");
                String roleName = resultSet.getString("name");
                role.setId(roleId);
                role.setName(roleName);
                roles.add(role);
            }
        } catch (SQLException e) {
            logger.error("Can't get roles by userId" + id, e);
        }
        return roles;
    }

    @Override
    public User update(User user) {
        try (PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_USER)) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getLogin());
            statement.setString(3, user.getPassword());
            statement.setLong(4, user.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Can't update user id=" + user.getId(), e);
        }
        return user;
    }

    @Override
    public User deleteById(Long id) {
        try (PreparedStatement statement = connection.prepareStatement(SQL_DELETE_USER)) {
            statement.setLong(1, id);
            User deletedUser = get(id);
            statement.executeUpdate();
            return deletedUser;
        } catch (SQLException e) {
            logger.error("Can't delete user id=" + id, e);
        }
        return null;
    }

    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SQL_GET_ALL_USERS)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                users.add(getUser(resultSet));
            }
        } catch (SQLException e) {
            logger.error("Can't get users", e);
        }
        return users;
    }

    @Override
    public User login(String login, String password) throws AuthenticationException {
        try (PreparedStatement statement = connection
                .prepareStatement(SQL_GET_USER_BY_LOGIN_AND_PASSWORD)) {
            statement.setString(1, login);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return getUser(resultSet);
            } else {
                throw new AuthenticationException("Incorrect username or password");
            }
        } catch (SQLException e) {
            logger.error("Login error", e);
        }
        return null;
    }

    @Override
    public Optional<User> getByToken(String token) {
        User user = null;
        try (PreparedStatement statement = connection.prepareStatement(SQL_GET_USER_BY_TOKEN)) {
            statement.setString(1, token);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = getUser(resultSet);
            }
        } catch (SQLException e) {
            logger.error("Can't get user by token");
        }
        return Optional.ofNullable(user);
    }

    @Override
    public User getByLogin(String login) {
        try (PreparedStatement statement = connection.prepareStatement(SQL_GET_USER_BY_LOGIN)) {
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return getUser(resultSet);
            }
        } catch (SQLException e) {
            logger.error("Can't find user by login");
        }
        return null;
    }
}
