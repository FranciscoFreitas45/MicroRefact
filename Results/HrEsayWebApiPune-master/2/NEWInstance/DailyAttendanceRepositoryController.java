import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;
@AllArgsConstructor
@RestController
@CrossOrigin
public class DailyAttendanceRepositoryController {

 private DailyAttendanceRepository dailyattendancerepository;


@GetMapping
("/updateOTById")
public int updateOTById(@RequestParam(name = "otHr") String otHr,@RequestParam(name = "id") int id){
  return dailyattendancerepository.updateOTById(otHr,id);
}


@GetMapping
("/updateOTByIdAndApprove")
public int updateOTByIdAndApprove(@RequestParam(name = "otHr") String otHr,@RequestParam(name = "id") int id){
  return dailyattendancerepository.updateOTByIdAndApprove(otHr,id);
}


}