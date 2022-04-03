package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class FinderServiceController {

 private FinderService finderservice;


@GetMapping
("/findAll")
public List<Finder> findAll(){
  return finderservice.findAll();
}


@PutMapping
("/updateAllFinders")
public void updateAllFinders(){
finderservice.updateAllFinders();
}


}