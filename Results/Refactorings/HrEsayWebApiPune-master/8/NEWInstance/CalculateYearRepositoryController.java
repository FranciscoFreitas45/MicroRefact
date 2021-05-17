import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;
@AllArgsConstructor
@RestController
@CrossOrigin
public class CalculateYearRepositoryController {

 private CalculateYearRepository calculateyearrepository;


@GetMapping
("/findByIsCurrent")
public CalenderYear findByIsCurrent(@RequestParam(name = "i") int i){
  return calculateyearrepository.findByIsCurrent(i);
}


}