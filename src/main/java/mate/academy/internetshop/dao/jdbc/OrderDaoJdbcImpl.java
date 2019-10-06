package mate.academy.internetshop.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import mate.academy.internetshop.dao.OrderDao;
import mate.academy.internetshop.lib.annotation.Dao;
import mate.academy.internetshop.model.Item;
import mate.academy.internetshop.model.Order;
import org.apache.log4j.Logger;

@Dao
public class OrderDaoJdbcImpl extends AbstractDao<Order> implements OrderDao {
    private static Logger logger = Logger.getLogger(OrderDaoJdbcImpl.class);

    private static final String SQL_INSERT_ORDER = "INSERT INTO `order` (user_id) VALUES (?)";
    private static final String SQL_INSERT_ITEM_TO_ORDER = "INSERT INTO order_item VALUES (?, ?)";
    private static final String SQL_GET_ORDER = "SELECT * FROM `order` WHERE id=?";
    private static final String SQL_GET_ITEMS_BY_ORDER_ID = "SELECT i.id, i.name, i.price "
            + "FROM item i INNER JOIN order_item oi ON i.id = oi.item_id WHERE order_id=?";
    private static final String SQL_UPDATE_ORDER = "UPDATE `order` SET user_id=? WHERE id=?";
    private static final String SQL_DELETE_ORDER = "DELETE FROM `order` WHERE id=?";
    private static final String SQL_GET_ALL_ORDERS = "SELECT * FROM `order` WHERE user_id=?";


    public OrderDaoJdbcImpl(Connection connection) {
        super(connection);
    }

    @Override
    public Order create(Order order) {
        try (PreparedStatement statement = connection
                .prepareStatement(SQL_INSERT_ORDER, Statement.RETURN_GENERATED_KEYS)) {
            statement.setLong(1, order.getUserId());
            statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                Long orderId = generatedKeys.getLong(1);
                order.setId(orderId);
            } else {
                throw new SQLException("Creating order failed, no ID obtained.");
            }

            List<Item> items = order.getItems();
            for (Item item : items) {
                setupItem(order.getId(), item.getId());
            }
        } catch (SQLException e) {
            logger.error("Can't create order id=" + order.getId(), e);
        }
        return order;
    }

    private void setupItem(Long orderId, Long itemId) {
        try (PreparedStatement statement = connection.prepareStatement(SQL_INSERT_ITEM_TO_ORDER)) {
            statement.setLong(1, orderId);
            statement.setLong(2, itemId);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Can't setup item id=" + itemId + " to order id=" + orderId);
        }
    }

    @Override
    public Order get(Long id) {
        try (PreparedStatement statement = connection.prepareStatement(SQL_GET_ORDER)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return getOrder(resultSet);
            }
        } catch (SQLException e) {
            logger.error("Can't get order id=" + id);
        }
        return null;
    }

    private Order getOrder(ResultSet resultSet) throws SQLException {
        long orderId = resultSet.getLong("id");
        long userIdFromOrder = resultSet.getLong("user_id");
        List<Item> items = getAllItems(orderId);
        Order order = new Order();
        order.setId(orderId);
        order.setUserId(userIdFromOrder);
        order.setItems(items);
        return order;
    }

    private List<Item> getAllItems(Long orderId) {
        List<Item> items = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SQL_GET_ITEMS_BY_ORDER_ID)) {
            statement.setLong(1, orderId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                long itemId = resultSet.getLong("id");
                String name = resultSet.getString("name");
                double price = resultSet.getDouble("price");
                Item item = new Item();
                item.setId(itemId);
                item.setName(name);
                item.setPrice(price);
                items.add(item);
            }
        } catch (SQLException e) {
            logger.error("Can't get items from order id=" + orderId);
        }
        return items;
    }

    @Override
    public Order update(Order order) {
        try (PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_ORDER)) {
            statement.setLong(1, order.getUserId());
            statement.setLong(2, order.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Can't update order id=" + order.getId());
        }
        return order;
    }

    @Override
    public Order deleteById(Long id) {
        Order order = get(id);
        try (PreparedStatement statement = connection.prepareStatement(SQL_DELETE_ORDER)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Can't delete order id=" + id);
        }
        return order;
    }

    @Override
    public List<Order> getOrdersByUserId(Long userId) {
        List<Order> orders = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SQL_GET_ALL_ORDERS)) {
            statement.setLong(1, userId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                orders.add(getOrder(resultSet));
            }
        } catch (SQLException e) {
            logger.error("Can't get orders by userId=" + userId);
        }
        return orders;
    }
}
