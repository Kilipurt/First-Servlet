package dao;

import entity.Item;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateUtil;

import javax.persistence.PersistenceException;

public class ItemDAO {
    public void save(Item item) throws HibernateException {
        try (Session session = new HibernateUtil().createSessionFactory().openSession()) {
            Transaction tr = session.getTransaction();
            tr.begin();

            session.save(item);

            tr.commit();
        } catch (HibernateException e) {
            throw new HibernateException("Saving is failed");
        }
    }

    public void delete(long id) throws HibernateException, IllegalArgumentException {
        try (Session session = new HibernateUtil().createSessionFactory().openSession()) {
            Transaction tr = session.getTransaction();
            tr.begin();

            session.delete(session.get(Item.class, id));

            tr.commit();
        } catch (HibernateException e) {
            throw new HibernateException("Deleting is failed");
        }
    }

    public void update(Item item) throws HibernateException {
        try (Session session = new HibernateUtil().createSessionFactory().openSession()) {
            Transaction tr = session.getTransaction();
            tr.begin();

            session.update(item);

            tr.commit();
        } catch (HibernateException e) {
            throw new HibernateException("Updating is failed");
        }
    }

    public Item findById(long id) throws HibernateException {
        try (Session session = new HibernateUtil().createSessionFactory().openSession()) {

            return session.get(Item.class, id);

        } catch (HibernateException e) {
            throw new HibernateException("Searching is failed");
        }
    }
}
