package com.sobey.cmop.mvc.web.apply.iaas;
 import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.sobey.cmop.mvc.comm.BaseController;
import com.sobey.cmop.mvc.entity.StorageItem;
@Controller
@RequestMapping(value = "/apply/es3")
public class ES3Controller extends BaseController{

 private  String REDIRECT_SUCCESS_URL;


@RequestMapping(value = "/save", method = RequestMethod.GET)
public String createForm(Model model){
    return "apply/es3/es3Form";
}


@RequestMapping(value = "/save", method = RequestMethod.POST)
public String save(Integer applyId,String[] spaces,String[] storageTypes,String[] computeIds,RedirectAttributes redirectAttributes){
    comm.es3Service.saveES3ToApply(applyId, spaces, storageTypes, computeIds);
    redirectAttributes.addFlashAttribute("message", "创建ES3存储空间成功.");
    return REDIRECT_SUCCESS_URL;
}


@RequestMapping(value = "/update/{id}/applyId", method = RequestMethod.POST)
public String update(Integer id,Integer applyId,Integer space,Integer storageType,String[] computeIds,RedirectAttributes redirectAttributes){
    StorageItem storageItem = comm.es3Service.getStorageItem(id);
    comm.es3Service.updateES3ToApply(storageItem, space, storageType, computeIds);
    redirectAttributes.addFlashAttribute("message", "修改ES3存储空间 " + storageItem.getIdentifier() + " 成功");
    return "redirect:/apply/update/" + applyId;
}


@RequestMapping(value = "/update/{id}/applyId/{applyId}", method = RequestMethod.GET)
public String updateForm(Integer id,Integer applyId,Model model){
    model.addAttribute("storage", comm.es3Service.getStorageItem(id));
    return "apply/es3/es3UpateForm";
}


@RequestMapping(value = "/delete/{id}/applyId/{applyId}")
public String delete(Integer id,Integer applyId,RedirectAttributes redirectAttributes){
    comm.es3Service.deleteStorageItem(id);
    redirectAttributes.addFlashAttribute("message", "删除ES3存储空间成功");
    return "redirect:/apply/update/" + applyId;
}


}