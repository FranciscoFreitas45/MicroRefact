package cn.com.cnc.fcc.service.util;
 import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import cn.com.cnc.fcc.repository.SystemRepository;
@Service
public class DateUtil {

@Resource
 private  SystemRepository systemRepository;

 private  String dataFormat;

 private  Logger log;


public ZonedDateTime getZonedDateByTime(String todayDate,String dataFormat){
    /* 定义格式化时间样式 */
    DateTimeFormatter datetimeFormatter = DateTimeFormatter.ofPattern(dataFormat).withZone(ZoneId.of("Asia/Shanghai"));
    /* 格式化当前时间 */
    ZonedDateTime nowtoday = ZonedDateTime.parse(todayDate, datetimeFormatter);
    /* 返回时间 */
    return nowtoday.withZoneSameInstant(ZoneId.of("UTC"));
}


public ZonedDateTime getDBNowDate(String dataFormat){
    /* 初始化当前时间 */
    Date day = systemRepository.getSysteTime();
    /* 定义格式化时间样式 */
    SimpleDateFormat df = new SimpleDateFormat(dataFormat);
    /* 格式化当前时间 */
    String todayDate = df.format(day);
    /* 定义格式化时间样式 */
    DateTimeFormatter datetimeFormatter = DateTimeFormatter.ofPattern(dataFormat).withZone(ZoneId.of("Asia/Shanghai"));
    /* 格式化当前时间 */
    ZonedDateTime nowtoday = ZonedDateTime.parse(todayDate, datetimeFormatter);
    /* 返回时间 */
    return nowtoday.withZoneSameInstant(ZoneId.of("UTC"));
}


public LocalDate getTime(String date){
    String dateStr = "";
    /* 初始化当前时间 */
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-d");
    Date day;
    LocalDate dateTime = null;
    try {
        day = sdf.parse(date);
        /* 定义格式化时间样式 */
        SimpleDateFormat df = new SimpleDateFormat(dataFormat);
        /* 格式化当前时间 */
        dateStr = df.format(day);
        /* 定义格式化时间样式 */
        DateTimeFormatter datetimeFormatter = DateTimeFormatter.ofPattern(dataFormat).withZone(ZoneId.of("Asia/Shanghai"));
        /* 格式化当前时间 */
        dateTime = ZonedDateTime.parse(dateStr, datetimeFormatter).toLocalDate();
    /* 返回时间 */
    } catch (ParseException e) {
        log.info(e.getMessage());
    }
    return dateTime;
}


public boolean compareByDataFormatterTo(ZonedDateTime firstDateTime,ZonedDateTime secondDateTime,String dataFormat){
    DateTimeFormatter datetimeFormatter = DateTimeFormatter.ofPattern(dataFormat).withZone(ZoneId.of("Asia/Shanghai"));
    String strFirst = firstDateTime.format(datetimeFormatter);
    String strSecond = secondDateTime.format(datetimeFormatter);
    return strFirst.equals(strSecond);
}


}