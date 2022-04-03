package restock.Interface;
import restock.DTO.Botiga;
public interface BotigaRepository {

   public Botiga findById(Integer id);
   public List<Botiga> findByOrganitzacioId(Integer organitzacioId);
}