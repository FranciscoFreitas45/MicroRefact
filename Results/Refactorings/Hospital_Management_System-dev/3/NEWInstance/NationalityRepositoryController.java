import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;
@AllArgsConstructor
@RestController
@CrossOrigin
public class NationalityRepositoryController {

 private NationalityRepository nationalityrepository;


@GetMapping
("/findByName")
public Nationality findByName(@RequestParam(name = "name") String name){
  return nationalityrepository.findByName(name);
}


}