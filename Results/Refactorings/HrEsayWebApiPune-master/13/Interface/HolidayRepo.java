public interface HolidayRepo {

   public Object saveAndFlush(Object Object);
   public Object saveAll(Object Object);
   public List<Holiday> getHolidayListByDates(List<String> dates,int catId);
   public Holiday findByHolidayIdAndDelStatus(int holidayId,int i);
   public List<Holiday> getHolidayByYearIdAndCateId(int yearId,int catId);
   public int deleteHoliday(int holidayId);
   public int deleteHolidayByGroup(int yearId,int catid);
}