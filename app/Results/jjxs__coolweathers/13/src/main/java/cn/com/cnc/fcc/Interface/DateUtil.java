package cn.com.cnc.fcc.Interface;
public interface DateUtil {

   public ZonedDateTime getDBNowDate(String dataFormat);
   public ZonedDateTime getZonedDateByTime(String todayDate,String dataFormat);
}