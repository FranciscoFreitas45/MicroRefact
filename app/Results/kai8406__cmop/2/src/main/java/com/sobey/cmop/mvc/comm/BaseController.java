package com.sobey.cmop.mvc.comm;
 import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.apache.shiro.SecurityUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import com.sobey.cmop.mvc.constant.AccountConstant;
import com.sobey.cmop.mvc.constant.ApplyConstant;
import com.sobey.cmop.mvc.constant.AuditConstant;
import com.sobey.cmop.mvc.constant.CPConstant;
import com.sobey.cmop.mvc.constant.ComputeConstant;
import com.sobey.cmop.mvc.constant.HostServerConstant;
import com.sobey.cmop.mvc.constant.IpPoolConstant;
import com.sobey.cmop.mvc.constant.MdnConstant;
import com.sobey.cmop.mvc.constant.MonitorConstant;
import com.sobey.cmop.mvc.constant.NetworkConstant;
import com.sobey.cmop.mvc.constant.RedmineConstant;
import com.sobey.cmop.mvc.constant.ResourcesConstant;
import com.sobey.cmop.mvc.constant.StorageConstant;
import com.sobey.cmop.mvc.entity.Apply;
import com.sobey.cmop.mvc.entity.ComputeItem;
import com.sobey.cmop.mvc.entity.Department;
import com.sobey.cmop.mvc.entity.Location;
import com.sobey.cmop.mvc.entity.NetworkEipItem;
import com.sobey.cmop.mvc.entity.NetworkElbItem;
import com.sobey.cmop.mvc.entity.NetworkEsgItem;
import com.sobey.cmop.mvc.entity.ServiceTag;
import com.sobey.cmop.mvc.entity.User;
import com.sobey.cmop.mvc.entity.Vlan;
import com.sobey.cmop.mvc.service.account.ShiroDbRealm.ShiroUser;
import com.sobey.cmop.mvc.Interface.CommonService;
public class BaseController {

@Resource
 public  CommonService comm;

 public  String PAGE_SIZE;

 public  String REQUEST_PREFIX;


public Integer getCurrentUserId(){
    ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
    return user != null ? user.id : 0;
}


@ModelAttribute("computeTypeMap")
public Map<Integer,String> computeTypeMap(){
    return ComputeConstant.ComputeType.map;
}


@ModelAttribute("encoderModeMap")
public Map<Integer,String> encoderModeMap(){
    return MdnConstant.EncoderMode.map;
}


@ModelAttribute("applyServiceTypeMap")
public Map<Integer,String> applyServiceTypeMap(){
    return ApplyConstant.ServiceType.map;
}


@ModelAttribute("allowApplyStatus")
public List<Integer> allowApplyStatus(){
    List<Integer> allowApplyStatus = new ArrayList<Integer>();
    allowApplyStatus.add(ApplyConstant.Status.已申请.toInteger());
    allowApplyStatus.add(ApplyConstant.Status.已退回.toInteger());
    return allowApplyStatus;
}


@ModelAttribute("auditResultMap")
public Map<Integer,String> auditResultMap(){
    return AuditConstant.Result.map;
}


@ModelAttribute("leaders")
public List<User> leaders(){
    return comm.accountService.getUserListByType(AccountConstant.DefaultGroups.audit.toInteger());
}


@ModelAttribute("osTypeMap")
public Map<Integer,String> osTypeMap(){
    return ComputeConstant.OS_TYPE_MAP;
}


@ModelAttribute("DEFAULT_IPADDRESS")
public String defaultIpaddress(){
    return IpPoolConstant.DEFAULT_IPADDRESS;
}


@ModelAttribute("hostServerTypeMap")
public Map<Integer,String> hostServerTypeMap(){
    return HostServerConstant.HostServerType.map;
}


@ModelAttribute("outputModeMap")
public Map<Integer,String> outputModeMap(){
    return MdnConstant.OutputMode.map;
}


@ModelAttribute("doneRatioMap")
public Map<Integer,String> doneRatioMap(){
    return RedmineConstant.REDMINE_DONERATIO_MAP;
}


@ModelAttribute("ipStausMap")
public Map<Integer,String> ipStausMap(){
    return IpPoolConstant.IpStatus.map;
}


@ModelAttribute("projectMap")
public Map<Integer,String> projectMap(){
    return RedmineConstant.Project.map;
}


@ModelAttribute("resourcesServiceTypeMap")
public Map<Integer,String> resourcesServiceTypeMap(){
    return ResourcesConstant.ServiceType.map;
}


@ModelAttribute("ecsServerTypeMap")
public Map<Integer,String> ecsServerTypeMap(){
    return ComputeConstant.ECSServerType.map;
}


@ModelAttribute("ispTypeMap")
public Map<Integer,String> ispTypeMap(){
    return NetworkConstant.ISPType.map;
}


@ModelAttribute("videoOutputWayMap")
public Map<Integer,String> videoOutputWayMap(){
    return CPConstant.VideoOutputWay.map;
}


@ModelAttribute("thresholdLtMap")
public Map<Integer,String> thresholdLtMap(){
    return MonitorConstant.THRESHOLD_LT;
}


@ModelAttribute("esgProtocolMap")
public Map<String,String> esgProtocolMap(){
    return NetworkConstant.EsgProtocol.map;
}


@ModelAttribute("keepSessionMap")
public Map<Boolean,String> keepSessionMap(){
    return NetworkConstant.KeepSession.map;
}


@ModelAttribute("isPushCtpMap")
public Map<Boolean,String> isPushCtpMap(){
    return CPConstant.IsPushCtp.map;
}


@ModelAttribute("protocolMap")
public Map<String,String> protocolMap(){
    return NetworkConstant.Protocol.map;
}


@ModelAttribute("bandwidthMap")
public Map<Integer,String> bandwidthMap(){
    return MdnConstant.BANDWIDTH_MAP;
}


@ModelAttribute("tags")
public List<ServiceTag> tags(){
    return comm.serviceTagService.getServiceTagToResourcesList();
}


@ModelAttribute("resourcesStatusMap")
public Map<Integer,String> resourcesStatusMap(){
    return ResourcesConstant.Status.map;
}


@ModelAttribute("storageTypeMap")
public Map<Integer,String> storageTypeMap(){
    return StorageConstant.StorageType.map;
}


@ModelAttribute("recordTypeMap")
public Map<Integer,String> recordTypeMap(){
    return CPConstant.RecordType.map;
}


@ModelAttribute("maxProcessMap")
public Map<Integer,String> maxProcessMap(){
    return MonitorConstant.MAX_PROCESS;
}


@ModelAttribute("palyProtocolMap")
public Map<String,String> palyProtocolMap(){
    return MdnConstant.Protocol.map;
}


@ModelAttribute("osBitMap")
public Map<Integer,String> osBitMap(){
    return ComputeConstant.OS_BIT_MAP;
}


@ModelAttribute("exportEncodeMap")
public Map<Integer,String> exportEncodeMap(){
    return CPConstant.EXPORTENCODE_MAP;
}


@ModelAttribute("thresholdNetGtMap")
public Map<Integer,String> thresholdNetGtMap(){
    return MonitorConstant.THRESHOLD_NET_GT;
}


@ModelAttribute("domainTypeMap")
public Map<Integer,String> domainTypeMap(){
    return NetworkConstant.DomainType.map;
}


@ModelAttribute("pcsServerTypeMap")
public Map<Integer,String> pcsServerTypeMap(){
    return ComputeConstant.PCSServerType.map;
}


@ModelAttribute("recordBitrateMap")
public Map<String,String> recordBitrateMap(){
    return CPConstant.RECORDBITRATE_MAP_STRING_KEY;
}


@ModelAttribute("esgList")
public List<NetworkEsgItem> esgList(){
    return comm.esgService.getESGList();
}


@ModelAttribute("assigneeMap")
public Map<Integer,String> assigneeMap(){
    return RedmineConstant.Assignee.map;
}


@ModelAttribute("priorityMap")
public Map<Integer,String> priorityMap(){
    return RedmineConstant.Priority.map;
}


@ModelAttribute("userTypeMap")
public Map<Integer,String> userTypeMap(){
    return AccountConstant.UserTypes.map;
}


@ModelAttribute("allElbs")
public List<NetworkElbItem> allElbs(){
    return comm.elbService.getNetworkElbItemListByUserId(getCurrentUserId() == null ? 0 : getCurrentUserId());
}


@ModelAttribute("allDepartments")
public List<Department> allDepartments(){
    return comm.departmentService.getDepartmentList();
}


@ModelAttribute("thresholdGtMap")
public Map<Integer,String> thresholdGtMap(){
    return MonitorConstant.THRESHOLD_GT;
}


@ModelAttribute("allowResourcesStatus")
public List<Integer> allowResourcesStatus(){
    List<Integer> allowResourcesStatus = new ArrayList<Integer>();
    allowResourcesStatus.add(ResourcesConstant.Status.未变更.toInteger());
    allowResourcesStatus.add(ResourcesConstant.Status.已变更.toInteger());
    allowResourcesStatus.add(ResourcesConstant.Status.已退回.toInteger());
    allowResourcesStatus.add(ResourcesConstant.Status.已创建.toInteger());
    return allowResourcesStatus;
}


@ModelAttribute("operateStatusMap")
public Map<Integer,String> operateStatusMap(){
    return RedmineConstant.Status.map;
}


@ModelAttribute("vlanList")
public List<Vlan> getVlanList(){
    return comm.vlanService.getVlanList();
}


@ModelAttribute("applyStatusMap")
public Map<Integer,String> applyStatusMap(){
    return ApplyConstant.Status.map;
}


@ModelAttribute("locationList")
public List<Location> locationList(){
    return comm.locationService.getLocationList();
}


@ModelAttribute("allEips")
public List<NetworkEipItem> allEips(){
    return comm.eipService.getNetworkEipItemListByUserId(getCurrentUserId() == null ? 0 : getCurrentUserId());
}


@ModelAttribute("trackerMap")
public Map<Integer,String> trackerMap(){
    return RedmineConstant.Tracker.map;
}


@ModelAttribute("poolTypeMap")
public Map<Integer,String> poolTypeMap(){
    return IpPoolConstant.PoolType.map;
}


@ModelAttribute("baseStationApplys")
public List<Apply> baseStationApplys(){
    return comm.applyService.getBaseStationApplyList();
}


@ModelAttribute("allComputes")
public List<ComputeItem> allComputes(){
    return comm.computeService.getComputeListByUserId(getCurrentUserId() == null ? 0 : getCurrentUserId());
}


@ModelAttribute("allTags")
public List<ServiceTag> allTags(){
    return comm.serviceTagService.getServiceTagList();
}


}