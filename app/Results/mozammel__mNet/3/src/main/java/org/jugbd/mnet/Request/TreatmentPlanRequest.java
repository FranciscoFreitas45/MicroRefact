package org.jugbd.mnet.Request;
import org.jugbd.mnet.DTO.TreatmentPlan;
public interface TreatmentPlanRequest {

   public OutdoorRegister setTreatmentPlan(TreatmentPlan treatmentPlan,Long id);
   public TreatmentPlan getTreatmentPlan(Long id);
}