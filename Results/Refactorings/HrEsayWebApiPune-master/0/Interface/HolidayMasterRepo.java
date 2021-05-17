public interface HolidayMasterRepo {

   public List<HolidayMaster> getHolidaysForDash(String currDate);
   public List<HolidayMaster> getUserApplicableHoliday(String date,int empId);
}