package com.uec.imonitor.task;
 import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.CronExpression;
import org.quartz.CronScheduleBuilder;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;
import com.uec.imonitor.task.service.IScheduledTaskService;
@Component("quartzManager")
public class QuartzManager {

 private Log log;

@Autowired
@Qualifier("schedulerFactoryBean")
 private SchedulerFactoryBean schedulerFactoryBean;

@Autowired
 private IScheduledTaskService scheduledTaskService;

 private  SimpleDateFormat sdf;


public String getTriggerName(String jobName){
    String triggerName = jobName + "_tiggerName";
    return triggerName;
}


public String getJobGroupName(String groupName){
    String jobGroupName = groupName + "_GroupName";
    return jobGroupName;
}


public void removeJob(String jobName,String groupName){
    String tiggerName = this.getTriggerName(jobName);
    String jobGroupName = this.getJobGroupName(groupName);
    // 从工厂中获取一个调度
    Scheduler scheduler = schedulerFactoryBean.getScheduler();
    // 从知道的 触发组 里删除一个指定名称的job
    TriggerKey triggerKey = new TriggerKey(jobName, tiggerName);
    // 从任务调度里暂停一个 触发
    scheduler.pauseTrigger(triggerKey);
    // 将触发变成不可触发的任务
    scheduler.unscheduleJob(triggerKey);
    // 从指定的 任务组 里获取一个 指定名称的 任务
    JobKey jobKey = new JobKey(jobName, jobGroupName);
    // 从触发中删除一个指定的任务
    scheduler.deleteJob(jobKey);
    log.info("Successed to remove a scheduler task ,scheduler task's name is " + jobName);
}


public void clear(){
    Scheduler scheduler = schedulerFactoryBean.getScheduler();
    scheduler.clear();
    log.info("Successed to cleared all scheduler task.");
}


public void addJob(String jobName,String groupName,int triggerPriority,Class<? extends Job> jobClass,int intervalInSeconds,Map<String,Object> map){
    String tiggerName = this.getTriggerName(jobName);
    String jobGroupName = this.getJobGroupName(groupName);
    // 先删除指定的 任务
    this.removeJob(jobName, groupName);
    // 从 调度 工厂中获取一个 任务调度
    Scheduler scheduler = schedulerFactoryBean.getScheduler();
    // 要执行的任务
    JobDetail jobDetail = JobBuilder.newJob(jobClass).withIdentity(jobName, jobGroupName).build();
    // 传入参数
    jobDetail.getJobDataMap().putAll(map);
    // 任务触发器
    Trigger trigger = TriggerBuilder.newTrigger().withIdentity(tiggerName).startAt(new Date()).withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(intervalInSeconds).repeatForever()).withPriority(triggerPriority).build();
    scheduler.scheduleJob(jobDetail, trigger);
    scheduler.start();
    log.info("Successed to add a scheduler task and the scheduler task name is " + tiggerName + "and the interval in seconds is '" + intervalInSeconds + "'");
    log.info("The scheduler task next fire time is " + sdf.format(trigger.getNextFireTime()));
}


}