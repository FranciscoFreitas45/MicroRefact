package Interface;
public interface CacheServiceI {

   public Object get(String cacheName,Object key);
   public void put(String cacheName,Object key,Object value);
   public boolean remove(String cacheName,Object key);
   public void clean(String cacheName);
}