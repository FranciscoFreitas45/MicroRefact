package restock.Interface;
import restock.DTO.Producte;
public interface ProducteRepository {

   public Producte findById(Integer id);
}