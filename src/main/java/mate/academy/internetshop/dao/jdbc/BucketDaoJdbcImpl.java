package mate.academy.internetshop.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import mate.academy.internetshop.dao.BucketDao;
import mate.academy.internetshop.lib.annotation.Dao;
import mate.academy.internetshop.model.Bucket;
import mate.academy.internetshop.model.Item;
import org.apache.log4j.Logger;

@Dao
public class BucketDaoJdbcImpl extends AbstractDao<Bucket> implements BucketDao {
    private static Logger logger = Logger.getLogger(BucketDaoJdbcImpl.class);

    private static final String SQL_INSERT_BUCKET = "INSERT INTO bucket (user_id) VALUES (?)";
    private static final String SQL_GET_BY_ID = "SELECT * FROM bucket WHERE id=?";
    private static final String SQL_UPDATE_BUCKET = "UPDATE bucket SET user_id=? WHERE id=?";
    private static final String SQL_GET_ITEMS_BY_BUCKET_ID = "SELECT i.id, i.name, i.price "
            + "FROM item i INNER JOIN bucket_item bi ON i.id = bi.item_id WHERE bucket_id=?";
    private static final String SQL_DELETE_BUCKET = "DELETE FROM bucket WHERE id=?";
    private static final String SQL_INSERT_ITEM_INTO_BUCKET = "INSERT INTO bucket_item "
            + "VALUES(?, ?)";
    private static final String SQL_DELETE_ITEM_FROM_BUCKET = "DELETE FROM bucket_item "
            + "WHERE bucket_id=? AND item_id=?";
    private static final String SQL_CLEAR_BUCKET = "DELETE FROM bucket_item WHERE bucket_id=?";

    public BucketDaoJdbcImpl(Connection connection) {
        super(connection);
    }

    @Override
    public Bucket create(Bucket bucket) {
        try (PreparedStatement statement = connection
                .prepareStatement(SQL_INSERT_BUCKET, Statement.RETURN_GENERATED_KEYS)) {
            statement.setLong(1, bucket.getUserId());
            statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                Long bucketId = generatedKeys.getLong(1);
                bucket.setId(bucketId);
            } else {
                throw new SQLException("Creating bucket failed, no ID obtained.");
            }
        } catch (SQLException e) {
            logger.error("Can't create bucket id=" + bucket.getId(), e);
        }
        return bucket;
    }

    @Override
    public Bucket get(Long id) {
        try (PreparedStatement statement = connection.prepareStatement(SQL_GET_BY_ID)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Bucket bucket = new Bucket();
                long bucketId = resultSet.getLong("id");
                long userId = resultSet.getLong("user_id");
                List<Item> items = getAllItems(bucketId);
                bucket.setId(bucketId);
                bucket.setUserId(userId);
                bucket.setItems(items);
                return bucket;
            }
        } catch (SQLException e) {
            logger.error("Can't get bucket by id" + id, e);
        }
        return null;
    }

    @Override
    public Bucket update(Bucket bucket) {
        try (PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_BUCKET)) {
            statement.setLong(1, bucket.getUserId());
            statement.setLong(2, bucket.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Can't update bucket id=" + bucket.getId(), e);
        }
        return bucket;
    }

    @Override
    public Bucket deleteById(Long id) {
        Bucket deletedBucket = get(id);
        try (PreparedStatement statement = connection.prepareStatement(SQL_DELETE_BUCKET)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Can't delete bucket id" + id, e);
        }
        return deletedBucket;
    }

    @Override
    public Bucket addItem(Long bucketId, Long itemId) {
        Bucket bucket = null;
        try (PreparedStatement statement = connection
                .prepareStatement(SQL_INSERT_ITEM_INTO_BUCKET)) {
            statement.setLong(1, bucketId);
            statement.setLong(2, itemId);
            statement.executeUpdate();
            bucket = get(bucketId);
        } catch (SQLException e) {
            logger.error("Can't add item id=" + itemId + " to bucket id=" + bucketId, e);
        }
        return bucket;
    }

    @Override
    public Bucket clear(Long bucketId) {
        Bucket bucket = null;
        try (PreparedStatement statement = connection.prepareStatement(SQL_CLEAR_BUCKET)) {
            statement.setLong(1, bucketId);
            statement.executeUpdate();
            bucket = get(bucketId);
        } catch (SQLException e) {
            logger.error("Can't clear bucket id=" + bucketId, e);
        }
        return bucket;
    }

    @Override
    public List<Item> getAllItems(Long bucketId) {
        List<Item> items = new ArrayList<>();
        try (PreparedStatement statement = connection
                .prepareStatement(SQL_GET_ITEMS_BY_BUCKET_ID)) {
            statement.setLong(1, bucketId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Item item = new Item();
                long itemId = resultSet.getLong("id");
                String name = resultSet.getString("name");
                double price = resultSet.getDouble("price");
                item.setId(itemId);
                item.setName(name);
                item.setPrice(price);
                items.add(item);
            }
        } catch (SQLException e) {
            logger.error("Can't get items by id=" + bucketId, e);
        }
        return items;
    }

    @Override
    public Bucket removeItemFromBucket(Long bucketId, Long itemId) {
        Bucket bucket = null;
        try (PreparedStatement statement = connection
                .prepareStatement(SQL_DELETE_ITEM_FROM_BUCKET)) {
            statement.setLong(1, bucketId);
            statement.setLong(2, itemId);
            bucket = get(bucketId);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Can't remove item id=" + itemId + " from bucket id=" + bucketId, e);
        }
        return bucket;
    }
}
