package com.sobey.cmop.mvc.service.audit;
 import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.sobey.cmop.mvc.comm.BaseSevcie;
import com.sobey.cmop.mvc.constant.AccountConstant;
import com.sobey.cmop.mvc.constant.ApplyConstant;
import com.sobey.cmop.mvc.constant.AuditConstant;
import com.sobey.cmop.mvc.constant.RedmineConstant;
import com.sobey.cmop.mvc.constant.ResourcesConstant;
import com.sobey.cmop.mvc.dao.AuditDao;
import com.sobey.cmop.mvc.dao.AuditFlowDao;
import com.sobey.cmop.mvc.entity.Apply;
import com.sobey.cmop.mvc.entity.Audit;
import com.sobey.cmop.mvc.entity.AuditFlow;
import com.sobey.cmop.mvc.entity.RedmineIssue;
import com.sobey.cmop.mvc.entity.Resources;
import com.sobey.cmop.mvc.entity.ServiceTag;
import com.sobey.cmop.mvc.entity.User;
import com.sobey.cmop.mvc.service.redmine.RedmineService;
import com.sobey.framework.utils.DynamicSpecifications;
import com.sobey.framework.utils.SearchFilter;
import com.sobey.framework.utils.SearchFilter.Operator;
import com.taskadapter.redmineapi.RedmineManager;
import com.taskadapter.redmineapi.bean.Issue;
import com.taskadapter.redmineapi.bean.Tracker;
@Service
@Transactional(readOnly = true)
public class AuditService extends BaseSevcie{

 private  Logger logger;

@Resource
 private  AuditDao auditDao;

@Resource
 private  AuditFlowDao auditFlowDao;


public Page<Audit> getAuditResourcesPageable(Map<String,Object> searchParams,int pageNumber,int pageSize){
    PageRequest pageRequest = buildPageRequest(pageNumber, pageSize, new Sort(Direction.DESC, "serviceTag.id"));
    Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
    filters.put("audit.auditFlow.user.id", new SearchFilter("auditFlow.user.id", Operator.EQ, getCurrentUserId()));
    filters.put("audit.serviceTag.id", new SearchFilter("serviceTag.id", Operator.NotNull, null));
    Specification<Audit> spec = DynamicSpecifications.bySearchFilter(filters.values(), Audit.class);
    return auditDao.findAll(spec, pageRequest);
}


@Transactional(readOnly = false)
public Audit saveOrUpdateAudit(Audit audit){
    return auditDao.save(audit);
}


public List<Audit> getAuditListByApplyId(Integer applyId){
    return auditDao.findByApplyId(applyId);
}


public Page<Audit> getAuditApplyPageable(Map<String,Object> searchParams,int pageNumber,int pageSize){
    PageRequest pageRequest = buildPageRequest(pageNumber, pageSize, new Sort(Direction.DESC, "apply.id"));
    Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
    filters.put("audit.auditFlow.user.id", new SearchFilter("auditFlow.user.id", Operator.EQ, getCurrentUserId()));
    filters.put("audit.apply.id", new SearchFilter("apply.id", Operator.NotNull, null));
    Specification<Audit> spec = DynamicSpecifications.bySearchFilter(filters.values(), Audit.class);
    return auditDao.findAll(spec, pageRequest);
}


@Transactional(readOnly = false)
public boolean saveAuditToApply(Audit audit,Integer applyId,Integer userId){
    Apply apply = comm.applyService.getApply(applyId);
    User user = AccountConstant.FROM_PAGE_USER_ID.equals(userId) ? comm.accountService.getCurrentUser() : comm.accountService.getUser(userId);
    Integer flowType = AuditConstant.FlowType.????????????_?????????????????????.toInteger();
    AuditFlow auditFlow = this.findAuditFlowByUserIdAndFlowType(user.getId(), flowType);
    audit.setApply(apply);
    audit.setAuditFlow(auditFlow);
    audit.setCreateTime(new Date());
    audit.setStatus(AuditConstant.AuditStatus.??????.toInteger());
    if (audit.getResult().equals(AuditConstant.Result.??????????????????.toString())) {
        logger.info("--->??????Apply????????????...");
        apply.setStatus(ApplyConstant.Status.?????????.toInteger());
        String contentText = "?????????????????? " + apply.getTitle() + " ????????????<a href=\"" + CONFIG_LOADER.getProperty("APPLY_URL") + "\">&#8594??????????????????</a><br>";
        // ????????????????????????
        comm.simpleMailService.sendNotificationMail(apply.getUser().getEmail(), "????????????/??????????????????", contentText);
    } else {
        if (auditFlow.getIsFinal()) {
            // ?????????
            logger.info("--->??????Apply????????????...");
            apply.setStatus(ApplyConstant.Status.?????????.toInteger());
            // ??????Redmine??????
            String description = comm.redmineUtilService.applyRedmineDesc(apply);
            // ????????????Issue???Redmine
            Issue issue = new Issue();
            Integer trackerId = RedmineConstant.Tracker.??????.toInteger();
            Tracker tracker = new Tracker(trackerId, RedmineConstant.Tracker.get(trackerId));
            issue.setTracker(tracker);
            issue.setSubject(apply.getTitle());
            issue.setPriorityId(apply.getPriority());
            issue.setDescription(description);
            Integer projectId = RedmineConstant.Project.SobeyCloud??????.toInteger();
            // ????????????????????????
            RedmineManager mgr = RedmineService.FIRST_REDMINE_ASSIGNEE_REDMINEMANAGER;
            if (apply.getTitle().indexOf(ApplyConstant.ServiceType.get(ApplyConstant.ServiceType.MDN.toInteger())) > 0) {
                mgr = RedmineService.MDN_REDMINE_ASSIGNEE_REDMINEMANAGER;
            } else if (apply.getTitle().indexOf(ApplyConstant.ServiceType.get(ApplyConstant.ServiceType.?????????.toInteger())) > 0) {
                mgr = RedmineService.CP_REDMINE_ASSIGNEE_REDMINEMANAGER;
            } else if (apply.getTitle().indexOf(ApplyConstant.ServiceType.get(ApplyConstant.ServiceType.??????.toInteger())) > 0) {
                mgr = RedmineService.MONITOR_REDMINE_ASSIGNEE_REDMINEMANAGER;
            }
            boolean isCreated = RedmineService.createIssue(issue, projectId.toString(), mgr);
            logger.info("--->??????Apply Redmine isCreated?" + isCreated);
            if (isCreated) {
                // ??????Redmine??????
                Integer assignee = RedmineService.FIRST_REDMINE_ASSIGNEE;
                if (apply.getTitle().indexOf(ApplyConstant.ServiceType.get(ApplyConstant.ServiceType.MDN.toInteger())) > 0) {
                    assignee = RedmineService.MDN_REDMINE_ASSIGNEE;
                } else if (apply.getTitle().indexOf(ApplyConstant.ServiceType.get(ApplyConstant.ServiceType.?????????.toInteger())) > 0) {
                    assignee = RedmineService.CP_REDMINE_ASSIGNEE;
                } else if (apply.getTitle().indexOf(ApplyConstant.ServiceType.get(ApplyConstant.ServiceType.??????.toInteger())) > 0) {
                    assignee = RedmineService.MONITOR_REDMINE_ASSIGNEE;
                }
                issue = RedmineService.getIssueBySubject(issue.getSubject(), mgr);
                RedmineIssue redmineIssue = new RedmineIssue();
                redmineIssue.setProjectId(projectId);
                redmineIssue.setTrackerId(issue.getTracker().getId());
                redmineIssue.setSubject(issue.getSubject());
                redmineIssue.setAssignee(assignee);
                redmineIssue.setStatus(RedmineConstant.Status.??????.toInteger());
                redmineIssue.setIssueId(issue.getId());
                redmineIssue.setApplyId(applyId);
                comm.operateService.saveOrUpdate(redmineIssue);
                // ????????????User
                User assigneeUser = comm.accountService.findUserByRedmineUserId(assignee);
                // ????????????????????????
                comm.templateMailService.sendApplyOperateNotificationMail(apply, assigneeUser);
            } else {
                return false;
            }
        } else {
            // ???????????????
            logger.info("--->??????Apply ????????????...");
            // ??????????????????????????????????????????????????????.????????????????????????????????????1??????,???????????????2.
            Integer auditOrder = auditFlow.getAuditOrder() + 1;
            AuditFlow nextAuditFlow = this.findAuditFlowByAuditOrderAndFlowType(auditOrder, flowType);
            apply.setAuditFlow(nextAuditFlow);
            apply.setStatus(ApplyConstant.Status.?????????.toInteger());
            // ?????????????????????????????????
            comm.templateMailService.sendApplyNotificationMail(apply, nextAuditFlow);
            // ???????????????????????????????????????audit.
            this.saveSubAudit(userId, apply);
        }
    }
    comm.applyService.saveOrUpateApply(apply);
    this.saveOrUpdateAudit(audit);
    return true;
}


public Audit findAuditByApplyIdAndStatusAndAuditFlow(Integer applyId,Integer status,AuditFlow auditFlow){
    return auditDao.findByApplyIdAndStatusAndAuditFlow(applyId, status, auditFlow);
}


public Audit findAuditByServiceTagIdAndStatusAndAuditFlow(Integer serviceTagId,Integer status,AuditFlow auditFlow){
    return auditDao.findByServiceTagIdAndStatusAndAuditFlow(serviceTagId, status, auditFlow);
}


public Audit getAudit(Integer id){
    return auditDao.findOne(id);
}


public AuditFlow findAuditFlowByUserIdAndFlowType(Integer userId,Integer flowType){
    return auditFlowDao.findByUserIdAndFlowType(userId, flowType);
}


public List<Audit> getAuditListByApplyIdAndStatus(Integer applyId,Integer status){
    return auditDao.findByapplyIdAndStatus(applyId, status);
}


public boolean isAudited(ServiceTag serviceTag,Integer userId){
    return this.isAudited(null, serviceTag.getId(), userId);
}


@Transactional(readOnly = false)
public Audit saveSubAudit(Integer userId,ServiceTag serviceTag){
    return this.saveSubAudit(userId, null, serviceTag);
}


@Transactional(readOnly = false)
public boolean saveAuditToResources(Audit audit,Integer serviceTagId,Integer userId){
    ServiceTag serviceTag = comm.serviceTagService.getServiceTag(serviceTagId);
    List<Resources> resourcesList = comm.resourcesService.getCommitedResourcesListByServiceTagId(serviceTagId);
    // true:?????????????????? user??????????????? ; false:?????????????????????????????? ?????????????????????userId??????ID
    User user = AccountConstant.FROM_PAGE_USER_ID.equals(userId) ? comm.accountService.getCurrentUser() : comm.accountService.getUser(userId);
    Integer flowType = AuditConstant.FlowType.????????????_?????????????????????.toInteger();
    AuditFlow auditFlow = this.findAuditFlowByUserIdAndFlowType(user.getId(), flowType);
    audit.setServiceTag(serviceTag);
    audit.setAuditFlow(auditFlow);
    audit.setCreateTime(new Date());
    audit.setStatus(AuditConstant.AuditStatus.??????.toInteger());
    if (audit.getResult().equals(AuditConstant.Result.??????????????????.toString())) {
        logger.info("--->????????????Resource????????????...");
        serviceTag.setStatus(ResourcesConstant.Status.?????????.toInteger());
        String contentText = "?????????????????? " + serviceTag.getName() + " ????????????<a href=\"" + CONFIG_LOADER.getProperty("RESOURCE_URL") + "\">&#8594??????????????????</a><br>";
        // ????????????????????????
        comm.simpleMailService.sendNotificationMail(serviceTag.getUser().getEmail(), "????????????/??????????????????", contentText);
        for (Resources resources : resourcesList) {
            /* ???????????????????????????????????? */
            resources = comm.resourcesService.restoreResources(resources);
            resources.setStatus(ResourcesConstant.Status.?????????.toInteger());
            comm.resourcesService.saveOrUpdate(resources);
        }
    } else {
        if (auditFlow.getIsFinal()) {
            // ?????????
            logger.info("--->????????????Resource????????????...");
            serviceTag.setStatus(ResourcesConstant.Status.?????????.toInteger());
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
                redmineIssue.setServiceTagId(serviceTagId);
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
                return false;
            }
        } else {
            // ???????????????
            logger.info("--->????????????Resource ????????????...");
            // ??????????????????????????????????????????????????????.????????????????????????????????????1??????,???????????????2.
            Integer auditOrder = auditFlow.getAuditOrder() + 1;
            AuditFlow nextAuditFlow = this.findAuditFlowByAuditOrderAndFlowType(auditOrder, flowType);
            serviceTag.setAuditFlow(nextAuditFlow);
            serviceTag.setStatus(ResourcesConstant.Status.?????????.toInteger());
            // ?????????????????????????????????
            comm.templateMailService.sendResourcesNotificationMail(serviceTag, nextAuditFlow, audit);
            for (Resources resources : resourcesList) {
                // ???????????????????????? 2.?????????.
                resources.setStatus(ResourcesConstant.Status.?????????.toInteger());
                comm.resourcesService.saveOrUpdate(resources);
            }
            // ???????????????????????????????????????audit.
            Audit nextAudit = this.saveSubAudit(userId, serviceTag);
            /* ???????????????????????????,?????????????????????????????????????????????. */
            comm.changeHistoryService.copyHistory(resourcesList, nextAudit);
        }
    }
    comm.serviceTagService.saveOrUpdate(serviceTag);
    this.saveOrUpdateAudit(audit);
    return true;
}


@Transactional(readOnly = false)
public void initAuditStatus(Apply apply){
    List<Audit> audits = auditDao.findByApplyId(apply.getId());
    for (Audit audit : audits) {
        audit.setStatus(AuditConstant.AuditStatus.?????????.toInteger());
        this.saveOrUpdateAudit(audit);
    }
}


public List<Audit> getAuditListByServiceTagId(Integer serviceTagId){
    return auditDao.findByServiceTagId(serviceTagId);
}


public AuditFlow findAuditFlowByAuditOrderAndFlowType(Integer auditOrder,Integer flowType){
    return auditFlowDao.findByAuditOrderAndFlowType(auditOrder, flowType);
}


public List<Audit> getAuditListByServiceTagIdAndStatus(Integer serviceTagId,Integer status){
    return auditDao.findByServiceTagIdAndStatus(serviceTagId, status);
}


}