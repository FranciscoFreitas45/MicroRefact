package upce.semprace.eshop.Interface;
import java.util.*;
import upce.semprace.eshop.DTO.*;
public interface NakoupenaPolozkaRepository {

   public Object save(Object Object);
   public List<NakoupenaPolozka> findAll();

}