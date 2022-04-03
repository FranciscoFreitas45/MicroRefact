package upce.semprace.eshop.Interface;
public interface DopravaRepository {

   public Optional<Doprava> findById(Long id);
}