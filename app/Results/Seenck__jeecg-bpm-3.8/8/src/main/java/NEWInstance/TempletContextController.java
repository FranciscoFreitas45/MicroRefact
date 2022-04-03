package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class TempletContextController {

 private TempletContext templetcontext;


@PutMapping
("/removeTemplateFromCache")
public void removeTemplateFromCache(@RequestParam(name = "tableName") String tableName){
templetcontext.removeTemplateFromCache(tableName);
}


}