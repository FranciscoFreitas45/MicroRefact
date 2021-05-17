import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;
@AllArgsConstructor
@RestController
@CrossOrigin
public class AllowancesRepoController {

 private AllowancesRepo allowancesrepo;


@GetMapping
("/findBydelStatusAndIsActive")
public List<Allowances> findBydelStatusAndIsActive(@RequestParam(name = "del") int del,@RequestParam(name = "active") int active){
  return allowancesrepo.findBydelStatusAndIsActive(del,active);
}


}