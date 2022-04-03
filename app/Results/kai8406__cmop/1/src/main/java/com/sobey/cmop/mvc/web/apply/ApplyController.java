package com.sobey.cmop.mvc.web.apply;
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
import com.sobey.cmop.mvc.constant.ApplyConstant;
import com.sobey.cmop.mvc.constant.AuditConstant;
import com.sobey.cmop.mvc.entity.Apply;
import com.sobey.framework.utils.Servlets;
@Controller
@RequestMapping(value = "/apply")
public class ApplyController extends BaseController{

 private  String REDIRECT_SUCCESS_URL;


@RequestMapping(value = "/save", method = RequestMethod.GET)
public String createForm(Model model){
    return "apply/applyForm";
}


@RequestMapping(value = "/audit/{id}", method = RequestMethod.GET)
public String audit(Integer id,RedirectAttributes redirectAttributes){
    Apply apply = comm.applyService.getApply(id);
    String message = comm.applyService.saveAuditByApply(apply);
    redirectAttributes.addFlashAttribute("message", message);
    return REDIRECT_SUCCESS_URL;
}


@RequestMapping(value = "/save", method = RequestMethod.POST)
public String save(RedirectAttributes redirectAttributes,Apply apply){
    comm.applyService.saveApplyByServiceType(apply, ApplyConstant.ServiceType.基础设施.toInteger());
    redirectAttributes.addFlashAttribute("message", "创建服务申请 " + apply.getTitle() + " 成功");
    return REDIRECT_SUCCESS_URL + "?applyId=" + apply.getId();
}


@RequestMapping(value = "/update", method = RequestMethod.POST)
public String update(Integer id,String serviceTag,String serviceStart,String serviceEnd,Integer priority,String description,RedirectAttributes redirectAttributes){
    Apply apply = comm.applyService.getApply(id);
    apply.setServiceTag(serviceTag);
    apply.setServiceStart(serviceStart);
    apply.setServiceEnd(serviceEnd);
    apply.setPriority(priority);
    apply.setDescription(description);
    comm.applyService.saveOrUpateApply(apply);
    redirectAttributes.addFlashAttribute("message", "修改服务申请 " + apply.getTitle() + " 成功");
    return REDIRECT_SUCCESS_URL;
}


@RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
public String updateForm(Integer id,Model model){
    model.addAttribute("apply", comm.applyService.getApply(id));
    return "apply/applyForm";
}


@RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
public String detail(Integer id,Model model){
    Apply apply = comm.applyService.getApply(id);
    model.addAttribute("apply", apply);
    // 根据审批状态获得服务申请的审批记录(只取最新的,当前的审批记录.即audit的状态为1)
    model.addAttribute("audits", comm.auditService.getAuditListByApplyIdAndStatus(id, AuditConstant.AuditStatus.有效.toInteger()));
    model.addAttribute("sumCost", comm.costService.costPrice(apply));
    return "apply/applyDetail";
}


@RequestMapping(value = { "list", "" })
public String list(int pageNumber,int pageSize,Model model,ServletRequest request){
    Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, REQUEST_PREFIX);
    model.addAttribute("page", comm.applyService.getApplyPageable(searchParams, pageNumber, pageSize));
    // 将搜索条件编码成字符串,分页的URL
    model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefix(searchParams, REQUEST_PREFIX));
    return "apply/applyList";
}


@RequestMapping(value = "delete/{id}")
public String delete(Integer id,RedirectAttributes redirectAttributes){
    comm.applyService.deleteApply(id);
    redirectAttributes.addFlashAttribute("message", "删除申请单成功");
    return REDIRECT_SUCCESS_URL;
}


}