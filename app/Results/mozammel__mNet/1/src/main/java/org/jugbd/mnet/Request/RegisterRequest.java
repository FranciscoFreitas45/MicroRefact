package org.jugbd.mnet.Request;
import org.jugbd.mnet.DTO.Register;
public interface RegisterRequest {

   public TreatmentPlan setRegister(Register register,Long id);
   public Register getRegister(Long id);
}