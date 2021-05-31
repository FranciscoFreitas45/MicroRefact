import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;
@AllArgsConstructor
@RestController
@CrossOrigin
public class InfoForUploadAttendanceRepositoryController {

 private InfoForUploadAttendanceRepository infoforuploadattendancerepository;


@GetMapping
("/getInformationOfUploadedShiftAssign")
public InfoForUploadAttendance getInformationOfUploadedShiftAssign(@RequestParam(name = "fromDate") String fromDate,@RequestParam(name = "toDate") String toDate){
  return infoforuploadattendancerepository.getInformationOfUploadedShiftAssign(fromDate,toDate);
}


}