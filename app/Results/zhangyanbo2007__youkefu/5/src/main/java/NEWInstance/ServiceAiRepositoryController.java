package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ServiceAiRepositoryController {

 private ServiceAiRepository serviceairepository;


@GetMapping
("/findByOrgi")
public Page<ServiceAi> findByOrgi(@RequestParam(name = "orgi") String orgi,@RequestParam(name = "page") Pageable page){
  return serviceairepository.findByOrgi(orgi,page);
}


}