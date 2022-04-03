package run.halo.app.cache;
 import java.util.Optional;
import java.util.concurrent.TimeUnit;
import org.springframework.lang.NonNull;
public interface CacheStore {


@NonNull
public Optional<V> get(K key)
;

public Boolean putIfAbsent(K key,V value,long timeout,TimeUnit timeUnit)
;

public void delete(K key)
;

public void put(K key,V value)
;

}