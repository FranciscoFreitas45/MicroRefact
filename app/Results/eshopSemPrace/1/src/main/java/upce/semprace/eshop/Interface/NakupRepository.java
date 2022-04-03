package upce.semprace.eshop.Interface;
import java.util.*;
import upce.semprace.eshop.DTO.*;
public interface NakupRepository {

   public Object save(Object Object);
   public List<Nakup> findAll();
}