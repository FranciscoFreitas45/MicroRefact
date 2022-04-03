package com.sobey.cmop.mvc.web;
 import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.sobey.cmop.mvc.comm.BaseController;
import com.sobey.cmop.mvc.entity.ToJson.ComputeJson;
import com.sobey.cmop.mvc.entity.ToJson.CpJson;
import com.sobey.cmop.mvc.entity.ToJson.DnsJson;
import com.sobey.cmop.mvc.entity.ToJson.EipJson;
import com.sobey.cmop.mvc.entity.ToJson.ElbJson;
import com.sobey.cmop.mvc.entity.ToJson.MdnJson;
import com.sobey.cmop.mvc.entity.ToJson.MonitorComputeJson;
import com.sobey.cmop.mvc.entity.ToJson.MonitorElbJson;
import com.sobey.cmop.mvc.entity.ToJson.StorageJson;
@Controller
@RequestMapping(value = "/ajax")
public class ResourceAjaxController extends BaseController{


@RequestMapping(value = "getCompute")
@ResponseBody
public ComputeJson getCompute(Integer id){
    return comm.resourcesJsonService.convertComputeJsonToComputeItem(comm.computeService.getComputeItem(comm.resourcesService.getResources(id).getServiceId()));
}


@RequestMapping(value = "getmonitorElb")
@ResponseBody
public MonitorElbJson getmonitorElb(Integer id){
    return comm.resourcesJsonService.convertMonitorElbJsonToMonitorElb(comm.monitorElbServcie.getMonitorElb(comm.resourcesService.getResources(id).getServiceId()));
}


@RequestMapping(value = "getStorage")
@ResponseBody
public StorageJson getStorage(Integer id){
    return comm.resourcesJsonService.convertStorageJsonToComputeItem(comm.es3Service.getStorageItem(comm.resourcesService.getResources(id).getServiceId()));
}


@RequestMapping(value = "getmonitorCompute")
@ResponseBody
public MonitorComputeJson getmonitorCompute(Integer id){
    return comm.resourcesJsonService.convertMonitorComputeJsonToMonitorCompute(comm.monitorComputeServcie.getMonitorCompute(comm.resourcesService.getResources(id).getServiceId()));
}


@RequestMapping(value = "getCP")
@ResponseBody
public CpJson getCP(Integer id){
    return comm.resourcesJsonService.convertCpJsonToCpItem(comm.cpService.getCpItem((comm.resourcesService.getResources(id).getServiceId())));
}


@RequestMapping(value = "getMdn")
@ResponseBody
public MdnJson getMdn(Integer id){
    return comm.resourcesJsonService.convertMdnJsonToMdn(comm.mdnService.getMdnItem(comm.resourcesService.getResources(id).getServiceId()));
}


@RequestMapping(value = "getDns")
@ResponseBody
public DnsJson getDns(Integer id){
    return comm.resourcesJsonService.convertDnsJsonToNetworkDnsItem(comm.dnsService.getNetworkDnsItem(comm.resourcesService.getResources(id).getServiceId()));
}


@RequestMapping(value = "getElb")
@ResponseBody
public ElbJson getElb(Integer id){
    return comm.resourcesJsonService.convertElbJsonToNetworkElbItem(comm.elbService.getNetworkElbItem(comm.resourcesService.getResources(id).getServiceId()));
}


@RequestMapping(value = "getEip")
@ResponseBody
public EipJson getEip(Integer id){
    return comm.resourcesJsonService.convertEipJsonToNetworkEipItem(comm.eipService.getNetworkEipItem(comm.resourcesService.getResources(id).getServiceId()));
}


}