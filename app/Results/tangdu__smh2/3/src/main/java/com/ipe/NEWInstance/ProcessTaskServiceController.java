package com.ipe.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ProcessTaskServiceController {

 private ProcessTaskService processtaskservice;


@GetMapping
("/userTaskList")
public BodyWrapper userTaskList(@RequestParam(name = "params") String params,@RequestParam(name = "rest") RestRequest rest){
  return processtaskservice.userTaskList(params,rest);
}


@GetMapping
("/getTaskList")
public BodyWrapper getTaskList(@RequestParam(name = "params") String params,@RequestParam(name = "rest") RestRequest rest){
  return processtaskservice.getTaskList(params,rest);
}


@PutMapping
("/delTask")
public void delTask(@RequestParam(name = "taskId") String taskId){
processtaskservice.delTask(taskId);
}


@PutMapping
("/clainTask")
public void clainTask(@RequestParam(name = "taskId") String taskId){
processtaskservice.clainTask(taskId);
}


@PutMapping
("/releaseTask")
public void releaseTask(@RequestParam(name = "taskId") String taskId){
processtaskservice.releaseTask(taskId);
}


@PutMapping
("/taskProxy")
public void taskProxy(@RequestParam(name = "taskId") String taskId,@RequestParam(name = "userId") String userId){
processtaskservice.taskProxy(taskId,userId);
}


@PutMapping
("/taskDelegate")
public void taskDelegate(@RequestParam(name = "taskId") String taskId,@RequestParam(name = "userId") String userId){
processtaskservice.taskDelegate(taskId,userId);
}


}