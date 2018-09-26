package com.dao;

import com.entity.Item;
import com.utils.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

public class ItemDAO {

    @Autowired
    private HibernateUtil hibernateUtil;

    public void save(Item item) throws HibernateException {
        try (Session session = hibernateUtil.createSessionFactory().openSession()) {
            Transaction tr = session.getTransaction();
            tr.begin();

            session.save(item);

            tr.commit();
        } catch (HibernateException e) {
            throw new HibernateException("Saving is failed");
        }
    }

    public void delete(long id) throws HibernateException, IllegalArgumentException {
        try (Session session = hibernateUtil.createSessionFactory().openSession()) {
            Transaction tr = session.getTransaction();
            tr.begin();

            session.delete(session.get(Item.class, id));

            tr.commit();
        } catch (HibernateException e) {
            throw new HibernateException("Deleting is failed");
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Object with id " + id + " was not found");
        }
    }

    public void update(Item item) throws HibernateException {
        try (Session session = hibernateUtil.createSessionFactory().openSession()) {
            Transaction tr = session.getTransaction();
            tr.begin();

            session.update(item);

            tr.commit();
        } catch (HibernateException e) {
            throw new HibernateException("Updating is failed");
        }
    }

    public Item findById(long id) throws HibernateException {
        try (Session session = hibernateUtil.createSessionFactory().openSession()) {

            return session.get(Item.class, id);

        } catch (HibernateException e) {
            throw new HibernateException("Searching is failed");
        }
    }
}
