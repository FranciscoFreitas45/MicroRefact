package pt.iscte.hospital.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import pt.iscte.hospital.entities.Slot;
@RestController
@CrossOrigin
public class SlotAppointmentController {

@Autowired
 private SlotAppointmentService slotappointmentservice;


@PutMapping
("/Appointment/{id}/Slot/setSlot")
public void setSlot(@PathVariable(name="id") Long slotId,@RequestBody Slot slot){
slotappointmentservice.setSlot(slotId,slot);
}


@GetMapping
("/Appointment/{id}/Slot/getSlot")
public Slot getSlot(@PathVariable(name="id") Long slotId){
return slotappointmentservice.getSlot(slotId);
}


}