package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class OfficeDaoController {

 private OfficeDao officedao;


@GetMapping
("/create")
public Object create(@RequestParam(name = "Object") Object Object){
  return officedao.create(Object);
}


@GetMapping
("/getList")
public Object getList(@RequestParam(name = "Object") Object Object){
  return officedao.getList(Object);
}


@GetMapping
("/delete")
public Object delete(@RequestParam(name = "Object") Object Object){
  return officedao.delete(Object);
}


@GetMapping
("/findById")
public Object findById(@RequestParam(name = "Object") Object Object){
  return officedao.findById(Object);
}


}