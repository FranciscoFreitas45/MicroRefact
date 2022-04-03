package co.edu.uniquindio.gri.Request;
import co.edu.uniquindio.gri.DTO.Tipo;
public interface TipoRequest {

   public void setTipo(Tipo tipo,long id);
   public Tipo getTipo(long id);
}