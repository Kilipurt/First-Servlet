package utils;

import entity.Item;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;

public class JsonUtil {
    public static String getJsonString(HttpServletRequest req) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();

        try (BufferedReader in = req.getReader()) {
            String line;

            while ((line = in.readLine()) != null) {
                stringBuilder.append(line);
            }

            return stringBuilder.toString();
        } catch (IOException e) {
            throw new IOException("Data was not read");
        }
    }

    public static Item getItem(String response, HttpServletRequest req) throws ParseException {
        JSONObject itemJson = (JSONObject) JSONValue.parseWithException(response);

        Item item = new Item();

        if (req.getMethod().equals("PUT")) {
            try {
                item.setId(Long.parseLong((String)itemJson.get("id")));
            } catch (NumberFormatException e) {
                throw new NumberFormatException("Wrong enter id " + itemJson.get("id"));
            }
        }

        item.setName((String) itemJson.get("name"));
        item.setDescription((String) itemJson.get("description"));

        return item;
    }
}
