package upce.semprace.eshop.Interface;
public interface UzivatelRepository {

   public Boolean existsByUsername(String username);
   public Boolean existsByEmail(String email);
   public Object save(Object Object);
}