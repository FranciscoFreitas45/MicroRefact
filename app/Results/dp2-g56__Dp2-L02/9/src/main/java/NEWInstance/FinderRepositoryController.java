package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class FinderRepositoryController {

 private FinderRepository finderrepository;


@GetMapping
("/getParadesByArea")
public List<Parade> getParadesByArea(@RequestParam(name = "area") String area){
  return finderrepository.getParadesByArea(area);
}


}