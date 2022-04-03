package com.sobey.cmop.mvc.web.apply.iaas;
 import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.sobey.cmop.mvc.comm.BaseController;
import com.sobey.cmop.mvc.entity.Apply;
import com.sobey.cmop.mvc.entity.ComputeItem;
import com.sobey.cmop.mvc.entity.MonitorCompute;
import com.sobey.cmop.mvc.entity.MonitorElb;
import com.sobey.cmop.mvc.entity.NetworkElbItem;
import com.sobey.cmop.mvc.DTO.MonitorElb;
@Controller
@RequestMapping(value = "/apply/monitor")
public class MonitorController extends BaseController{

 private  String REDIRECT_SUCCESS_URL;


@RequestMapping(value = "/elb/update/{id}/applyId", method = RequestMethod.POST)
public String updateElb(Integer id,Integer applyId,Integer elbId,RedirectAttributes redirectAttributes){
    MonitorElb monitorElb = comm.monitorElbServcie.getMonitorElb(id);
    comm.monitorElbServcie.updateMonitorElbToApply(monitorElb, elbId);
    redirectAttributes.addFlashAttribute("message", "修改ELB监控 " + monitorElb.getIdentifier() + " 成功");
    return "redirect:/apply/update/" + applyId;
}


@RequestMapping(value = "/compute/update/{id}/applyId", method = RequestMethod.POST)
public String updateCompute(Integer id,Integer applyId,String ipAddress,String cpuWarn,String cpuCritical,String memoryWarn,String memoryCritical,String pingLossWarn,String pingLossCritical,String diskWarn,String diskCritical,String pingDelayWarn,String pingDelayCritical,String maxProcessWarn,String maxProcessCritical,String port,String process,String mountPoint,RedirectAttributes redirectAttributes){
    MonitorCompute monitorCompute = comm.monitorComputeServcie.getMonitorCompute(id);
    comm.monitorComputeServcie.updateMonitorComputeToApply(monitorCompute, ipAddress, cpuWarn, cpuCritical, memoryWarn, memoryCritical, pingLossWarn, pingLossCritical, diskWarn, diskCritical, pingDelayWarn, pingDelayCritical, maxProcessWarn, maxProcessCritical, port, process, mountPoint);
    redirectAttributes.addFlashAttribute("message", "修改实例监控 " + monitorCompute.getIdentifier() + " 成功");
    return "redirect:/apply/update/" + applyId;
}


@RequestMapping(value = "/save", method = RequestMethod.GET)
public String createForm(Model model){
    return "apply/monitor/monitorForm";
}


@ModelAttribute("elbResources")
public List<NetworkElbItem> elbResources(){
    return comm.basicUnitService.getNetworkElbItemListByResources(getCurrentUserId());
}


@RequestMapping(value = "/save/", method = RequestMethod.POST)
public String save(String serviceTag,Integer priority,String serviceStart,String serviceEnd,String description,String[] monitorMails,String[] monitorPhones,String[] elbIds,String[] computeIds,String[] cpuWarns,String[] cpuCriticals,String[] memoryWarns,String[] memoryCriticals,String[] pingLossWarns,String[] pingLossCriticals,String[] diskWarns,String[] diskCriticals,String[] pingDelayWarns,String[] pingDelayCriticals,String[] maxProcessWarns,String[] maxProcessCriticals,String[] ports,String[] processes,String[] mountPoints,RedirectAttributes redirectAttributes){
    Apply apply = new Apply();
    apply.setServiceTag(serviceTag);
    apply.setPriority(priority);
    apply.setServiceStart(serviceStart);
    apply.setServiceEnd(serviceEnd);
    apply.setDescription(description);
    comm.monitorServcie.saveMonitorToApply(apply, monitorMails, monitorPhones, elbIds, computeIds, cpuWarns, cpuCriticals, memoryWarns, memoryCriticals, pingLossWarns, pingLossCriticals, diskWarns, diskCriticals, pingDelayWarns, pingDelayCriticals, maxProcessWarns, maxProcessCriticals, ports, processes, mountPoints);
    redirectAttributes.addFlashAttribute("message", "创建监控成功.");
    return REDIRECT_SUCCESS_URL;
}


@ModelAttribute("computeResources")
public List<ComputeItem> computeResources(){
    return comm.basicUnitService.getComputeItemListByResources(getCurrentUserId());
}


@RequestMapping(value = "/elb/update/{id}/applyId/{applyId}", method = RequestMethod.GET)
public String updateElbForm(Integer id,Integer applyId,Model model){
    model.addAttribute("monitorElb", comm.monitorElbServcie.getMonitorElb(id));
    model.addAttribute("monitorMails", comm.monitorMailService.getMonitorMailByApplyList(applyId));
    model.addAttribute("monitorPhones", comm.monitorPhoneService.getMonitorPhoneByApplyList(applyId));
    return "apply/monitor/monitorElbUpateForm";
}


@RequestMapping(value = "/elb/delete/{id}/applyId/{applyId}")
public String deleteElb(Integer id,Integer applyId,RedirectAttributes redirectAttributes){
    comm.monitorElbServcie.deleteMonitorElb(id);
    redirectAttributes.addFlashAttribute("message", "删除ELB监控成功");
    return "redirect:/apply/update/" + applyId;
}


@RequestMapping(value = "/compute/delete/{id}/applyId/{applyId}")
public String deleteCompute(Integer id,Integer applyId,RedirectAttributes redirectAttributes){
    comm.monitorComputeServcie.deleteMonitorCompute(id);
    redirectAttributes.addFlashAttribute("message", "删除实例监控成功");
    return "redirect:/apply/update/" + applyId;
}


@RequestMapping(value = "/compute/update/{id}/applyId/{applyId}", method = RequestMethod.GET)
public String updateComputeForm(Integer id,Integer applyId,Model model){
    MonitorCompute monitorCompute = comm.monitorComputeServcie.getMonitorCompute(id);
    model.addAttribute("monitorCompute", monitorCompute);
    model.addAttribute("ports", comm.monitorComputeServcie.wrapMonitorComputeParametToList(monitorCompute.getPort()));
    model.addAttribute("processes", comm.monitorComputeServcie.wrapMonitorComputeParametToList(monitorCompute.getProcess()));
    model.addAttribute("mountPoints", comm.monitorComputeServcie.wrapMonitorComputeParametToList(monitorCompute.getMountPoint()));
    model.addAttribute("monitorMails", comm.monitorMailService.getMonitorMailByApplyList(applyId));
    model.addAttribute("monitorPhones", comm.monitorPhoneService.getMonitorPhoneByApplyList(applyId));
    return "apply/monitor/monitorComputeUpateForm";
}


}