package org.jugbd.mnet.Request;
import org.jugbd.mnet.DTO.OutdoorRegister;
public interface OutdoorRegisterRequest {

   public Visit setOutdoorRegister(OutdoorRegister outdoorRegister,Long id);
   public OutdoorRegister getOutdoorRegister(Long id);
}