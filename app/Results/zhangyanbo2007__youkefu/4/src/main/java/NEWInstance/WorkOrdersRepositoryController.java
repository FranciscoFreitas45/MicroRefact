package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class WorkOrdersRepositoryController {

 private WorkOrdersRepository workordersrepository;


@GetMapping
("/save")
public Object save(@RequestParam(name = "Object") Object Object){
  return workordersrepository.save(Object);
}


@GetMapping
("/findByOrgiAndQualitydisorgan")
public Object findByOrgiAndQualitydisorgan(@RequestParam(name = "Object") Object Object){
  return workordersrepository.findByOrgiAndQualitydisorgan(Object);
}


}