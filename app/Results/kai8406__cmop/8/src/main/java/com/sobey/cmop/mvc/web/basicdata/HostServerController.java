package com.sobey.cmop.mvc.web.basicdata;
 import java.util.List;
import java.util.Map;
import javax.servlet.ServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.sobey.cmop.mvc.comm.BaseController;
import com.sobey.cmop.mvc.entity.ServerModel;
import com.sobey.cmop.mvc.service.onecmdb.OneCmdbService;
import com.sobey.framework.utils.Servlets;
@Controller
@RequestMapping(value = "/basicdata/host")
public class HostServerController extends BaseController{

 private  String REDIRECT_SUCCESS_URL;


@ModelAttribute("rackMap")
public Map<String,String> getRackFromOnecmdb(){
    return OneCmdbService.findCiByText("Rack");
}


@RequestMapping(value = "/save", method = RequestMethod.POST)
public String save(Integer serverModelId,String rack,String site,String switchs,String switchSite,String height,String locationAlias,String ipAddress,Integer serverType,String description,String managementMac,String[] nicSites,String[] nicMacs,String[] nicIpAddress,RedirectAttributes redirectAttributes){
    boolean flag = comm.hostServerService.addHostServer(serverType, serverModelId, rack, site, switchs, switchSite, height, locationAlias, ipAddress, description, managementMac, nicSites, nicMacs, nicIpAddress);
    redirectAttributes.addFlashAttribute("message", flag ? "创建服务器成功！" : "服务器名称已存在,请按照格式 Company Model Rack-Site 正确输入.");
    return REDIRECT_SUCCESS_URL;
}


@RequestMapping(value = "/update", method = RequestMethod.POST)
public String update(Integer id,Integer serverModelId,String rack,String site,String switchs,String switchSite,String height,String locationAlias,String ipAddress,Integer serverType,String description,String managementMac,String[] nicSites,String[] nicMacs,String[] nicIpAddress,RedirectAttributes redirectAttributes){
    boolean flag = comm.hostServerService.updateHostServer(id, serverType, serverModelId, rack, site, switchs, switchSite, height, locationAlias, ipAddress, description, managementMac, nicSites, nicMacs, nicIpAddress);
    redirectAttributes.addFlashAttribute("message", flag ? "修改服务器成功！" : "服务器名称已存在,请按照格式 Company Model Rack-Site 正确输入.");
    return REDIRECT_SUCCESS_URL;
}


@RequestMapping(value = { "syn" })
public String syn(Model model,RedirectAttributes redirectAttributes){
    String rtn = comm.hostServerService.syn();
    String[] rtnArr = rtn.split("-");
    if (rtnArr[0].equals("true")) {
        redirectAttributes.addFlashAttribute("message", "同步宿主机和虚拟机成功！共计：宿主机（" + rtnArr[1] + "），虚拟机（" + rtnArr[2] + "）");
    }
    return REDIRECT_SUCCESS_URL;
}


@RequestMapping(value = { "list", "" })
public String list(int pageNumber,int pageSize,String serverType,Model model,ServletRequest request){
    Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, REQUEST_PREFIX);
    // 将搜索条件编码成字符串,分页的URL
    model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefix(searchParams, REQUEST_PREFIX));
    model.addAttribute("page", comm.hostServerService.getHostServerPageable(searchParams, pageNumber, pageSize));
    return "basicdata/host/hostList";
}


@RequestMapping(value = "delete/{id}")
public String delete(Integer id,RedirectAttributes redirectAttributes){
    boolean flag = comm.hostServerService.delete(id);
    redirectAttributes.addFlashAttribute("message", flag ? "删除服务器成功！" : "删除服务器失败！");
    return REDIRECT_SUCCESS_URL;
}


@RequestMapping(value = { "hostTree/{id}" })
public String hostTree(Integer id,Model model){
    model.addAttribute("hostServer", comm.hostServerService.getHostServer(id));
    model.addAttribute("ecsList", comm.hostServerService.getEcsByHost(id));
    // 物理机
    model.addAttribute("server", comm.operateService.findHostMapByServerType(2));
    // 宿主机
    model.addAttribute("vm", comm.operateService.findHostMapByServerType(1));
    return "basicdata/host/hostTree";
}


@ModelAttribute("switchMap")
public Map<String,String> getSwitchFromOnecmdb(){
    return OneCmdbService.findCiByText("Switch");
}


@RequestMapping(value = "/save", method = RequestMethod.GET)
public String createForm(Model model){
    return "basicdata/host/hostForm";
}


@RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
public String updateForm(Integer id,Model model){
    model.addAttribute("hostServer", comm.hostServerService.getHostServer(id));
    return "basicdata/host/hostForm";
}


@RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
public String detail(Integer id,Model model){
    model.addAttribute("hostServer", comm.hostServerService.getHostServer(id));
    return "basicdata/host/hostDetail";
}


@RequestMapping(value = "/hostTree", method = RequestMethod.POST)
public String updateHostTree(String[] computeIds,String[] serverAlias,RedirectAttributes redirectAttributes){
    comm.hostServerService.updateHostServerTree(computeIds, serverAlias);
    redirectAttributes.addFlashAttribute("message", "更新宿主机/服务器挂载的实例");
    return REDIRECT_SUCCESS_URL;
}


@RequestMapping(value = { "export" })
public String export(Model model,RedirectAttributes redirectAttributes){
    boolean flag = comm.hostServerService.export();
    if (flag) {
        redirectAttributes.addFlashAttribute("message", "导出宿主机及其虚拟机关系成功！D:\\Host_Vm.xls");
    }
    return REDIRECT_SUCCESS_URL;
}


@ModelAttribute("serverModelList")
public List<ServerModel> getServerModelList(){
    return comm.serverModelService.getServerModelList();
}


}