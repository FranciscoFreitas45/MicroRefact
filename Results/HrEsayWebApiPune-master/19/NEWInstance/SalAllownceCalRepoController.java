import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;
@AllArgsConstructor
@RestController
@CrossOrigin
public class SalAllownceCalRepoController {

 private SalAllownceCalRepo salallowncecalrepo;


@GetMapping
("/findByEmpId")
public List<SalAllownceCal> findByEmpId(@RequestParam(name = "i") int i){
  return salallowncecalrepo.findByEmpId(i);
}


}