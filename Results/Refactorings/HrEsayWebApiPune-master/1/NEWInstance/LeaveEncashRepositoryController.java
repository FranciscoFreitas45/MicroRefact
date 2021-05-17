import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;
@AllArgsConstructor
@RestController
@CrossOrigin
public class LeaveEncashRepositoryController {

 private LeaveEncashRepository leaveencashrepository;


@GetMapping
("/updateEncashAmt")
public int updateEncashAmt(@RequestParam(name = "month") int month,@RequestParam(name = "year") int year,@RequestParam(name = "empIds") List<Integer> empIds){
  return leaveencashrepository.updateEncashAmt(month,year,empIds);
}


}