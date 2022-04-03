package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class UserEventRepositoryController {

 private UserEventRepository usereventrepository;


@GetMapping
("/findByOrgiAndCreatetimeRange")
public List<Object> findByOrgiAndCreatetimeRange(@RequestParam(name = "orgi") String orgi,@RequestParam(name = "start") Date start,@RequestParam(name = "end") Date end){
  return usereventrepository.findByOrgiAndCreatetimeRange(orgi,start,end);
}


}