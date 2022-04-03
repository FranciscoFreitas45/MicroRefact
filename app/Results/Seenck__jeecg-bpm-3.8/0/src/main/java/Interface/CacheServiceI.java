package Interface;
public interface CacheServiceI {

   public void put(String cacheName,Object key,Object value);
   public Object get(String cacheName,Object key);
   public void clean(String cacheName);
}