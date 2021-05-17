import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;
@AllArgsConstructor
@RestController
@CrossOrigin
public class ShiftAssignDailyRepositoryController {

 private ShiftAssignDailyRepository shiftassigndailyrepository;


@GetMapping
("/updateAssignShiftByDate")
public int updateAssignShiftByDate(@RequestParam(name = "empIdList") List<Integer> empIdList,@RequestParam(name = "fromDate") String fromDate,@RequestParam(name = "toDate") String toDate,@RequestParam(name = "shiftId") int shiftId){
  return shiftassigndailyrepository.updateAssignShiftByDate(empIdList,fromDate,toDate,shiftId);
}


}