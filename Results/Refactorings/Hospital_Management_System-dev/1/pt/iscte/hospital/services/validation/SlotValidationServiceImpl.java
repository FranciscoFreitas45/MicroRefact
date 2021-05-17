import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import pt.iscte.hospital.entities.Slot;
import pt.iscte.hospital.repositories.SlotRepository;
import pt.iscte.hospital.services.ErrorMessage;
@Service
public class SlotValidationServiceImpl implements SlotValidationService,pt.iscte.hospital.services.validation.SlotValidationService{

@Autowired
 private SlotRepository slotRepository;

 private  Slot slot;

 private  boolean isValid;

 private  ModelMap errorModelMap;


@Override
public SlotValidationService setSlot(Slot slot){
    this.slot = slot;
    return this;
}


@Override
public boolean isValid(){
    return isValid;
}


@Override
public SlotValidationService clear(){
    errorModelMap = new ModelMap();
    slot = null;
    isValid = true;
    return this;
}


@Override
public SlotValidationService validSlot(){
    Slot extraSlot = slotRepository.findByDoctorAndDateAndTimeBegin(slot.getDoctor(), slot.getDate(), slot.getTimeBegin());
    if (extraSlot != null) {
        isValid = false;
        errorModelMap.put("errorMsgSlot", ErrorMessage.SLOT_DUPLICATED.getErrorMsg());
    }
    return this;
}


@Override
public ModelMap getErrorModelMap(){
    return errorModelMap;
}


}