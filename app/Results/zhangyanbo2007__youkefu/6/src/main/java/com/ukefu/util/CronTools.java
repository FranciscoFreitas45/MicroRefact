package com.ukefu.util;
 import java.text.ParseException;
import java.util.Date;
import org.quartz.CronExpression;
public class CronTools {


public CronExpression getFireTime(String crontabExp){
    return new CronExpression(crontabExp);
}


public void main(String[] args){
    System.out.println(UKTools.dateFormate.format(CronTools.getFinalFireTime("0 0/40 0/1 * * ?", new Date())));
}


public Date getFinalFireTime(String crontabExp,Date date){
    CronExpression expression = new CronExpression(crontabExp);
    return expression.getNextValidTimeAfter(date != null ? date : new Date());
}


}