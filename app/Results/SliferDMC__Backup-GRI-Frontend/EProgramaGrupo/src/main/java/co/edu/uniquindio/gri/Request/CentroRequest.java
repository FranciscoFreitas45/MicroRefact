package co.edu.uniquindio.gri.Request;
import co.edu.uniquindio.gri.DTO.Centro;
public interface CentroRequest {

   public Centro getCentro(long id);
   public void setCentro(Centro centro,long id);
}