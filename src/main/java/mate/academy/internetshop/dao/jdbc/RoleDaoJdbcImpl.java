package mate.academy.internetshop.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import mate.academy.internetshop.dao.RoleDao;
import mate.academy.internetshop.lib.annotation.Dao;
import mate.academy.internetshop.model.Role;
import org.apache.log4j.Logger;

@Dao
public class RoleDaoJdbcImpl extends AbstractDao<Role> implements RoleDao {
    private static Logger logger = Logger.getLogger(RoleDaoJdbcImpl.class);

    private static final String SQL_CREATE_ROLE = "INSERT INTO role (name) VALUES (?)";
    private static final String SQL_GET_ROLE_BY_ID = "SELECT * FROM role WHERE id=?";
    private static final String SQL_GET_ROLE_BY_NAME = "SELECT * FROM role WHERE name=?";

    public RoleDaoJdbcImpl(Connection connection) {
        super(connection);
    }

    @Override
    public Role create(Role role) {
        try (PreparedStatement statement = connection
                .prepareStatement(SQL_CREATE_ROLE, PreparedStatement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, role.getName());
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                Long roleId = generatedKeys.getLong(1);
                role.setId(roleId);
            } else {
                throw new SQLException("Creating role failed, no ID obtained.");
            }
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Can't create role with id=" + role.getId(), e);
        }
        return role;
    }

    @Override
    public Role getById(Long id) {
        try (PreparedStatement statement = connection.prepareStatement(SQL_GET_ROLE_BY_ID)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return getRole(resultSet);
            }
        } catch (SQLException e) {
            logger.error("Can't get role by id=" + id, e);
        }
        return null;
    }

    @Override
    public Role getByName(String name) {
        try (PreparedStatement statement = connection.prepareStatement(SQL_GET_ROLE_BY_NAME)) {
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return getRole(resultSet);
            }
        } catch (SQLException e) {
            logger.error("Can't get role by name=" + name, e);
        }
        return null;
    }

    private Role getRole(ResultSet resultSet) throws SQLException {
        Long roleId = resultSet.getLong("id");
        String roleName = resultSet.getString("name");
        Role role = new Role();
        role.setId(roleId);
        role.setName(roleName);
        return role;
    }
}
