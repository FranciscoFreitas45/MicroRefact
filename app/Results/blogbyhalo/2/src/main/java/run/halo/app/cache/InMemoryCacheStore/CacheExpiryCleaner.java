package run.halo.app.cache.InMemoryCacheStore;
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
public class CacheExpiryCleaner extends TimerTask{


@Override
public void run(){
    CACHE_CONTAINER.keySet().forEach(key -> {
        if (!InMemoryCacheStore.this.get(key).isPresent()) {
            log.debug("Deleted the cache: [{}] for expiration", key);
        }
    });
}


}