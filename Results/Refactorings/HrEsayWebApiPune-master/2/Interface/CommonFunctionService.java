public interface CommonFunctionService {

   public Integer findDateInWeekEnd(String fromDate,String toDate,List<WeeklyOff> weeklyList,List<WeeklyOffShit> weeklyOffShitList,int locationId,int weekendCatId,int empId,List<WeeklyOffShit> weeklyOffShitFromList);
   public Integer findDateInHoliday(String fromDate,String toDate,List<Holiday> holidayList,int locationId,int holidayCatId);
   public LeaveStsAndLeaveId findDateInLeave(String fromDate,List<LeaveApply> leavetList,int empId);
   public int findDateInOptionalHoliday(String format,List<EmpListForHolidayApprove> optionalHolidayList,int empId);
   public List<String> getDatesOfWeeklyOfForShiftingDate(String fromDate,String toDate,List<WeeklyOff> weeklyOfflist,int locationId,int holidayCatId);
}