package org.jugbd.mnet.Request;
import org.jugbd.mnet.DTO.Register;
public interface RegisterRequest {

   public Vital setRegister(Register register,Long id);
   public Register getRegister(Long id);
}