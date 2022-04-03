package pt.iscte.hospital.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import pt.iscte.hospital.repositories.SlotRepository;
import pt.iscte.hospital.entities.Slot;
@Service
public class SlotAppointmentService {

@Autowired
 private SlotRepository slotrepository;


public void setSlot(Long slotId,Slot slot){
slotrepository.setSlot(slotId,slot);
}


public Slot getSlot(Long slotId){
return slotrepository.getSlot(slotId);
}


}