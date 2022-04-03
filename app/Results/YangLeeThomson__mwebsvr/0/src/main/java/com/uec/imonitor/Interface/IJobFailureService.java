package com.uec.imonitor.Interface;
public interface IJobFailureService {

   public List<JobFailureEntity> listTopNByJobAndTableName(String jobName,String tableName,Integer topN);
   public Boolean deleteJobFailure(int innerid);
   public boolean saveFailureJob(String jobName,String tableName,List<Integer> idList,Date updateTime,int num);
}