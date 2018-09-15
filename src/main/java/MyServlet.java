import controller.ItemController;
import entity.Item;
import org.hibernate.HibernateException;

import javax.persistence.PersistenceException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/item")
public class MyServlet extends HttpServlet {
    private ItemController itemController = new ItemController();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String id = req.getParameter("object");

        if (id.equals("all")) {
            try {
                List<Item> items = itemController.get();

                StringBuilder response = new StringBuilder("");
                for (int i = 0; i < items.size(); i++) {
                    response.append(items.get(i).toString());

                    if (i != items.size() - 1)
                        response.append("\n");
                }

                resp.getWriter().println(response);
            } catch (HibernateException e) {
                resp.getWriter().println(e.getMessage());
            }
        } else {
            try {
                resp.getWriter().println(itemController.findById(Integer.parseInt(req.getParameter("object"))).toString());
            } catch (Exception e) {
                resp.getWriter().println(e.getMessage());
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedReader br = req.getReader();

        Item newItem = new Item();
        newItem.setName(br.readLine());
        newItem.setDescription(br.readLine());

        try {
            itemController.save(newItem);
        } catch (PersistenceException e) {
            resp.getWriter().println(e.getMessage());
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedReader br = req.getReader();

        Item newItem = new Item();
        try {
            newItem.setId(Long.parseLong(br.readLine()));
        } catch (NumberFormatException e) {
            resp.getWriter().println("Wrong enter id " + br.readLine() + ". " + e.getMessage());
        }
        newItem.setName(br.readLine());
        newItem.setDescription(br.readLine());

        try {
            itemController.update(newItem);
        } catch (Exception e) {
            resp.getWriter().println(e.getMessage());
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedReader br = req.getReader();
        try {
            itemController.delete(Long.parseLong(br.readLine()));
        } catch (NumberFormatException e) {
            resp.getWriter().println("Wrong enter id. Please use only numbers");
        } catch (Exception e) {
            resp.getWriter().println(e.getMessage());
        }
    }
}
