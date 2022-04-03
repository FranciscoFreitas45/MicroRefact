package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class CallOutFilterRepositoryController {

 private CallOutFilterRepository calloutfilterrepository;


@GetMapping
("/save")
public Object save(@RequestParam(name = "Object") Object Object){
  return calloutfilterrepository.save(Object);
}


@GetMapping
("/findAll")
public Page<CallOutFilter> findAll(@RequestParam(name = "spec") Specification<CallOutFilter> spec,@RequestParam(name = "pageable") Pageable pageable){
  return calloutfilterrepository.findAll(spec,pageable);
}


}