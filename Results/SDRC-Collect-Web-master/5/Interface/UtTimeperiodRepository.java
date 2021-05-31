public interface UtTimeperiodRepository {

   public UtTimeperiod findByStartDateAndEndDateAndPeriodicity(Date startDate,Date endDate,String string);
   public UtTimeperiod save(UtTimeperiod timeperiod);
   public List<UtTimeperiod> findByPeriodicity(String string);
}