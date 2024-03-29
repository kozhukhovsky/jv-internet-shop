package me.kozhukhovsky.internetshop.dao.hibernate;

import java.util.List;
import me.kozhukhovsky.internetshop.dao.ItemDao;
import me.kozhukhovsky.internetshop.lib.annotation.Dao;
import me.kozhukhovsky.internetshop.model.Item;
import me.kozhukhovsky.internetshop.util.HibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

@Dao
public class ItemDaoHibernateImpl implements ItemDao {
    private static Logger logger = Logger.getLogger(ItemDaoHibernateImpl.class);

    @Override
    public Item create(Item item) {
        Transaction transaction = null;
        Long itemId = null;
        Session session = null;
        try {
            session = HibernateUtil.sessionFactory().openSession();
            transaction = session.beginTransaction();
            itemId = (Long) session.save(item);
            transaction.commit();
        } catch (Exception e) {
            logger.error("Can't create item", e);
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        item.setId(itemId);
        return item;
    }

    @Override
    public Item get(Long id) {
        try (Session session = HibernateUtil.sessionFactory().openSession()) {
            Item item = session.get(Item.class, id);
            return item;
        }
    }

    @Override
    public Item update(Item item) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.sessionFactory().openSession();
            transaction = session.beginTransaction();
            session.update(item);
            transaction.commit();
        } catch (Exception e) {
            logger.error("Can't update item id=" + item.getId(), e);
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return item;
    }

    @Override
    public Item deleteById(Long id) {
        Item item = null;
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.sessionFactory().openSession();
            transaction = session.beginTransaction();
            item = get(id);
            session.delete(item);
            transaction.commit();
        } catch (Exception e) {
            logger.error("Can't delete item id=" + id);
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return item;
    }

    @Override
    public List<Item> getAll() {
        try (Session session = HibernateUtil.sessionFactory().openSession()) {
            return session.createQuery("FROM Item").list();
        } catch (Exception e) {
            logger.error("Can't get all Items", e);
            throw new RuntimeException(e);
        }
    }
}
