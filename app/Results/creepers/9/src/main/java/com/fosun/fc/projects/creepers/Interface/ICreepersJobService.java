package com.fosun.fc.projects.creepers.Interface;
public interface ICreepersJobService {

   public CreepersJobDTO findJob(String jobName);
   public void updateResumeRequestByJobName(String jobName,String request);
}