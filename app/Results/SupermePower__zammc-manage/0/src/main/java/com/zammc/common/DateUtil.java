package com.zammc.common;
 import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
public class DateUtil {


public Timestamp getCurrentTime(Timestamp timestamp){
    Instant instant = timestamp.toInstant();
    ZoneId zone = ZoneId.systemDefault();
    LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zone);
    LocalDate localDate = localDateTime.toLocalDate();
    Instant instantLocalDate = localDate.atStartOfDay().atZone(zone).toInstant();
    return new Timestamp(Date.from(instantLocalDate).getTime());
}


}