package run.halo.app.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class OptionRepositoryController {

 private OptionRepository optionrepository;


@GetMapping
("/findByKey")
public Optional<Option> findByKey(@RequestParam(name = "key") String key){
  return optionrepository.findByKey(key);
}


@GetMapping
("/save")
public Object save(@RequestParam(name = "Object") Object Object){
  return optionrepository.save(Object);
}


}