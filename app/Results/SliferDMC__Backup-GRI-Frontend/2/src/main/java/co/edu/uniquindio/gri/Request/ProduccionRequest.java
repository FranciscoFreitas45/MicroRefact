package co.edu.uniquindio.gri.Request;
import co.edu.uniquindio.gri.DTO.Produccion;
public interface ProduccionRequest {

   public void setProducciones(List<Produccion> producciones,long id);
   public List<Produccion> getProducciones(long id);
}