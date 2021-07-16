import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;
@AllArgsConstructor
@RestController
@CrossOrigin
public class RTableRepositoryController {

 private RTableRepository rtablerepository;


@GetMapping
("/findById")
public Object findById(@RequestParam(name = "Object") Object Object){
  return rtablerepository.findById(Object);
}


}