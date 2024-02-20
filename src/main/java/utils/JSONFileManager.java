package utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;

public class JSONFileManager {

    protected Map<String, Object> jsonData;

    private JSONFileManager(String filePath) {
        try {
            jsonData =  new ObjectMapper().readValue(new File(filePath), new TypeReference<Map<String, Object>>() {});
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static JSONFileManager getInstance(String jsonFilePath) {
        JSONFileManager instance = null;
//        if (instance == null) {
//            instance = new JSONFileManager(jsonFilePath);
//        }
//        return instance;
        return  (Objects.isNull(instance))?   new JSONFileManager(jsonFilePath): instance;
    }

    public Object getProperty(String key) {
//        if (key != null || key.isEmpty()) {
//            return jsonData.get(key.toLowerCase());
//        }
        return (Objects.nonNull(key) || !key.isEmpty())? jsonData.get(key.toLowerCase()): new NullPointerException("The key is null");
    }
}
