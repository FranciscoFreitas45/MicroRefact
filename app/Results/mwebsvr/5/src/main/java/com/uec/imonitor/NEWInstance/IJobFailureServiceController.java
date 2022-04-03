package com.uec.imonitor.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class IJobFailureServiceController {

 private IJobFailureService ijobfailureservice;


@GetMapping
("/listTopNByJobAndTableName")
public List<JobFailureEntity> listTopNByJobAndTableName(@RequestParam(name = "jobName") String jobName,@RequestParam(name = "tableName") String tableName,@RequestParam(name = "topN") Integer topN){
  return ijobfailureservice.listTopNByJobAndTableName(jobName,tableName,topN);
}


@GetMapping
("/deleteJobFailure")
public Boolean deleteJobFailure(@RequestParam(name = "innerid") int innerid){
  return ijobfailureservice.deleteJobFailure(innerid);
}


@GetMapping
("/saveFailureJob")
public boolean saveFailureJob(@RequestParam(name = "jobName") String jobName,@RequestParam(name = "tableName") String tableName,@RequestParam(name = "idList") List<Integer> idList,@RequestParam(name = "updateTime") Date updateTime,@RequestParam(name = "num") int num){
  return ijobfailureservice.saveFailureJob(jobName,tableName,idList,updateTime,num);
}


}