package com.kingen.util;
 import java.util.List;
import java.util.Map;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.kingen.bean.User;
import com.kingen.bean.workflow.ActivitiAware;
import com.kingen.util.workflow.ProcessDefinitionCache;
public class ActivitiUtils {


public User principal(Session session){
    PrincipalCollection principalCollection = (PrincipalCollection) session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
    return (User) principalCollection.getPrimaryPrincipal();
// return (String)principalCollection.getPrimaryPrincipal();
}


public Map mapSetterProcessInstanceWithEntity(ProcessInstance pi,ActivitiAware a){
    if (pi == null)
        return null;
    Map<String, Object> m = Maps.newHashMap();
    // 如果当前是流程实例，那么ID跟processInstanceId 是一样的
    m.put("id", pi.getId());
    m.put("processInstanceId", pi.getProcessInstanceId());
    m.put("processDefinitionId", pi.getProcessDefinitionId());
    m.put("activityId", pi.getActivityId());
    m.put("name", pi.getName());
    m.put("description", pi.getDescription());
    m.put("suspended", pi.isSuspended());
    m.put("version", pi.getProcessDefinitionVersion());
    m.put("clientUint", a.getClientUint());
    m.put("contract", a.getContract());
    m.put("priority", a.getPriority());
    m.put("title", a.getTitle());
    m.put("businessType", a.getBusinessType());
    m.put("businessName", a.getBusinessType());
    m.put("applyDateStr", a.getApplyDateStr());
    // 当前节点
    ProcessDefinitionCache.setRepositoryService(SpringContextHolder.getBean(org.activiti.engine.RepositoryService.class));
    String activityName = ProcessDefinitionCache.getActivityName(pi.getProcessDefinitionId(), ObjectUtils.toString(pi.getActivityId()));
    m.put("activityName", activityName);
    return m;
}


public Map<String,Object> mapSetterSession(Session s){
    if (s == null)
        return null;
    Map<String, Object> m = Maps.newHashMap();
    m.put("id", s.getId());
    m.put("host", s.getHost());
    m.put("lastAccessTime", s.getLastAccessTime());
    m.put("userName", principal(s).getUsername());
    m.put("forced", isForceLogout(s));
    return m;
}


public Map mapSetterProcessInstance(ProcessInstance p){
    if (p == null)
        return null;
    Map<String, Object> m = Maps.newHashMap();
    m.put("id", p.getId());
    m.put("processInstanceId", p.getProcessInstanceId());
    m.put("processDefinitionId", p.getProcessDefinitionId());
    m.put("activityId", p.getActivityId());
    m.put("name", p.getName());
    m.put("description", p.getDescription());
    m.put("suspended", p.isSuspended());
    m.put("version", p.getProcessDefinitionVersion());
    return m;
}


public boolean isForceLogout(Session session){
    return session.getAttribute(Constants.SESSION_FORCE_LOGOUT_KEY) != null;
}


public Map mapSetterHistoricTaskInstance(HistoricTaskInstance p){
    if (p == null)
        return null;
    Map<String, Object> m = Maps.newHashMap();
    m.put("id", p.getId());
    m.put("processInstanceId", p.getProcessInstanceId());
    m.put("processDefinitionId", p.getProcessDefinitionId());
    m.put("name", p.getName());
    m.put("description", p.getDescription());
    m.put("assignee", p.getAssignee());
    m.put("formKey", p.getFormKey());
    m.put("createTime", p.getCreateTime());
    m.put("startTime", p.getStartTime());
    m.put("endTime", p.getEndTime());
    m.put("claimTime", p.getClaimTime());
    m.put("deleteReason", p.getDeleteReason());
    return m;
}


public Map mapSetterTask(Task p){
    if (p == null)
        return null;
    Map<String, Object> m = Maps.newHashMap();
    m.put("id", p.getId());
    m.put("processInstanceId", p.getProcessInstanceId());
    m.put("processDefinitionId", p.getProcessDefinitionId());
    m.put("name", p.getName());
    m.put("description", p.getDescription());
    m.put("assignee", p.getAssignee());
    m.put("owner", p.getOwner());
    m.put("createTime", p.getCreateTime());
    m.put("formKey", p.getFormKey());
    m.put("taskDefinitionKey", p.getTaskDefinitionKey());
    return m;
}


public Map mapSetterHistoricProcessInstance(HistoricProcessInstance p){
    if (p == null)
        return null;
    Map<String, Object> m = Maps.newHashMap();
    m.put("id", p.getId());
    m.put("processDefinitionId", p.getProcessDefinitionId());
    m.put("name", p.getName());
    m.put("description", p.getDescription());
    m.put("startTime", p.getStartTime());
    m.put("endTime", p.getEndTime());
    m.put("businessKey", p.getBusinessKey());
    m.put("deploymentId", p.getDeploymentId());
    m.put("startActivityId", p.getStartActivityId());
    m.put("endActivityId", p.getEndActivityId());
    m.put("deleteReason", p.getDeleteReason());
    return m;
}


public Map mapSetter(ProcessDefinition p){
    if (p == null)
        return null;
    Map<String, Object> m = Maps.newHashMap();
    m.put("id", p.getId());
    m.put("description", p.getDescription());
    m.put("deploymentId", p.getDeploymentId());
    m.put("name", p.getName());
    m.put("version", p.getVersion());
    m.put("resourceName", p.getResourceName());
    m.put("diagramResourceName", p.getDiagramResourceName());
    return m;
}


}