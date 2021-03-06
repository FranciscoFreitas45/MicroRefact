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
    serviceTag.setStatus(ResourcesConstant.Status.?????????.toInteger());
    serviceTag.setCreateTime(new Date());
    this.saveOrUpdate(serviceTag);
    // ??????oneCMDB
    comm.oneCmdbUtilService.saveServiceTagToOneCMDB(serviceTag);
    return serviceTag;
}


public List<ServiceTag> getServiceTagList(){
    return serviceTagDao.findByUserId(getCurrentUserId());
}


public List<ServiceTag> getServiceTagToResourcesList(){
    List<Integer> status = new ArrayList<Integer>();
    status.add(ResourcesConstant.Status.?????????.toInteger());
    status.add(ResourcesConstant.Status.?????????.toInteger());
    status.add(ResourcesConstant.Status.?????????.toInteger());
    status.add(ResourcesConstant.Status.?????????.toInteger());
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
    filters.put("serviceTag.status", new SearchFilter("status", Operator.EQ, ResourcesConstant.Status.?????????.toInteger()));
    filters.put("serviceTag.user.id", new SearchFilter("user.id", Operator.EQ, getCurrentUserId()));
    Specification<ServiceTag> spec = DynamicSpecifications.bySearchFilter(filters.values(), ServiceTag.class);
    return serviceTagDao.findAll(spec, pageRequest);
}


@Transactional(readOnly = false)
public String saveAuditByServiceTag(ServiceTag serviceTag){
    String message = "";
    // ???????????????????????????,???????????????,?????????????????????????????????????????????????????????.
    List<Resources> resourcesList = comm.resourcesService.getCommitingResourcesListByServiceTagId(serviceTag.getId());
    try {
        serviceTag.setStatus(ResourcesConstant.Status.?????????.toInteger());
        this.saveOrUpdate(serviceTag);
        message = "???????????? " + serviceTag.getName() + " ??????????????????";
        String description = comm.redmineUtilService.resourcesRedmineDesc(serviceTag);
        // ????????????Issue???Redmine
        Issue issue = new Issue();
        Integer trackerId = RedmineConstant.Tracker.??????.toInteger();
        Tracker tracker = new Tracker(trackerId, RedmineConstant.Tracker.get(trackerId));
        issue.setTracker(tracker);
        issue.setSubject(comm.applyService.generateTitle(serviceTag.getUser().getLoginName(), "change"));
        issue.setPriorityId(serviceTag.getPriority());
        issue.setDescription(description);
        Integer projectId = RedmineConstant.Project.SobeyCloud??????.toInteger();
        // ????????????????????????
        RedmineManager mgr = RedmineService.FIRST_REDMINE_ASSIGNEE_REDMINEMANAGER;
        boolean isCreated = RedmineService.createIssue(issue, projectId.toString(), mgr);
        logger.info("--->????????????Resource Redmine isCreated?" + isCreated);
        if (isCreated) {
            // ??????Redmine??????
            Integer assignee = RedmineService.FIRST_REDMINE_ASSIGNEE;
            issue = RedmineService.getIssueBySubject(issue.getSubject(), mgr);
            RedmineIssue redmineIssue = new RedmineIssue();
            redmineIssue.setProjectId(projectId);
            redmineIssue.setTrackerId(issue.getTracker().getId());
            redmineIssue.setSubject(issue.getSubject());
            redmineIssue.setAssignee(assignee);
            redmineIssue.setStatus(RedmineConstant.Status.??????.toInteger());
            redmineIssue.setIssueId(issue.getId());
            redmineIssue.setServiceTagId(serviceTag.getId());
            comm.operateService.saveOrUpdate(redmineIssue);
            // ????????????User
            User assigneeUser = comm.accountService.findUserByRedmineUserId(assignee);
            // ????????????????????????
            comm.templateMailService.sendResourcesOperateNotificationMail(serviceTag, assigneeUser);
            for (Resources resources : resourcesList) {
                // ??????redmine?????????,?????????????????????????????? 4.?????????
                resources.setStatus(ResourcesConstant.Status.?????????.toInteger());
                comm.resourcesService.saveOrUpdate(resources);
            }
        } else {
            message = "??????????????????,????????????????????????";
        }
    } catch (Exception e) {
        message = "??????????????????,????????????????????????";
        e.printStackTrace();
    }
    return message;
}


@Transactional(readOnly = false)
public void delete(Integer id){
    // ??????oneCMDB?????????.
    comm.oneCmdbUtilService.deleteServiceTagToOneCMDB(this.getServiceTag(id));
    serviceTagDao.delete(id);
}


@Transactional(readOnly = false)
public ServiceTag saveOrUpdate(ServiceTag serviceTag){
    return serviceTagDao.save(serviceTag);
}


}