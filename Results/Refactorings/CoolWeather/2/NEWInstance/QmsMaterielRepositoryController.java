import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;
@AllArgsConstructor
@RestController
@CrossOrigin
public class QmsMaterielRepositoryController {

 private QmsMaterielRepository qmsmaterielrepository;


@GetMapping
("/findByIdAndFlagStatus")
public List<QmsMateriel> findByIdAndFlagStatus(@RequestParam(name = "valueOf") Long valueOf,@RequestParam(name = "string") String string){
  return qmsmaterielrepository.findByIdAndFlagStatus(valueOf,string);
}


@GetMapping
("/findByMaterielCdAndFlagStatus")
public List<QmsMateriel> findByMaterielCdAndFlagStatus(@RequestParam(name = "materielCd") String materielCd,@RequestParam(name = "flagStatus") String flagStatus){
  return qmsmaterielrepository.findByMaterielCdAndFlagStatus(materielCd,flagStatus);
}


}