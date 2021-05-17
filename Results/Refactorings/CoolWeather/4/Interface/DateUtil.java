public interface DateUtil {

   public ZonedDateTime getDBNowDate(String dataFormat);
   public boolean compareByDataFormatterTo(ZonedDateTime firstDateTime,ZonedDateTime secondDateTime,String dataFormat);
   public ZonedDateTime getDBNowDate(String dataFormat);
}