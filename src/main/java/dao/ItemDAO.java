package dao;

import entity.Item;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import utils.HibernateUtil;

import javax.persistence.PersistenceException;
import java.util.List;

public class ItemDAO {

    private static final String getAllQuery = "FROM entity.Item";

    public void save(Item item) {
        try (Session session = new HibernateUtil().createSessionFactory().openSession()) {
            Transaction tr = session.getTransaction();
            tr.begin();

            session.save(item);

            tr.commit();
        } catch (HibernateException e) {
//            System.err.println("Saving is failed");
//            System.err.println(e.getMessage());
        } catch (PersistenceException e) {
//            System.err.println("Object was not saved.\n" + e.getMessage());
        }
    }

    public void delete(long id) {
        try (Session session = new HibernateUtil().createSessionFactory().openSession()) {
            Transaction tr = session.getTransaction();
            tr.begin();

            session.delete(session.get(Item.class, id));

            tr.commit();
        } catch (HibernateException e) {
//            System.err.println("Deleting is failed");
//            System.err.println(e.getMessage());
        } catch (IllegalArgumentException e) {
//            System.err.println("Object " + id + " was not found");
        }
    }

    public void update(Item item) {
        try (Session session = new HibernateUtil().createSessionFactory().openSession()) {
            Transaction tr = session.getTransaction();
            tr.begin();

            session.update(item);

            tr.commit();
        } catch (HibernateException e) {
//            System.err.println("Updating is failed");
//            System.err.println(e.getMessage());
        } catch (PersistenceException e) {
//            System.err.println("Object was not found or data is wrong");
        }
    }

    public Item findById(long id) {
        try (Session session = new HibernateUtil().createSessionFactory().openSession()) {

            return session.get(Item.class, id);

        } catch (HibernateException e) {
//            System.err.println("Searching is failed");
//            System.err.println(e.getMessage());
            e.printStackTrace();
        }

        return null;
    }

    public List<Item> get() {
        try (Session session = new HibernateUtil().createSessionFactory().openSession()) {

            Query query = session.createQuery(getAllQuery);
            return query.list();

        } catch (HibernateException e) {
//            System.err.println("Searching is failed");
//            System.err.println(e.getMessage());
        }

        return null;
    }
}
