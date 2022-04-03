package com.ipe.module.bpm.controller.web;
 import com.ipe.module.bpm.service.ProcessTaskService;
import com.ipe.module.core.web.controller.AbstractController;
import com.ipe.module.core.web.util.BodyWrapper;
import com.ipe.module.core.web.util.RestRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
@RequestMapping("bpm/task")
public class ProcessTaskController extends AbstractController{

 private  Logger LOGGER;

@Autowired
 private  ProcessTaskService taskService;


@RequestMapping("/task_delegate")
@ResponseBody
public BodyWrapper taskDelegate(String taskId,String userId){
    try {
        taskService.taskDelegate(taskId, userId);
        return success();
    } catch (Exception e) {
        LOGGER.error("Exception {}", e);
        return failure();
    }
}


@RequestMapping("/clain_task")
@ResponseBody
public BodyWrapper clainTask(String taskId){
    try {
        taskService.clainTask(taskId);
        return success();
    } catch (Exception e) {
        LOGGER.error("Exception {}", e);
        return failure();
    }
}


@RequestMapping("/del_task")
@ResponseBody
public BodyWrapper delList(String taskId){
    try {
        taskService.delTask(taskId);
        return success();
    } catch (Exception e) {
        LOGGER.error("Exception {}", e);
        return failure();
    }
}


@RequestMapping("/release_task")
@ResponseBody
public BodyWrapper releaseTask(String taskId){
    try {
        taskService.releaseTask(taskId);
        return success();
    } catch (Exception e) {
        LOGGER.error("Exception {}", e);
        return failure();
    }
}


@RequestMapping("/my_task_list")
@ResponseBody
public BodyWrapper myTaskList(RestRequest restRequest,String params){
    try {
        return taskService.userTaskList(params, restRequest);
    } catch (Exception e) {
        LOGGER.error("Exception {}", e);
        return failure();
    }
}


@RequestMapping("/get_task_list")
@ResponseBody
public BodyWrapper getTaskList(RestRequest restRequest,String params){
    try {
        return taskService.getTaskList(params, restRequest);
    } catch (Exception e) {
        LOGGER.error("Exception {}", e);
        return failure();
    }
}


@RequestMapping("/task_proxy")
@ResponseBody
public BodyWrapper taskProxy(String taskId,String userId){
    try {
        taskService.taskProxy(taskId, userId);
        return success();
    } catch (Exception e) {
        LOGGER.error("Exception {}", e);
        return failure();
    }
}


}