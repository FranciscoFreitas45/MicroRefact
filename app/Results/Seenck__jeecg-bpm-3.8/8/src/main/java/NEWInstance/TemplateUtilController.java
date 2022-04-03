package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class TemplateUtilController {

 private TemplateUtil templateutil;

 private TemplateUtil templateutil;


@GetMapping
("/processor")
public Map<String,Object> processor(@RequestParam(name = "content") String content){
  return templateutil.processor(content);
}


}