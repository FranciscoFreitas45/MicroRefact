package com.fosun.fc.projects.creepers.utils;
 import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.util.Date;
import org.apache.commons.lang.StringUtils;
import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.triggers.CronTriggerImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fosun.fc.projects.creepers.dto.CreepersJobDTO;
import com.fosun.fc.projects.creepers.schedule.QuartzJobFactoryDisallowConcurrentExecution;
public class JobUtils {

 private  Logger logger;

 private  String index;

 private  String name;


public void invokMethod(CreepersJobDTO scheduleJob){
    Object object = null;
    Class<?> clazz = null;
    // springId不为空先按springId查找bean
    if (StringUtils.isNotBlank(scheduleJob.getSpringId())) {
    // object = SpringUtils.getBean(scheduleJob.getSpringId());
    } else if (StringUtils.isNotBlank(scheduleJob.getJobClass())) {
        // 按jobClass查找
        try {
            clazz = Class.forName(scheduleJob.getJobClass());
            object = clazz.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    if (object == null) {
        logger.error("任务名称 = [" + scheduleJob.getJobName() + "]---------------未启动成功，请检查执行类是否配置正确！！！");
        return;
    }
    clazz = object.getClass();
    Method method = null;
    try {
        method = clazz.getDeclaredMethod(scheduleJob.getMethodName(), new Class[] { String.class });
    } catch (NoSuchMethodException e) {
        logger.error("任务名称 = [" + scheduleJob.getJobName() + "]---------------未启动成功，请检查执行类的方法名是否设置错误！！！");
    } catch (SecurityException e) {
        e.printStackTrace();
    }
    if (method != null) {
        try {
            method.invoke(object, new Object[] { scheduleJob.getJobName() });
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}


public boolean isValidExpression(String cronExpression){
    // CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);
    CronTriggerImpl trigger = new CronTriggerImpl();
    try {
        trigger.setCronExpression(cronExpression);
        Date date = trigger.computeFirstFireTime(null);
        return date != null && date.after(new Date());
    } catch (ParseException e) {
    }
    return false;
}


public String getName(){
    return name;
}


public String getIndex(){
    return index;
}


public void main(String[] args){
    SchedulerFactory schedulerFactory = new StdSchedulerFactory();
    Scheduler scheduler = schedulerFactory.getScheduler();
    JobDetail jobDetail = JobBuilder.newJob().ofType(QuartzJobFactoryDisallowConcurrentExecution.class).usingJobData("Test1", "Quartz").withIdentity("Test1", "Group1").build();
    Trigger trigger = TriggerBuilder.newTrigger().withSchedule(// 还有更多时间格式，详情可以百度一下
    CronScheduleBuilder.cronSchedule("0/5 * * * * ?")).forJob("Test1", // Trigger找到对应的名称为Test1组为Group1的Job,如果不存在则会在执行scheduler.scheduleJob(jobDetail,trigger);报错
    "Group1").build();
    // 任务每5秒触发一次
    scheduler.scheduleJob(jobDetail, trigger);
    scheduler.start();
}


public void run(String jobName){
    System.out.println("Job running====" + System.currentTimeMillis());
    System.out.println("Job jobName====" + jobName);
}


}