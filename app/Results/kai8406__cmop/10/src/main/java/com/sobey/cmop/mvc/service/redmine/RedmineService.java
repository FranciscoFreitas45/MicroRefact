package com.sobey.cmop.mvc.service.redmine;
 import java.util.Date;
import java.util.List;
import java.util.Map;
import org.hibernate.bytecode.buildtime.spi.ExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.google.common.collect.Maps;
import com.sobey.cmop.mvc.comm.BaseSevcie;
import com.sobey.cmop.mvc.constant.RedmineConstant;
import com.taskadapter.redmineapi.RedmineException;
import com.taskadapter.redmineapi.RedmineManager;
import com.taskadapter.redmineapi.RedmineManager.INCLUDE;
import com.taskadapter.redmineapi.bean.Issue;
import com.taskadapter.redmineapi.bean.User;
@Service
@Transactional(readOnly = true)
public class RedmineService extends BaseSevcie{

 private  Logger logger;

 private  int COUNT;

 public  String HOST;

 public  Integer FIRST_REDMINE_ASSIGNEE;

 public  RedmineManager FIRST_REDMINE_ASSIGNEE_REDMINEMANAGER;

 public  Integer MDN_REDMINE_ASSIGNEE;

 public  RedmineManager MDN_REDMINE_ASSIGNEE_REDMINEMANAGER;

 public  Integer CP_REDMINE_ASSIGNEE;

 public  RedmineManager CP_REDMINE_ASSIGNEE_REDMINEMANAGER;

 public  Integer MONITOR_REDMINE_ASSIGNEE;

 public  RedmineManager MONITOR_REDMINE_ASSIGNEE_REDMINEMANAGER;


@Transactional(readOnly = false)
public boolean createIssue(Issue issue,String projectId,RedmineManager mgr){
    boolean result = false;
    try {
        Integer default_doneratio = 0;
        // 默认：新建
        issue.setStatusName("新建");
        issue.setDoneRatio(default_doneratio);
        // 默认：登录者
        issue.setAssignee(mgr.getCurrentUser());
        // 默认：登录者
        issue.setAuthor(mgr.getCurrentUser());
        issue.setStartDate(new Date());
        issue.setDueDate(new Date());
        issue.setCreatedOn(new Date());
        mgr.createIssue(projectId, issue);
        result = true;
        logger.info("--->创建Issue成功！");
    } catch (RedmineException e) {
        e.printStackTrace();
        COUNT++;
        // 重新连接
        repeatConnect(mgr);
        if (COUNT <= 3) {
            result = createIssue(issue, projectId, mgr);
        } else {
            logger.info("--->创建Issue失败！");
            throw new ExecutionException("创建Issue失败！");
        }
    }
    // 重置次数
    COUNT = 0;
    return result;
}


@Transactional(readOnly = false)
public boolean changeIssue(Issue issue,RedmineManager mgr){
    boolean result = false;
    try {
        logger.info("issueId--->" + issue.getId());
        issue.setUpdatedOn(new Date());
        // 已关闭的更新会出错
        mgr.update(issue);
        result = true;
        logger.info("--->更新Issue成功！");
    } catch (RedmineException e) {
        e.printStackTrace();
        COUNT++;
        repeatConnect(mgr);
        if (COUNT <= 3) {
            result = changeIssue(issue, mgr);
        } else {
            logger.info("--->更新Issue失败！");
        }
    }
    // 重置次数
    COUNT = 0;
    return result;
}


public boolean repeatConnect(RedmineManager mgr){
    logger.info("--->RedmineException..." + COUNT);
    try {
        // 延时2秒
        Thread.sleep(2000);
        User user = mgr.getCurrentUser();
        logger.info("--->重新连接Redmine成功！" + user.getFirstName());
        return true;
    } catch (Exception e) {
        e.printStackTrace();
        logger.info("--->重新连接Redmine失败！");
    }
    return false;
}


public Issue getIssue(Integer issueId){
    // 此处若更新了Issue,则保存时将起作用.
    Issue issue = getIssueById(issueId, FIRST_REDMINE_ASSIGNEE_REDMINEMANAGER);
    return issue;
}


public Issue getIssueById(int issueId,RedmineManager mgr){
    Issue issue = null;
    try {
        issue = mgr.getIssueById(issueId, INCLUDE.journals);
        logger.info("--->获取Issue成功！");
    } catch (RedmineException e) {
        e.printStackTrace();
        COUNT++;
        repeatConnect(mgr);
        if (COUNT <= 3) {
            issue = getIssueById(issueId, mgr);
        } else {
            logger.info("--->获取Issue失败！");
        }
    }
    // 重置次数
    COUNT = 0;
    return issue;
}


@SuppressWarnings("unchecked")
public Issue getIssueBySubject(String subject,RedmineManager mgr){
    List<Issue> list = null;
    try {
        Map<String, String> paraMap = Maps.newHashMap();
        paraMap.put("subject", subject);
        list = mgr.getIssues(paraMap);
    } catch (RedmineException e) {
        e.printStackTrace();
        COUNT++;
        repeatConnect(mgr);
        if (COUNT <= 3) {
            list = (List<Issue>) getIssueBySubject(subject, mgr);
        } else {
            logger.info("--->获取Issue失败！");
        }
    }
    // 重置次数
    COUNT = 0;
    if (list != null && list.size() > 0) {
        logger.info("--->获取Issue成功！");
        return list.get(0);
    }
    return null;
}


}