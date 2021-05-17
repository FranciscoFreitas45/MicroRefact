import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;
@AllArgsConstructor
@RestController
@CrossOrigin
public class BonusCalcRepoController {

 private BonusCalcRepo bonuscalcrepo;


@GetMapping
("/findByDelStatusAndBonusId")
public List<BonusCalc> findByDelStatusAndBonusId(@RequestParam(name = "i") int i,@RequestParam(name = "bonusId") int bonusId){
  return bonuscalcrepo.findByDelStatusAndBonusId(i,bonusId);
}


}