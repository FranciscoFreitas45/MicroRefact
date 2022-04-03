package com.xwtec.xwserver.util;
 import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import org.apache.log4j.Logger;
public class TimerUtil {

 private  Logger log;


public void schedule(Class<? extends TimerTask> clazz,Date date){
    Timer timer = new Timer();
    try {
        timer.schedule(clazz.newInstance(), date);
    } catch (InstantiationException e) {
        log.error(CommonUtil.getErrorMessage(Thread.currentThread().getStackTrace()[1], e));
    } catch (IllegalAccessException e) {
        log.error(CommonUtil.getErrorMessage(Thread.currentThread().getStackTrace()[1], e));
    }
}


}