package com.ipe.module.bpm.service;
 import com.ipe.common.dao.CustomerRepository;
import com.ipe.common.entity.IDEntity;
import com.ipe.common.service.BaseService;
import com.ipe.module.bpm.controller.pojo.ActTask;
import com.ipe.module.core.entity.User;
import com.ipe.module.core.repository.RoleRepository;
import com.ipe.module.core.repository.UserRepository;
import com.ipe.module.core.web.security.SystemRealm;
import com.ipe.module.core.web.util.BodyWrapper;
import com.ipe.module.core.web.util.RestRequest;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;
@Service
@Transactional(readOnly = false)
public class ProcessTaskService extends BaseService<IDEntity, String>{

@Autowired
 private ProcessEngine processEngine;

@Autowired
 private RepositoryService repositoryService;

@Autowired
 private RuntimeService runtimeService;

@Autowired
 private TaskService taskService;

@Autowired
 private UserRepository userRepository;

@Autowired
 private RoleRepository roleRepository;


public void delTask(String taskId){
    SystemRealm.UserInfo userInfo = (SystemRealm.UserInfo) SecurityUtils.getSubject().getPrincipal();
    Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
    String info = "流程取消【" + userInfo.getUserName() + "(" + userInfo.getUserAccount() + ")】";
    taskService.addComment(taskId, task.getProcessInstanceId(), info);
    runtimeService.deleteProcessInstance(task.getProcessInstanceId(), info);
}


public void taskDelegate(String taskId,String userId){
    taskService.delegateTask(taskId, userId);
}


public void clainTask(String taskId){
    SystemRealm.UserInfo userInfo = (SystemRealm.UserInfo) SecurityUtils.getSubject().getPrincipal();
    taskService.claim(taskId, userInfo.getUserId());
}


@Override
public CustomerRepository<IDEntity,String> getRepository(){
    return null;
}


public List<ActTask> setValue(List<Task> tasklist){
    List<ActTask> list = new ArrayList<ActTask>();
    for (Task task : tasklist) {
        ActTask actTask = new ActTask();
        actTask.setDescription(task.getDescription());
        if (StringUtils.isNotBlank(task.getAssignee())) {
            User user = userRepository.findOne(task.getAssignee());
            actTask.setAssignee(user.getUserName() + "(" + user.getUserAccount() + ")");
        }
        actTask.setCreateTime(task.getCreateTime());
        actTask.setDueDate(task.getDueDate());
        actTask.setExecutionId(task.getExecutionId());
        actTask.setName(task.getName());
        actTask.setOwner(task.getOwner());
        actTask.setParentTaskId(task.getParentTaskId());
        actTask.setPriority(task.getPriority());
        actTask.setProcessDefinitionId(task.getProcessDefinitionId());
        actTask.setProcessInstanceId(task.getProcessInstanceId());
        list.add(actTask);
    }
    return list;
}


public BodyWrapper userTaskList(String params,RestRequest rest){
    BodyWrapper bodyWrapp = new BodyWrapper();
    SystemRealm.UserInfo userInfo = (SystemRealm.UserInfo) SecurityUtils.getSubject().getPrincipal();
    List<Task> tasklist = taskService.createTaskQuery().taskAssignee(userInfo.getUserId()).active().orderByTaskCreateTime().desc().listPage(rest.getStart(), rest.getLimit() + rest.getStart());
    Long count = taskService.createTaskQuery().taskAssignee(userInfo.getUserId()).active().count();
    bodyWrapp.setRows(setValue(tasklist));
    bodyWrapp.setTotal(count);
    return bodyWrapp;
}


public void releaseTask(String taskId){
    taskService.claim(taskId, null);
}


public BodyWrapper getTaskList(String params,RestRequest rest){
    BodyWrapper bodyWrapp = new BodyWrapper();
    SystemRealm.UserInfo userInfo = (SystemRealm.UserInfo) SecurityUtils.getSubject().getPrincipal();
    // 得到用户角色
    List<String> roleIdss = userRepository.getUserRoleIds(userInfo.getUserId());
    // 得到用户组任务
    List<Task> tasklist = taskService.createTaskQuery().taskCandidateGroupIn(roleIdss).active().orderByTaskCreateTime().desc().listPage(rest.getStart(), rest.getLimit() + rest.getStart());
    Long count = taskService.createTaskQuery().taskCandidateGroupIn(roleIdss).active().count();
    bodyWrapp.setRows(setValue(tasklist));
    bodyWrapp.setTotal(count);
    return bodyWrapp;
}


public void taskProxy(String taskId,String userId){
    taskService.addCandidateUser(taskId, userId);
    taskService.claim(taskId, userId);
}


}