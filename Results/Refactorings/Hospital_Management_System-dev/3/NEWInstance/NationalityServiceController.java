import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;
@AllArgsConstructor
@RestController
@CrossOrigin
public class NationalityServiceController {

 private NationalityService nationalityservice;


@GetMapping
("/findAll")
public List<Nationality> findAll(){
  return nationalityservice.findAll();
}


}