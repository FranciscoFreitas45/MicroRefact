package com.sobey.cmop.mvc.web.operate;
 import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.sobey.cmop.mvc.comm.BaseController;
import com.sobey.cmop.mvc.constant.IpPoolConstant;
import com.sobey.cmop.mvc.constant.RedmineConstant;
import com.sobey.cmop.mvc.entity.ComputeItem;
import com.sobey.cmop.mvc.entity.IpPool;
import com.sobey.cmop.mvc.entity.NetworkEipItem;
import com.sobey.cmop.mvc.entity.NetworkElbItem;
import com.sobey.cmop.mvc.entity.RedmineIssue;
import com.sobey.cmop.mvc.entity.StorageItem;
import com.sobey.cmop.mvc.service.redmine.RedmineService;
import com.sobey.framework.utils.Servlets;
import com.taskadapter.redmineapi.bean.Issue;
import com.taskadapter.redmineapi.bean.Project;
import com.taskadapter.redmineapi.bean.User;
@Controller
@RequestMapping(value = "/operate")
public class OperateController extends BaseController{

 private  Logger logger;

 private  String REDIRECT_SUCCESS_URL;


public String replaceELBTextFromOldIpToInnerIp(String desc,List<NetworkElbItem> networkElbList){
    int size = networkElbList.size();
    String[] searchList = new String[size];
    String[] replacementList = new String[size];
    for (int i = 0; i < networkElbList.size(); i++) {
        searchList[i] = networkElbList.get(i).getIdentifier() + "(" + networkElbList.get(i).getOldIp() + ")";
        replacementList[i] = networkElbList.get(i).getIdentifier() + "(" + networkElbList.get(i).getVirtualIp() + ")";
    }
    return StringUtils.replaceEach(desc, searchList, replacementList);
}


@RequestMapping(value = "reported")
public String reported(int pageNumber,int pageSize,Model model,ServletRequest request){
    Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, REQUEST_PREFIX);
    model.addAttribute("page", comm.operateService.getReportedIssuePageable(searchParams, pageNumber, pageSize));
    // ?????????????????????????????????,?????????URL
    model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefix(searchParams, REQUEST_PREFIX));
    return "operate/operateList";
}


@RequestMapping(value = "update/{id}")
public String update(Integer id,Model model,RedirectAttributes redirectAttributes){
    logger.info("--->????????????...issueId=" + id);
    Issue issue = RedmineService.getIssue(id);
    model.addAttribute("issue", issue);
    model.addAttribute("user", comm.accountService.getCurrentUser());
    RedmineIssue redmineIssue = comm.operateService.findByIssueId(id);
    model.addAttribute("redmineIssue", redmineIssue);
    if (issue != null) {
        String desc = issue.getDescription();
        desc = desc.replaceAll("\\*???????????????????????????\\*", "");
        desc = desc.replaceAll("\\*???????????????????????????\\*", "");
        if (desc.indexOf("# ????????????") >= 0) {
            desc = desc.replaceAll("# ????????????", "<br># ????????????");
        } else {
            desc = "<br>" + desc;
        }
        // ????????????Redmine???IP
        List list = comm.operateService.getComputeStorageElbEip(redmineIssue);
        List<ComputeItem> computeList = (List) list.get(0);
        List<StorageItem> storageList = (List) list.get(1);
        List<NetworkElbItem> networkElbList = (List) list.get(2);
        List<NetworkEipItem> networkEipList = (List) list.get(3);
        // oldIP???????????????IP.
        logger.info("--->????????????Redmine???IP??????????????????..." + computeList.size());
        desc = this.replaceComputeTextFromOldIpToInnerIp(desc, computeList);
        logger.info("--->????????????Redmine???IP???EIP???..." + networkEipList.size());
        desc = this.replaceEIPTextFromOldIpToInnerIp(desc, networkEipList);
        logger.info("--->????????????Redmine???IP???ELB???..." + networkElbList.size());
        desc = this.replaceELBTextFromOldIpToInnerIp(desc, networkElbList);
        model.addAttribute("description", desc);
        if (!computeList.isEmpty()) {
            model.addAttribute("computeList", computeList);
            int poolType = 0;
            logger.info("--->has compute: " + computeList.size() + ",poolType=" + poolType);
            // ?????????
            model.addAttribute("server", comm.operateService.findHostMapByServerType(2));
            model.addAttribute("hostServer", comm.operateService.findHostMapByServerType(1));
            model.addAttribute("osStorage", comm.oneCmdbUtilService.getOsStorageFromOnecmdb());
        }
        if (!storageList.isEmpty()) {
            model.addAttribute("storageList", storageList);
            model.addAttribute("fimasController", comm.oneCmdbUtilService.getFimasHardWareFromOnecmdb());
            model.addAttribute("netappController", comm.oneCmdbUtilService.getNfsHardWareFromOnecmdb());
        }
        if (!networkEipList.isEmpty()) {
            model.addAttribute("eipList", networkEipList);
            logger.info("--->has eip: " + networkEipList.size());
            List<IpPool> ipPools = comm.ipPoolService.getIpPoolByPoolTypeAndStatus(IpPoolConstant.PoolType.?????????IP???.toInteger(), IpPoolConstant.IpStatus.?????????.toInteger());
            for (NetworkEipItem networkEipItem : networkEipList) {
                IpPool ipPool = comm.ipPoolService.findIpPoolByIpAddress(networkEipItem.getIpAddress());
                ipPools.add(ipPool);
            }
            model.addAttribute("internetIpPool", ipPools);
        }
        if (!networkElbList.isEmpty()) {
            model.addAttribute("elbList", networkElbList);
        // ????????????ELB?????????IP???
        }
        model.addAttribute("location", comm.operateService.getLocationFromOnecmdb());
        model.addAttribute("vlan", comm.operateService.getVlanFromOnecmdb());
        return "operate/operateForm";
    } else {
        // ??????Redmine??????Issue????????????
        redirectAttributes.addFlashAttribute("message", "?????????????????????????????????????????????");
        return "redirect:/operate";
    }
}


@RequestMapping(value = { "list", "" })
public String assigned(int pageNumber,int pageSize,Model model,ServletRequest request){
    Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, REQUEST_PREFIX);
    model.addAttribute("page", comm.operateService.getAssignedIssuePageable(searchParams, pageNumber, pageSize));
    // ?????????????????????????????????,?????????URL
    model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefix(searchParams, REQUEST_PREFIX));
    // ?????????reported
    model.addAttribute("toReported", "toReported");
    return "operate/operateList";
}


public String replaceEIPTextFromOldIpToInnerIp(String desc,List<NetworkEipItem> networkEipList){
    int size = networkEipList.size();
    String[] searchList = new String[size];
    String[] replacementList = new String[size];
    for (int i = 0; i < networkEipList.size(); i++) {
        searchList[i] = networkEipList.get(i).getIdentifier() + "(" + networkEipList.get(i).getOldIp() + ")";
        replacementList[i] = networkEipList.get(i).getIdentifier() + "(" + networkEipList.get(i).getIpAddress() + ")";
    }
    return StringUtils.replaceEach(desc, searchList, replacementList);
}


@RequestMapping(value = "update")
public String updateForm(int issueId,int projectId,String note,int priority,int authorId,int assignTo,String dueDate,float estimatedHours,int doneRatio,String computes,String storages,String hostNames,String serverAlias,String osStorageAlias,String controllerAlias,String volumes,String innerIps,String[] eipIds,String[] eipAddress,String locationAlias,String elbIds,String virtualIps,RedirectAttributes redirectAttributes){
    logger.info("[issueId,projectId,priority,assignTo,dueDate,estimatedHours,doneRatio,note]???" + issueId + "," + projectId + "," + priority + "," + assignTo + "," + dueDate + "," + estimatedHours + "," + doneRatio + "," + note);
    logger.info("[computes,storages,hostNames,serverAlias,osStorageAlias,controllerAlias, volumes]???" + computes + "|" + storages + "|" + hostNames + "|" + serverAlias + "|" + osStorageAlias + "|" + controllerAlias + "|" + volumes);
    logger.info("[innerIps,eipIds,eipAddresss,locationAlias,elbIds,virtualIps]???" + innerIps + "|" + eipIds + "|" + eipAddress + "|" + locationAlias + "|" + elbIds + "|" + virtualIps);
    Issue issue = RedmineService.getIssue(issueId);
    // ?????????User???redmine??????User??????.
    User assignee = new User();
    assignee.setId(assignTo);
    User author = new User();
    author.setId(authorId);
    Project project = new Project();
    project.setId(projectId);
    // ???????????????100???,???????????? statusId ??? 5.??????; ????????????????????? 2.?????????.
    Integer statusId = RedmineConstant.MAX_DONERATIO.equals(doneRatio) ? RedmineConstant.Status.??????.toInteger() : RedmineConstant.Status.?????????.toInteger();
    // ?????????
    issue.setAssignee(assignee);
    // ?????????
    issue.setDoneRatio(doneRatio);
    // ????????????
    issue.setStatusId(statusId);
    // ??????????????????
    issue.setStatusName(RedmineConstant.Status.get(statusId));
    // ????????????
    issue.setDueDate(new Date());
    // ????????????
    issue.setEstimatedHours(new Float(estimatedHours));
    // ??????
    issue.setNotes(note);
    // ?????????
    issue.setPriorityId(priority);
    // ????????????
    issue.setProject(project);
    // issue??????
    issue.setAuthor(author);
    // TODO ??????IP???????????????,???????????????.
    boolean result = comm.operateService.updateOperate(issue, computes, storages, hostNames, serverAlias, osStorageAlias, controllerAlias, volumes, innerIps, eipIds, eipAddress, locationAlias, elbIds, virtualIps);
    String message = result ? "?????????????????????" : "?????????????????????????????????????????????????????????";
    redirectAttributes.addFlashAttribute("message", message);
    return REDIRECT_SUCCESS_URL;
}


public String replaceComputeTextFromOldIpToInnerIp(String desc,List<ComputeItem> computeList){
    int size = computeList.size();
    String[] searchList = new String[size];
    String[] replacementList = new String[size];
    for (int i = 0; i < computeList.size(); i++) {
        searchList[i] = computeList.get(i).getIdentifier() + "(" + computeList.get(i).getRemark() + " - " + computeList.get(i).getOldIp() + ")";
        replacementList[i] = computeList.get(i).getIdentifier() + "(" + computeList.get(i).getRemark() + " - " + computeList.get(i).getInnerIp() + ")";
    }
    return StringUtils.replaceEach(desc, searchList, replacementList);
}


}