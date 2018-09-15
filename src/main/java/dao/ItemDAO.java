package dao;

import entity.Item;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.query.Query;
import utils.HibernateUtil;

import javax.persistence.PersistenceException;
import java.util.List;

public class ItemDAO {

    private static final String getAllQuery = "FROM entity.Item";

    public void save(Item item) throws PersistenceException {
        try (Session session = new HibernateUtil().createSessionFactory().openSession()) {
            Transaction tr = session.getTransaction();
            tr.begin();

            session.save(item);

            tr.commit();
        } catch (HibernateException e) {
            System.err.println("Saving is failed");
            throw e;
        } catch (PersistenceException e) {
            System.err.println("Object was not saved.\n" + e.getMessage());
            throw e;
        }
    }

    public void delete(long id) throws HibernateException, IllegalArgumentException {
        try (Session session = new HibernateUtil().createSessionFactory().openSession()) {
            Transaction tr = session.getTransaction();
            tr.begin();

            session.delete(session.get(Item.class, id));

            tr.commit();
        } catch (HibernateException e) {
            System.err.println("Deleting is failed");
            throw e;
        } catch (IllegalArgumentException e) {
            System.err.println("Object " + id + " was not found");
            throw e;
        }
    }

    public void update(Item item) throws PersistenceException {
        try (Session session = new HibernateUtil().createSessionFactory().openSession()) {
            Transaction tr = session.getTransaction();
            tr.begin();

            session.update(item);

            tr.commit();
        } catch (HibernateException e) {
            System.err.println("Updating is failed");
            throw e;
        } catch (PersistenceException e) {
            System.err.println("Object was not found or data is wrong");
            throw e;
        }
    }

    public Item findById(long id) throws HibernateException {
        try (Session session = new HibernateUtil().createSessionFactory().openSession()) {

            return session.get(Item.class, id);

        } catch (HibernateException e) {
            System.err.println("Searching is failed");
            throw e;
        }
    }

    public List<Item> get() throws HibernateException {
        try (Session session = new HibernateUtil().createSessionFactory().openSession()) {

            Query query = session.createQuery(getAllQuery);
            return query.list();

        } catch (HibernateException e) {
            System.err.println("Searching is failed");
            throw e;
        }
    }
}
