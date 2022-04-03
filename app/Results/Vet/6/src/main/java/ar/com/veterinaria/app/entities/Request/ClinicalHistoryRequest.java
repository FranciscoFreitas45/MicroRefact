package ar.com.veterinaria.app.entities.Request;
import ar.com.veterinaria.app.entities.DTO.ClinicalHistory;
public interface ClinicalHistoryRequest {

   public void setClinicalHistory(ClinicalHistory clinicalHistory,Integer id);
   public ClinicalHistory getClinicalHistory(Integer id);
}