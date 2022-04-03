package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class TempletContextWordController {

 private TempletContextWord templetcontextword;


@GetMapping
("/autoFormGenerateHtml")
public String autoFormGenerateHtml(@RequestParam(name = "tableName") String tableName,@RequestParam(name = "id") String id,@RequestParam(name = "mode") String mode){
  return templetcontextword.autoFormGenerateHtml(tableName,id,mode);
}


}