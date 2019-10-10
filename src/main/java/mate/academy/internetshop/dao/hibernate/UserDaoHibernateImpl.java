package mate.academy.internetshop.dao.hibernate;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import mate.academy.internetshop.dao.UserDao;
import mate.academy.internetshop.exceptions.AuthenticationException;
import mate.academy.internetshop.exceptions.RegistrationException;
import mate.academy.internetshop.lib.annotation.Dao;
import mate.academy.internetshop.model.User;
import mate.academy.internetshop.util.HibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

@Dao
public class UserDaoHibernateImpl implements UserDao {
    private static Logger logger = Logger.getLogger(UserDaoHibernateImpl.class);

    @Override
    public User create(User user) throws RegistrationException {
        Transaction transaction = null;
        Long userId = null;
        Session session = null;
        try {
            session = HibernateUtil.sessionFactory().openSession();
            transaction = session.beginTransaction();
            userId = (Long) session.save(user);
            transaction.commit();
        } catch (Exception e) {
            logger.error("Can't create user", e);
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        user.setId(userId);
        return user;
    }

    @Override
    public User get(Long id) {
        try (Session session = HibernateUtil.sessionFactory().openSession()) {
            User user = session.get(User.class, id);
            return user;
        } catch (Exception e) {
            logger.error("Can't get user by id=" + id);
        }
        return null;
    }

    @Override
    public User update(User user) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.sessionFactory().openSession();
            transaction = session.beginTransaction();
            session.update(user);
            transaction.commit();
        } catch (Exception e) {
            logger.error("Can't update user id=" + user.getId(), e);
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return user;
    }

    @Override
    public User deleteById(Long id) {
        User user = null;
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.sessionFactory().openSession();
            transaction = session.beginTransaction();
            user = get(id);
            session.delete(user);
            transaction.commit();
        } catch (Exception e) {
            logger.error("Can't delete user id=" + id);
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return user;
    }

    @Override
    public List<User> getAll() {
        try (Session session = HibernateUtil.sessionFactory().openSession()) {
            return session.createQuery("FROM User").list();
        } catch (Exception e) {
            logger.error("Can't get all users", e);
        }
        return Collections.emptyList();
    }

    @Override
    public User login(String login, String password) throws AuthenticationException {
        try (Session session = HibernateUtil.sessionFactory().openSession()) {
            Query query = session
                    .createQuery("from User where login=:login and password=:password");
            query.setParameter("login", login);
            query.setParameter("password", password);
            User user = (User) query.getSingleResult();
            return user;
        } catch (Exception e) {
            logger.error("Can't login user", e);
            throw new AuthenticationException(e.getMessage());
        }
    }

    @Override
    public Optional<User> getByToken(String token) {
        try (Session session = HibernateUtil.sessionFactory().openSession()) {
            Query query = session.createQuery("from User where token=:token");
            query.setParameter("token", token);
            User user = (User) query.getSingleResult();
            return Optional.ofNullable(user);
        } catch (Exception e) {
            logger.error("Can't get user by token", e);
        }
        return Optional.empty();
    }

    @Override
    public User getByLogin(String login) throws AuthenticationException {
        try (Session session = HibernateUtil.sessionFactory().openSession()) {
            Query query = session.createQuery("from User where login=:login");
            query.setParameter("login", login);
            return (User) query.getSingleResult();
        } catch (Exception e) {
            logger.error("Can't get user by login", e);
            throw new AuthenticationException(e.getMessage());
        }
    }
}
