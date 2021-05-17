import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
@AllArgsConstructor
@RestController
@CrossOrigin
public class SpecialityDoctorController {

 private SpecialityDoctorService specialitydoctorservice;


@GetMapping
("/Doctor/{id}/Speciality/getSpeciality")
public Speciality getSpeciality(@PathVariable(name="id") Long Long){
specialitydoctorservice.getSpeciality(Long);
}


@PutMapping
("/Doctor/{id}/Speciality/setSpeciality")
public void setSpeciality(@PathVariable(name="id") Long Long,@RequestBody Speciality speciality){
specialitydoctorservice.setSpeciality(Long,speciality);
}


}