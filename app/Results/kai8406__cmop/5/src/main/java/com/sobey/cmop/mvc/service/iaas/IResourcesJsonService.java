package com.sobey.cmop.mvc.service.iaas;
 import com.sobey.cmop.mvc.entity.ComputeItem;
import com.sobey.cmop.mvc.entity.CpItem;
import com.sobey.cmop.mvc.entity.MdnItem;
import com.sobey.cmop.mvc.entity.MonitorCompute;
import com.sobey.cmop.mvc.entity.MonitorElb;
import com.sobey.cmop.mvc.entity.NetworkDnsItem;
import com.sobey.cmop.mvc.entity.NetworkEipItem;
import com.sobey.cmop.mvc.entity.NetworkElbItem;
import com.sobey.cmop.mvc.entity.Resources;
import com.sobey.cmop.mvc.entity.StorageItem;
import com.sobey.cmop.mvc.entity.ToJson.ComputeJson;
import com.sobey.cmop.mvc.entity.ToJson.CpJson;
import com.sobey.cmop.mvc.entity.ToJson.DnsJson;
import com.sobey.cmop.mvc.entity.ToJson.EipJson;
import com.sobey.cmop.mvc.entity.ToJson.ElbJson;
import com.sobey.cmop.mvc.entity.ToJson.MdnJson;
import com.sobey.cmop.mvc.entity.ToJson.MonitorComputeJson;
import com.sobey.cmop.mvc.entity.ToJson.MonitorElbJson;
import com.sobey.cmop.mvc.entity.ToJson.ResourcesJson;
import com.sobey.cmop.mvc.entity.ToJson.StorageJson;
public interface IResourcesJsonService {


public MdnJson convertMdnJsonToMdn(MdnItem mdnItem)
;

public ResourcesJson convertResourcesJsonToResourcesJson(Resources resources)
;

public ComputeJson convertComputeJsonToComputeItem(ComputeItem computeItem)
;

public ElbJson convertElbJsonToNetworkElbItem(NetworkElbItem networkElbItem)
;

public MonitorComputeJson convertMonitorComputeJsonToMonitorCompute(MonitorCompute monitorCompute)
;

public EipJson convertEipJsonToNetworkEipItem(NetworkEipItem networkEipItem)
;

public DnsJson convertDnsJsonToNetworkDnsItem(NetworkDnsItem networkDnsItem)
;

public StorageJson convertStorageJsonToComputeItem(StorageItem storageItem)
;

public CpJson convertCpJsonToCpItem(CpItem cpItem)
;

public MonitorElbJson convertMonitorElbJsonToMonitorElb(MonitorElb monitorElb)
;

}