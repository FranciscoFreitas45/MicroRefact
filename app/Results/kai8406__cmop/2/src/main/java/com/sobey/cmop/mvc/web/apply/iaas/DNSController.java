package com.sobey.cmop.mvc.web.apply.iaas;
 import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.sobey.cmop.mvc.comm.BaseController;
import com.sobey.cmop.mvc.entity.NetworkDnsItem;
@Controller
@RequestMapping(value = "/apply/dns")
public class DNSController extends BaseController{

 private  String REDIRECT_SUCCESS_URL;


@RequestMapping(value = "/save", method = RequestMethod.GET)
public String createForm(Model model){
    return "apply/dns/dnsForm";
}


@RequestMapping(value = "/save", method = RequestMethod.POST)
public String save(Integer applyId,String[] domainNames,String[] domainTypes,String[] eipIds,RedirectAttributes redirectAttributes){
    comm.dnsService.saveDNSToApply(applyId, domainNames, domainTypes, eipIds);
    redirectAttributes.addFlashAttribute("message", "创建DNS域名映射成功.");
    return REDIRECT_SUCCESS_URL;
}


@RequestMapping(value = "/update/{id}/applyId", method = RequestMethod.POST)
public String update(Integer id,Integer applyId,String domainName,Integer domainType,String cnameDomain,String[] eipIds,RedirectAttributes redirectAttributes){
    NetworkDnsItem networkDnsItem = comm.dnsService.getNetworkDnsItem(id);
    comm.dnsService.updateDNSToApply(networkDnsItem, domainName, domainType, cnameDomain, eipIds);
    redirectAttributes.addFlashAttribute("message", "修改DNS " + networkDnsItem.getIdentifier() + " 成功");
    return "redirect:/apply/update/" + applyId;
}


@RequestMapping(value = "/update/{id}/applyId/{applyId}", method = RequestMethod.GET)
public String updateForm(Integer id,Integer applyId,Model model){
    model.addAttribute("dns", comm.dnsService.getNetworkDnsItem(id));
    return "apply/dns/dnsUpateForm";
}


@RequestMapping(value = "/delete/{id}/applyId/{applyId}")
public String delete(Integer id,Integer applyId,RedirectAttributes redirectAttributes){
    comm.dnsService.deleteNetworkDnsItem(id);
    redirectAttributes.addFlashAttribute("message", "删除DNS成功");
    return "redirect:/apply/update/" + applyId;
}


}