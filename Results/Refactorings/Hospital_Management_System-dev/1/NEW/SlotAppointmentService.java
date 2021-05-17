import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
@AllArgsConstructor
@Service
public class SlotAppointmentService {

 private SlotRepository slotrepository;


public void setSlot(Long Long,Slot slot){
slotrepository.setSlot(Long,slot);
}


public Slot getSlot(Long Long){
slotrepository.getSlot(Long);
}


}