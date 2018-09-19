package utils;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import entity.Item;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;

public class JsonUtil {
    private static ObjectMapper objectMapper;

    public static Item getItem(String jsonContent) throws IOException {
        objectMapper = createObjectMapper();

        try {
            return objectMapper.readValue(jsonContent, Item.class);
        } catch (JsonParseException | JsonMappingException e) {
            throw new IOException("Can't process json content");
        } catch (IOException e) {
            throw new IOException("Data was not read");
        }
    }

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

    private static ObjectMapper createObjectMapper() {
        if (objectMapper == null)
            objectMapper = new ObjectMapper();

        return objectMapper;
    }
}
