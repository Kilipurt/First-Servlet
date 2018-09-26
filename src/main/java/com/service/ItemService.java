package com.service;

import com.dao.ItemDAO;
import com.entity.Item;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

public class ItemService {

    @Autowired
    private ItemDAO itemDAO;

    public void save(Item item) throws HibernateException {
        item.setDateCreated(new Date());
        item.setLastUpdatedDate(new Date());
        itemDAO.save(item);
    }

    public void update(Item item) throws Exception {
        if (item.getId() == null)
            throw new Exception("Id was not found. Please enter id.");

        if (item.getId() <= 0)
            throw new Exception("Wrong enter id " + item.getId() + ". Id must be above 0.");

        Item updatedItem = itemDAO.findById(item.getId());

        if (updatedItem == null)
            throw new Exception("Item was not found");

        item.setDateCreated(updatedItem.getDateCreated());
        item.setLastUpdatedDate(new Date());
        itemDAO.update(item);
    }

    public void delete(long id) throws Exception {
        if (id <= 0)
            throw new Exception("Wrong enter id " + id + ". Id must be above 0.");

        itemDAO.delete(id);
    }

    public Item findById(long id) throws Exception {
        if (id <= 0)
            throw new Exception("Wrong enter id " + id + ". Id must be above 0.");

        Item item = itemDAO.findById(id);

        if (item == null)
            throw new Exception("Item with id " + id + " was not found");

        return item;
    }
}
