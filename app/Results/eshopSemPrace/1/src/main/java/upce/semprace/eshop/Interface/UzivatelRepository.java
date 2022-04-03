package upce.semprace.eshop.Interface;
import upce.semprace.eshop.DTO.Uzivatel;
import java.util.*;
public interface UzivatelRepository {

   public Optional<Uzivatel> findByUsername(String username);
   public Object get(Object Object);
   public Object getId(Object Object);
   public Optional<Uzivatel> findById(Long id);
}