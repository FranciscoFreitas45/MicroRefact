package run.halo.app.cache;
 import com.fasterxml.jackson.core.JsonProcessingException;
import java.io.IOException;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.util.Assert;
import run.halo.app.exception.ServiceException;
import run.halo.app.utils.JsonUtils;
@Slf4j
public class AbstractStringCacheStore extends AbstractCacheStore<String, String>{


public Optional<CacheWrapper<String>> jsonToCacheWrapper(String json){
    Assert.hasText(json, "json value must not be null");
    CacheWrapper<String> cacheWrapper = null;
    try {
        cacheWrapper = JsonUtils.jsonToObject(json, CacheWrapper.class);
    } catch (IOException e) {
        log.debug("Failed to convert json to wrapper value bytes: [{}]", json, e);
    }
    return Optional.ofNullable(cacheWrapper);
}


public void putAny(String key,T value,long timeout,TimeUnit timeUnit){
    try {
        put(key, JsonUtils.objectToJson(value), timeout, timeUnit);
    } catch (JsonProcessingException e) {
        throw new ServiceException("Failed to convert " + value + " to json", e);
    }
}


public Optional<T> getAny(String key,Class<T> type){
    Assert.notNull(type, "Type must not be null");
    return get(key).map(value -> {
        try {
            return JsonUtils.jsonToObject(value, type);
        } catch (IOException e) {
            log.error("Failed to convert json to type: " + type.getName(), e);
            return null;
        }
    });
}


}