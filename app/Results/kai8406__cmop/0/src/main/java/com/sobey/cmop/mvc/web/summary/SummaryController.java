package com.sobey.cmop.mvc.web.summary;
 import java.util.Map;
import java.util.Map.Entry;
import javax.servlet.ServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.sobey.cmop.mvc.comm.BaseController;
import com.sobey.cmop.mvc.constant.ResourcesConstant;
import com.sobey.cmop.mvc.entity.Resources;
import com.sobey.cmop.mvc.entity.User;
import com.sobey.framework.utils.Servlets;
@Controller
@RequestMapping(value = "/summary")
public class SummaryController extends BaseController{


@RequestMapping(value = "/migrate", method = RequestMethod.POST)
public String migrateForm(RedirectAttributes redirectAttributes,Integer userId,Integer resourceId){
    Resources resources = comm.resourcesService.getResources(resourceId);
    User user = comm.accountService.getUser(userId);
    resources.setServiceTag(comm.serviceTagService.saveServiceTag(resources, user));
    resources.setUser(user);
    comm.resourcesService.saveOrUpdate(resources);
    return "redirect:/summary/migrate/" + resourceId;
}


@RequestMapping(value = { "list", "" })
public String assigned(int pageNumber,int pageSize,Model model,ServletRequest request){
    Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, REQUEST_PREFIX);
    model.addAttribute("page", comm.resourcesService.getSummaryPageable(searchParams, pageNumber, pageSize));
    // 将搜索条件编码成字符串,分页的URL
    model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefix(searchParams, REQUEST_PREFIX));
    /**
     * 返回不同服务类型的资源统计.页面参数为:服务类型名+COUNT. eg: PCSCOUNT,ECSCOUNT.
     *
     * 服务类型注意是从ResourcesConstant.ServiceType中迭代出来的. 所以枚举中修改了名称的话, 页面的参数名和链接后的查询参数也需要修改.
     */
    for (Entry<Integer, String> entry : ResourcesConstant.ServiceType.map.entrySet()) {
        model.addAttribute(entry.getValue() + "COUNT", comm.resourcesService.getResourcesSummaryStatistics(entry.getKey()));
    }
    return "summary/summaryList";
}


@RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
public String detail(Integer id,Model model){
    Resources resources = comm.resourcesService.getResources(id);
    model.addAttribute("resources", resources);
    model.addAttribute("change", comm.changeServcie.findChangeByResourcesId(id));
    String detailUrl = null;
    Integer serviceId = resources.getServiceId();
    // 服务类型
    Integer serviceType = resources.getServiceType();
    if (serviceType.equals(ResourcesConstant.ServiceType.PCS.toInteger()) || serviceType.equals(ResourcesConstant.ServiceType.ECS.toInteger())) {
        model.addAttribute("compute", comm.computeService.getComputeItem(serviceId));
        detailUrl = "resource/detail/computeDetail";
    } else if (serviceType.equals(ResourcesConstant.ServiceType.ES3.toInteger())) {
        model.addAttribute("storage", comm.es3Service.getStorageItem(serviceId));
        detailUrl = "resource/detail/storageDetail";
    } else if (serviceType.equals(ResourcesConstant.ServiceType.ELB.toInteger())) {
        model.addAttribute("elb", comm.elbService.getNetworkElbItem(serviceId));
        detailUrl = "resource/detail/elbDetail";
    } else if (serviceType.equals(ResourcesConstant.ServiceType.EIP.toInteger())) {
        model.addAttribute("eip", comm.eipService.getNetworkEipItem(serviceId));
        detailUrl = "resource/detail/eipDetail";
    } else if (serviceType.equals(ResourcesConstant.ServiceType.DNS.toInteger())) {
        model.addAttribute("dns", comm.dnsService.getNetworkDnsItem(serviceId));
        detailUrl = "resource/detail/dnsDetail";
    } else if (serviceType.equals(ResourcesConstant.ServiceType.MONITOR_COMPUTE.toInteger())) {
        model.addAttribute("monitorCompute", comm.monitorComputeServcie.getMonitorCompute(serviceId));
        detailUrl = "resource/detail/monitorComputeDetail";
    } else if (serviceType.equals(ResourcesConstant.ServiceType.MONITOR_ELB.toInteger())) {
        model.addAttribute("monitorElb", comm.monitorElbServcie.getMonitorElb(serviceId));
        detailUrl = "resource/detail/monitorElbDetail";
    } else if (serviceType.equals(ResourcesConstant.ServiceType.MDN.toInteger())) {
        model.addAttribute("mdn", comm.mdnService.getMdnItem(serviceId));
        detailUrl = "resource/detail/mdnDetail";
    } else if (serviceType.equals(ResourcesConstant.ServiceType.CP.toInteger())) {
        model.addAttribute("cp", comm.cpService.getCpItem(serviceId));
        detailUrl = "resource/detail/cpDetail";
    } else {
        detailUrl = "resource/resourceList";
    }
    return detailUrl;
}


@RequestMapping(value = "/migrate/{id}", method = RequestMethod.GET)
public String migrate(Integer id,Model model){
    Resources resources = comm.resourcesService.getResources(id);
    model.addAttribute("resources", resources);
    model.addAttribute("migrate", "migrate");
    model.addAttribute("change", comm.changeServcie.findChangeByResourcesId(id));
    model.addAttribute("users", comm.accountService.getUsers());
    String detailUrl = null;
    Integer serviceId = resources.getServiceId();
    // 服务类型
    Integer serviceType = resources.getServiceType();
    if (serviceType.equals(ResourcesConstant.ServiceType.PCS.toInteger()) || serviceType.equals(ResourcesConstant.ServiceType.ECS.toInteger())) {
        model.addAttribute("compute", comm.computeService.getComputeItem(serviceId));
        detailUrl = "resource/detail/computeDetail";
    } else if (serviceType.equals(ResourcesConstant.ServiceType.ES3.toInteger())) {
        model.addAttribute("storage", comm.es3Service.getStorageItem(serviceId));
        detailUrl = "resource/detail/storageDetail";
    } else if (serviceType.equals(ResourcesConstant.ServiceType.ELB.toInteger())) {
        model.addAttribute("elb", comm.elbService.getNetworkElbItem(serviceId));
        detailUrl = "resource/detail/elbDetail";
    } else if (serviceType.equals(ResourcesConstant.ServiceType.EIP.toInteger())) {
        model.addAttribute("eip", comm.eipService.getNetworkEipItem(serviceId));
        detailUrl = "resource/detail/eipDetail";
    } else if (serviceType.equals(ResourcesConstant.ServiceType.DNS.toInteger())) {
        model.addAttribute("dns", comm.dnsService.getNetworkDnsItem(serviceId));
        detailUrl = "resource/detail/dnsDetail";
    } else if (serviceType.equals(ResourcesConstant.ServiceType.MONITOR_COMPUTE.toInteger())) {
        model.addAttribute("monitorCompute", comm.monitorComputeServcie.getMonitorCompute(serviceId));
        detailUrl = "resource/detail/monitorComputeDetail";
    } else if (serviceType.equals(ResourcesConstant.ServiceType.MONITOR_ELB.toInteger())) {
        model.addAttribute("monitorElb", comm.monitorElbServcie.getMonitorElb(serviceId));
        detailUrl = "resource/detail/monitorElbDetail";
    } else if (serviceType.equals(ResourcesConstant.ServiceType.MDN.toInteger())) {
        model.addAttribute("mdn", comm.mdnService.getMdnItem(serviceId));
        detailUrl = "resource/detail/mdnDetail";
    } else if (serviceType.equals(ResourcesConstant.ServiceType.CP.toInteger())) {
        model.addAttribute("cp", comm.cpService.getCpItem(serviceId));
        detailUrl = "resource/detail/cpDetail";
    } else {
        detailUrl = "resource/resourceList";
    }
    return detailUrl;
}


}