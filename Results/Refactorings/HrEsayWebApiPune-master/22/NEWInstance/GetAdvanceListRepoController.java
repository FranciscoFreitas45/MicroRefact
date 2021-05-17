import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;
@AllArgsConstructor
@RestController
@CrossOrigin
public class GetAdvanceListRepoController {

 private GetAdvanceListRepo getadvancelistrepo;


@GetMapping
("/getAdvanceList")
public List<GetAdvanceList> getAdvanceList(@RequestParam(name = "month") int month,@RequestParam(name = "year") int year,@RequestParam(name = "empIds") List<Integer> empIds){
  return getadvancelistrepo.getAdvanceList(month,year,empIds);
}


}