package run.halo.app.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class CategoryServiceController {

 private CategoryService categoryservice;


@GetMapping
("/count")
public Object count(@RequestParam(name = "Object") Object Object){
  return categoryservice.count(Object);
}


@GetMapping
("/create")
public Object create(@RequestParam(name = "Object") Object Object){
  return categoryservice.create(Object);
}


@GetMapping
("/listAll")
public List<Category> listAll(@RequestParam(name = "sort") Sort sort,@RequestParam(name = "queryEncryptCategory") boolean queryEncryptCategory){
  return categoryservice.listAll(sort,queryEncryptCategory);
}


@GetMapping
("/createInBatch")
public Object createInBatch(@RequestParam(name = "Object") Object Object){
  return categoryservice.createInBatch(Object);
}


}