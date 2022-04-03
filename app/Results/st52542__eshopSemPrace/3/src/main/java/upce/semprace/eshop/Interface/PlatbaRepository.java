package upce.semprace.eshop.Interface;
public interface PlatbaRepository {

   public Optional<Platba> findById(Long id);
}