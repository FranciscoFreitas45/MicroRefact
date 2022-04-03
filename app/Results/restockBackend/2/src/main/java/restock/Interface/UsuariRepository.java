package restock.Interface;
import restock.DTO.Producte;
public interface UsuariRepository {

   public Usuari findByUserAndOrganitzacioId(String user,Integer organitzacioId);
   public Usuari findById(Integer usuariId);
   public Object save(Object Object);
}