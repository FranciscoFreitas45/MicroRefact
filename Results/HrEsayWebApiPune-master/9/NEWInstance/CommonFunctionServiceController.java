import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;
@AllArgsConstructor
@RestController
@CrossOrigin
public class CommonFunctionServiceController {

 private CommonFunctionService commonfunctionservice;


@GetMapping
("/findDateInWeekEnd")
public Integer findDateInWeekEnd(@RequestParam(name = "fromDate") String fromDate,@RequestParam(name = "toDate") String toDate,@RequestParam(name = "weeklyList") List<WeeklyOff> weeklyList,@RequestParam(name = "weeklyOffShitList") List<WeeklyOffShit> weeklyOffShitList,@RequestParam(name = "locationId") int locationId,@RequestParam(name = "weekendCatId") int weekendCatId,@RequestParam(name = "empId") int empId,@RequestParam(name = "weeklyOffShitFromList") List<WeeklyOffShit> weeklyOffShitFromList){
  return commonfunctionservice.findDateInWeekEnd(fromDate,toDate,weeklyList,weeklyOffShitList,locationId,weekendCatId,empId,weeklyOffShitFromList);
}


@GetMapping
("/findDateInHoliday")
public Integer findDateInHoliday(@RequestParam(name = "fromDate") String fromDate,@RequestParam(name = "toDate") String toDate,@RequestParam(name = "holidayList") List<Holiday> holidayList,@RequestParam(name = "locationId") int locationId,@RequestParam(name = "holidayCatId") int holidayCatId){
  return commonfunctionservice.findDateInHoliday(fromDate,toDate,holidayList,locationId,holidayCatId);
}


@GetMapping
("/findDateInLeave")
public LeaveStsAndLeaveId findDateInLeave(@RequestParam(name = "fromDate") String fromDate,@RequestParam(name = "leavetList") List<LeaveApply> leavetList,@RequestParam(name = "empId") int empId){
  return commonfunctionservice.findDateInLeave(fromDate,leavetList,empId);
}


@GetMapping
("/findDateInOptionalHoliday")
public int findDateInOptionalHoliday(@RequestParam(name = "format") String format,@RequestParam(name = "optionalHolidayList") List<EmpListForHolidayApprove> optionalHolidayList,@RequestParam(name = "empId") int empId){
  return commonfunctionservice.findDateInOptionalHoliday(format,optionalHolidayList,empId);
}


@GetMapping
("/getDatesOfWeeklyOfForShiftingDate")
public List<String> getDatesOfWeeklyOfForShiftingDate(@RequestParam(name = "fromDate") String fromDate,@RequestParam(name = "toDate") String toDate,@RequestParam(name = "weeklyOfflist") List<WeeklyOff> weeklyOfflist,@RequestParam(name = "locationId") int locationId,@RequestParam(name = "holidayCatId") int holidayCatId){
  return commonfunctionservice.getDatesOfWeeklyOfForShiftingDate(fromDate,toDate,weeklyOfflist,locationId,holidayCatId);
}


}