package com.byr.warehouse.sheduler;
 import org.quartz;
import org.quartz.impl.StdScheduler;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
@Component
public class MyScheduler {


public void schedulerJob(){
    ApplicationContext annotationContext = SpringUtil.getApplicationContext();
    // 获得上面创建的bean
    StdScheduler stdScheduler = (StdScheduler) annotationContext.getBean("mySchedulerFactoryBean");
    Scheduler myScheduler = stdScheduler;
    startScheduler1(myScheduler);
    startScheduler2(myScheduler);
    myScheduler.start();
}


public void startScheduler1(Scheduler scheduler){
    JobDetail jobDetail = JobBuilder.newJob(MySchedulerJob.class).withIdentity("job1", "jobGroup1").build();
    CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule("0 50 23 * * ?");
    CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger1", "triggerGroup1").withSchedule(cronScheduleBuilder).build();
    scheduler.scheduleJob(jobDetail, trigger);
}


public void startScheduler2(Scheduler scheduler){
    JobDetail jobDetail = JobBuilder.newJob(MySchedulerJob2.class).withIdentity("job2", "jobGroup2").build();
    CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule("0 20 22 * * ?");
    CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger2", "triggerGroup2").withSchedule(cronScheduleBuilder).build();
    scheduler.scheduleJob(jobDetail, trigger);
}


}