package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class OrgiSkillRelRepositoryController {

 private OrgiSkillRelRepository orgiskillrelrepository;


@GetMapping
("/findByOrgi")
public List<OrgiSkillRel> findByOrgi(@RequestParam(name = "orgi") String orgi){
  return orgiskillrelrepository.findByOrgi(orgi);
}


}