package run.halo.app.cache;
 import java.util.Optional;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import javax.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.util.Assert;
@Slf4j
public class InMemoryCacheStore extends AbstractStringCacheStore{

 private  long PERIOD;

 private  ConcurrentHashMap<String,CacheWrapper<String>> CACHE_CONTAINER;

 private  Timer timer;

 private  Lock lock;

public InMemoryCacheStore() {
    // Run a cache store cleaner
    timer = new Timer();
    timer.scheduleAtFixedRate(new CacheExpiryCleaner(), 0, PERIOD);
}
@Override
public void putInternal(String key,CacheWrapper<String> cacheWrapper){
    Assert.hasText(key, "Cache key must not be blank");
    Assert.notNull(cacheWrapper, "Cache wrapper must not be null");
    // Put the cache wrapper
    CacheWrapper<String> putCacheWrapper = CACHE_CONTAINER.put(key, cacheWrapper);
    log.debug("Put [{}] cache result: [{}], original cache wrapper: [{}]", key, putCacheWrapper, cacheWrapper);
}


@Override
public Boolean putInternalIfAbsent(String key,CacheWrapper<String> cacheWrapper){
    Assert.hasText(key, "Cache key must not be blank");
    Assert.notNull(cacheWrapper, "Cache wrapper must not be null");
    log.debug("Preparing to put key: [{}], value: [{}]", key, cacheWrapper);
    lock.lock();
    try {
        // Get the value before
        Optional<String> valueOptional = get(key);
        if (valueOptional.isPresent()) {
            log.warn("Failed to put the cache, because the key: [{}] has been present already", key);
            return false;
        }
        // Put the cache wrapper
        putInternal(key, cacheWrapper);
        log.debug("Put successfully");
        return true;
    } finally {
        lock.unlock();
    }
}


public void clear(){
    CACHE_CONTAINER.clear();
}


@Override
@NonNull
public Optional<CacheWrapper<String>> getInternal(String key){
    Assert.hasText(key, "Cache key must not be blank");
    return Optional.ofNullable(CACHE_CONTAINER.get(key));
}


@PreDestroy
public void preDestroy(){
    log.debug("Cancelling all timer tasks");
    timer.cancel();
    clear();
}


@Override
public void run(){
    CACHE_CONTAINER.keySet().forEach(key -> {
        if (!InMemoryCacheStore.this.get(key).isPresent()) {
            log.debug("Deleted the cache: [{}] for expiration", key);
        }
    });
}


@Override
public void delete(String key){
    Assert.hasText(key, "Cache key must not be blank");
    CACHE_CONTAINER.remove(key);
    log.debug("Removed key: [{}]", key);
}


}