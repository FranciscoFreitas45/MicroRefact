import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;
@AllArgsConstructor
@RestController
@CrossOrigin
public class UtAreaEnRepositoryController {

 private UtAreaEnRepository utareaenrepository;


@GetMapping
("/findAll")
public List<UtAreaEn> findAll(){
  return utareaenrepository.findAll();
}


@GetMapping
("/findByAreaShortName")
public List<UtAreaEn> findByAreaShortName(@RequestParam(name = "string") String string){
  return utareaenrepository.findByAreaShortName(string);
}


}