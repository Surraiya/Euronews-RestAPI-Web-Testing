package Utils;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.SneakyThrows;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class JsonFileReader {
    private static final Map<String, ObjectNode> jsonObjects = new HashMap<>();
    private static final ObjectMapper mapper = new ObjectMapper(new JsonFactory());

    static {
        loadJsonFiles();
    }

    @SneakyThrows
    private static void loadJsonFiles() {
        Files.walk(Path.of(""))
                .filter(Files::isRegularFile)
                .filter(file -> file.getFileName().toString().endsWith(".json"))
                .forEach(file -> {
                    try {
                        String content = new String(Files.readAllBytes(file));
                        ObjectNode jsonObject = mapper.readValue(content, ObjectNode.class);
                        String filename = file.getFileName().toString().replace(".json","");
                        jsonObjects.put(filename, jsonObject);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
    }

    private static boolean isFileExist(String fileName){
        if (!jsonObjects.containsKey(fileName)) {
            throw new IllegalArgumentException(String.format("File not found: %s", fileName));
        } else return true;
    }

    private static boolean isKeyExist(JsonNode jsonNode){
        if (jsonNode == null || jsonNode.isNull() || !jsonNode.isTextual()) {
            throw new IllegalArgumentException("Key not found or not a string value: ");
        } else return true;
    }

    public static String getStringValue(String fileName, String key) {
        if(isFileExist(fileName)){
            JsonNode jsonNode = jsonObjects.get(fileName).get(key);
            if(isKeyExist(jsonNode)){
                return jsonNode.asText();
            }
        }
        return null;
    }

    public static int getIntValue(String fileName, String key) {
        if(isFileExist(fileName)){
            JsonNode jsonNode = jsonObjects.get(fileName).get(key);
            if(isKeyExist(jsonNode)){
                return jsonNode.asInt();
            }
        }
        return 0;
    }
}
