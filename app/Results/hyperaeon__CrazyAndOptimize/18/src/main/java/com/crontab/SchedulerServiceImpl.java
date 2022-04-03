package com.crontab;
 import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import org.apache.log4j.Logger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;
public class SchedulerServiceImpl extends QuartzSchedulerHelperimplements SchedulerService{

 private  Logger LOGGER;

 private  String DEFAULT_QUARTZ_CONFIG_FILE;

 private  Scheduler SCHEDULER;

 private  Lock LOCK;

 private  boolean INITED;


@Override
public boolean triggerJob(String jobName,String groupName){
    try {
        boolean found = getScheduler().getJobDetail(jobName, groupName) != null;
        if (found) {
            getScheduler().triggerJob(jobName, groupName);
            return true;
        }
    } catch (SchedulerException e) {
        LOGGER.error("failed to trigger job", e);
    }
    return false;
}


@Override
public boolean schedule(JobDetail jobDetail,Trigger trigger){
    return addTriggerToScheduler(trigger, jobDetail);
}


public Scheduler createScheduler(String config){
    if (SCHEDULER == null) {
        LOCK.lock();
        try {
            // created or not
            if (!INITED) {
                try {
                    SCHEDULER = new StdSchedulerFactory(config).getScheduler();
                    SCHEDULER.start();
                    INITED = true;
                } catch (SchedulerException e) {
                    LOGGER.error("scheduler failed to start", e);
                }
            }
        } finally {
            LOCK.unlock();
        }
    }
    return SCHEDULER;
}


@Override
public Scheduler getScheduler(){
    return createScheduler(DEFAULT_QUARTZ_CONFIG_FILE);
}


@Override
public boolean deleteJob(String jobName,String groupName){
    try {
        boolean found = getScheduler().getJobDetail(jobName, groupName) != null;
        if (found) {
            getScheduler().deleteJob(jobName, groupName);
            return true;
        }
    } catch (SchedulerException e) {
        LOGGER.error("failed to trigger job", e);
    }
    return false;
}


}