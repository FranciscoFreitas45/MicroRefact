package org.jugbd.mnet.Request;
import org.jugbd.mnet.DTO.Vital;
public interface VitalRequest {

   public OutdoorRegister setVitals(Set<Vital> vitals,Long id);
   public Set<Vital> getVitals(Long id);
}