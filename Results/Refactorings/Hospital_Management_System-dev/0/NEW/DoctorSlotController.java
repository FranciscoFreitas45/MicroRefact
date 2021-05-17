import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
@AllArgsConstructor
@RestController
@CrossOrigin
public class DoctorSlotController {

 private DoctorSlotService doctorslotservice;


@GetMapping
("/Slot/{id}/Doctor/getDoctor")
public Doctor getDoctor(@PathVariable(name="id") Long Long){
doctorslotservice.getDoctor(Long);
}


@PutMapping
("/Slot/{id}/Doctor/setDoctor")
public void setDoctor(@PathVariable(name="id") Long Long,@RequestBody Doctor doctor){
doctorslotservice.setDoctor(Long,doctor);
}


}