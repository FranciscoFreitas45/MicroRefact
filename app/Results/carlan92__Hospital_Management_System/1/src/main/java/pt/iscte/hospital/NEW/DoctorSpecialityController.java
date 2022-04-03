package pt.iscte.hospital.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import pt.iscte.hospital.entities.Doctor;
@RestController
@CrossOrigin
public class DoctorSpecialityController {

@Autowired
 private DoctorSpecialityService doctorspecialityservice;


@GetMapping
("/Speciality/{id}/Doctor/getDoctors")
public List<Doctor> getDoctors(@PathVariable(name="id") Long specialityId){
return doctorspecialityservice.getDoctors(specialityId);
}


@PutMapping
("/Speciality/{id}/Doctor/setDoctors")
public void setDoctors(@PathVariable(name="id") Long specialityId,@RequestBody List<Doctor> doctors){
doctorspecialityservice.setDoctors(specialityId,doctors);
}


}