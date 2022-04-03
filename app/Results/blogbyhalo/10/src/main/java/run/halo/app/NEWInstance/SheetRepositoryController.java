package run.halo.app.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class SheetRepositoryController {

 private SheetRepository sheetrepository;


@GetMapping
("/findById")
public Object findById(@RequestParam(name = "Object") Object Object){
  return sheetrepository.findById(Object);
}


@GetMapping
("/getOne")
public Object getOne(@RequestParam(name = "Object") Object Object){
  return sheetrepository.getOne(Object);
}


@GetMapping
("/findAllById")
public Object findAllById(@RequestParam(name = "Object") Object Object){
  return sheetrepository.findAllById(Object);
}


}