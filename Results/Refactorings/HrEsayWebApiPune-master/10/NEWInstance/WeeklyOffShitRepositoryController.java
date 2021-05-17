import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;
@AllArgsConstructor
@RestController
@CrossOrigin
public class WeeklyOffShitRepositoryController {

 private WeeklyOffShitRepository weeklyoffshitrepository;


@GetMapping
("/getWeeklyOffShitList")
public List<WeeklyOffShit> getWeeklyOffShitList(@RequestParam(name = "fromDate") String fromDate,@RequestParam(name = "toDate") String toDate){
  return weeklyoffshitrepository.getWeeklyOffShitList(fromDate,toDate);
}


@GetMapping
("/weeklyOffShitFromList")
public List<WeeklyOffShit> weeklyOffShitFromList(@RequestParam(name = "fromDate") String fromDate,@RequestParam(name = "toDate") String toDate){
  return weeklyoffshitrepository.weeklyOffShitFromList(fromDate,toDate);
}


@GetMapping
("/shiftWeeklyofById")
public WeeklyOffShit shiftWeeklyofById(@RequestParam(name = "id") int id){
  return weeklyoffshitrepository.shiftWeeklyofById(id);
}


}