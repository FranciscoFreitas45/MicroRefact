import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
@AllArgsConstructor
@RestController
@CrossOrigin
public class SlotAppointmentController {

 private SlotAppointmentService slotappointmentservice;


@PutMapping
("/Appointment/{id}/Slot/setSlot")
public void setSlot(@PathVariable(name="id") Long Long,@RequestBody Slot slot){
slotappointmentservice.setSlot(Long,slot);
}


@GetMapping
("/Appointment/{id}/Slot/getSlot")
public Slot getSlot(@PathVariable(name="id") Long Long){
slotappointmentservice.getSlot(Long);
}


}