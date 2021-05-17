import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;
@AllArgsConstructor
@RestController
@CrossOrigin
public class SpecialityRepositoryController {

 private SpecialityRepository specialityrepository;


@GetMapping
("/findByName")
public Speciality findByName(@RequestParam(name = "name") String name){
  return specialityrepository.findByName(name);
}


}