package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class CallOutTaskRepositoryController {

 private CallOutTaskRepository callouttaskrepository;


@GetMapping
("/findAll")
public Page<CallOutTask> findAll(@RequestParam(name = "spec") Specification<CallOutTask> spec,@RequestParam(name = "pageable") Pageable pageable){
  return callouttaskrepository.findAll(spec,pageable);
}


@GetMapping
("/save")
public Object save(@RequestParam(name = "Object") Object Object){
  return callouttaskrepository.save(Object);
}


}