import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;
@AllArgsConstructor
@RestController
@CrossOrigin
public class QmsBomTechnologyRepositoryController {

 private QmsBomTechnologyRepository qmsbomtechnologyrepository;


@GetMapping
("/findByProcessId")
public List<QmsBomTechnology> findByProcessId(@RequestParam(name = "s") Integer s){
  return qmsbomtechnologyrepository.findByProcessId(s);
}


}