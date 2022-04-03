package co.edu.uniquindio.gri.Request;
import co.edu.uniquindio.gri.DTO.Programa;
public interface ProgramaRequest {

   public void setPrograma(List<Programa> programa,long id);
   public List<Programa> getPrograma(long id);
}