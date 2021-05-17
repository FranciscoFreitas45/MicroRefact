import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;
@AllArgsConstructor
@RestController
@CrossOrigin
public class SpecialityServiceController {

 private SpecialityService specialityservice;


@GetMapping
("/findAll")
public List<Speciality> findAll(){
  return specialityservice.findAll();
}


}