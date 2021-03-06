package com.fosun.fc.projects.creepers.schedule;
 import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import com.fosun.fc.projects.creepers.dto.CreepersJobDTO;
import com.fosun.fc.projects.creepers.service.ICreepersListService;
import com.fosun.fc.projects.creepers.Interface.ICreepersListService;
public class QuartzJobFactory implements Job{

 private Logger logger;

@Autowired
 private  ICreepersListService creepersListServiceImpl;


@Override
public void execute(JobExecutionContext context){
    // 使得job对象可以通过注解实现依赖注入
    CreepersJobDTO scheduleJob = (CreepersJobDTO) context.getMergedJobDataMap().get("scheduleJob");
    logger.info("运行任务组名称 = [" + scheduleJob.getJobGroup() + "]");
    logger.info("运行任务名称 = [" + scheduleJob.getJobName() + "]");
    creepersListServiceImpl.addTaskByMerName(scheduleJob.getJobGroup(), scheduleJob.getJobName());
}


}