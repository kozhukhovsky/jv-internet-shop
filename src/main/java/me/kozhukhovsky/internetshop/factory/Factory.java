package me.kozhukhovsky.internetshop.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import me.kozhukhovsky.internetshop.dao.BucketDao;
import me.kozhukhovsky.internetshop.dao.ItemDao;
import me.kozhukhovsky.internetshop.dao.OrderDao;
import me.kozhukhovsky.internetshop.dao.RoleDao;
import me.kozhukhovsky.internetshop.dao.UserDao;
import me.kozhukhovsky.internetshop.dao.hibernate.BucketDaoHibernateImpl;
import me.kozhukhovsky.internetshop.dao.hibernate.ItemDaoHibernateImpl;
import me.kozhukhovsky.internetshop.dao.hibernate.OrderDaoHibernateImpl;
import me.kozhukhovsky.internetshop.dao.hibernate.RoleDaoHibernateImpl;
import me.kozhukhovsky.internetshop.dao.hibernate.UserDaoHibernateImpl;
import me.kozhukhovsky.internetshop.service.BucketService;
import me.kozhukhovsky.internetshop.service.ItemService;
import me.kozhukhovsky.internetshop.service.OrderService;
import me.kozhukhovsky.internetshop.service.RoleService;
import me.kozhukhovsky.internetshop.service.UserService;
import me.kozhukhovsky.internetshop.service.impl.BucketServiceImpl;
import me.kozhukhovsky.internetshop.service.impl.ItemServiceImpl;
import me.kozhukhovsky.internetshop.service.impl.OrderServiceImpl;
import me.kozhukhovsky.internetshop.service.impl.RoleServiceImpl;
import me.kozhukhovsky.internetshop.service.impl.UserServiceImpl;
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
            orderDao = new OrderDaoHibernateImpl();
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
