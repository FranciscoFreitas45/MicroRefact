package com.sobey.cmop.mvc.web.apply.iaas;
 import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.sobey.cmop.mvc.comm.BaseController;
import com.sobey.cmop.mvc.entity.ComputeItem;
@Controller
@RequestMapping(value = "/apply/compute")
public class ComputeController extends BaseController{

 private  String REDIRECT_SUCCESS_URL;


@RequestMapping(value = "/save/{computeType}", method = RequestMethod.GET)
public String createForm(Integer computeType,Model model){
    model.addAttribute("computeType", computeType);
    return "apply/compute/computeForm";
}


@RequestMapping(value = "/save/{computeType}", method = RequestMethod.POST)
public String save(Integer computeType,Integer applyId,String[] osTypes,String[] osBits,String[] serverTypes,String[] remarks,String[] esgIds,RedirectAttributes redirectAttributes){
    comm.computeService.saveComputeToApply(computeType, applyId, osTypes, osBits, serverTypes, remarks, esgIds);
    redirectAttributes.addFlashAttribute("message", "创建实例成功.");
    return REDIRECT_SUCCESS_URL;
}


@RequestMapping(value = "/update/{id}/applyId", method = RequestMethod.POST)
public String update(Integer id,Integer applyId,Integer osType,Integer osBit,Integer serverType,String[] esgIds,String remark,RedirectAttributes redirectAttributes){
    ComputeItem computeItem = comm.computeService.updateComputeToApply(id, osType, osBit, serverType, esgIds, remark);
    redirectAttributes.addFlashAttribute("message", "修改实例 " + computeItem.getIdentifier() + " 成功");
    return "redirect:/apply/update/" + applyId;
}


@RequestMapping(value = "/update/{id}/applyId/{applyId}", method = RequestMethod.GET)
public String updateForm(Integer id,Integer applyId,Model model){
    model.addAttribute("compute", comm.computeService.getComputeItem(id));
    return "apply/compute/computeUpateForm";
}


@RequestMapping(value = "/delete/{id}/applyId/{applyId}")
public String delete(Integer id,Integer applyId,RedirectAttributes redirectAttributes){
    comm.computeService.deleteCompute(id);
    redirectAttributes.addFlashAttribute("message", "删除实例成功");
    return "redirect:/apply/update/" + applyId;
}


}