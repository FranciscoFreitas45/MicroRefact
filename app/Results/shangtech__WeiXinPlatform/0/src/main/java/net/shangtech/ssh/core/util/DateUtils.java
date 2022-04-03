package net.shangtech.ssh.core.util;
 import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.lang3.StringUtils;
public class DateUtils {


public String format(Date date){
    if (date == null) {
        return "";
    }
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    return df.format(date);
}


public Date parse(String dateStr){
    if (StringUtils.isBlank(dateStr))
        return null;
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Date date = null;
    try {
        date = df.parse(dateStr);
    } catch (ParseException e) {
        e.printStackTrace();
    }
    return date;
}


}