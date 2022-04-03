package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class TagRepositoryController {

 private TagRepository tagrepository;


@GetMapping
("/findByOrgiAndTagtype")
public List<Tag> findByOrgiAndTagtype(@RequestParam(name = "orgi") String orgi,@RequestParam(name = "tagtype") String tagtype){
  return tagrepository.findByOrgiAndTagtype(orgi,tagtype);
}


}