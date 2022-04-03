package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class AssetsServiceController {

 private AssetsService assetsservice;


@GetMapping
("/list")
public Object list(@RequestParam(name = "Object") Object Object){
  return assetsservice.list(Object);
}


@GetMapping
("/uniqueEntity")
public Object uniqueEntity(@RequestParam(name = "Object") Object Object){
  return assetsservice.uniqueEntity(Object);
}


@PutMapping
("/delCascade")
public void delCascade(@RequestParam(name = "id") String id){
assetsservice.delCascade(id);
}


@GetMapping
("/addObj")
public Object addObj(@RequestParam(name = "Object") Object Object){
  return assetsservice.addObj(Object);
}


@PutMapping
("/updateForm")
public void updateForm(@RequestParam(name = "data") AssetsType data){
assetsservice.updateForm(data);
}


}