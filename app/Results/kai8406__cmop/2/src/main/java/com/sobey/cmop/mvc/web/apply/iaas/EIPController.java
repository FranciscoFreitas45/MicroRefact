package com.sobey.cmop.mvc.web.apply.iaas;
 import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.sobey.cmop.mvc.comm.BaseController;
import com.sobey.cmop.mvc.entity.NetworkEipItem;
@Controller
@RequestMapping(value = "/apply/eip")
public class EIPController extends BaseController{

 private  String REDIRECT_SUCCESS_URL;


@RequestMapping(value = "/save", method = RequestMethod.GET)
public String createForm(Model model){
    return "apply/eip/eipForm";
}


@RequestMapping(value = "/save", method = RequestMethod.POST)
public String save(Integer applyId,String[] ispTypes,String[] linkTypes,String[] linkIds,String[] protocols,String[] sourcePorts,String[] targetPorts,RedirectAttributes redirectAttributes){
    comm.eipService.saveEIPToApply(applyId, ispTypes, linkTypes, linkIds, protocols, sourcePorts, targetPorts);
    redirectAttributes.addFlashAttribute("message", "创建EIP成功.");
    return REDIRECT_SUCCESS_URL;
}


@RequestMapping(value = "/update/{id}/applyId", method = RequestMethod.POST)
public String update(Integer id,Integer applyId,String linkType,String linkId,String[] protocols,String[] sourcePorts,String[] targetPorts,RedirectAttributes redirectAttributes){
    NetworkEipItem networkEipItem = comm.eipService.getNetworkEipItem(id);
    comm.eipService.updateEIPToApply(networkEipItem, linkType, linkId, protocols, sourcePorts, targetPorts);
    redirectAttributes.addFlashAttribute("message", "修改EIP " + networkEipItem.getIdentifier() + " 成功");
    return "redirect:/apply/update/" + applyId;
}


@RequestMapping(value = "/update/{id}/applyId/{applyId}", method = RequestMethod.GET)
public String updateForm(Integer id,Integer applyId,Model model){
    model.addAttribute("eip", comm.eipService.getNetworkEipItem(id));
    return "apply/eip/eipUpateForm";
}


@RequestMapping(value = "/delete/{id}/applyId/{applyId}")
public String delete(Integer id,Integer applyId,RedirectAttributes redirectAttributes){
    comm.eipService.deleteNetworkEipItem(id);
    redirectAttributes.addFlashAttribute("message", "删除EIP成功");
    return "redirect:/apply/update/" + applyId;
}


}