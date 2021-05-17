import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
@AllArgsConstructor
@RestController
@CrossOrigin
public class DoctorSpecialityController {

 private DoctorSpecialityService doctorspecialityservice;


@GetMapping
("/Speciality/{id}/Doctor/getDoctors")
public List<Doctor> getDoctors(@PathVariable(name="id") Long specialityId){
doctorspecialityservice.getDoctors(specialityId);
}


@PutMapping
("/Speciality/{id}/Doctor/setDoctors")
public void setDoctors(@PathVariable(name="id") Long specialityId,@RequestBody List<Doctor> doctors){
doctorspecialityservice.setDoctors(specialityId,doctors);
}


}