package dao;

import entity.Item;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateUtil;

import javax.persistence.PersistenceException;

public class ItemDAO {

    private static final String getAllQuery = "FROM entity.Item";

    public void save(Item item) throws PersistenceException {
        try (Session session = new HibernateUtil().createSessionFactory().openSession()) {
            Transaction tr = session.getTransaction();
            tr.begin();

            session.save(item);

            tr.commit();
        } catch (HibernateException e) {
            throw new HibernateException("Saving is failed");
        } catch (PersistenceException e) {
            throw new PersistenceException("Object was not saved.\n" + e.getMessage());
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
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Object " + id + " was not found");
        }
    }

    public void update(Item item) throws PersistenceException {
        try (Session session = new HibernateUtil().createSessionFactory().openSession()) {
            Transaction tr = session.getTransaction();
            tr.begin();

            session.update(item);

            tr.commit();
        } catch (HibernateException e) {
            throw new HibernateException("Updating is failed");
        } catch (PersistenceException e) {
            throw new PersistenceException("Object was not found or data is wrong");
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
