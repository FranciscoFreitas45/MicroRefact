package com.kingen.util.ActivitiUtils;
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
public class MapSetter {


public User principal(Session session){
    PrincipalCollection principalCollection = (PrincipalCollection) session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
    return (User) principalCollection.getPrimaryPrincipal();
// return (String)principalCollection.getPrimaryPrincipal();
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


public boolean isForceLogout(Session session){
    return session.getAttribute(Constants.SESSION_FORCE_LOGOUT_KEY) != null;
}


}