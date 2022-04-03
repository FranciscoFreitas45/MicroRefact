package com.sobey.cmop.mvc.web.apply.iaas;
 import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.sobey.cmop.mvc.comm.BaseController;
@Controller
@RequestMapping(value = "/apply/monitorPhone")
public class MonitorPhoneController extends BaseController{


@RequestMapping(value = "/update/applyId", method = RequestMethod.POST)
public String update(Integer applyId,String[] monitorPhones,RedirectAttributes redirectAttributes){
    comm.monitorPhoneService.updateMonitorPhoneToApply(applyId, monitorPhones);
    redirectAttributes.addFlashAttribute("message", "修改手机监控列表成功");
    return "redirect:/apply/update/" + applyId;
}


@RequestMapping(value = "/update/applyId/{applyId}", method = RequestMethod.GET)
public String updateForm(Integer applyId,Model model){
    model.addAttribute("apply", comm.applyService.getApply(applyId));
    return "apply/monitor/monitorPhoneUpateForm";
}


}