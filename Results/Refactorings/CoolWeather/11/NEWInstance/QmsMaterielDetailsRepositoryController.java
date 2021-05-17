import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;
@AllArgsConstructor
@RestController
@CrossOrigin
public class QmsMaterielDetailsRepositoryController {

 private QmsMaterielDetailsRepository qmsmaterieldetailsrepository;


@GetMapping
("/findById")
public Object findById(@RequestParam(name = "Object") Object Object){
  return qmsmaterieldetailsrepository.findById(Object);
}


@GetMapping
("/findById")
public Object findById(@RequestParam(name = "Object") Object Object){
  return qmsmaterieldetailsrepository.findById(Object);
}


}