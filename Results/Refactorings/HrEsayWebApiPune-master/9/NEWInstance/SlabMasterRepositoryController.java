import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;
@AllArgsConstructor
@RestController
@CrossOrigin
public class SlabMasterRepositoryController {

 private SlabMasterRepository slabmasterrepository;


@GetMapping
("/findAll")
public Object findAll(@RequestParam(name = "Object") Object Object){
  return slabmasterrepository.findAll(Object);
}


}