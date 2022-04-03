package pl.edu.wat.wcy.pz.restaurantServer.Interface;
import pl.edu.wat.wcy.pz.restaurantServer.DTO.RTable;
import java.util.*;
public interface RTableService {

   public Optional<RTable> getRTableById(Long id);
   public List<RTable> getRTablesBySize(int size);
}