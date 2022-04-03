package org.jeecgframework.core.timer;
 import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
@Component
public class Job {


@Scheduled(cron = "0 0 1 * * ?")
public void oneOClockPerDay(){
    org.jeecgframework.core.util.LogUtil.info("1h");
}


}