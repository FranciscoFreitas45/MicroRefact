package com.sobey.cmop.mvc.service.resource;
 import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.sobey.cmop.mvc.comm.BaseSevcie;
import com.sobey.cmop.mvc.constant.RedmineConstant;
import com.sobey.cmop.mvc.constant.ResourcesConstant;
import com.sobey.cmop.mvc.dao.ServiceTagDao;
import com.sobey.cmop.mvc.entity.Apply;
import com.sobey.cmop.mvc.entity.RedmineIssue;
import com.sobey.cmop.mvc.entity.Resources;
import com.sobey.cmop.mvc.entity.ServiceTag;
import com.sobey.cmop.mvc.entity.User;
import com.sobey.cmop.mvc.service.redmine.RedmineService;
import com.sobey.framework.utils.DynamicSpecifications;
import com.sobey.framework.utils.Identities;
import com.sobey.framework.utils.SearchFilter;
import com.sobey.framework.utils.SearchFilter.Operator;
import com.taskadapter.redmineapi.RedmineManager;
import com.taskadapter.redmineapi.bean.Issue;
import com.taskadapter.redmineapi.bean.Tracker;
import com.sobey.cmop.mvc.DTO.SearchFilter;
import com.sobey.cmop.mvc.DTO.User;
import com.sobey.cmop.mvc.DTO.RedmineIssue;
import com.sobey.cmop.mvc.DTO.Resources;
@Service
@Transactional(readOnly = true)
public class ServiceTagService extends BaseSevcie{

 private  Logger logger;

 private  String TAG_IDENTIFIER;

@Resource
 private  ServiceTagDao serviceTagDao;


public ServiceTag findServiceTagByNameAndUserId(String name,Integer userId){
    return serviceTagDao.findByNameAndUserId(name, userId);
}


@Transactional(readOnly = false)
public ServiceTag updateServiceTagAndOneCMDB(ServiceTag serviceTag){
    comm.oneCmdbUtilService.saveServiceTagToOneCMDB(serviceTag);
    return serviceTagDao.save(serviceTag);
}


@Transactional(readOnly = false)
public ServiceTag saveServiceTag(ServiceTag serviceTag){
    serviceTag.setIdentifier(TAG_IDENTIFIER + "-" + Identities.randomBase62(8));
    serviceTag.setUser(comm.accountService.getCurrentUser());
    serviceTag.setStatus(ResourcesConstant.Status.未变更.toInteger());
    serviceTag.setCreateTime(new Date());
    this.saveOrUpdate(serviceTag);
    // 插入oneCMDB
    comm.oneCmdbUtilService.saveServiceTagToOneCMDB(serviceTag);
    return serviceTag;
}


public List<ServiceTag> getServiceTagList(){
    return serviceTagDao.findByUserId(getCurrentUserId());
}


public List<ServiceTag> getServiceTagToResourcesList(){
    List<Integer> status = new ArrayList<Integer>();
    status.add(ResourcesConstant.Status.未变更.toInteger());
    status.add(ResourcesConstant.Status.已变更.toInteger());
    status.add(ResourcesConstant.Status.已退回.toInteger());
    status.add(ResourcesConstant.Status.已创建.toInteger());
    return serviceTagDao.findByUserIdAndStatusInOrderByIdDesc(getCurrentUserId(), status);
}


public Page<ServiceTag> getServiceTagPageable(Map<String,Object> searchParams,int pageNumber,int pageSize){
    PageRequest pageRequest = buildPageRequest(pageNumber, pageSize);
    Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
    filters.put("serviceTag.user.id", new SearchFilter("user.id", Operator.EQ, getCurrentUserId()));
    Specification<ServiceTag> spec = DynamicSpecifications.bySearchFilter(filters.values(), ServiceTag.class);
    return serviceTagDao.findAll(spec, pageRequest);
}


public ServiceTag getServiceTag(Integer id){
    return serviceTagDao.findOne(id);
}


public Page<ServiceTag> getCommitServiceTagPageable(Map<String,Object> searchParams,int pageNumber,int pageSize){
    PageRequest pageRequest = buildPageRequest(pageNumber, pageSize);
    Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
    filters.put("serviceTag.status", new SearchFilter("status", Operator.EQ, ResourcesConstant.Status.已变更.toInteger()));
    filters.put("serviceTag.user.id", new SearchFilter("user.id", Operator.EQ, getCurrentUserId()));
    Specification<ServiceTag> spec = DynamicSpecifications.bySearchFilter(filters.values(), ServiceTag.class);
    return serviceTagDao.findAll(spec, pageRequest);
}


@Transactional(readOnly = false)
public String saveAuditByServiceTag(ServiceTag serviceTag){
    String message = "";
    // 如果有上级领导存在,则发送邮件,否则返回字符串提醒用户没有上级领导存在.
    List<Resources> resourcesList = comm.resourcesService.getCommitingResourcesListByServiceTagId(serviceTag.getId());
    try {
        serviceTag.setStatus(ResourcesConstant.Status.待审批.toInteger());
        this.saveOrUpdate(serviceTag);
        message = "服务标签 " + serviceTag.getName() + " 提交审批成功";
        String description = comm.redmineUtilService.resourcesRedmineDesc(serviceTag);
        // 写入工单Issue到Redmine
        Issue issue = new Issue();
        Integer trackerId = RedmineConstant.Tracker.支持.toInteger();
        Tracker tracker = new Tracker(trackerId, RedmineConstant.Tracker.get(trackerId));
        issue.setTracker(tracker);
        issue.setSubject(comm.applyService.generateTitle(serviceTag.getUser().getLoginName(), "change"));
        issue.setPriorityId(serviceTag.getPriority());
        issue.setDescription(description);
        Integer projectId = RedmineConstant.Project.SobeyCloud运营.toInteger();
        // 初始化第一接收人
        RedmineManager mgr = RedmineService.FIRST_REDMINE_ASSIGNEE_REDMINEMANAGER;
        boolean isCreated = RedmineService.createIssue(issue, projectId.toString(), mgr);
        logger.info("--->资源变更Resource Redmine isCreated?" + isCreated);
        if (isCreated) {
            // 写入Redmine成功
            Integer assignee = RedmineService.FIRST_REDMINE_ASSIGNEE;
            issue = RedmineService.getIssueBySubject(issue.getSubject(), mgr);
            RedmineIssue redmineIssue = new RedmineIssue();
            redmineIssue.setProjectId(projectId);
            redmineIssue.setTrackerId(issue.getTracker().getId());
            redmineIssue.setSubject(issue.getSubject());
            redmineIssue.setAssignee(assignee);
            redmineIssue.setStatus(RedmineConstant.Status.新建.toInteger());
            redmineIssue.setIssueId(issue.getId());
            redmineIssue.setServiceTagId(serviceTag.getId());
            comm.operateService.saveOrUpdate(redmineIssue);
            // 指派人的User
            User assigneeUser = comm.accountService.findUserByRedmineUserId(assignee);
            // 发送工单处理邮件
            comm.templateMailService.sendResourcesOperateNotificationMail(serviceTag, assigneeUser);
            for (Resources resources : resourcesList) {
                // 写入redmine成功后,资源状态也随之改变为 4.已审批
                resources.setStatus(ResourcesConstant.Status.已审批.toInteger());
                comm.resourcesService.saveOrUpdate(resources);
            }
        } else {
            message = "工单创建失败,请联系系统管理员";
        }
    } catch (Exception e) {
        message = "工单创建失败,请联系系统管理员";
        e.printStackTrace();
    }
    return message;
}


@Transactional(readOnly = false)
public void delete(Integer id){
    // 删除oneCMDB中数据.
    comm.oneCmdbUtilService.deleteServiceTagToOneCMDB(this.getServiceTag(id));
    serviceTagDao.delete(id);
}


@Transactional(readOnly = false)
public ServiceTag saveOrUpdate(ServiceTag serviceTag){
    return serviceTagDao.save(serviceTag);
}


}