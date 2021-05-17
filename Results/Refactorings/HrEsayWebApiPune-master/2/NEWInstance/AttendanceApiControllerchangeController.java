import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;
@AllArgsConstructor
@RestController
@CrossOrigin
public class AttendanceApiControllerchangeController {

 private AttendanceApiControllerchange attendanceapicontrollerchange;


@GetMapping
("/getVariousListForUploadAttendace")
public Info getVariousListForUploadAttendace(@RequestParam(name = "dataForUpdateAttendance") DataForUpdateAttendance dataForUpdateAttendance){
  return attendanceapicontrollerchange.getVariousListForUploadAttendace(dataForUpdateAttendance);
}


}