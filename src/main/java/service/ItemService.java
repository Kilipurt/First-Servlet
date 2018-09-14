package service;

import dao.ItemDAO;
import entity.Item;

import java.util.Date;
import java.util.List;

public class ItemService {
    private ItemDAO itemDAO = new ItemDAO();

    public void save(Item item) {
        item.setDateCreated(new Date());
        item.setLastUpdatedDate(new Date());
        itemDAO.save(item);
    }

    public void update(Item item) throws Exception {
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

        return itemDAO.findById(id);
    }

    public List<Item> get() {
        return itemDAO.get();
    }
}
