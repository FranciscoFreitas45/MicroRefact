package com.sobey.cmop.mvc.service.apply;
 import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.sobey.cmop.mvc.comm.BaseSevcie;
import com.sobey.cmop.mvc.constant.ApplyConstant;
import com.sobey.cmop.mvc.constant.AuditConstant;
import com.sobey.cmop.mvc.constant.ResourcesConstant;
import com.sobey.cmop.mvc.dao.ApplyDao;
import com.sobey.cmop.mvc.entity.Apply;
import com.sobey.cmop.mvc.entity.Audit;
import com.sobey.cmop.mvc.entity.AuditFlow;
import com.sobey.framework.utils.DynamicSpecifications;
import com.sobey.framework.utils.Identities;
import com.sobey.framework.utils.SearchFilter;
import com.sobey.framework.utils.SearchFilter.Operator;
@Service
@Transactional(readOnly = true)
public class ApplyService extends BaseSevcie{

 private  Logger logger;

@Resource
 private  ApplyDao applyDao;


public Apply getApply(Integer id){
    return applyDao.findOne(id);
}


@Transactional(readOnly = false)
public Apply saveApplyByServiceType(Apply apply,Integer serviceType){
    Integer status = ApplyConstant.Status.已申请.toInteger();
    String title = comm.applyService.generateTitle(comm.accountService.getCurrentUser().getLoginName(), ApplyConstant.ServiceType.get(serviceType));
    apply.setStatus(status);
    apply.setServiceType(serviceType);
    apply.setCreateTime(new Date());
    apply.setTitle(title);
    apply.setUser(comm.accountService.getCurrentUser());
    return this.saveOrUpateApply(apply);
}


public String generateTitle(String loginName,String serviceType){
    DateTime dateTime = new DateTime();
    return loginName + "-" + serviceType + "-" + dateTime.toString("yyyyMMddHHmmss");
}


@Transactional(readOnly = false)
public String saveAuditByApply(Apply apply){
    String message = "";
    try {
        /* Step.1 获得第一个审批人和审批流程 */
        Integer flowType = AuditConstant.FlowType.资源申请_变更的审批流程.toInteger();
        AuditFlow auditFlow = comm.auditService.findAuditFlowByAuditOrderAndFlowType(AuditConstant.AUDITORDER_FINAL, flowType);
        logger.info("---> 审批人 auditFlow.getUser().getLoginName():" + auditFlow.getUser().getLoginName());
        /* Step.2 根据资源拼装邮件内容并发送到第一个审批人的邮箱. */
        comm.templateMailService.sendApplyNotificationMail(apply, auditFlow);
        /* Step.3 更新Apply状态和Apply的审批流程. */
        apply.setAuditFlow(auditFlow);
        apply.setStatus(ApplyConstant.Status.待审批.toInteger());
        this.saveOrUpateApply(apply);
        message = "服务申请单 " + apply.getTitle() + " 提交审批成功";
        logger.info("--->服务申请邮件发送成功...");
        /* Step.4 初始化所有老审批记录. */
        comm.auditService.initAuditStatus(apply);
        /* Step.5 插入audit. */
        Audit audit = new Audit();
        audit.setApply(apply);
        audit.setAuditFlow(auditFlow);
        audit.setStatus(AuditConstant.AuditStatus.待审批.toInteger());
        comm.auditService.saveOrUpdateAudit(audit);
    } catch (Exception e) {
        message = "服务申请单提交审批失败";
        e.printStackTrace();
    }
    return message;
}


public List<Apply> getBaseStationApplyList(){
    Integer serviceType = ApplyConstant.ServiceType.基础设施.toInteger();
    List<Integer> status = new ArrayList<Integer>();
    status.add(ApplyConstant.Status.已申请.toInteger());
    status.add(ApplyConstant.Status.已退回.toInteger());
    return applyDao.findByUserIdAndServiceTypeAndStatusInOrderByIdDesc(getCurrentUserId(), serviceType, status);
}


@Transactional(readOnly = false)
public void deleteApply(Integer id){
    applyDao.delete(id);
}


public Page<Apply> getApplyPageable(Map<String,Object> searchParams,int pageNumber,int pageSize){
    PageRequest pageRequest = buildPageRequest(pageNumber, pageSize);
    Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
    filters.put("apply.user.id", new SearchFilter("user.id", Operator.EQ, getCurrentUserId()));
    Specification<Apply> spec = DynamicSpecifications.bySearchFilter(filters.values(), Apply.class);
    return applyDao.findAll(spec, pageRequest);
}


public String generateIdentifier(Integer serviceType){
    return ResourcesConstant.ServiceType.get(serviceType) + "-" + Identities.randomBase62(8);
}


@Transactional(readOnly = false)
public Apply saveOrUpateApply(Apply apply){
    return applyDao.save(apply);
}


}