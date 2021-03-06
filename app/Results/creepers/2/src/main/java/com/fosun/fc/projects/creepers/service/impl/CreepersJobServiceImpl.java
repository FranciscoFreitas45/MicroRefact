package com.fosun.fc.projects.creepers.service.impl;
 import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.Trigger.TriggerState;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.impl.matchers.GroupMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.fosun.fc.modules.mapper.BeanMapper;
import com.fosun.fc.projects.creepers.dao.CreepersJobDao;
import com.fosun.fc.projects.creepers.dto.CreepersJobDTO;
import com.fosun.fc.projects.creepers.entity.TCreepersJob;
import com.fosun.fc.projects.creepers.schedule.QuartzJobFactory;
import com.fosun.fc.projects.creepers.schedule.QuartzJobFactoryDisallowConcurrentExecution;
import com.fosun.fc.projects.creepers.service.ICreepersJobService;
import com.fosun.fc.projects.creepers.utils.CommonMethodUtils;
import com.fosun.fc.projects.creepers.utils.JobUtils;
@Component
@Transactional
public class CreepersJobServiceImpl implements ICreepersJobService{

 private Logger logger;

@Autowired
 private  SchedulerFactoryBean schedulerFactoryBean;

@Autowired
 private  CreepersJobDao creepersJobDao;


@Override
public List<CreepersJobDTO> getRunningJob(){
    Scheduler scheduler = schedulerFactoryBean.getScheduler();
    List<JobExecutionContext> executingJobs = scheduler.getCurrentlyExecutingJobs();
    List<CreepersJobDTO> jobList = new ArrayList<CreepersJobDTO>(executingJobs.size());
    for (JobExecutionContext executingJob : executingJobs) {
        CreepersJobDTO job = new CreepersJobDTO();
        JobDetail jobDetail = executingJob.getJobDetail();
        JobKey jobKey = jobDetail.getKey();
        Trigger trigger = executingJob.getTrigger();
        job.setJobName(jobKey.getName());
        job.setJobGroup(jobKey.getGroup());
        job.setDescription("?????????:" + trigger.getKey());
        TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
        job.setStatus(triggerState.name());
        if (trigger instanceof CronTrigger) {
            CronTrigger cronTrigger = (CronTrigger) trigger;
            String cronExpression = cronTrigger.getCronExpression();
            job.setCronExpression(cronExpression);
        }
        jobList.add(job);
    }
    return jobList;
}


@Override
public CreepersJobDTO findJob(String jobName){
    TCreepersJob job = creepersJobDao.findTop1ByJobName(jobName);
    CreepersJobDTO jobDTO = new CreepersJobDTO();
    BeanUtils.copyProperties(job, jobDTO);
    return jobDTO;
}


@Transactional(propagation = Propagation.REQUIRES_NEW)
@Override
public void updateResumeRequestByJobName(String jobName,String request){
    creepersJobDao.updateRequestByJobName(jobName, request);
    ;
}


@Override
public boolean resumeJob(CreepersJobDTO scheduleJob){
    Scheduler scheduler = schedulerFactoryBean.getScheduler();
    JobKey jobKey = JobKey.jobKey(scheduleJob.getJobName(), scheduleJob.getJobGroup());
    try {
        scheduler.resumeJob(jobKey);
        creepersJobDao.updateStatusByJobName(TriggerState.NORMAL.name(), scheduleJob.getJobName(), scheduleJob.getJobGroup());
        return true;
    } catch (SchedulerException e) {
    }
    return false;
}


@Override
public boolean addJob(CreepersJobDTO job){
    if (job == null || !TriggerState.NORMAL.name().equals(job.getStatus())) {
        return false;
    }
    if (!JobUtils.isValidExpression(job.getCronExpression())) {
        logger.error("????????????????????????" + job.getJobName() + "," + job.getJobGroup() + "???," + job.getCronExpression());
        return false;
    } else {
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        // ???????????????????????????????????????    // ?????????task_1 ..    // ??? ???group_1 ..
        TriggerKey triggerKey = TriggerKey.triggerKey(job.getJobName(), job.getJobGroup());
        CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
        // ????????????????????????
        if (trigger != null) {
            // ????????????JOB????????????????????????JOB
            deleteJob(job);
        }
        // ????????????????????????
        Class<? extends Job> clazz = CreepersJobDTO.CONCURRENT_IS.equals(job.getIsConcurrent()) ? QuartzJobFactory.class : QuartzJobFactoryDisallowConcurrentExecution.class;
        JobDetail jobDetail = JobBuilder.newJob(clazz).withIdentity(job.getJobName(), job.getJobGroup()).build();
        jobDetail.getJobDataMap().put("scheduleJob", job);
        // ????????????????????????
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(job.getCronExpression());
        // ????????????????????????????????????trigger
        trigger = TriggerBuilder.newTrigger().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();
        scheduler.scheduleJob(jobDetail, trigger);
        // threadSleep();//junit??????????????????
        // ????????????
        updateOrSaveJob(job);
    // } else {
    // // trigger??????????????????????????????????????????
    // CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(job.getCronExpression());
    // // ?????????cronExpression?????????????????????trigger
    // trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();
    // // ?????????trigger????????????job??????
    // scheduler.rescheduleJob(triggerKey, trigger);
    // //???????????????JOB??????
    // updateOrSaveJob(job);
    // //	                creepersJobDao.updateCronExpByJobName(job.getCronExpression(), job.getJobName(), job.getJobGroup());
    // }
    }
    return true;
}


@Override
public List<CreepersJobDTO> getAllJobs(){
    Scheduler scheduler = schedulerFactoryBean.getScheduler();
    GroupMatcher<JobKey> matcher = GroupMatcher.anyJobGroup();
    Set<JobKey> jobKeys = scheduler.getJobKeys(matcher);
    List<CreepersJobDTO> jobList = new ArrayList<CreepersJobDTO>();
    for (JobKey jobKey : jobKeys) {
        List<? extends Trigger> triggers = scheduler.getTriggersOfJob(jobKey);
        for (Trigger trigger : triggers) {
            CreepersJobDTO job = new CreepersJobDTO();
            job.setJobName(jobKey.getName());
            job.setJobGroup(jobKey.getGroup());
            job.setDescription("?????????:" + trigger.getKey());
            // ??????????????????
            job.setNextTime(trigger.getNextFireTime());
            // trigger.getFinalFireTime();//????????????????????????
            // ??????????????????
            job.setPreviousTime(trigger.getPreviousFireTime());
            // trigger.getStartTime();//????????????
            // trigger.getEndTime();//????????????
            // ?????????????????????
            TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
            job.setStatus(triggerState.name());
            // 
            if (trigger instanceof CronTrigger) {
                CronTrigger cronTrigger = (CronTrigger) trigger;
                String cronExpression = cronTrigger.getCronExpression();
                job.setCronExpression(cronExpression);
            }
            jobList.add(job);
        }
    }
    return jobList;
}


@Override
public boolean deleteJob(CreepersJobDTO scheduleJob){
    Scheduler scheduler = schedulerFactoryBean.getScheduler();
    JobKey jobKey = JobKey.jobKey(scheduleJob.getJobName(), scheduleJob.getJobGroup());
    try {
        scheduler.deleteJob(jobKey);
        creepersJobDao.updateStatusByJobName(TriggerState.NONE.name(), scheduleJob.getJobName(), scheduleJob.getJobGroup());
        return true;
    } catch (SchedulerException e) {
    }
    return false;
}


@Override
public boolean pauseJob(CreepersJobDTO scheduleJob){
    Scheduler scheduler = schedulerFactoryBean.getScheduler();
    JobKey jobKey = JobKey.jobKey(scheduleJob.getJobName(), scheduleJob.getJobGroup());
    try {
        scheduler.pauseJob(jobKey);
        creepersJobDao.updateStatusByJobName(TriggerState.PAUSED.name(), scheduleJob.getJobName(), scheduleJob.getJobGroup());
        return true;
    } catch (SchedulerException e) {
    }
    return false;
}


@Override
public void updateCronExpression(CreepersJobDTO scheduleJob){
    Scheduler scheduler = schedulerFactoryBean.getScheduler();
    TriggerKey triggerKey = TriggerKey.triggerKey(scheduleJob.getJobName(), scheduleJob.getJobGroup());
    // ??????trigger?????????spring???????????????????????? bean id="myTrigger"
    CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
    // ????????????????????????
    CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(scheduleJob.getCronExpression());
    // ?????????cronExpression?????????????????????trigger
    trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();
    // ?????????trigger????????????job??????
    scheduler.rescheduleJob(triggerKey, trigger);
    creepersJobDao.updateStatusByJobName(TriggerState.NORMAL.name(), scheduleJob.getJobName(), scheduleJob.getJobGroup());
}


@Override
public CreepersJobDTO getJob(String jobName,String jobGroup){
    CreepersJobDTO job = new CreepersJobDTO();
    TCreepersJob entity = creepersJobDao.findTop1ByJobName(jobName);
    Scheduler scheduler = schedulerFactoryBean.getScheduler();
    TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroup);
    CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
    if (null != trigger) {
        // ??????????????????
        entity.setNextTime(trigger.getNextFireTime());
        // ??????????????????
        entity.setPreviousTime(trigger.getPreviousFireTime());
        entity.setStartTime(trigger.getStartTime());
        if (trigger instanceof CronTrigger) {
            CronTrigger cronTrigger = (CronTrigger) trigger;
            String cronExpression = cronTrigger.getCronExpression();
            job.setCronExpression(cronExpression);
        }
    }
    job = BeanMapper.map(entity, CreepersJobDTO.class);
    return job;
}


public void updateOrSaveJob(CreepersJobDTO scheduleJob){
    TCreepersJob entity = new TCreepersJob();
    entity = BeanMapper.map(scheduleJob, TCreepersJob.class);
    String jobName = scheduleJob.getJobName();
    Long count = creepersJobDao.countByJobName(jobName);
    if (count == 1L) {
        TCreepersJob oldEntity = new TCreepersJob();
        oldEntity = creepersJobDao.findTop1ByJobName(jobName);
        entity.setId(oldEntity.getId());
        entity.setVersion(oldEntity.getVersion());
    }
    CommonMethodUtils.setByDT(entity);
    creepersJobDao.saveAndFlush(entity);
}


@Override
@SuppressWarnings({ "unchecked" })
public Page<?> findList(Map<String,Object> searchParams,int pageNumber,int pageSize,String sortType){
    Sort sort = null;
    if ("auto".equals(sortType)) {
        sort = new Sort(Direction.DESC, "id");
    }
    PageRequest pageable = new PageRequest(pageNumber - 1, pageSize, sort);
    Specification<TCreepersJob> spec = (Specification<TCreepersJob>) CommonMethodUtils.buildSpecification(searchParams, "Dt");
    Page<TCreepersJob> entityPage = creepersJobDao.findAll(spec, pageable);
    List<TCreepersJob> list = entityPage.getContent();
    List<CreepersJobDTO> dtoList = new ArrayList<CreepersJobDTO>();
    dtoList = BeanMapper.mapList(list, CreepersJobDTO.class);
    if (dtoList != null && !dtoList.isEmpty()) {
        for (CreepersJobDTO creepersJobDTO : dtoList) {
            CreepersJobDTO jobInner = getJob(creepersJobDTO.getJobName(), creepersJobDTO.getJobGroup());
            // ????????????
            creepersJobDTO.setStartTime(jobInner.getStartTime());
            // ??????????????????
            creepersJobDTO.setNextTime(jobInner.getNextTime());
            // ??????????????????
            creepersJobDTO.setPreviousTime(jobInner.getPreviousTime());
        }
    }
    Page<?> dtoPage = new PageImpl<CreepersJobDTO>(dtoList, pageable, entityPage.getTotalElements());
    return dtoPage;
}


@Override
public List<CreepersJobDTO> getTaskList(){
    List<CreepersJobDTO> jobs = new ArrayList<CreepersJobDTO>();
    List<TCreepersJob> jobList = creepersJobDao.findAllUseJobs();
    jobs = BeanMapper.mapList(jobList, CreepersJobDTO.class);
    return jobs;
}


@Override
public void testJob(CreepersJobDTO scheduleJob){
    Scheduler scheduler = schedulerFactoryBean.getScheduler();
    JobKey jobKey = JobKey.jobKey(scheduleJob.getJobName(), scheduleJob.getJobGroup());
    scheduler.triggerJob(jobKey);
}


}