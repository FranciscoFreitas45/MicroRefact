package com.uec.imonitor.task.service;
 import java.util.Date;
import java.util.List;
import com.uec.imonitor.common.exception.BaseException;
import com.uec.imonitor.common.exception.RequestParamException;
import com.uec.imonitor.task.bean.JobFailureEntity;
public interface IJobFailureService {


public Boolean add(JobFailureEntity jobStatus)
;

public JobFailureEntity findJobById(int id)
;

public List<JobFailureEntity> listTopNByJobAndTableName(String jobName,String tableName,Integer topN)
;

public List<JobFailureEntity> listByJobAndTableName(String jobName,String tableName)
;

public List<JobFailureEntity> listByJobName(String jobName)
;

public List<JobFailureEntity> listAll()
;

public Boolean deleteJobFailure(int innerid)
;

public boolean saveFailureJob(String jobName,String tableName,List<Integer> idList,Date updateTime,int num)
;

public Boolean deleteJobFailureList(List<JobFailureEntity> jobStatusList)
;

}