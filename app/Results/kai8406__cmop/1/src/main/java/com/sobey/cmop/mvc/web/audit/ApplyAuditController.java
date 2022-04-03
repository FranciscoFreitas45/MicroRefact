package com.sobey.cmop.mvc.web.audit;
 import java.util.Map;
import javax.servlet.ServletRequest;
import org.apache.commons.lang3.StringUtils;
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
import com.sobey.cmop.mvc.entity.Apply;
import com.sobey.cmop.mvc.entity.Audit;
import com.sobey.cmop.mvc.entity.AuditFlow;
import com.sobey.framework.utils.Servlets;
@Controller
@RequestMapping(value = "/audit")
public class ApplyAuditController extends BaseController{

 private  String REDIRECT_SUCCESS_URL;


@RequestMapping(value = "/apply/{applyId}", method = RequestMethod.POST)
public String saveApply(Integer applyId,Integer userId,String result,String opinion,RedirectAttributes redirectAttributes){
    // 获得指定apply当前审批记录
    Audit audit = this.getCurrentApplyAudit(userId, applyId);
    audit.setOpinion(opinion);
    audit.setResult(result);
    boolean flag = comm.auditService.saveAuditToApply(audit, applyId, userId);
    String message = flag ? "审批操作成功" : "审批操作失败,请稍后重试";
    redirectAttributes.addFlashAttribute("message", message);
    return REDIRECT_SUCCESS_URL;
}


public Audit getCurrentApplyAudit(Integer userId,Integer applyId){
    Integer flowType = AuditConstant.FlowType.资源申请_变更的审批流程.toInteger();
    AuditFlow auditFlow = comm.auditService.findAuditFlowByUserIdAndFlowType(userId, flowType);
    Integer status = AuditConstant.AuditStatus.待审批.toInteger();
    return comm.auditService.findAuditByApplyIdAndStatusAndAuditFlow(applyId, status, auditFlow);
}


@RequestMapping(value = "apply/auditOk")
public String auditOk(Integer applyId,Integer userId,String result,String opinion,Model model){
    String message;
    if (comm.auditService.isAudited(comm.applyService.getApply(applyId), userId)) {
        // 该服务申请已审批过.
        message = "你已审批";
    } else {
        // 获得指定apply当前审批记录
        Audit audit = this.getCurrentApplyAudit(userId, applyId);
        audit.setResult(result);
        audit.setOpinion(opinion);
        boolean flag = comm.auditService.saveAuditToApply(audit, applyId, userId);
        message = flag ? "审批操作成功" : "审批操作失败,请稍后重试";
    }
    model.addAttribute("message", message);
    return "audit/auditOk";
}


@RequestMapping(value = "/apply/{id}", method = RequestMethod.GET)
public String apply(Integer applyId,Integer userId,String result,String view,Model model){
    Apply apply = comm.applyService.getApply(applyId);
    String returnUrl = "";
    if (StringUtils.isEmpty(view) && comm.auditService.isAudited(apply, userId)) {
        // 判断该服务申请已审批过.
        model.addAttribute("message", "你已审批");
        returnUrl = "audit/auditOk";
    } else {
        model.addAttribute("result", result);
        model.addAttribute("view", view);
        // 页面进来userId为0,这个时候取当前UserId. 邮件进来的UserId就不为0.
        model.addAttribute("userId", AccountConstant.FROM_PAGE_USER_ID.equals(userId) ? getCurrentUserId() : userId);
        model.addAttribute("apply", apply);
        // TODO 成本核算
        model.addAttribute("sumCost", comm.costService.costPrice(apply));
        model.addAttribute("audits", comm.auditService.getAuditListByApplyId(applyId));
        returnUrl = "audit/apply/auditApplyForm";
    }
    return returnUrl;
}


@RequestMapping(value = "apply")
public String list(int pageNumber,int pageSize,Model model,ServletRequest request){
    Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, REQUEST_PREFIX);
    model.addAttribute("page", comm.auditService.getAuditApplyPageable(searchParams, pageNumber, pageSize));
    // 将搜索条件编码成字符串,分页的URL
    model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefix(searchParams, REQUEST_PREFIX));
    return "audit/apply/auditApplyList";
}


}