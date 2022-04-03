package pt.iscte.hospital.Request;
import pt.iscte.hospital.DTO.Slot;
public interface SlotRequest {

   public void setSlot(Slot slot,Long slotId);
   public Slot getSlot(Long slotId);
}