package pt.iscte.hospital.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import pt.iscte.hospital.entities.Doctor;
@RestController
@CrossOrigin
public class DoctorSlotController {

@Autowired
 private DoctorSlotService doctorslotservice;


@GetMapping
("/Slot/{id}/Doctor/getDoctor")
public Doctor getDoctor(@PathVariable(name="id") Long userId){
return doctorslotservice.getDoctor(userId);
}


@PutMapping
("/Slot/{id}/Doctor/setDoctor")
public void setDoctor(@PathVariable(name="id") Long userId,@RequestBody Doctor doctor){
doctorslotservice.setDoctor(userId,doctor);
}


}