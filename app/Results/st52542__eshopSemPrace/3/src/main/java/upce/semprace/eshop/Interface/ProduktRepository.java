package upce.semprace.eshop.Interface;
public interface ProduktRepository {

   public Optional<Produkt> findById(Long id);
}