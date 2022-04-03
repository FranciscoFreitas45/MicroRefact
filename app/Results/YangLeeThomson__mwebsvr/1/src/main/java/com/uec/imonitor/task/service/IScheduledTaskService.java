package com.uec.imonitor.task.service;
 import java.util.List;
import org.springframework.util.StringUtils;
import com.uec.imonitor.common.exception.BaseException;
import com.uec.imonitor.common.exception.RequestParamException;
import com.uec.imonitor.task.bean.ScheduledTaskEntity;
public interface IScheduledTaskService {


public ScheduledTaskEntity findByIdentification(String identification)
;

public List<ScheduledTaskEntity> listDisabled()
;

public ScheduledTaskEntity update(ScheduledTaskEntity scheduledTaskEntity)
;

public ScheduledTaskEntity updateIntervalInSeconds(String identification,String groupName,Integer priority,int intervalInSeconds,String className)
;

public List<ScheduledTaskEntity> listEnabled()
;

public ScheduledTaskEntity enableScheduledTask(String identification)
;

public ScheduledTaskEntity disableScheduledTask(String identification,String groupName)
;

public boolean disableAllScheduledTasks()
;

public ScheduledTaskEntity updateCronExpression(String identification,String groupName,Integer priority,int intervalInSeconds,String className)
;

public boolean enableAllScheduledTasks()
;

}