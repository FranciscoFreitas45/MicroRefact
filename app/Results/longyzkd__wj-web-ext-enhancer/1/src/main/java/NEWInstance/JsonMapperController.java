package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class JsonMapperController {

 private JsonMapper jsonmapper;


@GetMapping
("/toJsonString")
public String toJsonString(@RequestParam(name = "object") Object object){
  return jsonmapper.toJsonString(object);
}


}