import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;
@AllArgsConstructor
@RestController
@CrossOrigin
public class DoctorServiceController {

 private DoctorService doctorservice;


@GetMapping
("/findAll")
public List<Doctor> findAll(){
  return doctorservice.findAll();
}


}