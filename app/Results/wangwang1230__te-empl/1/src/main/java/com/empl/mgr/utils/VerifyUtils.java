package com.empl.mgr.utils;
 import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.lang.StringUtils;
public class VerifyUtils implements Serializable{

 private  long serialVersionUID;


public boolean verifyDate(String date){
    if (StringUtils.isEmpty(date))
        return false;
    try {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        format.setLenient(false);
        format.parse(date);
        return true;
    } catch (Exception e) {
        e.printStackTrace();
        return false;
    }
}


public boolean verdictSize(String minDate,String maxDate,boolean equals){
    try {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date min = format.parse(minDate);
        Date max = format.parse(maxDate);
        return equals ? min.getTime() <= max.getTime() : min.getTime() < max.getTime();
    } catch (ParseException e) {
        // TODO: handle exception
        return false;
    }
}


}