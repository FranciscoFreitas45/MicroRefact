package co.edu.uniquindio.gri.Request;
import co.edu.uniquindio.gri.DTO.Grupo;
public interface GrupoRequest {

   public void setGrupo(List<Grupo> grupo,long id);
   public List<Grupo> getGrupo(long id);
}