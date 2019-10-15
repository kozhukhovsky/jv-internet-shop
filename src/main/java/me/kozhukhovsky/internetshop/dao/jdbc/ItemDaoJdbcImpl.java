package me.kozhukhovsky.internetshop.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import me.kozhukhovsky.internetshop.dao.ItemDao;
import me.kozhukhovsky.internetshop.lib.annotation.Dao;
import me.kozhukhovsky.internetshop.model.Item;
import org.apache.log4j.Logger;

@Dao
public class ItemDaoJdbcImpl extends AbstractDao<Item> implements ItemDao {
    private static Logger logger = Logger.getLogger(ItemDaoJdbcImpl.class);

    private static final String SQL_INSERT_ITEM = "INSERT INTO item (name, price) VALUES (?, ?)";
    private static final String SQL_GET_ITEM = "SELECT * FROM item WHERE id=?";
    private static final String SQL_UPDATE_ITEM = "UPDATE item SET name=?, price=? "
            + "WHERE id=?";
    private static final String SQL_DELETE_ITEM = "DELETE FROM item WHERE id=?";
    private static final String SQL_GET_ALL_ITEMS = "SELECT * FROM item";

    public ItemDaoJdbcImpl(Connection connection) {
        super(connection);
    }

    @Override
    public Item create(Item item) {
        try (PreparedStatement statement = connection
                .prepareStatement(SQL_INSERT_ITEM, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, item.getName());
            statement.setDouble(2, item.getPrice());
            statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                Long itemId = generatedKeys.getLong(1);
                item.setId(itemId);
            } else {
                throw new SQLException("Creating user failed, no ID obtained.");
            }
        } catch (SQLException e) {
            logger.error("Can't create item id=" + item.getId(), e);
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
            logger.error("Can't get item by id=" + id, e);
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
            logger.error("Can't update item" + item.getId(), e);
        }
        return item;
    }

    @Override
    public Item deleteById(Long id) {
        try (PreparedStatement statement = connection.prepareStatement(SQL_DELETE_ITEM)) {
            statement.setLong(1, id);
            Item deletedItem = get(id);
            statement.executeUpdate();
            return deletedItem;
        } catch (SQLException e) {
            logger.error("Can't delete item by id=" + id, e);
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
            logger.error("Can't get items", e);
        }
        return items;
    }

    private Item getItem(ResultSet resultSet) throws SQLException {
        Long itemId = resultSet.getLong("id");
        String name = resultSet.getString("name");
        Double price = resultSet.getDouble("price");
        Item item = new Item();
        item.setId(itemId);
        item.setName(name);
        item.setPrice(price);
        return item;
    }
}
