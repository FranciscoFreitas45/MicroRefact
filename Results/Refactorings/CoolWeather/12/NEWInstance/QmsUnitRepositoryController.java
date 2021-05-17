import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;
@AllArgsConstructor
@RestController
@CrossOrigin
public class QmsUnitRepositoryController {

 private QmsUnitRepository qmsunitrepository;


@GetMapping
("/findByUnitCd")
public List<QmsUnit> findByUnitCd(@RequestParam(name = "s") String s){
  return qmsunitrepository.findByUnitCd(s);
}


@GetMapping
("/findByUnitCd")
public List<QmsUnit> findByUnitCd(@RequestParam(name = "s") String s){
  return qmsunitrepository.findByUnitCd(s);
}


}