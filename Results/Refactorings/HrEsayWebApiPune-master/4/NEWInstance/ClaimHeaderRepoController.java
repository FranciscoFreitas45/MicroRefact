import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;
@AllArgsConstructor
@RestController
@CrossOrigin
public class ClaimHeaderRepoController {

 private ClaimHeaderRepo claimheaderrepo;


@GetMapping
("/updateClaim")
public int updateClaim(@RequestParam(name = "month") int month,@RequestParam(name = "year") int year,@RequestParam(name = "empIds") List<Integer> empIds){
  return claimheaderrepo.updateClaim(month,year,empIds);
}


}