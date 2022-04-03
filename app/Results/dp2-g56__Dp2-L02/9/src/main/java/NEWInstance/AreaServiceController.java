package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class AreaServiceController {

 private AreaService areaservice;


@GetMapping
("/findAll")
public List<Area> findAll(){
  return areaservice.findAll();
}


@GetMapping
("/isEmpty")
public Object isEmpty(@RequestParam(name = "Object") Object Object){
  return areaservice.isEmpty(Object);
}


}