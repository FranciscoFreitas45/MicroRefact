package com.crontab;
 import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
public interface SchedulerService {


public boolean triggerJob(String jobName,String groupName)
;

public boolean schedule(JobDetail jobDetail,Trigger trigger)
;

public void setOverwriteExistingJobs(boolean overwriteExistingJobs)
;

public Scheduler getScheduler()
;

public boolean deleteJob(String jobName,String groupName)
;

}