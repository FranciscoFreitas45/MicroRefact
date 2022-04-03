package com.sobey.cmop.mvc.web.apply.iaas;
 import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.sobey.cmop.mvc.comm.BaseController;
import com.sobey.cmop.mvc.constant.NetworkConstant;
import com.sobey.cmop.mvc.entity.NetworkElbItem;
@Controller
@RequestMapping(value = "/apply/elb")
public class ELBController extends BaseController{

 private  String REDIRECT_SUCCESS_URL;


@RequestMapping(value = "/save", method = RequestMethod.GET)
public String createForm(Model model){
    return "apply/elb/elbForm";
}


@RequestMapping(value = "/save", method = RequestMethod.POST)
public String save(Integer applyId,String[] keepSessions,String[] protocols,String[] sourcePorts,String[] targetPorts,String[] computeIds,RedirectAttributes redirectAttributes){
    comm.elbService.saveELBToApply(applyId, keepSessions, protocols, sourcePorts, targetPorts, computeIds);
    redirectAttributes.addFlashAttribute("message", "创建负载均衡器ELB成功.");
    return REDIRECT_SUCCESS_URL;
}


@RequestMapping(value = "/update/{id}/applyId", method = RequestMethod.POST)
public String update(Integer id,Integer applyId,String keepSession,String[] protocols,String[] sourcePorts,String[] targetPorts,String[] computeIds,RedirectAttributes redirectAttributes){
    NetworkElbItem networkElbItem = comm.elbService.getNetworkElbItem(id);
    networkElbItem.setKeepSession(NetworkConstant.KeepSession.保持.toString().equals(keepSession) ? true : false);
    comm.elbService.updateELBToApply(networkElbItem, protocols, sourcePorts, targetPorts, computeIds);
    redirectAttributes.addFlashAttribute("message", "修改ELB " + networkElbItem.getIdentifier() + " 成功");
    return "redirect:/apply/update/" + applyId;
}


@RequestMapping(value = "/update/{id}/applyId/{applyId}", method = RequestMethod.GET)
public String updateForm(Integer id,Integer applyId,Model model){
    model.addAttribute("elb", comm.elbService.getNetworkElbItem(id));
    return "apply/elb/elbUpateForm";
}


@RequestMapping(value = "/delete/{id}/applyId/{applyId}")
public String delete(Integer id,Integer applyId,RedirectAttributes redirectAttributes){
    comm.elbService.deleteNetworkElbItem(id);
    redirectAttributes.addFlashAttribute("message", "删除ELB成功");
    return "redirect:/apply/update/" + applyId;
}


}