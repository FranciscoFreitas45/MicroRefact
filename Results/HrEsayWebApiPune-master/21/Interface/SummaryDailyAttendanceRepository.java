public interface SummaryDailyAttendanceRepository {

   public List<SummaryDailyAttendance> findAllByCompanyIdAndEmpId(int companyId,int empId,String fmonth,String fyear,String lmonth,String lyear);
   public List<SummaryDailyAttendance> findAllByCompanyId(int locId,String fmonth,String fyear,String lmonth,String lyear);
}