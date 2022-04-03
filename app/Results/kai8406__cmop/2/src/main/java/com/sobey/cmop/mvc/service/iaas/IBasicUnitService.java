package com.sobey.cmop.mvc.service.iaas;
 import java.util.List;
import com.sobey.cmop.mvc.entity.ComputeItem;
import com.sobey.cmop.mvc.entity.MonitorCompute;
import com.sobey.cmop.mvc.entity.MonitorElb;
import com.sobey.cmop.mvc.entity.NetworkDnsItem;
import com.sobey.cmop.mvc.entity.NetworkEipItem;
import com.sobey.cmop.mvc.entity.NetworkElbItem;
import com.sobey.cmop.mvc.entity.StorageItem;
public interface IBasicUnitService {


public List<MonitorCompute> getMonitorComputeListByResources(Integer userId)
;

public List<MonitorElb> getMonitorElbListByResources(Integer userId)
;

public List<ComputeItem> getComputeListByElb(Integer elbId)
;

public ComputeItem wrapComputeItem(Object[] object)
;

public List<ComputeItem> getComputeItemListByResources(Integer userId)
;

public List<NetworkElbItem> getNetworkElbItemListByResources(Integer userId)
;

public List<NetworkEipItem> getNetworkEipItemListByResources(Integer userId)
;

public List<NetworkDnsItem> getNetworkDnsItemListByResources(Integer userId)
;

public List<StorageItem> getStorageItemListByResources(Integer userId)
;

}