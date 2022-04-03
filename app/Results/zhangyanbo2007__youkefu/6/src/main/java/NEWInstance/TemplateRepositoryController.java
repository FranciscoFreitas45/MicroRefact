package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class TemplateRepositoryController {

 private TemplateRepository templaterepository;


@GetMapping
("/findByTemplettypeAndOrgi")
public List<Template> findByTemplettypeAndOrgi(@RequestParam(name = "templettype") String templettype,@RequestParam(name = "orgi") String orgi){
  return templaterepository.findByTemplettypeAndOrgi(templettype,orgi);
}


@GetMapping
("/findByIdAndOrgi")
public Template findByIdAndOrgi(@RequestParam(name = "id") String id,@RequestParam(name = "orgi") String orgi){
  return templaterepository.findByIdAndOrgi(id,orgi);
}


}