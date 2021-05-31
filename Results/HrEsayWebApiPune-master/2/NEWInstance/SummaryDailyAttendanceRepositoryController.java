import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;
@AllArgsConstructor
@RestController
@CrossOrigin
public class SummaryDailyAttendanceRepositoryController {

 private SummaryDailyAttendanceRepository summarydailyattendancerepository;


@GetMapping
("/findAllByCompanyIdAndEmpId")
public List<SummaryDailyAttendance> findAllByCompanyIdAndEmpId(@RequestParam(name = "companyId") int companyId,@RequestParam(name = "empId") int empId,@RequestParam(name = "fmonth") String fmonth,@RequestParam(name = "fyear") String fyear,@RequestParam(name = "lmonth") String lmonth,@RequestParam(name = "lyear") String lyear){
  return summarydailyattendancerepository.findAllByCompanyIdAndEmpId(companyId,empId,fmonth,fyear,lmonth,lyear);
}


@GetMapping
("/findAllByCompanyId")
public List<SummaryDailyAttendance> findAllByCompanyId(@RequestParam(name = "locId") int locId,@RequestParam(name = "fmonth") String fmonth,@RequestParam(name = "fyear") String fyear,@RequestParam(name = "lmonth") String lmonth,@RequestParam(name = "lyear") String lyear){
  return summarydailyattendancerepository.findAllByCompanyId(locId,fmonth,fyear,lmonth,lyear);
}


}