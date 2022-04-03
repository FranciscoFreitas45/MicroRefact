package upce.semprace.eshop.Interface;
import upce.semprace.eshop.DTO.Platba;
import java.util.*;
public interface PlatbaRepository {

   public Optional<Platba> findById(Long id);
}