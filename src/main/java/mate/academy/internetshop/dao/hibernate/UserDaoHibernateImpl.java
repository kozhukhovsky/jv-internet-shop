package mate.academy.internetshop.dao.hibernate;

import java.util.List;
import java.util.Optional;
import mate.academy.internetshop.dao.UserDao;
import mate.academy.internetshop.exceptions.AuthenticationException;
import mate.academy.internetshop.exceptions.RegistrationException;
import mate.academy.internetshop.lib.annotation.Dao;
import mate.academy.internetshop.model.User;
import mate.academy.internetshop.util.HibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
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
        try (Session session = HibernateUtil.sessionFactory().openSession()) {
            transaction = session.beginTransaction();
            userId = (Long) session.save(user);
            transaction.commit();
        } catch (Exception e) {
            logger.error("Can't create user", e);
            if (transaction != null) {
                transaction.rollback();
            }
        }
        user.setId(userId);
        return user;
    }

    @Override
    public User get(Long id) {
        return null;
    }

    @Override
    public User update(User user) {
        return null;
    }

    @Override
    public User deleteById(Long id) {
        return null;
    }

    @Override
    public List<User> getAll() {
        try (Session session = HibernateUtil.sessionFactory().openSession()) {
            return session.createQuery("FROM User").list();
        } catch (Exception e) {
            logger.error("Can't get all users", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public User login(String login, String password) throws AuthenticationException {
        try (Session session = HibernateUtil.sessionFactory().openSession()) {
            Query query = session
                    .createQuery("from User where login=:login and password=:password");
            query.setParameter("login", login);
            query.setParameter("password", password);
            User user = (User) query.getSingleResult();
            Hibernate.initialize(user.getRoles());
            return user;
        } catch (Exception e) {
            logger.error("Can't login user", e);
        }
        return null;
    }

    @Override
    public Optional<User> getByToken(String token) {
        try (Session session = HibernateUtil.sessionFactory().openSession()) {
            Query query = session.createQuery("from User where token=:token");
            query.setParameter("token", token);
            User user = (User) query.getSingleResult();
            Hibernate.initialize(user.getRoles());
            return Optional.ofNullable(user);
        } catch (Exception e) {
            logger.error("Can't get user by token", e);
        }
        return Optional.empty();
    }

    @Override
    public User getByLogin(String login) {
        try (Session session = HibernateUtil.sessionFactory().openSession()) {
            Query query = session.createQuery("from User where login=:login");
            query.setParameter("login", login);
            User user = (User) query.getSingleResult();
            Hibernate.initialize(user.getRoles());
            return user;
        } catch (Exception e) {
            logger.error("Can't get user by login", e);
        }
        return null;
    }
}
