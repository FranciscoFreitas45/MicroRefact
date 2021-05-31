import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;
@AllArgsConstructor
@RestController
@CrossOrigin
public class RoasterSummeryDetailRepositoryController {

 private RoasterSummeryDetailRepository roastersummerydetailrepository;


@GetMapping
("/getRoasterSummeryDetailForPayRoll")
public List<RoasterSummeryDetail> getRoasterSummeryDetailForPayRoll(@RequestParam(name = "fromDate") String fromDate,@RequestParam(name = "toDate") String toDate,@RequestParam(name = "design") int design){
  return roastersummerydetailrepository.getRoasterSummeryDetailForPayRoll(fromDate,toDate,design);
}


}