public interface LeaveApplyRepository {

   public List<LeaveApply> leaveListAddeBySystem(String fromDate,String toDate,int empId);
   public List<LeaveApply> getleavetListForAttndace(String fromDate,String toDate,int empId);
   public int reverseupdateNoOfDaysInLeave(int leaveId,float updateNoOfDays,String reason);
   public int deleteByLeaveId(int leaveId);
   public int updateNoOfDaysInLeave(int leaveId,float updateNoOfDays,String reason);
   public Object saveAndFlush(Object Object);
   public int updateLeaveApply(int leaveId,int trailId);
}