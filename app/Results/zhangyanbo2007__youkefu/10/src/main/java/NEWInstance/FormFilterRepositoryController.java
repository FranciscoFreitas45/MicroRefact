package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class FormFilterRepositoryController {

 private FormFilterRepository formfilterrepository;


@GetMapping
("/findByIdAndOrgi")
public FormFilter findByIdAndOrgi(@RequestParam(name = "id") String id,@RequestParam(name = "orgi") String orgi){
  return formfilterrepository.findByIdAndOrgi(id,orgi);
}


@GetMapping
("/save")
public Object save(@RequestParam(name = "Object") Object Object){
  return formfilterrepository.save(Object);
}


}