import controller.ItemController;
import entity.Item;
import org.json.simple.parser.ParseException;
import utils.JsonUtil;

import javax.persistence.PersistenceException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/item")
public class MyServlet extends HttpServlet {
    private ItemController itemController = new ItemController();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            resp.getWriter().println(itemController.findById(Long.parseLong(req.getParameter("id"))).toString());
        } catch (Exception e) {
            resp.getWriter().println(e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Item item = JsonUtil.getItem(JsonUtil.getJsonString(req), req);
            itemController.save(item);
        } catch (PersistenceException | ParseException e) {
            resp.getWriter().println(e.getMessage());
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Item item = JsonUtil.getItem(JsonUtil.getJsonString(req), req);
            itemController.update(item);
        } catch (Exception e) {
            resp.getWriter().println(e.getMessage());
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            itemController.delete(Long.parseLong(req.getParameter("id")));
        } catch (NumberFormatException e) {
            resp.getWriter().println("Wrong enter id. Please use only numbers");
        } catch (Exception e) {
            resp.getWriter().println(e.getMessage());
        }
    }
}
