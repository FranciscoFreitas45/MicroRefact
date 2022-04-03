package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ReflectHelperController {

 private ReflectHelper reflecthelper;

 private ReflectHelper reflecthelper;


@GetMapping
("/setMethodValue")
public boolean setMethodValue(@RequestParam(name = "property") String property,@RequestParam(name = "object") Object object){
  return reflecthelper.setMethodValue(property,object);
}


}