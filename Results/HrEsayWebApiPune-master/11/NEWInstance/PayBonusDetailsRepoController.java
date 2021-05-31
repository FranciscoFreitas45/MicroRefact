import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;
@AllArgsConstructor
@RestController
@CrossOrigin
public class PayBonusDetailsRepoController {

 private PayBonusDetailsRepo paybonusdetailsrepo;


@GetMapping
("/updateBonus")
public int updateBonus(@RequestParam(name = "month") int month,@RequestParam(name = "year") int year,@RequestParam(name = "empIds") List<Integer> empIds){
  return paybonusdetailsrepo.updateBonus(month,year,empIds);
}


}