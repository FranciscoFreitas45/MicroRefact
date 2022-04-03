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
    Integer flowType = AuditConstant.FlowType.资源申请_变更的审批流程.toInteger();
    AuditFlow auditFlow = this.findAuditFlowByUserIdAndFlowType(user.getId(), flowType);
    audit.setApply(apply);
    audit.setAuditFlow(auditFlow);
    audit.setCreateTime(new Date());
    audit.setStatus(AuditConstant.AuditStatus.有效.toInteger());
    if (audit.getResult().equals(AuditConstant.Result.不同意且退回.toString())) {
        logger.info("--->申请Apply审批退回...");
        apply.setStatus(ApplyConstant.Status.已退回.toInteger());
        String contentText = "你的服务申请 " + apply.getTitle() + " 已退回！<a href=\"" + CONFIG_LOADER.getProperty("APPLY_URL") + "\">&#8594点击进行处理</a><br>";
        // 发送退回通知邮件
        comm.simpleMailService.sendNotificationMail(apply.getUser().getEmail(), "服务申请/变更退回邮件", contentText);
    } else {
        if (auditFlow.getIsFinal()) {
            // 终审人
            logger.info("--->申请Apply终审审批...");
            apply.setStatus(ApplyConstant.Status.已审批.toInteger());
            // 拼装Redmine内容
            String description = comm.redmineUtilService.applyRedmineDesc(apply);
            // 写入工单Issue到Redmine
            Issue issue = new Issue();
            Integer trackerId = RedmineConstant.Tracker.支持.toInteger();
            Tracker tracker = new Tracker(trackerId, RedmineConstant.Tracker.get(trackerId));
            issue.setTracker(tracker);
            issue.setSubject(apply.getTitle());
            issue.setPriorityId(apply.getPriority());
            issue.setDescription(description);
            Integer projectId = RedmineConstant.Project.SobeyCloud运营.toInteger();
            // 初始化第一接收人
            RedmineManager mgr = RedmineService.FIRST_REDMINE_ASSIGNEE_REDMINEMANAGER;
            if (apply.getTitle().indexOf(ApplyConstant.ServiceType.get(ApplyConstant.ServiceType.MDN.toInteger())) > 0) {
                mgr = RedmineService.MDN_REDMINE_ASSIGNEE_REDMINEMANAGER;
            } else if (apply.getTitle().indexOf(ApplyConstant.ServiceType.get(ApplyConstant.ServiceType.云生产.toInteger())) > 0) {
                mgr = RedmineService.CP_REDMINE_ASSIGNEE_REDMINEMANAGER;
            } else if (apply.getTitle().indexOf(ApplyConstant.ServiceType.get(ApplyConstant.ServiceType.监控.toInteger())) > 0) {
                mgr = RedmineService.MONITOR_REDMINE_ASSIGNEE_REDMINEMANAGER;
            }
            boolean isCreated = RedmineService.createIssue(issue, projectId.toString(), mgr);
            logger.info("--->申请Apply Redmine isCreated?" + isCreated);
            if (isCreated) {
                // 写入Redmine成功
                Integer assignee = RedmineService.FIRST_REDMINE_ASSIGNEE;
                if (apply.getTitle().indexOf(ApplyConstant.ServiceType.get(ApplyConstant.ServiceType.MDN.toInteger())) > 0) {
                    assignee = RedmineService.MDN_REDMINE_ASSIGNEE;
                } else if (apply.getTitle().indexOf(ApplyConstant.ServiceType.get(ApplyConstant.ServiceType.云生产.toInteger())) > 0) {
                    assignee = RedmineService.CP_REDMINE_ASSIGNEE;
                } else if (apply.getTitle().indexOf(ApplyConstant.ServiceType.get(ApplyConstant.ServiceType.监控.toInteger())) > 0) {
                    assignee = RedmineService.MONITOR_REDMINE_ASSIGNEE;
                }
                issue = RedmineService.getIssueBySubject(issue.getSubject(), mgr);
                RedmineIssue redmineIssue = new RedmineIssue();
                redmineIssue.setProjectId(projectId);
                redmineIssue.setTrackerId(issue.getTracker().getId());
                redmineIssue.setSubject(issue.getSubject());
                redmineIssue.setAssignee(assignee);
                redmineIssue.setStatus(RedmineConstant.Status.新建.toInteger());
                redmineIssue.setIssueId(issue.getId());
                redmineIssue.setApplyId(applyId);
                comm.operateService.saveOrUpdate(redmineIssue);
                // 指派人的User
                User assigneeUser = comm.accountService.findUserByRedmineUserId(assignee);
                // 发送工单处理邮件
                comm.templateMailService.sendApplyOperateNotificationMail(apply, assigneeUser);
            } else {
                return false;
            }
        } else {
            // 不是终审人
            logger.info("--->申请Apply 中间审批...");
            // 当前审批人的的下一级审批人的审批顺序.如当前审批人的审批顺序是1的话,下一个就是2.
            Integer auditOrder = auditFlow.getAuditOrder() + 1;
            AuditFlow nextAuditFlow = this.findAuditFlowByAuditOrderAndFlowType(auditOrder, flowType);
            apply.setAuditFlow(nextAuditFlow);
            apply.setStatus(ApplyConstant.Status.审批中.toInteger());
            // 发送邮件到下一个审批人
            comm.templateMailService.sendApplyNotificationMail(apply, nextAuditFlow);
            // 插入一条下级审批人所用到的audit.
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
    // true:通过页面审批 user为当前用户 ; false:通过邮件直接审批同意 根据传递过来的userId获得ID
    User user = AccountConstant.FROM_PAGE_USER_ID.equals(userId) ? comm.accountService.getCurrentUser() : comm.accountService.getUser(userId);
    Integer flowType = AuditConstant.FlowType.资源申请_变更的审批流程.toInteger();
    AuditFlow auditFlow = this.findAuditFlowByUserIdAndFlowType(user.getId(), flowType);
    audit.setServiceTag(serviceTag);
    audit.setAuditFlow(auditFlow);
    audit.setCreateTime(new Date());
    audit.setStatus(AuditConstant.AuditStatus.有效.toInteger());
    if (audit.getResult().equals(AuditConstant.Result.不同意且退回.toString())) {
        logger.info("--->资源变更Resource审批退回...");
        serviceTag.setStatus(ResourcesConstant.Status.已退回.toInteger());
        String contentText = "你的资源变更 " + serviceTag.getName() + " 已退回！<a href=\"" + CONFIG_LOADER.getProperty("RESOURCE_URL") + "\">&#8594点击进行处理</a><br>";
        // 发送退回通知邮件
        comm.simpleMailService.sendNotificationMail(serviceTag.getUser().getEmail(), "服务申请/变更退回邮件", contentText);
        for (Resources resources : resourcesList) {
            /* 资源的变更项还原至变更前 */
            resources = comm.resourcesService.restoreResources(resources);
            resources.setStatus(ResourcesConstant.Status.已退回.toInteger());
            comm.resourcesService.saveOrUpdate(resources);
        }
    } else {
        if (auditFlow.getIsFinal()) {
            // 终审人
            logger.info("--->资源变更Resource终审审批...");
            serviceTag.setStatus(ResourcesConstant.Status.已审批.toInteger());
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
                redmineIssue.setServiceTagId(serviceTagId);
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
                return false;
            }
        } else {
            // 不是终审人
            logger.info("--->资源变更Resource 中间审批...");
            // 当前审批人的的下一级审批人的审批顺序.如当前审批人的审批顺序是1的话,下一个就是2.
            Integer auditOrder = auditFlow.getAuditOrder() + 1;
            AuditFlow nextAuditFlow = this.findAuditFlowByAuditOrderAndFlowType(auditOrder, flowType);
            serviceTag.setAuditFlow(nextAuditFlow);
            serviceTag.setStatus(ResourcesConstant.Status.审批中.toInteger());
            // 发送邮件到下一个审批人
            comm.templateMailService.sendResourcesNotificationMail(serviceTag, nextAuditFlow, audit);
            for (Resources resources : resourcesList) {
                // 更改资源的状态为 2.审批中.
                resources.setStatus(ResourcesConstant.Status.审批中.toInteger());
                comm.resourcesService.saveOrUpdate(resources);
            }
            // 插入一条下级审批人所用到的audit.
            Audit nextAudit = this.saveSubAudit(userId, serviceTag);
            /* 将变更详情拷贝一份,用于审批记录页面查看和历史记录. */
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
        audit.setStatus(AuditConstant.AuditStatus.已过期.toInteger());
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