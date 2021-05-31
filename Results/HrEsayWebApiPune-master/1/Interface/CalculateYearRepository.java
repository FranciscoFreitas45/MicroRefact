public interface CalculateYearRepository {

   public List<CalenderYear> getAllCalYearOrderByDesc();
   public List<CalenderYear> findByCalYrToDateAndCalYrIdNot(String inputValue,int primaryKey);
   public List<CalenderYear> findByCalYrToDate(String inputValue);
   public List<CalenderYear> findByCalYrFromDateAndCalYrIdNot(String inputValue,int primaryKey);
   public List<CalenderYear> findByCalYrFromDate(String inputValue);
   public Object save(Object Object);
   public int updateOtherIds(int calYrId);
   public CalenderYear findByCalYrId(int calYrId);
   public int countCalyear();
   public CalenderYear findByIsCurrent(int i);
}