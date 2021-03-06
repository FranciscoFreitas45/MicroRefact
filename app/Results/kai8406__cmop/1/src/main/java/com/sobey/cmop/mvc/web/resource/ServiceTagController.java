package com.sobey.cmop.mvc.web.resource;
 import java.util.ArrayList;
import java.util.List;
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
import com.sobey.cmop.mvc.constant.AuditConstant;
import com.sobey.cmop.mvc.constant.ResourcesConstant;
import com.sobey.cmop.mvc.entity.ComputeItem;
import com.sobey.cmop.mvc.entity.CpItem;
import com.sobey.cmop.mvc.entity.MdnItem;
import com.sobey.cmop.mvc.entity.MonitorCompute;
import com.sobey.cmop.mvc.entity.MonitorElb;
import com.sobey.cmop.mvc.entity.NetworkDnsItem;
import com.sobey.cmop.mvc.entity.NetworkEipItem;
import com.sobey.cmop.mvc.entity.NetworkElbItem;
import com.sobey.cmop.mvc.entity.Resources;
import com.sobey.cmop.mvc.entity.ServiceTag;
import com.sobey.cmop.mvc.entity.StorageItem;
import com.sobey.framework.utils.Servlets;
@Controller
@RequestMapping(value = "/serviceTag")
public class ServiceTagController extends BaseController{

 private  String REDIRECT_SUCCESS_URL;


@RequestMapping(value = "detail/{id}", method = RequestMethod.GET)
public String detailForm(Integer id,Model model){
    List<Resources> resourcesList = comm.resourcesService.getResourcesListByServiceTagId(id);
    List<ComputeItem> computeItems = new ArrayList<ComputeItem>();
    List<StorageItem> storageItems = new ArrayList<StorageItem>();
    List<NetworkElbItem> elbItems = new ArrayList<NetworkElbItem>();
    List<NetworkEipItem> eipItems = new ArrayList<NetworkEipItem>();
    List<NetworkDnsItem> dnsItems = new ArrayList<NetworkDnsItem>();
    List<MonitorCompute> monitorComputes = new ArrayList<MonitorCompute>();
    List<MonitorElb> monitorElbs = new ArrayList<MonitorElb>();
    List<MdnItem> mdnItems = new ArrayList<MdnItem>();
    List<CpItem> cpItems = new ArrayList<CpItem>();
    /* ???????????????????????? */
    comm.resourcesService.wrapBasicUntilListByResources(resourcesList, computeItems, storageItems, elbItems, eipItems, dnsItems, monitorComputes, monitorElbs, mdnItems, cpItems);
    model.addAttribute("serviceTag", comm.serviceTagService.getServiceTag(id));
    model.addAttribute("resourcesList", resourcesList);
    model.addAttribute("computeItems", computeItems);
    model.addAttribute("storageItems", storageItems);
    model.addAttribute("elbItems", elbItems);
    model.addAttribute("eipItems", eipItems);
    model.addAttribute("dnsItems", dnsItems);
    model.addAttribute("monitorComputes", monitorComputes);
    model.addAttribute("monitorElbs", monitorElbs);
    model.addAttribute("mdnItems", mdnItems);
    model.addAttribute("cpItems", cpItems);
    // ?????????????????????????????????????????????????????????(???????????????,?????????????????????.???audit????????????0)
    model.addAttribute("audits", comm.auditService.getAuditListByServiceTagIdAndStatus(id, AuditConstant.AuditStatus.??????.toInteger()));
    return "resource/serviceTag/serviceTagDetail";
}


@RequestMapping(value = "commitResources/detail/{id}", method = RequestMethod.GET)
public String commitDetailForm(Integer id,Model model){
    model.addAttribute("serviceTag", comm.serviceTagService.getServiceTag(id));
    model.addAttribute("resourcesList", comm.resourcesService.getCommitingResourcesListByServiceTagId(id));
    return "resource/serviceTag/serviceTagCommitDetail";
}


@RequestMapping(value = "/save", method = RequestMethod.GET)
public String createForm(Model model){
    return "resource/serviceTag/serviceTagForm";
}


@RequestMapping(value = "/save", method = RequestMethod.POST)
public String save(RedirectAttributes redirectAttributes,ServiceTag serviceTag){
    comm.serviceTagService.saveServiceTag(serviceTag);
    redirectAttributes.addFlashAttribute("message", "?????????????????? " + serviceTag.getName() + " ??????");
    return REDIRECT_SUCCESS_URL;
}


@RequestMapping(value = "commitResources")
public String commitList(int pageNumber,int pageSize,Model model,ServletRequest request){
    Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, REQUEST_PREFIX);
    model.addAttribute("page", comm.serviceTagService.getCommitServiceTagPageable(searchParams, pageNumber, pageSize));
    // ?????????????????????????????????,?????????URL
    model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefix(searchParams, REQUEST_PREFIX));
    return "resource/serviceTag/serviceTagCommitList";
}


@RequestMapping(value = "commitResources/commit/{id}", method = RequestMethod.GET)
public String commit(Integer id,RedirectAttributes redirectAttributes){
    ServiceTag serviceTag = comm.serviceTagService.getServiceTag(id);
    String message = comm.serviceTagService.saveAuditByServiceTag(serviceTag);
    redirectAttributes.addFlashAttribute("message", message);
    return "redirect:/resources/";
}


@RequestMapping(value = "/update", method = RequestMethod.POST)
public String update(Integer id,String name,String serviceStart,String serviceEnd,Integer priority,String description,RedirectAttributes redirectAttributes){
    ServiceTag serviceTag = comm.serviceTagService.getServiceTag(id);
    serviceTag.setName(name);
    serviceTag.setServiceStart(serviceStart);
    serviceTag.setServiceEnd(serviceEnd);
    serviceTag.setPriority(priority);
    serviceTag.setDescription(description);
    comm.serviceTagService.updateServiceTagAndOneCMDB(serviceTag);
    redirectAttributes.addFlashAttribute("message", "?????????????????? " + serviceTag.getName() + " ??????");
    return REDIRECT_SUCCESS_URL;
}


@RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
public String updateForm(Integer id,Model model){
    model.addAttribute("serviceTag", comm.serviceTagService.getServiceTag(id));
    return "resource/serviceTag/serviceTagForm";
}


@RequestMapping(value = { "list", "" })
public String list(int pageNumber,int pageSize,Model model,ServletRequest request){
    Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, REQUEST_PREFIX);
    model.addAttribute("page", comm.serviceTagService.getServiceTagPageable(searchParams, pageNumber, pageSize));
    // ?????????????????????????????????,?????????URL
    model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefix(searchParams, REQUEST_PREFIX));
    return "resource/serviceTag/serviceTagList";
}


@RequestMapping(value = "delete/{id}")
public String delete(Integer id,RedirectAttributes redirectAttributes){
    List<Resources> resourcesList = comm.resourcesService.getResourcesListByServiceTagId(id);
    boolean result = comm.resourcesService.recycleResources(resourcesList, id);
    if (result) {
        ServiceTag serviceTag = comm.serviceTagService.getServiceTag(id);
        serviceTag.setStatus(ResourcesConstant.Status.?????????.toInteger());
        comm.serviceTagService.saveOrUpdate(serviceTag);
        redirectAttributes.addFlashAttribute("message", "???????????????...");
    } else {
        redirectAttributes.addFlashAttribute("message", "??????????????????,???????????????");
    }
    return REDIRECT_SUCCESS_URL;
}


}