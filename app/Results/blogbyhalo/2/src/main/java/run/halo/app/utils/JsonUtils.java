package run.halo.app.utils;
 import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import java.io.IOException;
import java.util.Map;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
public class JsonUtils {

 public  ObjectMapper DEFAULT_JSON_MAPPER;

private JsonUtils() {
}
@NonNull
public Map<?,?> objectToMap(Object source,ObjectMapper objectMapper){
    // Serialize the source object
    String json = objectToJson(source, objectMapper);
    // Deserialize the json
    return jsonToObject(json, Map.class, objectMapper);
}


@NonNull
public T jsonToObject(String json,Class<T> type,ObjectMapper objectMapper){
    Assert.hasText(json, "Json content must not be blank");
    Assert.notNull(type, "Target type must not be null");
    Assert.notNull(objectMapper, "Object mapper must not null");
    return objectMapper.readValue(json, type);
}


@NonNull
public ObjectMapper createDefaultJsonMapper(PropertyNamingStrategy strategy){
    // Create object mapper
    ObjectMapper mapper = new ObjectMapper();
    // Configure
    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    // Set property naming strategy
    if (strategy != null) {
        mapper.setPropertyNamingStrategy(strategy);
    }
    return mapper;
}


@NonNull
public T mapToObject(Map<String,?> sourceMap,Class<T> type,ObjectMapper objectMapper){
    Assert.notEmpty(sourceMap, "Source map must not be empty");
    // Serialize the map
    String json = objectToJson(sourceMap, objectMapper);
    // Deserialize the json format of the map
    return jsonToObject(json, type, objectMapper);
}


@NonNull
public String objectToJson(Object source,ObjectMapper objectMapper){
    Assert.notNull(source, "Source object must not be null");
    Assert.notNull(objectMapper, "Object mapper must not null");
    return objectMapper.writeValueAsString(source);
}


}