import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;
@AllArgsConstructor
@RestController
@CrossOrigin
public class QmsProcessRepositoryController {

 private QmsProcessRepository qmsprocessrepository;


@GetMapping
("/findByFlagStatusAndId")
public QmsProcess findByFlagStatusAndId(@RequestParam(name = "string") String string,@RequestParam(name = "valueOf") Long valueOf){
  return qmsprocessrepository.findByFlagStatusAndId(string,valueOf);
}


}