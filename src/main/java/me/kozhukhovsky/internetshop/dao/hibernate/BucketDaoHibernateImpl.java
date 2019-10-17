package me.kozhukhovsky.internetshop.dao.hibernate;

import java.util.List;
import me.kozhukhovsky.internetshop.dao.BucketDao;
import me.kozhukhovsky.internetshop.lib.annotation.Dao;
import me.kozhukhovsky.internetshop.model.Bucket;
import me.kozhukhovsky.internetshop.model.Item;
import me.kozhukhovsky.internetshop.util.HibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

@Dao
public class BucketDaoHibernateImpl implements BucketDao {
    private static Logger logger = Logger.getLogger(BucketDaoHibernateImpl.class);

    @Override
    public Bucket create(Bucket bucket) {
        Transaction transaction = null;
        Long bucketId = null;
        Session session = null;
        try {
            session = HibernateUtil.sessionFactory().openSession();
            transaction = session.beginTransaction();
            bucketId = (Long) session.save(bucket);
            transaction.commit();
        } catch (Exception e) {
            logger.error("Can't create bucket", e);
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        bucket.setId(bucketId);
        return bucket;
    }

    @Override
    public Bucket get(Long id) {
        try (Session session = HibernateUtil.sessionFactory().openSession()) {
            Bucket bucket = session.get(Bucket.class, id);
            return bucket;
        } catch (Exception e) {
            logger.error("Can't get bucket by id=" + id);
        }
        return null;
    }

    @Override
    public Bucket update(Bucket bucket) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.sessionFactory().openSession();
            transaction = session.beginTransaction();
            session.update(bucket);
            transaction.commit();
        } catch (Exception e) {
            logger.error("Can't update bucket id=" + bucket.getId(), e);
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return bucket;
    }

    @Override
    public Bucket deleteById(Long id) {
        Bucket bucket = null;
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.sessionFactory().openSession();
            transaction = session.beginTransaction();
            bucket = get(id);
            session.delete(bucket);
            transaction.commit();
        } catch (Exception e) {
            logger.error("Can't delete bucket id=" + id);
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return bucket;
    }

    @Override
    public List<Item> getAllItems(Long bucketId) {
        Bucket bucket = get(bucketId);
        return bucket.getItems();
    }
}
