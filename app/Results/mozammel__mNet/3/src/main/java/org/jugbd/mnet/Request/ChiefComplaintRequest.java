package org.jugbd.mnet.Request;
import org.jugbd.mnet.DTO.ChiefComplaint;
public interface ChiefComplaintRequest {

   public OutdoorRegister setChiefComplaint(ChiefComplaint chiefComplaint,Long id);
   public ChiefComplaint getChiefComplaint(Long id);
}