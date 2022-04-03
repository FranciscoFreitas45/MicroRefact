package cn.maxcj.core.log;
 import java.util.TimerTask;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
public class LogManager {

 private  int OPERATE_DELAY_TIME;

 private  ScheduledThreadPoolExecutor executor;

 public  LogManager logManager;

private LogManager() {
}
public LogManager me(){
    return logManager;
}


public void executeLog(TimerTask task){
    executor.schedule(task, OPERATE_DELAY_TIME, TimeUnit.MILLISECONDS);
}


}