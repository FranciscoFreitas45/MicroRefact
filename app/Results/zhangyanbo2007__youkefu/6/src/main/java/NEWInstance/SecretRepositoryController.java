package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class SecretRepositoryController {

 private SecretRepository secretrepository;


@GetMapping
("/findByOrgi")
public List<Secret> findByOrgi(@RequestParam(name = "orgi") String orgi){
  return secretrepository.findByOrgi(orgi);
}


}