package com.zammc.idworker;
 import com.google.common.base.Preconditions;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
@Slf4j
@Component
public class DefaultIdWorker implements IdWorker{

 public  long EPOCH;

 private  long SEQUENCE_BITS;

 private  long WORKER_ID_BITS;

 private  long SEQUENCE_MASK;

 private  long WORKER_ID_LEFT_SHIFT_BITS;

 private  long TIMESTAMP_LEFT_SHIFT_BITS;

 private  long WORKER_ID_MAX_VALUE;

@Setter
 private  TimeService timeService;

 private  long workerId;

 private  long sequence;

 private  long lastTime;


@Override
public long nextId(){
    long currentMillis = timeService.getCurrentMillis();
    Preconditions.checkState(lastTime <= currentMillis, "Clock is moving backwards, last time is %d milliseconds, current time is %d milliseconds", lastTime, currentMillis);
    if (lastTime == currentMillis) {
        if (0L == (sequence = ++sequence & SEQUENCE_MASK)) {
            currentMillis = waitUntilNextTime(currentMillis);
        }
    } else {
        sequence = 0;
    }
    lastTime = currentMillis;
    if (log.isDebugEnabled()) {
        log.debug("{}-{}-{}", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date(lastTime)), workerId, sequence);
    }
    return ((currentMillis - EPOCH) << TIMESTAMP_LEFT_SHIFT_BITS) | (workerId << WORKER_ID_LEFT_SHIFT_BITS) | sequence;
}


public void setWorkerId(long workerId){
    Preconditions.checkArgument(workerId >= 0L && workerId < WORKER_ID_MAX_VALUE);
    DefaultIdWorker.workerId = workerId;
}


public long waitUntilNextTime(long lastTime){
    long time = timeService.getCurrentMillis();
    while (time <= lastTime) {
        time = timeService.getCurrentMillis();
    }
    return time;
}


}