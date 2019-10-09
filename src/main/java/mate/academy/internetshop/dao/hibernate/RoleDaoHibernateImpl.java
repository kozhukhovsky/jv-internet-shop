package mate.academy.internetshop.dao.hibernate;

import mate.academy.internetshop.dao.RoleDao;
import mate.academy.internetshop.lib.annotation.Dao;
import mate.academy.internetshop.model.Role;
import mate.academy.internetshop.util.HibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

@Dao
public class RoleDaoHibernateImpl implements RoleDao {
    private static Logger logger = Logger.getLogger(RoleDaoHibernateImpl.class);

    @Override
    public Role create(Role role) {
        Transaction transaction = null;
        Long roleId = null;
        try (Session session = HibernateUtil.sessionFactory().openSession()) {
            transaction = session.beginTransaction();
            roleId = (Long) session.save(role);
            transaction.commit();
        } catch (Exception e) {
            logger.error("Can't create role", e);
            if (transaction != null) {
                transaction.rollback();
            }
        }
        role.setId(roleId);
        return role;
    }

    @Override
    public Role getById(Long id) {
        try (Session session = HibernateUtil.sessionFactory().openSession()) {
            Role role = session.get(Role.class, id);
            return role;
        } catch (Exception e) {
            logger.error("Can't get role by id=" + id);
        }
        return null;
    }

    @Override
    public Role getByName(String name) {
        try (Session session = HibernateUtil.sessionFactory().openSession()) {
            Query query = session.createQuery("from Role where name=:name");
            query.setParameter("name", name);
            return (Role) query.getSingleResult();
        } catch (Exception e) {
            logger.error("Can't get role by name: " + name, e);
        }
        return null;
    }
}
