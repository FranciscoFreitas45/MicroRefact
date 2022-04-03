package run.halo.app.Interface;
public interface AbstractStringCacheStore {

   public Optional<T> getAny(String key,Class<T> type);
   public void putAny(String key,T value,long timeout,TimeUnit timeUnit);
}