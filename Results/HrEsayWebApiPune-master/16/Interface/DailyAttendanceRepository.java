public interface DailyAttendanceRepository {

   public int updateOTById(String otHr,int id);
   public int updateOTByIdAndApprove(String otHr,int id);
}