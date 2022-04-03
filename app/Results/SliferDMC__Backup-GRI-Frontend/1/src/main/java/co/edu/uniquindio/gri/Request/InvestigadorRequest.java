package co.edu.uniquindio.gri.Request;
import co.edu.uniquindio.gri.DTO.Investigador;
public interface InvestigadorRequest {

   public Investigador getInvestigador(long id);
   public void setInvestigador(Investigador investigador,long id);
}