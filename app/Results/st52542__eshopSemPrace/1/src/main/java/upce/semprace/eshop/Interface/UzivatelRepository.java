package upce.semprace.eshop.Interface;
public interface UzivatelRepository {

   public Optional<Uzivatel> findByUsername(String username);
   public Object get(Object Object);
   public Object getId(Object Object);
   public Optional<Uzivatel> findById(Long id);
}