import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;
@AllArgsConstructor
@RestController
@CrossOrigin
public class PayDeductionDetailsRepoController {

 private PayDeductionDetailsRepo paydeductiondetailsrepo;


@GetMapping
("/updatePayde")
public int updatePayde(@RequestParam(name = "month") int month,@RequestParam(name = "year") int year,@RequestParam(name = "empIds") List<Integer> empIds){
  return paydeductiondetailsrepo.updatePayde(month,year,empIds);
}


}