import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;
@AllArgsConstructor
@RestController
@CrossOrigin
public class GetPayDedListRepoController {

 private GetPayDedListRepo getpaydedlistrepo;


@GetMapping
("/getPayDedList")
public List<GetPayDedList> getPayDedList(@RequestParam(name = "month") int month,@RequestParam(name = "year") int year,@RequestParam(name = "empIds") List<Integer> empIds){
  return getpaydedlistrepo.getPayDedList(month,year,empIds);
}


@GetMapping
("/getBonusList")
public List<GetPayDedList> getBonusList(@RequestParam(name = "date") String date,@RequestParam(name = "empIds") List<Integer> empIds){
  return getpaydedlistrepo.getBonusList(date,empIds);
}


@GetMapping
("/getLoanList")
public List<GetPayDedList> getLoanList(@RequestParam(name = "date") String date,@RequestParam(name = "empIds") List<Integer> empIds){
  return getpaydedlistrepo.getLoanList(date,empIds);
}


@GetMapping
("/getPartialLoanList")
public List<GetPayDedList> getPartialLoanList(@RequestParam(name = "month") int month,@RequestParam(name = "year") int year,@RequestParam(name = "empIds") List<Integer> empIds){
  return getpaydedlistrepo.getPartialLoanList(month,year,empIds);
}


@GetMapping
("/getEncashLeaveAmtList")
public List<GetPayDedList> getEncashLeaveAmtList(@RequestParam(name = "month") int month,@RequestParam(name = "year") int year,@RequestParam(name = "empIds") List<Integer> empIds){
  return getpaydedlistrepo.getEncashLeaveAmtList(month,year,empIds);
}


}