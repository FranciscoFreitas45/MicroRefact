package com.sobey.cmop.mvc.web;
 import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.google.common.collect.Maps;
import com.sobey.cmop.mvc.comm.BaseController;
import com.sobey.cmop.mvc.entity.ComputeItem;
import com.sobey.cmop.mvc.entity.IpPool;
import com.sobey.cmop.mvc.entity.NetworkEsgItem;
import com.sobey.cmop.mvc.entity.ServerModel;
import com.sobey.cmop.mvc.entity.Vlan;
import com.sobey.cmop.mvc.entity.ToJson.ResourcesJson;
@Controller
@RequestMapping(value = "/ajax")
public class AjaxController extends BaseController{


@RequestMapping(value = "checkGroupName")
@ResponseBody
public String checkGroupName(String oldName,String name){
    return name.equals(oldName) || comm.accountService.findGroupByName(name) == null ? "true" : "false";
}


@RequestMapping(value = "checkLocation")
@ResponseBody
public String checkLocation(String oldName,String name){
    return name.equals(oldName) || comm.locationService.findLocationByName(name) == null ? "true" : "false";
}


@RequestMapping(value = "checkServiceTagName")
@ResponseBody
public String checkServiceTagName(String oldName,String name){
    return name.equals(oldName) || comm.serviceTagService.findServiceTagByNameAndUserId(name, getCurrentUserId()) == null ? "true" : "false";
}


@RequestMapping(value = "getIpPoolByVlan")
@ResponseBody
public List<IpPool> getIpPoolByVlan(String vlanAlias){
    return comm.ipPoolService.findIpPoolByVlan(vlanAlias);
}


@RequestMapping(value = "checkLoginName")
@ResponseBody
public String checkLoginName(String oldLoginName,String loginName){
    return loginName.equals(oldLoginName) || comm.accountService.findUserByLoginName(loginName) == null ? "true" : "false";
}


@RequestMapping(value = "getResourcesList", method = RequestMethod.POST)
@ResponseBody
public List<ResourcesJson> getResourcesList(String serviceType,String serviceTagName,String ipAddress,String serviceIdentifier){
    return comm.resourcesService.getResourcesJsonListByParamers(serviceType, serviceTagName, ipAddress, serviceIdentifier);
}


@RequestMapping(value = "checkVlan")
@ResponseBody
public String checkVlan(String oldName,String name){
    return name.equals(oldName) || comm.vlanService.findVlanByName(name) == null ? "true" : "false";
}


@RequestMapping(value = "checkDepartmentName")
@ResponseBody
public String checkDepartmentName(String oldName,String name){
    return name.equals(oldName) || comm.departmentService.findDepartmentByName(name) == null ? "true" : "false";
}


@RequestMapping(value = "getEsgList")
@ResponseBody
public List<NetworkEsgItem> getEsgList(){
    return comm.esgService.getESGList();
}


@RequestMapping(value = "getServerModel")
@ResponseBody
public ServerModel getServerModel(Integer id){
    return comm.serverModelService.getServerModel(id);
}


@RequestMapping(value = "checkServerModel")
@ResponseBody
public String checkServerModel(String oldName,String name){
    return name.equals(oldName) || comm.serverModelService.findServerModelByName(name) == null ? "true" : "false";
}


@RequestMapping(value = "getVlanByLocationAlias")
@ResponseBody
public Map<String,String> getVlanByLocationAlias(String locationAlias){
    Set<Vlan> vlans = comm.locationService.findLocationByAlias(locationAlias).getVlans();
    Map<String, String> map = Maps.newHashMap();
    for (Vlan vlan : vlans) {
        map.put(vlan.getAlias(), "Vlan" + vlan.getName() + "(" + vlan.getDescription() + ")");
    }
    return map;
}


@RequestMapping(value = "checkServerIsUsed")
@ResponseBody
public String checkServerIsUsed(String serverAlias){
    if (comm.hostServerService.findByAlias(serverAlias).getIpPools().size() > 0) {
        return "used";
    }
    return "";
}


@RequestMapping(value = "getComputeList")
@ResponseBody
public List<ComputeItem> getComputeList(){
    return comm.computeService.getComputeListByUserId(getCurrentUserId());
}


@RequestMapping(value = "getVlanByLocation")
@ResponseBody
public Map<Integer,String> getVlanByLocation(Integer location){
    Set<Vlan> vlans = comm.locationService.getLocation(location).getVlans();
    Map<Integer, String> map = Maps.newHashMap();
    for (Vlan vlan : vlans) {
        map.put(vlan.getId(), vlan.getName());
    }
    return map;
}


@RequestMapping(value = "checkEmail")
@ResponseBody
public String checkEmail(String oldEmail,String email){
    return email.equals(oldEmail) || comm.accountService.findUserByEmail(email) == null ? "true" : "false";
}


}