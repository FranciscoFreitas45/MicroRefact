package com.crontab;
 import org.apache.log4j.Logger;
import org.quartz;
public class QuartzSchedulerHelper {

 private  Logger LOGGER;

 private  boolean overwriteExistingJobs;


public boolean triggerExists(Trigger trigger){
    return getScheduler().getTrigger(trigger.getName(), trigger.getGroup()) != null;
}


public boolean jobDetailExists(JobDetail jobDetail){
    return getScheduler().getJobDetail(jobDetail.getName(), jobDetail.getGroup()) != null;
}


public void setOverwriteExistingJobs(boolean overwriteExistingJobs){
    this.overwriteExistingJobs = overwriteExistingJobs;
}


public boolean addTriggerToScheduler(Trigger trigger,JobDetail jobDetail){
    boolean triggerExists = triggerExists(trigger);
    if (!triggerExists || overwriteExistingJobs) {
        if (!triggerExists) {
            try {
                getScheduler().scheduleJob(jobDetail, trigger);
            } catch (ObjectAlreadyExistsException ex) {
                LOGGER.error("Ignored exception, nexpectedly found existing trigger", ex);
                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug("Unexpectedly found existing trigger, assumably due to cluster race condition: " + ex.getMessage() + " - can safely be ignored");
                }
                if (overwriteExistingJobs) {
                    rescheduleJob(trigger);
                }
            }
        } else {
            rescheduleJob(trigger);
        }
        return true;
    } else {
        return false;
    }
}


public Scheduler getScheduler()


public boolean isOverwriteExistingJobs(){
    return overwriteExistingJobs;
}


public void rescheduleJob(Trigger trigger){
    if (LOGGER.isDebugEnabled()) {
        LOGGER.debug("Reschedule Trigger : " + trigger);
    }
    getScheduler().rescheduleJob(trigger.getName(), trigger.getGroup(), trigger);
}


}