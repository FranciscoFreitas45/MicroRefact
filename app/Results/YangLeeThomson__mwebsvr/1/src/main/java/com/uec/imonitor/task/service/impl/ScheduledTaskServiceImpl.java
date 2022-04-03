package com.uec.imonitor.task.service.impl;
 import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.CronExpression;
import org.quartz.Job;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import com.uec.imonitor.common.base.BaseService;
import com.uec.imonitor.common.exception.BaseException;
import com.uec.imonitor.common.exception.BusinessException;
import com.uec.imonitor.common.exception.RequestParamException;
import com.uec.imonitor.common.util.ConstantUtil;
import com.uec.imonitor.es.service.IEsIndexService;
import com.uec.imonitor.task.QuartzManager;
import com.uec.imonitor.task.bean.ScheduledTaskEntity;
import com.uec.imonitor.task.dao.IScheduledTaskJPARepository;
import com.uec.imonitor.task.service.IScheduledTaskService;
import com.uec.imonitor.Interface.IEsIndexService;
@Service("scheduledTaskService")
@Order(value = 1)
public class ScheduledTaskServiceImpl extends BaseServiceimplements CommandLineRunner,IScheduledTaskService{

 private Log log;

@Value("${inews.quartz.task.isreload}")
 private  boolean isReload;

@Autowired
 private IScheduledTaskJPARepository scheduledTaskJPARepository;

@Autowired
 private QuartzManager quartzManager;

@Autowired
 private  IEsIndexService esIndexService;


@Override
public List<ScheduledTaskEntity> listDisabled(){
    List<ScheduledTaskEntity> list = scheduledTaskJPARepository.listAllByEnable(false);
    list.forEach(l -> {
        log.info("-----------" + l.toString() + ":" + l.getClassName());
    });
    list.stream().forEach(l -> {
        log.info("-----------" + l.toString() + ":" + l.getClassName());
    });
    return list;
}


@Override
public ScheduledTaskEntity findByIdentification(String identification){
    if (StringUtils.isEmpty(identification)) {
        throw new RequestParamException(new String[] { "identification" });
    }
    return scheduledTaskJPARepository.findByIdentification(identification);
}


@Override
public ScheduledTaskEntity update(ScheduledTaskEntity scheduledTaskEntity){
    return scheduledTaskJPARepository.saveAndFlush(scheduledTaskEntity);
}


@SuppressWarnings("unchecked")
@Override
public ScheduledTaskEntity updateIntervalInSeconds(String identification,String groupName,Integer priority,int intervalInSeconds,String className){
    if (StringUtils.isEmpty(identification)) {
        throw new RequestParamException(new String[] { "identification" });
    }
    if (StringUtils.isEmpty(groupName)) {
        throw new RequestParamException(new String[] { "groupName" });
    }
    if (priority == null) {
        // priority = ConstantUtil.TASK_DEFAULT_PRIORITY;
        priority = 5;
    }
    if (intervalInSeconds <= 0) {
        // 参数intervalInSeconds必须大于0.
        throw new BusinessException("005001003");
    }
    ScheduledTaskEntity scheduledTaskEntity = this.findByIdentification(identification);
    try {
        // quartzManager.addJob(identification,TaskJob.class,intervalInSeconds,new HashMap<String,Object>());
        quartzManager.addJob(identification, groupName, priority, (Class<? extends Job>) Class.forName("com.uec.imonitor.task.job." + className), intervalInSeconds, new HashMap<String, Object>());
    } catch (SchedulerException e) {
        log.error("Failed to update scheduled task in job_scheduled_tasks subsystem witch the identification is " + identification + ".");
        throw new BaseException(e);
    } catch (ClassNotFoundException e) {
        throw new BaseException(e);
    }
    if (null == scheduledTaskEntity) {
        // identification值为{0}的ScheduledTaskEntity实体不存在。
        throw new BusinessException("005001004", new String[] { identification });
    }
    if (null == scheduledTaskEntity.getEnable() || false == scheduledTaskEntity.getEnable()) {
        scheduledTaskEntity.setEnable(true);
    }
    scheduledTaskEntity.setIntervalInSeconds(intervalInSeconds);
    scheduledTaskEntity.setCronExpression(null);
    scheduledTaskEntity.setTriggerStartTime(new Date());
    scheduledTaskEntity.setGroupName(groupName);
    scheduledTaskEntity.setPriority(priority);
    return this.update(scheduledTaskEntity);
}


@Override
public void run(String arg0){
    log.info("ScheduledTaskServiceImpl running!");
    // esIndexService.EsIndexSetting();
    // esIndexService.initAllRequestNews();
    // esIndexService.initAllNewsSpreading();
    // -----------定时任务注解
    if (isReload) {
        this.disableAllScheduledTasks();
        this.enableAllScheduledTasks();
    }
    log.info("Successed to enabled all scheduled tasks.");
}


@Override
public List<ScheduledTaskEntity> listEnabled(){
    List<ScheduledTaskEntity> list = scheduledTaskJPARepository.listAllByEnable(true);
    for (ScheduledTaskEntity l : list) {
        log.info("+++++++++++" + l.toString() + ":" + l.getClassName());
    }
    return list;
}


@Override
public ScheduledTaskEntity enableScheduledTask(String identification){
    if (StringUtils.isEmpty(identification)) {
        throw new RequestParamException(new String[] { "identification" });
    }
    ScheduledTaskEntity scheduledTaskEntity = this.findByIdentification(identification);
    if (null == scheduledTaskEntity.getEnable() || false == scheduledTaskEntity.getEnable()) {
        scheduledTaskEntity.setEnable(true);
        return this.update(scheduledTaskEntity);
    } else {
        return scheduledTaskEntity;
    }
}


@Override
public ScheduledTaskEntity disableScheduledTask(String identification,String groupName){
    if (StringUtils.isEmpty(identification)) {
        throw new RequestParamException(new String[] { "identification" });
    }
    try {
        quartzManager.removeJob(identification, groupName);
    } catch (SchedulerException e) {
        log.error("Failed to remove a scheduled task in job_scheduled_tasks subsystem witch the identification is " + identification + ".");
        throw new BaseException(e);
    }
    ScheduledTaskEntity scheduledTaskEntity = this.findByIdentification(identification);
    if (null == scheduledTaskEntity.getEnable() || true == scheduledTaskEntity.getEnable()) {
        scheduledTaskEntity.setEnable(false);
        return this.update(scheduledTaskEntity);
    } else {
        return scheduledTaskEntity;
    }
}


@Override
public boolean disableAllScheduledTasks(){
    List<ScheduledTaskEntity> disablelist = this.listDisabled();
    if (CollectionUtils.isEmpty(disablelist)) {
        log.info("There is no scheduled task.");
        return true;
    } else {
        List<Map<String, String>> identificationList = new ArrayList<>();
        for (ScheduledTaskEntity entity : disablelist) {
            Map<String, String> entityMap = new HashMap<>();
            entityMap.put("jobName", entity.getIdentification());
            entityMap.put("groupName", entity.getGroupName());
            identificationList.add(entityMap);
        }
        removeListJobs(identificationList);
    }
    return false;
}


@SuppressWarnings("unchecked")
@Override
public ScheduledTaskEntity updateCronExpression(String identification,String groupName,Integer priority,int intervalInSeconds,String className){
    if (StringUtils.isEmpty(identification)) {
        throw new RequestParamException(new String[] { "identification" });
    }
    if (intervalInSeconds <= 0) {
        // 参数intervalInSeconds必须大于0.
        throw new BusinessException("005001003");
    }
    if (StringUtils.isEmpty(groupName)) {
        throw new RequestParamException(new String[] { "groupName" });
    }
    if (priority == null) {
        // priority = ConstantUtil.TASK_DEFAULT_PRIORITY;
        priority = 5;
    }
    ScheduledTaskEntity scheduledTaskEntity = this.findByIdentification(identification);
    try {
        // quartzManager.addJob(identification,TaskJob.class,intervalInSeconds,new HashMap<String,Object>());
        quartzManager.addJob(identification, groupName, priority, (Class<? extends Job>) Class.forName("com.uec.imonitor.task.job." + className), intervalInSeconds, new HashMap<String, Object>());
    } catch (SchedulerException e) {
        log.error("Failed to update scheduled task in job_scheduled_tasks subsystem witch the identification is " + identification + ".");
        throw new BaseException(e);
    } catch (ClassNotFoundException e) {
        throw new BaseException(e);
    }
    if (null == scheduledTaskEntity) {
        // identification值为{0}的ScheduledTaskEntity实体不存在。
        throw new BusinessException("005001004", new String[] { identification });
    }
    if (null == scheduledTaskEntity.getEnable() || false == scheduledTaskEntity.getEnable()) {
        scheduledTaskEntity.setEnable(true);
    }
    scheduledTaskEntity.setIntervalInSeconds(intervalInSeconds);
    scheduledTaskEntity.setCronExpression(null);
    scheduledTaskEntity.setTriggerStartTime(new Date());
    scheduledTaskEntity.setGroupName(groupName);
    scheduledTaskEntity.setPriority(priority);
    return this.update(scheduledTaskEntity);
}


@Override
public boolean enableAllScheduledTasks(){
    List<ScheduledTaskEntity> enablelist = this.listEnabled();
    if (CollectionUtils.isEmpty(enablelist)) {
        log.info("There is no scheduled task.");
        return true;
    } else {
        List<Map<String, String>> identificationList = new ArrayList<>();
        try {
            for (ScheduledTaskEntity entity : enablelist) {
                // 当cronExpression和intervalInSeconds都有值时，抛出异常
                if (!StringUtils.isEmpty(entity.getCronExpression()) && null != entity.getIntervalInSeconds() && 0 != entity.getIntervalInSeconds()) {
                    // 参数'cronExpression'和'intervalInSeconds',不能同时都有值.
                    throw new BusinessException("005001005");
                } else {
                    if (StringUtils.isEmpty(entity.getIdentification())) {
                        throw new RequestParamException(new String[] { "identification" });
                    }
                    if (StringUtils.isEmpty(entity.getGroupName())) {
                        throw new RequestParamException(new String[] { "groupName" });
                    }
                    if (entity.getPriority() == null) {
                        entity.setPriority(5);
                    }
                    if (!StringUtils.isEmpty(entity.getCronExpression())) {
                        this.updateCronExpression(entity.getIdentification(), entity.getGroupName(), entity.getPriority(), entity.getCronExpression(), entity.getClassName());
                    } else if (null != entity.getIntervalInSeconds()) {
                        this.updateIntervalInSeconds(entity.getIdentification(), entity.getGroupName(), entity.getPriority(), entity.getIntervalInSeconds(), entity.getClassName());
                    } else {
                        // 参数'cronExpression'和'intervalInSeconds'的值,
                        throw new BusinessException("005001006");
                    // 不能同时为'NULL'.
                    }
                }
                Map<String, String> entityMap = new HashMap<>();
                entityMap.put("jobName", entity.getIdentification());
                entityMap.put("groupName", entity.getGroupName());
                identificationList.add(entityMap);
            }
        } catch (BaseException e) {
            // 回滚定时任务
            removeListJobs(identificationList);
            throw e;
        } catch (Exception e) {
            // 回滚定时任务
            removeListJobs(identificationList);
            new BaseException(e);
        }
    }
    return false;
}


public void removeListJobs(List<Map<String,String>> identificationList){
    if (!CollectionUtils.isEmpty(identificationList)) {
        for (Map<String, String> identification : identificationList) {
            try {
                quartzManager.removeJob(identification.get("jobName"), identification.get("groupName"));
            } catch (SchedulerException e) {
                log.error("Failed to remove scheduled task in uec_scheduled_tasks subsystem witch the identification is " + identification + ".");
                new BaseException(e);
            }
        }
    }
}


}