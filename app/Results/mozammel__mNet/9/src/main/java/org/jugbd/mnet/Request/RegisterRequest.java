package org.jugbd.mnet.Request;
import org.jugbd.mnet.DTO.Register;
public interface RegisterRequest {

   public void setRegisters(Set<Register> registers,Long id);
   public Set<Register> getRegisters(Long id);
}