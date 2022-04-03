package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class AreaTypeRepositoryController {

 private AreaTypeRepository areatyperepository;


@GetMapping
("/findByOrgi")
public List<AreaType> findByOrgi(@RequestParam(name = "orgi") String orgi){
  return areatyperepository.findByOrgi(orgi);
}


@GetMapping
("/findByIdAndOrgi")
public AreaType findByIdAndOrgi(@RequestParam(name = "id") String id,@RequestParam(name = "orgi") String orgi){
  return areatyperepository.findByIdAndOrgi(id,orgi);
}


}