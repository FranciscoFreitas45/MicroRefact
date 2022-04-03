package org.jugbd.mnet.Request;
import org.jugbd.mnet.DTO.OutdoorRegister;
public interface OutdoorRegisterRequest {

   public Set<OutdoorRegister> getOutdoorRegisters(Long id);
   public Patient setOutdoorRegisters(Set<OutdoorRegister> outdoorRegisters,Long id);
}