package com.fosun.fc.projects.creepers.schedule;
 import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import com.fosun.fc.projects.creepers.dto.CreepersJobDTO;
import com.fosun.fc.projects.creepers.service.ICreepersListService;
import com.fosun.fc.projects.creepers.Interface.ICreepersListService;
@DisallowConcurrentExecution
public class QuartzJobFactoryDisallowConcurrentExecution extends QuartzJobBean{

 private Logger logger;

@Autowired
 private  ICreepersListService creepersListServiceImpl;


@Override
public void executeInternal(JobExecutionContext context){
    CreepersJobDTO scheduleJob = (CreepersJobDTO) context.getMergedJobDataMap().get("scheduleJob");
    logger.info("运行任务组名称 = [" + scheduleJob.getJobGroup() + "]");
    logger.info("运行任务名称 = [" + scheduleJob.getJobName() + "]");
    creepersListServiceImpl.addTaskByMerName(scheduleJob.getJobGroup(), scheduleJob.getJobName());
}


}