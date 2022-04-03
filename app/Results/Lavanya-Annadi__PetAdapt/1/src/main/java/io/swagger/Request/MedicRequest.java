package io.swagger.Request;
import io.swagger.DTO.Medic;
public interface MedicRequest {

   public Medic getMedic(Long id);
   public void setMedic(Medic medic,Long id);
}