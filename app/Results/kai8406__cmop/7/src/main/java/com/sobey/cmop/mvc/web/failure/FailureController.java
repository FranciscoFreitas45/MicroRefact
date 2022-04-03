package com.sobey.cmop.mvc.web.failure;
 import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
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
import com.sobey.cmop.mvc.entity.ComputeItem;
import com.sobey.cmop.mvc.entity.CpItem;
import com.sobey.cmop.mvc.entity.Failure;
import com.sobey.cmop.mvc.entity.MdnItem;
import com.sobey.cmop.mvc.entity.MonitorCompute;
import com.sobey.cmop.mvc.entity.MonitorElb;
import com.sobey.cmop.mvc.entity.NetworkDnsItem;
import com.sobey.cmop.mvc.entity.NetworkEipItem;
import com.sobey.cmop.mvc.entity.NetworkElbItem;
import com.sobey.cmop.mvc.entity.Resources;
import com.sobey.cmop.mvc.entity.StorageItem;
import com.sobey.cmop.mvc.entity.User;
import com.sobey.cmop.mvc.service.redmine.RedmineService;
import com.sobey.framework.utils.Servlets;
import com.taskadapter.redmineapi.bean.Issue;
import com.sobey.cmop.mvc.DTO.User;
@Controller
@RequestMapping(value = "/failure")
public class FailureController extends BaseController{

 private  String REDIRECT_SUCCESS_URL;


@RequestMapping(value = "/upload/file", method = RequestMethod.POST)
@ResponseBody
public List<UploadedFile> upload(HttpServletRequest request,MultipartFile file){
    return comm.failureService.saveUploadByAjax(request, file);
}


@RequestMapping(value = "/save", method = RequestMethod.GET)
public String createForm(Model model){
    return "failure/failureForm";
}


@RequestMapping(value = "/save", method = RequestMethod.POST)
public String save(String resourcesId,String fileNames,String fileDescs,Failure failure,RedirectAttributes redirectAttributes){
    User user = comm.accountService.getCurrentUser();
    failure.setRelatedId(resourcesId);
    failure.setCreateTime(new Date());
    failure.setUser(user);
    failure.setTitle(comm.applyService.generateTitle(user.getLoginName(), "bug"));
    boolean result = comm.failureService.saveFailure(failure, fileNames, fileDescs);
    redirectAttributes.addFlashAttribute("message", result ? "故障申报成功" : "故障申报失败,请稍后重试");
    return REDIRECT_SUCCESS_URL;
}


@RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
public String detail(Integer id,Model model){
    Failure failure = comm.failureService.getFailure(id);
    Integer issueId = failure.getRedmineIssue().getIssueId();
    Issue issue = RedmineService.getIssue(issueId);
    // 查询Redmine中的Issue信息失败
    if (issue == null) {
        model.addAttribute("message", "查询工单信息失败，请稍后重试！");
    }
    List<Resources> resourcesList = new ArrayList<Resources>();
    List<ComputeItem> computeItems = new ArrayList<ComputeItem>();
    List<StorageItem> storageItems = new ArrayList<StorageItem>();
    List<NetworkElbItem> elbItems = new ArrayList<NetworkElbItem>();
    List<NetworkEipItem> eipItems = new ArrayList<NetworkEipItem>();
    List<NetworkDnsItem> dnsItems = new ArrayList<NetworkDnsItem>();
    List<MonitorCompute> monitorComputes = new ArrayList<MonitorCompute>();
    List<MonitorElb> monitorElbs = new ArrayList<MonitorElb>();
    List<MdnItem> mdnItems = new ArrayList<MdnItem>();
    List<CpItem> cpItems = new ArrayList<CpItem>();
    if (StringUtils.isNotBlank(failure.getRelatedId())) {
        String[] resourcesIds = failure.getRelatedId().split(",");
        for (String resourcesId : resourcesIds) {
            Resources resources = comm.resourcesService.getResources(Integer.valueOf(resourcesId));
            resourcesList.add(resources);
        }
    }
    /* 封装各个资源对象 */
    comm.resourcesService.wrapBasicUntilListByResources(resourcesList, computeItems, storageItems, elbItems, eipItems, dnsItems, monitorComputes, monitorElbs, mdnItems, cpItems);
    model.addAttribute("issue", issue);
    model.addAttribute("failure", failure);
    model.addAttribute("computeItems", computeItems);
    model.addAttribute("storageItems", storageItems);
    model.addAttribute("elbItems", elbItems);
    model.addAttribute("eipItems", eipItems);
    model.addAttribute("dnsItems", dnsItems);
    model.addAttribute("monitorComputes", monitorComputes);
    model.addAttribute("monitorElbs", monitorElbs);
    model.addAttribute("mdnItems", mdnItems);
    model.addAttribute("cpItems", cpItems);
    return "failure/failureDetail";
}


@RequestMapping(value = { "list", "" })
public String list(int pageNumber,int pageSize,Model model,ServletRequest request){
    Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, REQUEST_PREFIX);
    model.addAttribute("page", comm.failureService.getFailurePageable(searchParams, pageNumber, pageSize));
    // 将搜索条件编码成字符串,分页的URL
    model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefix(searchParams, REQUEST_PREFIX));
    return "failure/failureList";
}


@RequestMapping(value = "/upload/delete")
@ResponseBody
public StatusResponse delete(HttpServletRequest request,String fileName){
    boolean result = comm.failureService.deleteUploadByAjax(request, fileName);
    return new StatusResponse(result, result == true ? "删除成功" : "删除失败");
}


}