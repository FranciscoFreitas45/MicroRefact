import org.springframework.ui.ModelMap;
import pt.iscte.hospital.entities.Slot;
public interface SlotValidationService {


public SlotValidationService setSlot(Slot slot)


public boolean isValid()


public SlotValidationService clear()


public SlotValidationService validSlot()


public ModelMap getErrorModelMap()


}