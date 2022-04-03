package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class SystemMessageRepositoryController {

 private SystemMessageRepository systemmessagerepository;


@GetMapping
("/findByOrgi")
public List<SystemMessage> findByOrgi(@RequestParam(name = "orgi") String orgi){
  return systemmessagerepository.findByOrgi(orgi);
}


}