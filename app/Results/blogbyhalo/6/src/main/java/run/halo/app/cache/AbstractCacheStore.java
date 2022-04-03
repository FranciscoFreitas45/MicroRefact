package run.halo.app.cache;
 import java.util.Date;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import run.halo.app.config.properties.HaloProperties;
import run.halo.app.utils.DateUtils;
import run.halo.app.Interface.HaloProperties;
@Slf4j
public class AbstractCacheStore implements CacheStore<K, V>{

 protected  HaloProperties haloProperties;


public void putInternal(K key,CacheWrapper<V> cacheWrapper)


@Override
public Optional<V> get(K key){
    Assert.notNull(key, "Cache key must not be blank");
    return getInternal(key).map(cacheWrapper -> {
        // Check expiration
        if (cacheWrapper.getExpireAt() != null && cacheWrapper.getExpireAt().before(run.halo.app.utils.DateUtils.now())) {
            // Expired then delete it
            log.warn("Cache key: [{}] has been expired", key);
            // Delete the key
            delete(key);
            // Return null
            return null;
        }
        return cacheWrapper.getData();
    });
}


public Boolean putInternalIfAbsent(K key,CacheWrapper<V> cacheWrapper)


@NonNull
public Optional<CacheWrapper<V>> getInternal(K key)


@Override
public Boolean putIfAbsent(K key,V value,long timeout,TimeUnit timeUnit){
    return putInternalIfAbsent(key, buildCacheWrapper(value, timeout, timeUnit));
}


@NonNull
public CacheWrapper<V> buildCacheWrapper(V value,long timeout,TimeUnit timeUnit){
    Assert.notNull(value, "Cache value must not be null");
    Assert.isTrue(timeout >= 0, "Cache expiration timeout must not be less than 1");
    Date now = run.halo.app.utils.DateUtils.now();
    Date expireAt = null;
    if (timeout > 0 && timeUnit != null) {
        expireAt = DateUtils.add(now, timeout, timeUnit);
    }
    // Build cache wrapper
    CacheWrapper<V> cacheWrapper = new CacheWrapper<>();
    cacheWrapper.setCreateAt(now);
    cacheWrapper.setExpireAt(expireAt);
    cacheWrapper.setData(value);
    return cacheWrapper;
}


@Override
public void put(K key,V value){
    putInternal(key, buildCacheWrapper(value, 0, null));
}


}