package restock.Interface;
public interface BotigaRepository {

   public Botiga findById(Integer id);
   public List<Botiga> findByOrganitzacioId(Integer organitzacioId);
}