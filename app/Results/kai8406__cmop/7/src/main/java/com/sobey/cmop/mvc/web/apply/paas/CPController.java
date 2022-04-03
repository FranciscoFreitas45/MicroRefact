package com.sobey.cmop.mvc.web.apply.paas;
 import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.sobey.cmop.mvc.comm.BaseController;
import com.sobey.cmop.mvc.entity.Apply;
import com.sobey.cmop.mvc.entity.CpItem;
import com.sobey.cmop.mvc.web.failure.StatusResponse;
import com.sobey.cmop.mvc.web.failure.UploadedFile;
import com.sobey.cmop.mvc.DTO.Apply;
import com.sobey.cmop.mvc.DTO.CpItem;
@Controller
@RequestMapping(value = "/apply/cp")
public class CPController extends BaseController{

 private  String REDIRECT_SUCCESS_URL;


@RequestMapping(value = "/upload/file", method = RequestMethod.POST)
@ResponseBody
public List<UploadedFile> upload(HttpServletRequest request,MultipartFile file){
    return comm.failureService.saveUploadByAjax(request, file);
}


@RequestMapping(value = "/save", method = RequestMethod.GET)
public String createForm(Model model){
    return "apply/cp/cpForm";
}


@RequestMapping(value = "/save", method = RequestMethod.POST)
public String save(String serviceTag,Integer priority,String serviceStart,String serviceEnd,String description,String recordStreamUrl,String recordBitrate,String exportEncode,Integer recordType,String recordTime,Integer recordDuration,String publishUrl,String isPushCtp,String videoFtpIp,String videoFtpPort,String videoFtpUsername,String videoFtpPassword,String videoFtpRootpath,String videoFtpUploadpath,String videoOutputGroup,String videoOutputWay,String pictrueFtpIp,String pictrueFtpPort,String pictrueFtpUsername,String pictrueFtpPassword,String pictrueFtpRootpath,String pictrueFtpUploadpath,String pictrueOutputGroup,String pictrueOutputMedia,String[] fileNames,String[] fileSizes,RedirectAttributes redirectAttributes){
    Apply apply = new Apply();
    apply.setServiceTag(serviceTag);
    apply.setPriority(priority);
    apply.setServiceStart(serviceStart);
    apply.setServiceEnd(serviceEnd);
    apply.setDescription(description);
    comm.cpService.saveCPToApply(apply, recordStreamUrl, recordBitrate, exportEncode, recordType, recordTime, recordDuration, publishUrl, isPushCtp, videoFtpIp, videoFtpPort, videoFtpUsername, videoFtpPassword, videoFtpRootpath, videoFtpUploadpath, videoOutputGroup, videoOutputWay, pictrueFtpIp, pictrueFtpPort, pictrueFtpUsername, pictrueFtpPassword, pictrueFtpRootpath, pictrueFtpUploadpath, pictrueOutputGroup, pictrueOutputMedia, fileNames, fileSizes);
    redirectAttributes.addFlashAttribute("message", "创建云生产成功.");
    return REDIRECT_SUCCESS_URL;
}


@RequestMapping(value = "/update/{id}/applyId", method = RequestMethod.POST)
public String update(Integer id,Integer applyId,String recordStreamUrl,String recordBitrate,String exportEncode,Integer recordType,String recordTime,Integer recordDuration,String publishUrl,String isPushCtp,String videoFtpIp,String videoFtpPort,String videoFtpUsername,String videoFtpPassword,String videoFtpRootpath,String videoFtpUploadpath,String videoOutputGroup,String videoOutputWay,String pictrueFtpIp,String pictrueFtpPort,String pictrueFtpUsername,String pictrueFtpPassword,String pictrueFtpRootpath,String pictrueFtpUploadpath,String pictrueOutputGroup,String pictrueOutputMedia,RedirectAttributes redirectAttributes){
    CpItem cpItem = comm.cpService.getCpItem(id);
    comm.cpService.updateCPToApply(cpItem, recordStreamUrl, recordBitrate, exportEncode, recordType, recordTime, recordDuration, publishUrl, isPushCtp, videoFtpIp, videoFtpPort, videoFtpUsername, videoFtpPassword, videoFtpRootpath, videoFtpUploadpath, videoOutputGroup, videoOutputWay, pictrueFtpIp, pictrueFtpPort, pictrueFtpUsername, pictrueFtpPassword, pictrueFtpRootpath, pictrueFtpUploadpath, pictrueOutputGroup, pictrueOutputMedia);
    redirectAttributes.addFlashAttribute("message", "修改云生产 " + cpItem.getIdentifier() + " 成功");
    return "redirect:/apply/update/" + applyId;
}


@RequestMapping(value = "/update/{id}/applyId/{applyId}", method = RequestMethod.GET)
public String updateForm(Integer id,Integer applyId,Model model){
    model.addAttribute("cp", comm.cpService.getCpItem(id));
    return "apply/cp/cpUpateForm";
}


@RequestMapping(value = "/upload/delete")
@ResponseBody
public StatusResponse delete(HttpServletRequest request,String fileName){
    boolean result = comm.failureService.deleteUploadByAjax(request, fileName);
    return new StatusResponse(result, result == true ? "删除成功" : "删除失败");
}


}