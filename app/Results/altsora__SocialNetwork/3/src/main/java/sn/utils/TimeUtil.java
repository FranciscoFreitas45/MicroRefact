package sn.utils;
 import java.time;
public class TimeUtil {

 public  ZoneId TIME_ZONE;

 public  ZoneOffset ZONE_OFFSET;

private TimeUtil() {
}
public boolean beforeNow(LocalDateTime localDateTime){
    return localDateTime.isBefore(now());
}


public LocalDateTime getLocalDateTimeFromTimestamp(long timestamp){
    return LocalDateTime.ofInstant(Instant.ofEpochSecond(timestamp), TIME_ZONE);
}


public LocalDateTime now(){
    return LocalDateTime.now(TimeUtil.TIME_ZONE);
}


public long getTimestampFromLocalDateTime(LocalDateTime localDateTime){
    return localDateTime.toInstant(ZONE_OFFSET).getEpochSecond();
}


public long getTimestampFromLocalDate(LocalDate localDate){
    return getTimestampFromLocalDateTime(localDate.atStartOfDay());
}


public LocalDate getLocalDateFromTimestamp(long timestamp){
    return LocalDate.ofInstant(Instant.ofEpochSecond(timestamp), TIME_ZONE);
}


}