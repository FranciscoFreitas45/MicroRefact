package run.halo.app.utils;
 import cn.hutool.core.date.DatePattern.NORM_DATETIME_PATTERN;
import cn.hutool.core.date.DatePattern.NORM_DATE_PATTERN;
import cn.hutool.core.date.DatePattern.PURE_DATETIME_PATTERN;
import cn.hutool.core.date.DatePattern.PURE_DATE_PATTERN;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.time.temporal.Temporal;
public class DateTimeUtils {

 public  DateTimeFormatter PURE_DATETIME_MS_FORMATTER;

 public  DateTimeFormatter PURE_DATE_FORMATTER;

 public  DateTimeFormatter NORM_DATE_FORMATTER;

 public  String TIME_PATTERN;

 public  DateTimeFormatter TIME_FORMATTER;

 public  String NORM_TIME_PATTERN;

 public  DateTimeFormatter NORM_TIME_FORMATTER;

 public  DateTimeFormatter NORM_DATETIME_FORMATTER;

 public  String HORIZONTAL_LINE_PATTERN;

 public  DateTimeFormatter HORIZONTAL_LINE_DATETIME_FORMATTER;

 public  String CTT;

 public  ZoneId CTT_ZONE_ID;

private DateTimeUtils() {
}
public String formatDate(LocalDate localDate){
    return format(localDate, PURE_DATE_FORMATTER);
}


public LocalDateTime plusOneDayToDateTime(LocalDateTime localDateTime){
    return plusDays(localDateTime, 1);
}


public String formatDateTime(LocalDateTime localDateTime){
    return format(localDateTime, NORM_DATETIME_FORMATTER);
}


public LocalTime plusMinutes(LocalTime localTime,long minutes){
    return localTime.plusMinutes(minutes);
}


public boolean isNoon(LocalTime startInclusive){
    return Duration.between(startInclusive, LocalTime.NOON).isZero();
}


public LocalDate plusDays(LocalDate localDate,long days){
    return localDate.plusDays(days);
}


public LocalTime minusMinutes(LocalTime localTime,long minutes){
    return localTime.minusMinutes(minutes);
}


public LocalTime plusOneMinuteToTime(LocalDateTime localDateTime){
    return plusMinutes(localDateTime, 1).toLocalTime();
}


public LocalDateTime now(ZoneId zoneId){
    return LocalDateTime.now(zoneId);
}


public boolean isZero(Temporal startInclusive,Temporal endInclusive){
    return Duration.between(startInclusive, endInclusive).isZero();
}


public LocalDateTime minusOneMinutes(LocalDateTime localDateTime){
    return minusMinutes(localDateTime, 1);
}


public boolean isGreaterOrEqual(Temporal startInclusive,Temporal endInclusive){
    return Duration.between(startInclusive, endInclusive).toNanos() >= 0;
}


public boolean isNegative(Temporal startInclusive,Temporal endInclusive){
    return Duration.between(startInclusive, endInclusive).isNegative();
}


public String formatTime(LocalTime localTime){
    return format(localTime, TIME_FORMATTER);
}


public String format(LocalDate localDate,DateTimeFormatter formatter){
    return localDate.format(formatter);
}


public boolean isGreater(Temporal startInclusive,Temporal endInclusive){
    return Duration.between(startInclusive, endInclusive).toNanos() > 0;
}


public long toEpochMilli(LocalDateTime localDateTime){
    return toInstant(localDateTime).toEpochMilli();
}


public LocalDateTime secondAndNanoSetZero(LocalDateTime localDateTime){
    return localDateTime.withSecond(0).withNano(0);
}


public LocalDate plusOneDayToDate(LocalDate localDate){
    return plusDays(localDate, 1);
}


public LocalDateTime parse(String time,DateTimeFormatter formatter){
    return LocalDateTime.parse(time, formatter);
}


public boolean isLessThanOrEqual(Temporal startInclusive,Temporal endInclusive){
    return Duration.between(startInclusive, endInclusive).toNanos() <= 0;
}


public Instant toInstant(LocalDateTime localDateTime,ZoneId zoneId){
    return localDateTime.atZone(zoneId).toInstant();
}


public LocalDateTime parseCttDateTime(String time){
    return parse(time, PURE_DATETIME_MS_FORMATTER);
}


public LocalTime plusThirtyMinute(LocalTime localTime){
    return plusMinutes(localTime, 30);
}


public LocalDateTime plusOneDay(LocalDate localDate,LocalTime localTime){
    final LocalDateTime localDateTime = LocalDateTime.of(localDate, localTime);
    return plusDays(localDateTime, 1);
}


public long getEpochSecond(LocalDateTime localDateTime){
    return toInstant(localDateTime).getEpochSecond();
}


public LocalTime plusOneMinute(LocalTime localTime){
    return plusMinutes(localTime, 1);
}


}