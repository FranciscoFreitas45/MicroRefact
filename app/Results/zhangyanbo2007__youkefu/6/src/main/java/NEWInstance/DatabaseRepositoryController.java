package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class DatabaseRepositoryController {

 private DatabaseRepository databaserepository;


@GetMapping
("/findByIdAndOrgi")
public Database findByIdAndOrgi(@RequestParam(name = "id") String id,@RequestParam(name = "orgi") String orgi){
  return databaserepository.findByIdAndOrgi(id,orgi);
}


}