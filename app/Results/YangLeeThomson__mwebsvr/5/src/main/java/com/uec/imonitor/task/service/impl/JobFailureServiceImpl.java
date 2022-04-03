package com.uec.imonitor.task.service.impl;
 import java.util.Date;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import com.uec.imonitor.common.exception.BaseException;
import com.uec.imonitor.common.exception.RequestParamException;
import com.uec.imonitor.common.util.CommonUtil;
import com.uec.imonitor.task.bean.JobFailureEntity;
import com.uec.imonitor.task.dao.IJobFailureJPARepository;
import com.uec.imonitor.task.service.IJobFailureService;
@Service("jobFailureService")
@Transactional(value = "transactionManager")
public class JobFailureServiceImpl implements IJobFailureService{

 private  Logger logger;

@Autowired
 private  IJobFailureJPARepository jobFailureRepository;


@Override
public Boolean add(JobFailureEntity jobStatus){
    if (jobStatus == null) {
        throw new RequestParamException(new String[] { "jobStatus" });
    } else {
        jobFailureRepository.save(jobStatus);
        return true;
    }
}


@Override
public JobFailureEntity findJobById(int id){
    if (id <= 0) {
        throw new RequestParamException(new String[] { "id" });
    }
    JobFailureEntity jobFailureEntity = jobFailureRepository.findByInnerId(id);
    return jobFailureEntity;
}


@Override
public List<JobFailureEntity> listTopNByJobAndTableName(String jobName,String tableName,Integer topN){
    if (null == jobName) {
        throw new RequestParamException(new String[] { "jobName" });
    }
    if (null == tableName) {
        throw new RequestParamException(new String[] { "jobName" });
    }
    if (null == topN) {
        throw new RequestParamException(new String[] { "topN" });
    }
    return jobFailureRepository.listTopNByJobAndTableName(jobName, tableName, topN);
}


@Override
public List<JobFailureEntity> listByJobAndTableName(String jobName,String tableName){
    if (null == jobName) {
        throw new RequestParamException(new String[] { "jobName" });
    }
    if (null == tableName) {
        throw new RequestParamException(new String[] { "tableName" });
    }
    return jobFailureRepository.listByJobAndTableName(jobName, tableName);
}


@Override
public List<JobFailureEntity> listByJobName(String jobName){
    if (null == jobName) {
        throw new RequestParamException(new String[] { "jobName" });
    }
    return jobFailureRepository.listByJobName(jobName);
}


@Override
public List<JobFailureEntity> listAll(){
    return jobFailureRepository.findAll();
}


@Override
public Boolean deleteJobFailure(int innerid){
    if (innerid <= 0) {
        throw new RequestParamException(new String[] { "jobName" });
    }
    boolean flag = false;
    JobFailureEntity je = jobFailureRepository.findOne(innerid);
    if (je == null) {
        logger.error("the jobFailure not exist");
        return flag;
    } else {
        jobFailureRepository.delete(innerid);
        return true;
    }
}


@Override
public Boolean deleteJobFailureList(List<JobFailureEntity> jobStatusList){
    if (CollectionUtils.isEmpty(jobStatusList)) {
        throw new RequestParamException(new String[] { "jobStatusList" });
    } else {
        jobFailureRepository.delete(jobStatusList);
        return true;
    }
}


@Override
public boolean saveFailureJob(String jobName,String tableName,List<Integer> idList,Date updateTime,int num){
    if (StringUtils.isBlank(jobName)) {
        throw new RequestParamException(new String[] { "jobName" });
    }
    if (StringUtils.isBlank(tableName)) {
        throw new RequestParamException(new String[] { "tableName" });
    }
    if (CollectionUtils.isEmpty(idList)) {
        throw new RequestParamException(new String[] { "idList" });
    }
    String idStr = null;
    if (idList != null) {
        idStr = CommonUtil.toJson(idList);
    }
    JobFailureEntity jobS = new JobFailureEntity();
    jobS.setIdSet(idStr);
    jobS.setJobName(jobName);
    jobS.setTableName(tableName);
    jobS.setLastModifyTime(updateTime);
    jobS.setNum(num);
    return this.add(jobS);
}


}