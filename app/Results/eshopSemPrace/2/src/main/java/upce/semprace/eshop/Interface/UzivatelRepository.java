package upce.semprace.eshop.Interface;
public interface UzivatelRepository {

   public Optional<Uzivatel> findByUsername(String username);
}