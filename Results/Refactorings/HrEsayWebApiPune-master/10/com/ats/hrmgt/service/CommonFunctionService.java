import com.ats.hrmgt.model;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public interface CommonFunctionService {


public int findDateInOptionalHoliday(String format,List<EmpListForHolidayApprove> optionalHolidayList,int empId)


public LeaveStsAndLeaveId findDateInLeave(String fromDate,List<LeaveApply> leavetList,int empId)


public Integer findDateInWeekEnd(String fromDate,String toDate,List<WeeklyOff> weeklyList,List<WeeklyOffShit> weeklyOffShitList,int locationId,int weekendCatId,int empId,List<WeeklyOffShit> weeklyOffShitFromList)


public Integer findDateInHoliday(String fromDate,String toDate,List<Holiday> holidayList,int locationId,int holidayCatId)


public List<String> getDatesOfWeeklyOfForShiftingDate(String fromDate,String toDate,List<WeeklyOff> weeklyOfflist,int locationId,int holidayCatId)


}