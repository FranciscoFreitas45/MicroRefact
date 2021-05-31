import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;
@AllArgsConstructor
@RestController
@CrossOrigin
public class SalaryTypesMasterRepoController {

 private SalaryTypesMasterRepo salarytypesmasterrepo;


@GetMapping
("/findAllByDelStatus")
public List<SalaryTypesMaster> findAllByDelStatus(@RequestParam(name = "i") int i){
  return salarytypesmasterrepo.findAllByDelStatus(i);
}


}