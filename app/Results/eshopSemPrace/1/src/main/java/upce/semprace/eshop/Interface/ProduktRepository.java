package upce.semprace.eshop.Interface;
import upce.semprace.eshop.DTO.Produkt;
import java.util.*;
public interface ProduktRepository {

   public Optional<Produkt> findById(Long id);
   Produkt save(Produkt produkt);
}