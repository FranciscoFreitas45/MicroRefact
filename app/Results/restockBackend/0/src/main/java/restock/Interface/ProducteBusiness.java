package restock.Interface;
import restock.DTO.Producte;
import java.util.*;
public interface ProducteBusiness {

   public List<Producte> getProductesPerProveidor(Integer provId);
}