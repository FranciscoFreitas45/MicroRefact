public interface DailyAttendanceRepository {

   public List<DailyAttendance> dailyAttendanceListRec(String filterDate);
   public DailyAttendance dailyAttendanceListLastRec();
}