package org.jugbd.mnet.Request;
import org.jugbd.mnet.DTO.Register;
public interface RegisterRequest {

   public Register getRegister(Long id);
   public void setRegister(Register register,Long id);
}