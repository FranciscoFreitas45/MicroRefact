import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;
@AllArgsConstructor
@RestController
@CrossOrigin
public class AdvanceRepoController {

 private AdvanceRepo advancerepo;


@GetMapping
("/deleteAdvanceBydefault")
public int deleteAdvanceBydefault(@RequestParam(name = "month") int month,@RequestParam(name = "year") int year,@RequestParam(name = "empIds") List<Integer> empIds){
  return advancerepo.deleteAdvanceBydefault(month,year,empIds);
}


@GetMapping
("/saveAll")
public Object saveAll(@RequestParam(name = "Object") Object Object){
  return advancerepo.saveAll(Object);
}


@GetMapping
("/updateAdv")
public int updateAdv(@RequestParam(name = "empIds") int empIds){
  return advancerepo.updateAdv(empIds);
}


}