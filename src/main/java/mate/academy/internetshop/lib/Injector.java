package mate.academy.internetshop.lib;

import java.lang.reflect.Field;
import mate.academy.internetshop.Main;
import mate.academy.internetshop.dao.BucketDao;
import mate.academy.internetshop.dao.ItemDao;
import mate.academy.internetshop.dao.OrderDao;
import mate.academy.internetshop.dao.UserDao;
import mate.academy.internetshop.factory.Factory;
import mate.academy.internetshop.lib.annotation.Inject;
import mate.academy.internetshop.service.BucketService;
import mate.academy.internetshop.service.ItemService;
import mate.academy.internetshop.service.OrderService;
import mate.academy.internetshop.service.UserService;
import mate.academy.internetshop.service.impl.BucketServiceImpl;
import mate.academy.internetshop.service.impl.ItemServiceImpl;
import mate.academy.internetshop.service.impl.OrderServiceImpl;
import mate.academy.internetshop.service.impl.UserServiceImpl;

public class Injector {
    public static void injectDependency() throws IllegalAccessException {
        Class[] classes = new Class[]{
            BucketServiceImpl.class, ItemServiceImpl.class, OrderServiceImpl.class,
            UserServiceImpl.class, Main.class
        };

        for (Class clazz : classes) {
            for (Field field : clazz.getDeclaredFields()) {
                if (field.getDeclaredAnnotation(Inject.class) != null) {
                    field.setAccessible(true);
                    Class<?> type = field.getType();
                    if (type == ItemDao.class) {
                        field.set(null, Factory.getItemDao());
                    }
                    if (type == BucketDao.class) {
                        field.set(null, Factory.getBucketDao());
                    }
                    if (type == OrderDao.class) {
                        field.set(null, Factory.getOrderDao());
                    }
                    if (type == UserDao.class) {
                        field.set(null, Factory.getUserDao());
                    }
                    if (type == BucketService.class) {
                        field.set(null, Factory.getBucketService());
                    }
                    if (type == ItemService.class) {
                        field.set(null, Factory.getItemService());
                    }
                    if (type == OrderService.class) {
                        field.set(null, Factory.getOrderService());
                    }
                    if (type == UserService.class) {
                        field.set(null, Factory.getUserService());
                    }
                }
            }
        }
    }
}
