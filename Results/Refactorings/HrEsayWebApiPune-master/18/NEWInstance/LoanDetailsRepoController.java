import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;
@AllArgsConstructor
@RestController
@CrossOrigin
public class LoanDetailsRepoController {

 private LoanDetailsRepo loandetailsrepo;


@GetMapping
("/saveAll")
public Object saveAll(@RequestParam(name = "Object") Object Object){
  return loandetailsrepo.saveAll(Object);
}


}