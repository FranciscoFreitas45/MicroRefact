import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;
@AllArgsConstructor
@RestController
@CrossOrigin
public class GetDailyDailyRecordRepositoryController {

 private GetDailyDailyRecordRepository getdailydailyrecordrepository;


@GetMapping
("/summaryDailyAttendanceListAll1")
public List<GetDailyDailyRecord> summaryDailyAttendanceListAll1(@RequestParam(name = "fromDate") String fromDate,@RequestParam(name = "toDate") String toDate,@RequestParam(name = "locId") int locId){
  return getdailydailyrecordrepository.summaryDailyAttendanceListAll1(fromDate,toDate,locId);
}


}