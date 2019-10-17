package me.kozhukhovsky.internetshop.lib;

import java.util.HashMap;
import java.util.Map;
import me.kozhukhovsky.internetshop.dao.BucketDao;
import me.kozhukhovsky.internetshop.dao.ItemDao;
import me.kozhukhovsky.internetshop.dao.OrderDao;
import me.kozhukhovsky.internetshop.dao.RoleDao;
import me.kozhukhovsky.internetshop.dao.UserDao;
import me.kozhukhovsky.internetshop.factory.Factory;
import me.kozhukhovsky.internetshop.service.BucketService;
import me.kozhukhovsky.internetshop.service.ItemService;
import me.kozhukhovsky.internetshop.service.OrderService;
import me.kozhukhovsky.internetshop.service.RoleService;
import me.kozhukhovsky.internetshop.service.UserService;

public class AnnotatedClassMap {
    private static final Map<Class, Object> classMap = new HashMap<>();

    static {
        classMap.put(ItemDao.class, Factory.getItemDao());
        classMap.put(BucketDao.class, Factory.getBucketDao());
        classMap.put(OrderDao.class, Factory.getOrderDao());
        classMap.put(UserDao.class, Factory.getUserDao());
        classMap.put(RoleDao.class, Factory.getRoleDao());
        classMap.put(ItemService.class, Factory.getItemService());
        classMap.put(BucketService.class, Factory.getBucketService());
        classMap.put(OrderService.class, Factory.getOrderService());
        classMap.put(UserService.class, Factory.getUserService());
        classMap.put(RoleService.class, Factory.getRoleService());
    }

    public static Object getImplementation(Class interfaceClass) {
        return classMap.get(interfaceClass);
    }
}
