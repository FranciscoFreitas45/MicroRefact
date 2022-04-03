package com.sobey.cmop.mvc.web.audit;
 import java.util.Map;
import javax.servlet.ServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.sobey.cmop.mvc.comm.BaseController;
import com.sobey.cmop.mvc.constant.AccountConstant;
import com.sobey.cmop.mvc.constant.AuditConstant;
import com.sobey.cmop.mvc.entity.Audit;
import com.sobey.cmop.mvc.entity.AuditFlow;
import com.sobey.cmop.mvc.entity.ServiceTag;
import com.sobey.framework.utils.Servlets;
@Controller
@RequestMapping(value = "/audit")
public class ResourcesAuditController extends BaseController{

 private  String REDIRECT_SUCCESS_URL;


@RequestMapping(value = "/resources/{serviceTagId}", method = RequestMethod.POST)
public String saveApply(Integer serviceTagId,Integer userId,String result,String opinion,RedirectAttributes redirectAttributes){
    // 获得指定apply当前审批记录
    Audit audit = this.getCurrentResourcesAudit(userId, serviceTagId);
    audit.setOpinion(opinion);
    audit.setResult(result);
    boolean flag = comm.auditService.saveAuditToResources(audit, serviceTagId, userId);
    String message = flag ? "审批操作成功" : "审批操作失败,请稍后重试";
    redirectAttributes.addFlashAttribute("message", message);
    return REDIRECT_SUCCESS_URL;
}


@RequestMapping(value = "resources/auditOk")
public String auditOk(Integer serviceTagId,Integer userId,String result,String opinion,Model model){
    String message;
    if (comm.auditService.isAudited(comm.serviceTagService.getServiceTag(serviceTagId), userId)) {
        // 该服务申请已审批过.
        message = "你已审批";
    } else {
        // 获得指定serviceTag当前审批记录
        Audit audit = this.getCurrentResourcesAudit(userId, serviceTagId);
        audit.setResult(result);
        audit.setOpinion(opinion);
        boolean flag = comm.auditService.saveAuditToResources(audit, serviceTagId, userId);
        message = flag ? "审批操作成功" : "审批操作失败,请稍后重试";
    }
    model.addAttribute("message", message);
    return "audit/auditOk";
}


public Audit getCurrentResourcesAudit(Integer userId,Integer serviceTagId){
    Integer flowType = AuditConstant.FlowType.资源申请_变更的审批流程.toInteger();
    AuditFlow auditFlow = comm.auditService.findAuditFlowByUserIdAndFlowType(userId, flowType);
    Integer status = AuditConstant.AuditStatus.待审批.toInteger();
    return comm.auditService.findAuditByServiceTagIdAndStatusAndAuditFlow(serviceTagId, status, auditFlow);
}


@RequestMapping(value = "/resources/{id}", method = RequestMethod.GET)
public String resources(Integer serviceTagId,Integer userId,String result,Integer auditId,Integer view,Model model){
    String returnUrl = "";
    ServiceTag serviceTag = comm.serviceTagService.getServiceTag(serviceTagId);
    if (view == null && comm.auditService.isAudited(serviceTag, userId)) {
        // 判断该服务申请已审批过.
        model.addAttribute("message", "你已审批");
        returnUrl = "audit/auditOk";
    } else {
        model.addAttribute("result", result);
        model.addAttribute("view", view);
        model.addAttribute("userId", AccountConstant.FROM_PAGE_USER_ID.equals(userId) ? getCurrentUserId() : userId);
        model.addAttribute("serviceTag", serviceTag);
        model.addAttribute("resourcesList", comm.resourcesService.getCommitedResourcesListByServiceTagId(serviceTagId));
        model.addAttribute("audits", comm.auditService.getAuditListByServiceTagId(serviceTagId));
        model.addAttribute("changes", comm.changeHistoryService.getChangeHistoryListByAudit(comm.auditService.getAudit(auditId)));
        returnUrl = "audit/resource/auditResourcesForm";
    }
    return returnUrl;
}


@RequestMapping(value = "resources")
public String list(int pageNumber,int pageSize,Model model,ServletRequest request){
    Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, REQUEST_PREFIX);
    model.addAttribute("page", comm.auditService.getAuditResourcesPageable(searchParams, pageNumber, pageSize));
    // 将搜索条件编码成字符串,分页的URL
    model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefix(searchParams, REQUEST_PREFIX));
    return "audit/resource/auditResourcesList";
}


}