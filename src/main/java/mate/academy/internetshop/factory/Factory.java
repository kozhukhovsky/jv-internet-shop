package mate.academy.internetshop.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import mate.academy.internetshop.dao.BucketDao;
import mate.academy.internetshop.dao.ItemDao;
import mate.academy.internetshop.dao.OrderDao;
import mate.academy.internetshop.dao.RoleDao;
import mate.academy.internetshop.dao.UserDao;
import mate.academy.internetshop.dao.hibernate.BucketDaoHibernateImpl;
import mate.academy.internetshop.dao.hibernate.ItemDaoHibernateImpl;
import mate.academy.internetshop.dao.hibernate.RoleDaoHibernateImpl;
import mate.academy.internetshop.dao.hibernate.UserDaoHibernateImpl;
import mate.academy.internetshop.dao.jdbc.OrderDaoJdbcImpl;
import mate.academy.internetshop.dao.jdbc.RoleDaoJdbcImpl;
import mate.academy.internetshop.service.BucketService;
import mate.academy.internetshop.service.ItemService;
import mate.academy.internetshop.service.OrderService;
import mate.academy.internetshop.service.RoleService;
import mate.academy.internetshop.service.UserService;
import mate.academy.internetshop.service.impl.BucketServiceImpl;
import mate.academy.internetshop.service.impl.ItemServiceImpl;
import mate.academy.internetshop.service.impl.OrderServiceImpl;
import mate.academy.internetshop.service.impl.RoleServiceImpl;
import mate.academy.internetshop.service.impl.UserServiceImpl;
import org.apache.log4j.Logger;

public class Factory {
    private static Logger logger = Logger.getLogger(Factory.class);
    private static Connection connection;

    private static ItemDao itemDao;
    private static BucketDao bucketDao;
    private static OrderDao orderDao;
    private static UserDao userDao;
    private static RoleDao roleDao;

    private static ItemService itemService;
    private static BucketService bucketService;
    private static OrderService orderService;
    private static UserService userService;
    private static RoleService roleService;

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/jv_internet_shop?user=anton&password=qwerty"
                            + "&serverTimezone=UTC");
        } catch (ClassNotFoundException | SQLException e) {
            logger.error("Can't establish connection to our DB", e);
        }
    }

    public static ItemDao getItemDao() {
        if (itemDao == null) {
            itemDao = new ItemDaoHibernateImpl();
        }
        return itemDao;
    }

    public static BucketDao getBucketDao() {
        if (bucketDao == null) {
            bucketDao = new BucketDaoHibernateImpl();
        }
        return bucketDao;
    }

    public static OrderDao getOrderDao() {
        if (orderDao == null) {
            orderDao = new OrderDaoJdbcImpl(connection);
        }
        return orderDao;
    }

    public static UserDao getUserDao() {
        if (userDao == null) {
            userDao = new UserDaoHibernateImpl();
        }
        return userDao;
    }

    public static RoleDao getRoleDao() {
        if (roleDao == null) {
            roleDao = new RoleDaoHibernateImpl();
        }
        return roleDao;
    }

    public static ItemService getItemService() {
        if (itemService == null) {
            itemService = new ItemServiceImpl();
        }
        return itemService;
    }

    public static BucketService getBucketService() {
        if (bucketService == null) {
            bucketService = new BucketServiceImpl();
        }
        return bucketService;
    }

    public static OrderService getOrderService() {
        if (orderService == null) {
            orderService = new OrderServiceImpl();
        }
        return orderService;
    }

    public static UserService getUserService() {
        if (userService == null) {
            userService = new UserServiceImpl();
        }
        return userService;
    }

    public static RoleService getRoleService() {
        if (roleService == null) {
            roleService = new RoleServiceImpl();
        }
        return roleService;
    }
}
