package com.fosun.fc.projects.creepers.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class CreepersParamDTOController {

 private CreepersParamDTO creepersparamdto;

 private CreepersParamDTO creepersparamdto;


@PutMapping
("/setTaskType")
public void setTaskType(@RequestParam(name = "taskType") String taskType){
creepersparamdto.setTaskType(taskType);
}


@PutMapping
("/setTaskStatus")
public void setTaskStatus(@RequestParam(name = "taskStatus") String taskStatus){
creepersparamdto.setTaskStatus(taskStatus);
}


@PutMapping
("/putOrderUrl")
public void putOrderUrl(@RequestParam(name = "order") OrderUrlKey order,@RequestParam(name = "url") String url){
creepersparamdto.putOrderUrl(order,url);
}


@PutMapping
("/setErrorPath")
public void setErrorPath(@RequestParam(name = "errorPath") String errorPath){
creepersparamdto.setErrorPath(errorPath);
}


@PutMapping
("/setErrorInfo")
public void setErrorInfo(@RequestParam(name = "errorInfo") String errorInfo){
creepersparamdto.setErrorInfo(errorInfo);
}


@PutMapping
("/putNameValuePair")
public void putNameValuePair(@RequestParam(name = "key") String key,@RequestParam(name = "value") String value){
creepersparamdto.putNameValuePair(key,value);
}


@PutMapping
("/putSearchKeyWord")
public void putSearchKeyWord(@RequestParam(name = "key") String key,@RequestParam(name = "value") String value){
creepersparamdto.putSearchKeyWord(key,value);
}


}