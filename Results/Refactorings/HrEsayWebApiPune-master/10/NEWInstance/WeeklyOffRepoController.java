import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;
@AllArgsConstructor
@RestController
@CrossOrigin
public class WeeklyOffRepoController {

 private WeeklyOffRepo weeklyoffrepo;


@GetMapping
("/findByExInt1AndDelStatus")
public List<WeeklyOff> findByExInt1AndDelStatus(@RequestParam(name = "hoCatId") int hoCatId,@RequestParam(name = "i") int i){
  return weeklyoffrepo.findByExInt1AndDelStatus(hoCatId,i);
}


@GetMapping
("/findByExInt2AndDelStatus")
public List<EmployeeMaster> findByExInt2AndDelStatus(@RequestParam(name = "skillId") int skillId,@RequestParam(name = "i") int i){
  return weeklyoffrepo.findByExInt2AndDelStatus(skillId,i);
}


}