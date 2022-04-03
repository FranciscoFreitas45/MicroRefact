package com.dtdhehe.ptulife.util;
 import org.springframework.util.StringUtils;
import java.text.SimpleDateFormat;
import java.util.Date;
public class DateUtils {


public String getCurrentDateTime(){
    Date now = new Date();
    // 获取当前年月日
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss:mm");
    String currentDate = dateFormat.format(now);
    String time = currentDate.replace("/", "");
    String currentTime = time.replace(" ", "");
    return currentTime.replace(":", "");
}


public String getCurrentDateTime2(){
    Date now = new Date();
    // 获取当前年月日
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss:mm");
    return dateFormat.format(now);
}


public String getCurrentDate(){
    Date now = new Date();
    // 获取当前年月日
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
    String currentDate = dateFormat.format(now);
    return currentDate.replace("/", "-");
}


public String viewType2Date(String date){
    String formatDate = "";
    if (StringUtils.isEmpty(date)) {
        return date;
    }
    formatDate = date.replaceAll("\\s|-|:", "");
    return formatDate;
}


public void main(String[] args){
    System.out.println(getCurrentDateTime());
}


public String date2ViewType(String date,String dateType){
    String formateDate = "";
    if (StringUtils.isEmpty(date)) {
        return date;
    }
    if ("datetime".equals(dateType)) {
        formateDate = date.substring(0, 4) + "-" + date.substring(4, 6) + "-" + date.substring(6, 8) + " " + date.substring(8, 10) + ":" + date.substring(10, 12) + ":" + date.substring(12, 14);
        return formateDate;
    } else if ("date".equals(dateType)) {
        formateDate = date.substring(0, 4) + "-" + date.substring(4, 6) + "-" + date.substring(6, 8);
        return formateDate;
    }
    return date;
}


}