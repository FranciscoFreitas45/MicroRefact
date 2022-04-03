package com.gbcom.common.template.xml.jobm;
 import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.gbcom.op.scheduler.moduler.CronJobTrigger;
import com.gbcom.op.scheduler.moduler.Job;
import com.gbcom.op.scheduler.moduler.JobInfo;
import com.gbcom.op.scheduler.moduler.JobTrigger;
import com.gbcom.op.scheduler.moduler.SimpleJobTrigger;
import com.gbcom.op.scheduler.util.JobException;
import com.gbcom.op.util.Assert;
public class JobWrapper {

 private  Logger LOG;

 private  Job job;

 private  JobInfo jobInfo;

 private  List<JobTrigger> triggerList;

/**
 * 缺省构造函数
 *
 * @param jobGroup
 *            作业组
 * @param jobName
 *            作业名
 * @param jobClass
 *            作业执行类
 */
public JobWrapper(final String jobGroup, final String jobName, final Class<? extends org.quartz.Job> jobClass) {
    jobInfo.setJobGroup(jobGroup);
    jobInfo.setJobName(jobName);
    jobInfo.setJobClass(jobClass);
    jobInfo.setDurability(false);
    job.setJobinfo(jobInfo);
    job.setJobTriggers(triggerList);
}
public void addPeriodicTrigger(String triggerGroup,String triggerName,Date beginTime,Date endTime,long repeatInterval){
    LOG.debug("addPeriodicTrigger, triggerGroup[{}], triggerName[{}], beginTime[{}], endTime[{}], repeatInterval[{}]", new Object[] { triggerGroup, triggerName, beginTime, endTime, repeatInterval });
    SimpleJobTrigger trigger = new SimpleJobTrigger(triggerName, triggerGroup);
    trigger.setBegainTime(beginTime == null ? new Date() : beginTime);
    if (endTime != null) {
        trigger.setEndTime(endTime);
    }
    trigger.setRepeatInterval(repeatInterval);
    // 不断地重复，直到endtime
    trigger.setRepeatCount(-1);
    triggerList.add(trigger);
}


public void addTrigger(String triggerGroup,String triggerName,Date triggerTime){
    Assert.notNull(triggerTime, "argument triggerTime is null");
    if (LOG.isDebugEnabled()) {
        LOG.debug("addPeriodicTrigger, triggerGroup[{}], triggerName[{}], triggerTime[{}]", new Object[] { triggerGroup, triggerName, triggerTime });
    }
    SimpleJobTrigger trigger = new SimpleJobTrigger(triggerName, triggerGroup);
    trigger.setBegainTime(triggerTime);
    triggerList.add(trigger);
}


public void setDurability(boolean durability){
    jobInfo.setDurability(durability);
}


public void addJobProperty(String key,Object value){
    Assert.notNull(key, "argument key is null");
    Assert.notNull(value, "argument value is null");
    jobInfo.getMap().add(key, value);
}


public void addCronTrigger(String triggerGroup,String triggerName,Date beginTime,Date endTime,String cronExpression){
    if (LOG.isDebugEnabled()) {
        LOG.debug("addCronTrigger, triggerGroup[{}], triggerName[{}], beginTime[{}], endTime[{}], cronExpression[{}]", new Object[] { triggerGroup, triggerName, beginTime, endTime, cronExpression });
    }
    CronJobTrigger trigger = new CronJobTrigger(triggerName, triggerGroup);
    if (beginTime != null) {
        trigger.setBegainTime(beginTime);
    }
    if (endTime != null) {
        trigger.setEndTime(endTime);
    }
    try {
        trigger.setCronExpression(cronExpression);
        triggerList.add(trigger);
    } catch (JobException e) {
        LOG.error("add cron trigger failed", e);
    }
}


public Job getJob(){
    return job;
}


}