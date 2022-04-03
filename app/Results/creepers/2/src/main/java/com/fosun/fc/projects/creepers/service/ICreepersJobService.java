package com.fosun.fc.projects.creepers.service;
 import java.util.List;
import java.util.Map;
import org.quartz.SchedulerException;
import org.springframework.data.domain.Page;
import com.fosun.fc.projects.creepers.dto.CreepersJobDTO;
public interface ICreepersJobService {


public List<CreepersJobDTO> getRunningJob()
;

public CreepersJobDTO findJob(String jobName)
;

public void updateResumeRequestByJobName(String jobName,String request)
;

public boolean resumeJob(CreepersJobDTO scheduleJob)
;

public boolean addJob(CreepersJobDTO job)
;

public List<CreepersJobDTO> getAllJobs()
;

public boolean deleteJob(CreepersJobDTO scheduleJob)
;

public boolean pauseJob(CreepersJobDTO scheduleJob)
;

public void updateCronExpression(CreepersJobDTO scheduleJob)
;

public CreepersJobDTO getJob(String jobName,String jobGroup)
;

public Page<?> findList(Map<String,Object> searchParams,int pageNumber,int pageSize,String sortType)
;

public List<CreepersJobDTO> getTaskList()
;

public void testJob(CreepersJobDTO scheduleJob)
;

}