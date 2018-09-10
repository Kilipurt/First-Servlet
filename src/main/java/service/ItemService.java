package service;

import dao.ItemDAO;
import entity.Item;

import java.util.Date;
import java.util.List;

public class ItemService {
    private ItemDAO itemDAO = new ItemDAO();

    public void save(Item item) {
        item.setDateCreated(new Date());
        itemDAO.save(item);
    }

    public void update(Item item) throws Exception {
        if (item.getId() <= 0)
            throw new Exception("Wrong enter id " + item.getId());

        item.setLastUpdatedDate(new Date());
        itemDAO.update(item);
    }

    public void delete(long id) throws Exception {
        if (id <= 0)
            throw new Exception("Wrong enter id " + id);

        itemDAO.delete(id);
    }

    public Item findById(long id) throws Exception {
        if (id <= 0)
            throw new Exception("Wrong enter id " + id);

        return itemDAO.findById(id);
    }

    public List<Item> get() {
        return itemDAO.get();
    }
}
