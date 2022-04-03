package pt.iscte.hospital.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import pt.iscte.hospital.entities.Speciality;
@RestController
@CrossOrigin
public class SpecialityDoctorController {

@Autowired
 private SpecialityDoctorService specialitydoctorservice;


@GetMapping
("/Doctor/{id}/Speciality/getSpeciality")
public Speciality getSpeciality(@PathVariable(name="id") Long specialityId){
return specialitydoctorservice.getSpeciality(specialityId);
}


@PutMapping
("/Doctor/{id}/Speciality/setSpeciality")
public void setSpeciality(@PathVariable(name="id") Long specialityId,@RequestBody Speciality speciality){
specialitydoctorservice.setSpeciality(specialityId,speciality);
}


}