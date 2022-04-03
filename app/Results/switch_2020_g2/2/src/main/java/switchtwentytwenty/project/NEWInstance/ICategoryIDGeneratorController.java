package switchtwentytwenty.project.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ICategoryIDGeneratorController {

 private ICategoryIDGenerator icategoryidgenerator;


@GetMapping
("/generate")
public CategoryID generate(){
  return icategoryidgenerator.generate();
}


}