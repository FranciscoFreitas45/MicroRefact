import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;
@AllArgsConstructor
@RestController
@CrossOrigin
public class HolidayMasterRepoController {

 private HolidayMasterRepo holidaymasterrepo;


@GetMapping
("/getHolidaysForDash")
public List<HolidayMaster> getHolidaysForDash(@RequestParam(name = "currDate") String currDate){
  return holidaymasterrepo.getHolidaysForDash(currDate);
}


@GetMapping
("/getUserApplicableHoliday")
public List<HolidayMaster> getUserApplicableHoliday(@RequestParam(name = "date") String date,@RequestParam(name = "empId") int empId){
  return holidaymasterrepo.getUserApplicableHoliday(date,empId);
}


}