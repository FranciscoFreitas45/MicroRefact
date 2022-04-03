package run.halo.app.Interface;
public interface AbstractStringCacheStore {

   public void putAny(String key,T value,long timeout,TimeUnit timeUnit);
   public Optional<T> getAny(String key,Class<T> type);
}