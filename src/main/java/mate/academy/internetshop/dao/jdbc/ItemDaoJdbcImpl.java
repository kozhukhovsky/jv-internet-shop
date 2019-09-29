package mate.academy.internetshop.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import mate.academy.internetshop.dao.ItemDao;
import mate.academy.internetshop.lib.annotation.Dao;
import mate.academy.internetshop.model.Item;
import org.apache.log4j.Logger;

@Dao
public class ItemDaoJdbcImpl extends AbstractDao<Item> implements ItemDao {
    private static Logger logger = Logger.getLogger(ItemDaoJdbcImpl.class);
    private static final String SQL_INSERT_ITEM = "INSERT INTO item (name, price) VALUES (?, ?);";
    private static final String SQL_GET_ITEM = "SELECT * FROM item WHERE item_id=?;";
    private static final String SQL_UPDATE_ITEM = "UPDATE item SET name=?, price=? "
            + "WHERE item_id=?;";
    private static final String SQL_DELETE_ITEM = "DELETE FROM item WHERE item_id=?;";
    private static final String SQL_GET_ALL_ITEMS = "SELECT * FROM item;";

    public ItemDaoJdbcImpl(Connection connection) {
        super(connection);
    }

    @Override
    public Item create(Item item) {
        PreparedStatement statement = null;

        try {
            statement = connection
                    .prepareStatement(SQL_INSERT_ITEM, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, item.getName());
            statement.setDouble(2, item.getPrice());
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.warn("Can't create item id=" + item.getId());
        }

        try {
            ResultSet generatedKeys = Objects.requireNonNull(statement).getGeneratedKeys();
            if (generatedKeys.next()) {
                Long itemId = generatedKeys.getLong("item_id");
                item.setId(itemId);
            } else {
                throw new SQLException("Creating user failed, no ID obtained.");
            }
        } catch (SQLException e) {
            logger.warn("Creating user failed, no ID obtained.");
        } finally {
            try {
                Objects.requireNonNull(statement).close();
            } catch (SQLException e) {
                logger.error("Can't close statement", e);
            }
        }
        return item;
    }

    @Override
    public Item get(Long id) {
        try (PreparedStatement statement = connection.prepareStatement(SQL_GET_ITEM)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return getItem(resultSet);
            }
        } catch (SQLException e) {
            logger.warn("Can't get item by id=" + id);
        }

        return null;
    }

    @Override
    public Item update(Item item) {
        try (PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_ITEM)) {
            statement.setString(1, item.getName());
            statement.setDouble(2, item.getPrice());
            statement.setLong(3, item.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.warn("Can't update item" + item.getId());
        }
        return item;
    }

    @Override
    public Item deleteById(Long id) {
        try (PreparedStatement statement = connection.prepareStatement(SQL_DELETE_ITEM)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.warn("Can't delete item by id=" + id);
        }
        return null;
    }

    @Override
    public List<Item> getAll() {
        List<Item> items = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SQL_GET_ALL_ITEMS)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                items.add(getItem(resultSet));
            }
        } catch (SQLException e) {
            logger.warn("Can't get items");
        }
        return items;
    }

    private Item getItem(ResultSet resultSet) throws SQLException {
        Long itemId = resultSet.getLong("item_id");
        String name = resultSet.getString("name");
        Double price = resultSet.getDouble("price");
        Item item = new Item();
        item.setId(itemId);
        item.setName(name);
        item.setPrice(price);
        return item;
    }
}
