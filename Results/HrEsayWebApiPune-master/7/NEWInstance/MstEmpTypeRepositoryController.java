import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;
@AllArgsConstructor
@RestController
@CrossOrigin
public class MstEmpTypeRepositoryController {

 private MstEmpTypeRepository mstemptyperepository;


@GetMapping
("/findAll")
public Object findAll(@RequestParam(name = "Object") Object Object){
  return mstemptyperepository.findAll(Object);
}


}