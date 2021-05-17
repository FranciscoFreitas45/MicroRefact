public interface WeeklyOffShitRepository {

   public List<WeeklyOffShit> getWeeklyOffShitList(String fromDate,String toDate);
   public List<WeeklyOffShit> weeklyOffShitFromList(String fromDate,String toDate);
   public WeeklyOffShit shiftWeeklyofById(int id);
}