package com.controller;

import com.entity.Item;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.service.ItemService;
import com.utils.JsonUtil;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Controller
public class ItemController {

    @Autowired
    private ItemService itemService;

    @Autowired
    private JsonUtil jsonUtil;

    @RequestMapping(method = RequestMethod.POST, value = "/item", produces = "text/plain")
    public @ResponseBody String save(HttpServletRequest req) {
        try {
            Item item = jsonUtil.getItem(jsonUtil.getJsonString(req));
            itemService.save(item);
            return "Item was saved";
        } catch (HibernateException e) {
            return e.getMessage();
        } catch (IOException e) {
            return "Can't read request";
        }
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/item", produces = "text/plain")
    public @ResponseBody String update(HttpServletRequest req) {
        try {
            Item item = jsonUtil.getItem(jsonUtil.getJsonString(req));
            itemService.update(item);
            return "Item was updated";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/item", params = {"id"}, produces = "text/plain")
    public @ResponseBody String delete(
            @RequestParam(value = "id") String id
    ) {
        try {
            itemService.delete(Long.parseLong(id));
            return "Item was deleted";
        } catch (NumberFormatException e) {
            return "Wrong enter id. Please use only numbers";
        } catch (Exception e) {
            return e.getMessage();
        }
    }


    @RequestMapping(method = RequestMethod.GET, value = "/item", params = {"id"}, produces = "text/plain")
    public @ResponseBody String findById(
            @RequestParam(value = "id") String id) {
        try {
            return itemService.findById(Long.parseLong(id)).toString();
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
