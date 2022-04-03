package pt.iscte.hospital.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import pt.iscte.hospital.entities.Slot;
@RestController
@CrossOrigin
public class SlotDoctorController {

@Autowired
 private SlotDoctorService slotdoctorservice;


}