package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class QuickReplyRepositoryController {

 private QuickReplyRepository quickreplyrepository;


@GetMapping
("/findByOrgiAndCreater")
public Object findByOrgiAndCreater(@RequestParam(name = "Object") Object Object){
  return quickreplyrepository.findByOrgiAndCreater(Object);
}


@GetMapping
("/save")
public Object save(@RequestParam(name = "Object") Object Object){
  return quickreplyrepository.save(Object);
}


@GetMapping
("/findOne")
public Object findOne(@RequestParam(name = "Object") Object Object){
  return quickreplyrepository.findOne(Object);
}


@GetMapping
("/delete")
public Object delete(@RequestParam(name = "Object") Object Object){
  return quickreplyrepository.delete(Object);
}


@GetMapping
("/getByOrgiAndCate")
public Object getByOrgiAndCate(@RequestParam(name = "Object") Object Object){
  return quickreplyrepository.getByOrgiAndCate(Object);
}


}