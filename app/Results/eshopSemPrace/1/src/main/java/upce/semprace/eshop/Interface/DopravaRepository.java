package upce.semprace.eshop.Interface;
import upce.semprace.eshop.DTO.*;
import java.util.*;
public interface DopravaRepository {

   public Optional<Doprava> findById(Long id);
}