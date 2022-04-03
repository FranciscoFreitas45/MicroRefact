package cn.com.cnc.fcc.web.rest.hst;
 import java.util.concurrent.ScheduledFuture;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;
@RestController
@Component
public class HostSlaveTokenTask {

 private  Logger log;

 private  ThreadPoolTaskScheduler threadPoolTaskScheduler;

 private  ScheduledFuture<?> future;

public HostSlaveTokenTask() {
}
public void stopTask(){
    if (future != null) {
        future.cancel(true);
        log.debug("Task is Stop");
    } else {
        log.debug("Task is not running");
    }
}


public void startTask(){
    if (threadPoolTaskScheduler == null) {
        throw new IllegalArgumentException(this.getClass().getSimpleName() + " is not Initial");
    }
    HostSlaveRunnable hostSlaveTokenRunnable = new HostSlaveRunnable();
    // future = threadPoolTaskScheduler.schedule(new HostSlaveTokenRunnable(), new CronTrigger("0 0 0/1 * * ? "));
    future = threadPoolTaskScheduler.schedule(hostSlaveTokenRunnable, new CronTrigger("0 0/1 * * * ? "));
}


public void initTask(int poolSize){
    threadPoolTaskScheduler = new ThreadPoolTaskScheduler();
    threadPoolTaskScheduler.setPoolSize(poolSize);
    threadPoolTaskScheduler.setWaitForTasksToCompleteOnShutdown(true);
    threadPoolTaskScheduler.setAwaitTerminationSeconds(60);
    threadPoolTaskScheduler.setThreadNamePrefix("cnc-Executor-");
    threadPoolTaskScheduler.initialize();
}


}