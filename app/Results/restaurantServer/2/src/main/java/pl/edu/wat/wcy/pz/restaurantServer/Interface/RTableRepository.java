package pl.edu.wat.wcy.pz.restaurantServer.Interface;
import java.util.*;
import pl.edu.wat.wcy.pz.restaurantServer.DTO.RTable;

public interface RTableRepository {

   public Optional<RTable> findById(Object Object);
}