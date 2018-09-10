package controller;

import entity.Item;
import service.ItemService;

import java.util.List;

public class ItemController {
    private ItemService itemService = new ItemService();

    public void save(Item item) {
        itemService.save(item);
    }

    public void update(Item item) throws Exception {
        itemService.update(item);
    }

    public void delete(long id) throws Exception {
        itemService.delete(id);
    }

    public Item findById(long id) throws Exception {
        Item item = itemService.findById(id);

        if (item == null)
            throw new Exception("Item with id " + id + " was not found");

        return item;
    }

    public List<Item> get() {
        return itemService.get();
    }
}
