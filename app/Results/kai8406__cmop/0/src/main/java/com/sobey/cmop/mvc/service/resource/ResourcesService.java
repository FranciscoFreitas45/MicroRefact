package com.sobey.cmop.mvc.service.resource;
 import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.google.common.collect.Maps;
import com.sobey.cmop.mvc.comm.BaseSevcie;
import com.sobey.cmop.mvc.constant.AccountConstant;
import com.sobey.cmop.mvc.constant.CPConstant;
import com.sobey.cmop.mvc.constant.ComputeConstant;
import com.sobey.cmop.mvc.constant.FieldNameConstant;
import com.sobey.cmop.mvc.constant.IpPoolConstant;
import com.sobey.cmop.mvc.constant.NetworkConstant;
import com.sobey.cmop.mvc.constant.RedmineConstant;
import com.sobey.cmop.mvc.constant.ResourcesConstant;
import com.sobey.cmop.mvc.dao.ResourcesDao;
import com.sobey.cmop.mvc.entity.Apply;
import com.sobey.cmop.mvc.entity.Change;
import com.sobey.cmop.mvc.entity.ChangeItem;
import com.sobey.cmop.mvc.entity.ComputeItem;
import com.sobey.cmop.mvc.entity.CpItem;
import com.sobey.cmop.mvc.entity.EipPortItem;
import com.sobey.cmop.mvc.entity.ElbPortItem;
import com.sobey.cmop.mvc.entity.Group;
import com.sobey.cmop.mvc.entity.MdnItem;
import com.sobey.cmop.mvc.entity.MdnLiveItem;
import com.sobey.cmop.mvc.entity.MdnVodItem;
import com.sobey.cmop.mvc.entity.MonitorCompute;
import com.sobey.cmop.mvc.entity.MonitorElb;
import com.sobey.cmop.mvc.entity.MonitorMail;
import com.sobey.cmop.mvc.entity.MonitorPhone;
import com.sobey.cmop.mvc.entity.NetworkDnsItem;
import com.sobey.cmop.mvc.entity.NetworkEipItem;
import com.sobey.cmop.mvc.entity.NetworkElbItem;
import com.sobey.cmop.mvc.entity.NetworkEsgItem;
import com.sobey.cmop.mvc.entity.RedmineIssue;
import com.sobey.cmop.mvc.entity.Resources;
import com.sobey.cmop.mvc.entity.ServiceTag;
import com.sobey.cmop.mvc.entity.StorageItem;
import com.sobey.cmop.mvc.entity.User;
import com.sobey.cmop.mvc.entity.ToJson.ResourcesJson;
import com.sobey.cmop.mvc.service.redmine.RedmineService;
import com.sobey.framework.utils.Collections3;
import com.sobey.framework.utils.DynamicSpecifications;
import com.sobey.framework.utils.SearchFilter;
import com.sobey.framework.utils.SearchFilter.Operator;
import com.taskadapter.redmineapi.RedmineManager;
import com.taskadapter.redmineapi.bean.Issue;
import com.taskadapter.redmineapi.bean.Tracker;
import com.sobey.cmop.mvc.DTO.Apply;
import com.sobey.cmop.mvc.DTO.Group;
import com.sobey.cmop.mvc.DTO.User;
import com.sobey.cmop.mvc.DTO.NetworkElbItem;
import com.sobey.cmop.mvc.DTO.NetworkEipItem;
import com.sobey.cmop.mvc.DTO.CpItem;
import com.sobey.cmop.mvc.DTO.MdnItem;
import com.sobey.cmop.mvc.DTO.MdnVodItem;
import com.sobey.cmop.mvc.DTO.MdnLiveItem;
import com.sobey.cmop.mvc.DTO.MonitorElb;
import com.sobey.cmop.mvc.DTO.MonitorCompute;
import com.sobey.cmop.mvc.DTO.NetworkDnsItem;
import com.sobey.cmop.mvc.DTO.RedmineIssue;
import com.sobey.cmop.mvc.DTO.SearchFilter;
@Service
@Transactional(readOnly = true)
public class ResourcesService extends BaseSevcie{

 private  Logger logger;

@Resource
 private  ResourcesDao resourcesDao;


@Transactional(readOnly = false)
public Resources saveAndWrapResources(Apply apply,Integer serviceType,ServiceTag serviceTag,Integer serviceId,String serviceIdentifier,String ipAddress){
    Resources resources = new Resources();
    resources.setUser(apply.getUser());
    resources.setServiceType(serviceType);
    resources.setServiceTag(serviceTag);
    resources.setServiceId(serviceId);
    resources.setServiceIdentifier(serviceIdentifier);
    resources.setCreateTime(new Date());
    resources.setStatus(ResourcesConstant.Status.?????????.toInteger());
    resources.setIpAddress(ipAddress);
    resources.setOldIp(ipAddress);
    return saveOrUpdate(resources);
}


public List<ResourcesJson> getResourcesJsonListByParamers(String serviceType,String serviceTagName,String ipAddress,String serviceIdentifier){
    Map<String, SearchFilter> filters = Maps.newHashMap();
    // ????????????,??????filters.?????????????????????????????????.
    User user = comm.accountService.getCurrentUser();
    List<Integer> groups = new ArrayList<Integer>();
    boolean flag = true;
    // ???????????????????????????.???????????????????????????????????????.
    groups.add(AccountConstant.DefaultGroups.om_a.toInteger());
    groups.add(AccountConstant.DefaultGroups.om_b.toInteger());
    // ?????????????????????????????????,?????????flag???false???break Loop.
    for (Group group : user.getGroupList()) {
        if (groups.contains(group.getId())) {
            flag = false;
            break;
        }
    }
    if (flag) {
        filters.put("resources.user.id", new SearchFilter("user.id", Operator.EQ, getCurrentUserId()));
    }
    if (StringUtils.isNotBlank(serviceType)) {
        filters.put("resources.serviceType", new SearchFilter("serviceType", Operator.EQ, serviceType));
    }
    if (StringUtils.isNotBlank(serviceTagName)) {
        filters.put("resources.serviceTag.name", new SearchFilter("serviceTag.name", Operator.LIKE, serviceTagName));
    }
    if (StringUtils.isNotBlank(ipAddress)) {
        filters.put("resources.ipAddress", new SearchFilter("ipAddress", Operator.EQ, ipAddress));
    }
    if (StringUtils.isNotBlank(serviceIdentifier)) {
        filters.put("resources.serviceIdentifier", new SearchFilter("serviceIdentifier", Operator.LIKE, serviceIdentifier));
    }
    Specification<Resources> spec = DynamicSpecifications.bySearchFilter(filters.values(), Resources.class);
    List<ResourcesJson> jsonList = new ArrayList<ResourcesJson>();
    // ???Resources??????ResourcesJson
    for (Resources resources : resourcesDao.findAll(spec)) {
        jsonList.add(comm.resourcesJsonService.convertResourcesJsonToResourcesJson(resources));
    }
    return jsonList;
}


public void restoreEIPPort(NetworkEipItem networkEipItem,String oldChangeValue){
    this.restorePort(null, networkEipItem, oldChangeValue);
}


public void restorePort(NetworkElbItem networkElbItem,NetworkEipItem networkEipItem,String oldChangeValue){
    // ??????????????????????????????<br>,?????????<br>????????????????????????.
    String[] portItems = StringUtils.splitByWholeSeparator(StringUtils.substringBeforeLast(oldChangeValue, "<br>"), "<br>");
    String[] protocols = new String[portItems.length];
    String[] sourcePorts = new String[portItems.length];
    String[] targetPorts = new String[portItems.length];
    if (portItems != null && portItems.length != 0) {
        for (int i = 0; i < portItems.length; i++) {
            String[] ports = StringUtils.split(portItems[i], ",");
            for (int j = 0; j < ports.length; j++) {
                protocols[i] = ports[0];
                sourcePorts[i] = ports[1];
                targetPorts[i] = ports[2];
            }
        }
    }
    if (networkElbItem != null) {
        /* ELB : ???ELB??????????????????????????????,????????????????????????????????? */
        comm.elbService.deleteElbPortItem(comm.elbService.getElbPortItemListByElbId(networkElbItem.getId()));
        for (int i = 0; i < protocols.length; i++) {
            ElbPortItem elbPortItem = new ElbPortItem(networkElbItem, protocols[i], sourcePorts[i], targetPorts[i]);
            comm.elbService.saveOrUpdateElbPortItem(elbPortItem);
        }
    }
    if (networkEipItem != null) {
        /* EIP : ???EIP??????????????????????????????,????????????????????????????????? */
        comm.eipService.deleteEipPortItem(comm.eipService.getEipPortItemListByEipId(networkEipItem.getId()));
        for (int i = 0; i < protocols.length; i++) {
            EipPortItem eipPortItem = new EipPortItem(networkEipItem, protocols[i], sourcePorts[i], targetPorts[i]);
            comm.eipService.saveOrUpdateEipPortItem(eipPortItem);
        }
    }
}


public List<Resources> getChangedResourcesListByServiceTagId(Integer serviceTagId){
    List<Integer> status = new ArrayList<Integer>();
    status.add(ResourcesConstant.Status.?????????.toInteger());
    status.add(ResourcesConstant.Status.?????????.toInteger());
    return resourcesDao.findByServiceTagIdAndStatusInOrderByIdDesc(serviceTagId, status);
}


@Transactional(readOnly = false)
public Resources restoreResources(Resources resources){
    Integer serviceType = resources.getServiceType();
    Integer serviceId = resources.getServiceId();
    List<Change> changes = comm.changeServcie.getChangeListByResourcesId(resources.getId());
    for (Change change : changes) {
        List<ChangeItem> changeItems = comm.changeServcie.getChangeItemListByChangeId(change.getId());
        if (ResourcesConstant.ServiceType.PCS.toInteger().equals(serviceType) || ResourcesConstant.ServiceType.ECS.toInteger().equals(serviceType)) {
            ComputeItem computeItem = comm.computeService.getComputeItem(serviceId);
            for (ChangeItem changeItem : changeItems) {
                if (FieldNameConstant.Compate.????????????.toString().equals(changeItem.getFieldName())) {
                    computeItem.setOsType(Integer.valueOf(changeItem.getOldValue()));
                } else if (FieldNameConstant.Compate.????????????.toString().equals(changeItem.getFieldName())) {
                    computeItem.setOsBit(Integer.valueOf(changeItem.getOldValue()));
                } else if (FieldNameConstant.Compate.??????.toString().equals(changeItem.getFieldName())) {
                    computeItem.setServerType(Integer.valueOf(changeItem.getOldValue()));
                } else if (FieldNameConstant.Compate.????????????.toString().equals(changeItem.getFieldName())) {
                    computeItem.setRemark(changeItem.getOldValue());
                } else if (FieldNameConstant.Compate.ESG.toString().equals(changeItem.getFieldName())) {
                    // ????????????????????????Id,?????????????????????.
                    String[] esgIds = StringUtils.split(changeItem.getOldValue(), ",");
                    List<NetworkEsgItem> networkEsgItemList = new ArrayList<NetworkEsgItem>();
                    for (String esgId : esgIds) {
                        networkEsgItemList.add(comm.esgService.getNetworkEsgItem(Integer.valueOf(esgId)));
                    }
                    computeItem.setNetworkEsgItemList(networkEsgItemList);
                } else if (FieldNameConstant.Compate.????????????.toString().equals(changeItem.getFieldName())) {
                    this.restoreApplication(computeItem, changeItem.getOldValue());
                }
            }
            comm.computeService.saveOrUpdate(computeItem);
        } else if (ResourcesConstant.ServiceType.ES3.toInteger().equals(serviceType)) {
            StorageItem storageItem = comm.es3Service.getStorageItem(serviceId);
            for (ChangeItem changeItem : changeItems) {
                if (FieldNameConstant.Storage.????????????.toString().equals(changeItem.getFieldName())) {
                    storageItem.setStorageType(Integer.valueOf(changeItem.getOldValue()));
                } else if (FieldNameConstant.Storage.????????????.toString().equals(changeItem.getFieldName())) {
                    storageItem.setSpace(Integer.valueOf(changeItem.getOldValue()));
                } else if (FieldNameConstant.Storage.????????????.toString().equals(changeItem.getFieldName())) {
                    // ????????????????????????Id,?????????????????????.
                    String[] computeIds = StringUtils.split(changeItem.getOldValue(), ",");
                    if (computeIds != null) {
                        List<ComputeItem> computeItemList = new ArrayList<ComputeItem>();
                        for (int i = 0; i < computeIds.length; i++) {
                            ComputeItem computeItem = comm.computeService.getComputeItem(Integer.valueOf(computeIds[i]));
                            computeItemList.add(computeItem);
                        }
                        storageItem.setComputeItemList(computeItemList);
                    }
                }
            }
            comm.es3Service.saveOrUpdate(storageItem);
        } else if (ResourcesConstant.ServiceType.ELB.toInteger().equals(serviceType)) {
            NetworkElbItem networkElbItem = comm.elbService.getNetworkElbItem(serviceId);
            for (ChangeItem changeItem : changeItems) {
                if (FieldNameConstant.Elb.??????????????????.toString().equals(changeItem.getFieldName())) {
                    networkElbItem.setKeepSession(NetworkConstant.KeepSession.??????.toString().equals(changeItem.getOldValue()) ? true : false);
                } else if (FieldNameConstant.Elb.????????????.toString().equals(changeItem.getFieldName())) {
                    this.restoreELBPort(networkElbItem, changeItem.getOldValue());
                } else if (FieldNameConstant.Elb.????????????.toString().equals(changeItem.getFieldName())) {
                    // ????????????????????????Id,?????????????????????.
                    String[] computeIds = StringUtils.split(changeItem.getOldValue(), ",");
                    if (computeIds != null) {
                        List<ComputeItem> computeItemList = new ArrayList<ComputeItem>();
                        for (int i = 0; i < computeIds.length; i++) {
                            ComputeItem computeItem = comm.computeService.getComputeItem(Integer.valueOf(computeIds[i]));
                            computeItemList.add(computeItem);
                        }
                        networkElbItem.setComputeItemList(computeItemList);
                    }
                }
            }
            comm.elbService.saveOrUpdate(networkElbItem);
        } else if (ResourcesConstant.ServiceType.EIP.toInteger().equals(serviceType)) {
            NetworkEipItem networkEipItem = comm.eipService.getNetworkEipItem(serviceId);
            for (ChangeItem changeItem : changeItems) {
                if (FieldNameConstant.Eip.ISP.toString().equals(changeItem.getFieldName())) {
                    networkEipItem.setIspType(Integer.valueOf(changeItem.getOldValue()));
                } else if (FieldNameConstant.Eip.????????????.toString().equals(changeItem.getFieldName())) {
                    this.restoreEIPPort(networkEipItem, changeItem.getOldValue());
                } else if (FieldNameConstant.Eip.??????ELB.toString().equals(changeItem.getFieldName())) {
                    networkEipItem.setNetworkElbItem(comm.elbService.getNetworkElbItem(Integer.valueOf(changeItem.getOldValue())));
                } else if (FieldNameConstant.Eip.????????????.toString().equals(changeItem.getFieldName())) {
                    networkEipItem.setComputeItem(comm.computeService.getComputeItem(Integer.valueOf(changeItem.getOldValue())));
                }
            }
            comm.eipService.saveOrUpdate(networkEipItem);
        } else if (ResourcesConstant.ServiceType.DNS.toInteger().equals(serviceType)) {
            NetworkDnsItem networkDnsItem = comm.dnsService.getNetworkDnsItem(serviceId);
            for (ChangeItem changeItem : changeItems) {
                if (FieldNameConstant.Dns.??????.toString().equals(changeItem.getFieldName())) {
                    networkDnsItem.setDomainName(changeItem.getOldValue());
                } else if (FieldNameConstant.Dns.????????????.toString().equals(changeItem.getFieldName())) {
                    networkDnsItem.setDomainType(Integer.valueOf(changeItem.getOldValue()));
                } else if (FieldNameConstant.Dns.CNAME??????.toString().equals(changeItem.getFieldName())) {
                    networkDnsItem.setCnameDomain(StringUtils.defaultIfBlank(changeItem.getOldValue(), null));
                } else if (FieldNameConstant.Dns.??????IP.toString().equals(changeItem.getFieldName())) {
                    if (StringUtils.isNotBlank(changeItem.getOldValue())) {
                        String[] eipIds = StringUtils.split(changeItem.getOldValue(), ",");
                        if (eipIds != null) {
                            List<NetworkEipItem> networkEipItemList = new ArrayList<NetworkEipItem>();
                            for (String eipId : eipIds) {
                                NetworkEipItem networkEipItem = comm.eipService.getNetworkEipItem(Integer.valueOf(eipId));
                                networkEipItemList.add(networkEipItem);
                            }
                            networkDnsItem.setNetworkEipItemList(networkEipItemList);
                        }
                    }
                }
            }
            comm.dnsService.saveOrUpdate(networkDnsItem);
        } else if (ResourcesConstant.ServiceType.MONITOR_COMPUTE.toInteger().equals(serviceType)) {
            MonitorCompute monitorCompute = comm.monitorComputeServcie.getMonitorCompute(serviceId);
            for (ChangeItem changeItem : changeItems) {
                if (FieldNameConstant.monitorCompute.????????????.toString().equals(changeItem.getFieldName())) {
                    monitorCompute.setIpAddress(changeItem.getOldValue());
                } else if (FieldNameConstant.monitorCompute.????????????.toString().equals(changeItem.getFieldName())) {
                    monitorCompute.setPort(changeItem.getOldValue());
                } else if (FieldNameConstant.monitorCompute.????????????.toString().equals(changeItem.getFieldName())) {
                    monitorCompute.setProcess(changeItem.getOldValue());
                } else if (FieldNameConstant.monitorCompute.????????????.toString().equals(changeItem.getFieldName())) {
                    monitorCompute.setMountPoint(changeItem.getOldValue());
                } else if (FieldNameConstant.monitorCompute.CPU?????????????????????.toString().equals(changeItem.getFieldName())) {
                    monitorCompute.setCpuWarn(changeItem.getOldValue());
                } else if (FieldNameConstant.monitorCompute.CPU?????????????????????.toString().equals(changeItem.getFieldName())) {
                    monitorCompute.setCpuCritical(changeItem.getOldValue());
                } else if (FieldNameConstant.monitorCompute.???????????????????????????.toString().equals(changeItem.getFieldName())) {
                    monitorCompute.setMemoryWarn(changeItem.getOldValue());
                } else if (FieldNameConstant.monitorCompute.???????????????????????????.toString().equals(changeItem.getFieldName())) {
                    monitorCompute.setMaxProcessCritical(changeItem.getOldValue());
                } else if (FieldNameConstant.monitorCompute.???????????????????????????.toString().equals(changeItem.getFieldName())) {
                    monitorCompute.setPingLossWarn(changeItem.getOldValue());
                } else if (FieldNameConstant.monitorCompute.???????????????????????????.toString().equals(changeItem.getFieldName())) {
                    monitorCompute.setPingLossCritical(changeItem.getOldValue());
                } else if (FieldNameConstant.monitorCompute.???????????????????????????.toString().equals(changeItem.getFieldName())) {
                    monitorCompute.setDiskWarn(changeItem.getOldValue());
                } else if (FieldNameConstant.monitorCompute.???????????????????????????.toString().equals(changeItem.getFieldName())) {
                    monitorCompute.setDiskCritical(changeItem.getOldValue());
                } else if (FieldNameConstant.monitorCompute.???????????????????????????.toString().equals(changeItem.getFieldName())) {
                    monitorCompute.setPingLossWarn(changeItem.getOldValue());
                } else if (FieldNameConstant.monitorCompute.???????????????????????????.toString().equals(changeItem.getFieldName())) {
                    monitorCompute.setPingLossCritical(changeItem.getOldValue());
                } else if (FieldNameConstant.monitorCompute.???????????????????????????.toString().equals(changeItem.getFieldName())) {
                    monitorCompute.setMaxProcessWarn(changeItem.getOldValue());
                } else if (FieldNameConstant.monitorCompute.???????????????????????????.toString().equals(changeItem.getFieldName())) {
                    monitorCompute.setMaxProcessCritical(changeItem.getOldValue());
                }
            }
            comm.monitorComputeServcie.saveOrUpdate(monitorCompute);
        } else if (ResourcesConstant.ServiceType.MONITOR_ELB.toInteger().equals(serviceType)) {
            MonitorElb monitorElb = comm.monitorElbServcie.getMonitorElb(serviceId);
            for (ChangeItem changeItem : changeItems) {
                if (FieldNameConstant.monitorElb.??????ELB.toString().equals(changeItem.getFieldName())) {
                    monitorElb.setNetworkElbItem(comm.elbService.getNetworkElbItem(Integer.valueOf(changeItem.getOldValue())));
                }
            }
            comm.monitorElbServcie.saveOrUpdate(monitorElb);
        } else if (ResourcesConstant.ServiceType.MDN.toInteger().equals(serviceType)) {
            MdnItem mdnItem = comm.mdnService.getMdnItem(serviceId);
            MdnVodItem mdnVodItem = comm.mdnService.getMdnVodItem(change.getSubResourcesId());
            MdnLiveItem mdnLiveItem = comm.mdnService.getMdnLiveItem(change.getSubResourcesId());
            for (ChangeItem changeItem : changeItems) {
                if (FieldNameConstant.MdnItem.??????????????????.toString().equals(changeItem.getFieldName())) {
                    mdnItem.setCoverArea(changeItem.getOldValue());
                }
                if (FieldNameConstant.MdnItem.??????????????????.toString().equals(changeItem.getFieldName())) {
                    mdnItem.setCoverIsp(changeItem.getOldValue());
                }
                if (FieldNameConstant.MdnItem.??????????????????.toString().equals(changeItem.getFieldName())) {
                    mdnItem.setBandwidth(changeItem.getOldValue());
                }
                // vod
                if (FieldNameConstant.MdnVodItem.??????????????????.toString().equals(changeItem.getFieldName())) {
                    mdnVodItem.setVodDomain(changeItem.getOldValue());
                }
                if (FieldNameConstant.MdnVodItem.????????????????????????.toString().equals(changeItem.getFieldName())) {
                    mdnVodItem.setVodProtocol(changeItem.getOldValue());
                }
                if (FieldNameConstant.MdnVodItem.Streamer??????.toString().equals(changeItem.getFieldName())) {
                    mdnVodItem.setSourceStreamerUrl(changeItem.getOldValue());
                }
                if (FieldNameConstant.MdnVodItem.????????????????????????.toString().equals(changeItem.getFieldName())) {
                    mdnVodItem.setSourceOutBandwidth(changeItem.getOldValue());
                }
                // live
                if (FieldNameConstant.MdnLiveItem.??????????????????.toString().equals(changeItem.getFieldName())) {
                    mdnLiveItem.setLiveDomain(changeItem.getOldValue());
                }
                if (FieldNameConstant.MdnLiveItem.????????????????????????.toString().equals(changeItem.getFieldName())) {
                    mdnLiveItem.setLiveProtocol(changeItem.getOldValue());
                }
                if (FieldNameConstant.MdnLiveItem.?????????????????????.toString().equals(changeItem.getFieldName())) {
                    mdnLiveItem.setStreamOutMode(Integer.valueOf(changeItem.getOldValue()));
                }
                if (FieldNameConstant.MdnLiveItem.????????????.toString().equals(changeItem.getFieldName())) {
                    mdnLiveItem.setName(changeItem.getOldValue());
                }
                if (FieldNameConstant.MdnLiveItem.??????GUID.toString().equals(changeItem.getFieldName())) {
                    mdnLiveItem.setGuid(changeItem.getOldValue());
                }
                if (FieldNameConstant.MdnLiveItem.????????????????????????.toString().equals(changeItem.getFieldName())) {
                    mdnLiveItem.setBandwidth(changeItem.getOldValue());
                }
                if (FieldNameConstant.MdnLiveItem.???????????????.toString().equals(changeItem.getFieldName())) {
                    mdnLiveItem.setEncoderMode(Integer.valueOf(changeItem.getOldValue()));
                }
                if (FieldNameConstant.MdnLiveItem.HTTP?????????.toString().equals(changeItem.getFieldName()) || FieldNameConstant.MdnLiveItem.????????????.toString().equals(changeItem.getFieldName())) {
                    mdnLiveItem.setHttpUrl(StringUtils.defaultIfBlank(changeItem.getOldValue(), null));
                }
                if (FieldNameConstant.MdnLiveItem.HTTP???????????????.toString().equals(changeItem.getFieldName()) || FieldNameConstant.MdnLiveItem.??????????????????.toString().equals(changeItem.getFieldName())) {
                    mdnLiveItem.setHttpBitrate(StringUtils.defaultIfBlank(changeItem.getOldValue(), null));
                }
                if (FieldNameConstant.MdnLiveItem.HSL?????????.toString().equals(changeItem.getFieldName()) || FieldNameConstant.MdnLiveItem.????????????.toString().equals(changeItem.getFieldName())) {
                    mdnLiveItem.setHlsUrl(StringUtils.defaultIfBlank(changeItem.getOldValue(), null));
                }
                if (FieldNameConstant.MdnLiveItem.HSL???????????????.toString().equals(changeItem.getFieldName()) || FieldNameConstant.MdnLiveItem.??????????????????.toString().equals(changeItem.getFieldName())) {
                    mdnLiveItem.setHlsBitrate(StringUtils.defaultIfBlank(changeItem.getOldValue(), null));
                }
            }
            comm.mdnService.saveOrUpdate(mdnItem);
            if (mdnLiveItem != null) {
                comm.mdnService.saveOrUpdate(mdnLiveItem);
            }
            if (mdnVodItem != null) {
                comm.mdnService.saveOrUpdate(mdnVodItem);
            }
        } else if (ResourcesConstant.ServiceType.CP.toInteger().equals(serviceType)) {
            CpItem cpItem = comm.cpService.getCpItem(serviceId);
            for (ChangeItem changeItem : changeItems) {
                if (FieldNameConstant.CpItem.?????????URL.toString().equals(changeItem.getFieldName())) {
                    cpItem.setRecordStreamUrl(changeItem.getOldValue());
                }
                if (FieldNameConstant.CpItem.????????????.toString().equals(changeItem.getFieldName())) {
                    cpItem.setRecordBitrate(changeItem.getOldValue());
                }
                if (FieldNameConstant.CpItem.????????????.toString().equals(changeItem.getFieldName())) {
                    cpItem.setExportEncode(changeItem.getOldValue());
                }
                if (FieldNameConstant.CpItem.????????????.toString().equals(changeItem.getFieldName())) {
                    cpItem.setRecordType(Integer.valueOf(changeItem.getOldValue()));
                }
                if (FieldNameConstant.CpItem.????????????.toString().equals(changeItem.getFieldName())) {
                    cpItem.setRecordTime(changeItem.getOldValue());
                }
                if (FieldNameConstant.CpItem.????????????.toString().equals(changeItem.getFieldName())) {
                    cpItem.setRecordDuration(Integer.valueOf(changeItem.getOldValue()));
                }
                if (FieldNameConstant.CpItem.??????????????????.toString().equals(changeItem.getFieldName())) {
                    cpItem.setPublishUrl(changeItem.getOldValue());
                }
                if (FieldNameConstant.CpItem.??????????????????????????????.toString().equals(changeItem.getFieldName())) {
                    cpItem.setIsPushCtp(CPConstant.IsPushCtp.??????.toString().equals(changeItem.getOldValue()) ? true : false);
                }
                if (FieldNameConstant.CpItem.??????FTP??????IP.toString().equals(changeItem.getFieldName())) {
                    cpItem.setVideoFtpIp(changeItem.getOldValue());
                }
                if (FieldNameConstant.CpItem.????????????.toString().equals(changeItem.getFieldName())) {
                    cpItem.setVideoFtpPort(changeItem.getOldValue());
                }
                if (FieldNameConstant.CpItem.??????FTP?????????.toString().equals(changeItem.getFieldName())) {
                    cpItem.setVideoFtpUsername(changeItem.getOldValue());
                }
                if (FieldNameConstant.CpItem.??????FTP??????.toString().equals(changeItem.getFieldName())) {
                    cpItem.setVideoFtpPassword(changeItem.getOldValue());
                }
                if (FieldNameConstant.CpItem.??????FTP?????????.toString().equals(changeItem.getFieldName())) {
                    cpItem.setVideoFtpRootpath(changeItem.getOldValue());
                }
                if (FieldNameConstant.CpItem.??????FTP????????????.toString().equals(changeItem.getFieldName())) {
                    cpItem.setVideoFtpUploadpath(changeItem.getOldValue());
                }
                if (FieldNameConstant.CpItem.?????????????????????.toString().equals(changeItem.getFieldName())) {
                    cpItem.setVideoOutputGroup(changeItem.getOldValue());
                }
                if (FieldNameConstant.CpItem.??????????????????.toString().equals(changeItem.getFieldName())) {
                    cpItem.setVideoOutputWay(changeItem.getOldValue());
                }
                if (FieldNameConstant.CpItem.??????FTP??????IP.toString().equals(changeItem.getFieldName())) {
                    cpItem.setPictrueFtpIp(changeItem.getOldValue());
                }
                if (FieldNameConstant.CpItem.????????????.toString().equals(changeItem.getFieldName())) {
                    cpItem.setPictrueFtpPort(changeItem.getOldValue());
                }
                if (FieldNameConstant.CpItem.??????FTP?????????.toString().equals(changeItem.getFieldName())) {
                    cpItem.setPictrueFtpUsername(changeItem.getOldValue());
                }
                if (FieldNameConstant.CpItem.??????FTP??????.toString().equals(changeItem.getFieldName())) {
                    cpItem.setPictrueFtpPassword(changeItem.getOldValue());
                }
                if (FieldNameConstant.CpItem.??????FTP?????????.toString().equals(changeItem.getFieldName())) {
                    cpItem.setPictrueFtpRootpath(changeItem.getOldValue());
                }
                if (FieldNameConstant.CpItem.??????FTP????????????.toString().equals(changeItem.getFieldName())) {
                    cpItem.setPictrueFtpUploadpath(changeItem.getOldValue());
                }
                if (FieldNameConstant.CpItem.?????????????????????.toString().equals(changeItem.getFieldName())) {
                    cpItem.setPictrueOutputGroup(changeItem.getOldValue());
                }
                if (FieldNameConstant.CpItem.??????????????????.toString().equals(changeItem.getFieldName())) {
                    cpItem.setPictrueOutputMedia(changeItem.getOldValue());
                }
            }
            comm.cpService.saveOrUpdate(cpItem);
        }
        // ??????????????????Change?????????
        comm.changeServcie.deleteChange(change.getId());
    }
    return resources;
}


public List<Resources> getOperateResourcesListByServiceTagId(Integer serviceTagId){
    List<Integer> status = new ArrayList<Integer>();
    status.add(ResourcesConstant.Status.?????????.toInteger());
    status.add(ResourcesConstant.Status.?????????.toInteger());
    status.add(ResourcesConstant.Status.?????????.toInteger());
    // status.add(ResourcesConstant.Status.?????????.toInteger());
    return resourcesDao.findByServiceTagIdAndStatusInOrderByIdDesc(serviceTagId, status);
}


public List<Resources> getCommitedResourcesListByServiceTagId(Integer serviceTagId){
    List<Integer> status = new ArrayList<Integer>();
    status.add(ResourcesConstant.Status.?????????.toInteger());
    status.add(ResourcesConstant.Status.?????????.toInteger());
    status.add(ResourcesConstant.Status.?????????.toInteger());
    return resourcesDao.findByServiceTagIdAndStatusInOrderByIdDesc(serviceTagId, status);
}


@Transactional(readOnly = false)
public boolean recycleResources(List<Resources> resourcesList,Integer serviceTagId){
    boolean result = false;
    for (Resources resources : resourcesList) {
        resources.setStatus(ResourcesConstant.Status.?????????.toInteger());
        this.saveOrUpdate(resources);
    }
    // TODO ???????????????????????????????????????????????????????????????.
    // ???????????????ID
    String recycleId = Collections3.extractToString(resourcesList, "id", ",");
    List<ComputeItem> computeItems = new ArrayList<ComputeItem>();
    List<StorageItem> storageItems = new ArrayList<StorageItem>();
    List<NetworkElbItem> elbItems = new ArrayList<NetworkElbItem>();
    List<NetworkEipItem> eipItems = new ArrayList<NetworkEipItem>();
    List<NetworkDnsItem> dnsItems = new ArrayList<NetworkDnsItem>();
    List<MonitorMail> monitorMails = new ArrayList<MonitorMail>();
    List<MonitorPhone> monitorPhones = new ArrayList<MonitorPhone>();
    List<MonitorCompute> monitorComputes = new ArrayList<MonitorCompute>();
    List<MonitorElb> monitorElbs = new ArrayList<MonitorElb>();
    List<MdnItem> mdnItems = new ArrayList<MdnItem>();
    List<CpItem> cpItems = new ArrayList<CpItem>();
    this.wrapBasicUntilListByResources(resourcesList, computeItems, storageItems, elbItems, eipItems, dnsItems, monitorComputes, monitorElbs, mdnItems, cpItems);
    String description = comm.redmineUtilService.recycleResourcesRedmineDesc(comm.accountService.getCurrentUser(), computeItems, storageItems, elbItems, eipItems, dnsItems, monitorMails, monitorPhones, monitorComputes, monitorElbs, mdnItems, cpItems);
    // ????????????Issue???Redmine
    Issue issue = new Issue();
    Integer trackerId = RedmineConstant.Tracker.??????.toInteger();
    Tracker tracker = new Tracker(trackerId, RedmineConstant.Tracker.get(trackerId));
    issue.setTracker(tracker);
    issue.setSubject(comm.applyService.generateTitle(comm.accountService.getCurrentUser().getLoginName(), "recycle"));
    issue.setPriorityId(RedmineConstant.Priority.???.toInteger());
    issue.setDescription(description);
    Integer projectId = RedmineConstant.Project.SobeyCloud??????.toInteger();
    RedmineManager mgr = RedmineService.FIRST_REDMINE_ASSIGNEE_REDMINEMANAGER;
    boolean isCreated = RedmineService.createIssue(issue, projectId.toString(), mgr);
    logger.info("--->?????? Redmine isCreated?" + isCreated);
    if (isCreated) {
        // ??????Redmine??????
        result = true;
        Integer assignee = RedmineService.FIRST_REDMINE_ASSIGNEE;
        issue = RedmineService.getIssueBySubject(issue.getSubject(), mgr);
        RedmineIssue redmineIssue = new RedmineIssue();
        redmineIssue.setProjectId(projectId);
        redmineIssue.setTrackerId(issue.getTracker().getId());
        redmineIssue.setSubject(issue.getSubject());
        redmineIssue.setAssignee(assignee);
        redmineIssue.setStatus(RedmineConstant.Status.??????.toInteger());
        redmineIssue.setIssueId(issue.getId());
        redmineIssue.setResourceId(recycleId);
        redmineIssue.setServiceTagId(serviceTagId);
        logger.info("--->?????????resourceId..." + recycleId);
        comm.operateService.saveOrUpdate(redmineIssue);
        // ????????????User
        User assigneeUser = comm.accountService.findUserByRedmineUserId(assignee);
        // ????????????????????????
        comm.templateMailService.sendRecycleResourcesOperateNotificationMail(comm.accountService.getCurrentUser(), computeItems, storageItems, elbItems, eipItems, dnsItems, monitorComputes, monitorElbs, mdnItems, cpItems, assigneeUser);
    }
    return result;
}


public void wrapBasicUntilListByResources(List<Resources> resourcesList,List<ComputeItem> computeItems,List<StorageItem> storageItems,List<NetworkElbItem> elbItems,List<NetworkEipItem> eipItems,List<NetworkDnsItem> dnsItems,List<MonitorCompute> monitorComputes,List<MonitorElb> monitorElbs,List<MdnItem> mdnItems,List<CpItem> cpItems){
    for (Resources resources : resourcesList) {
        Integer serviceType = resources.getServiceType();
        Integer serviceId = resources.getServiceId();
        if (ResourcesConstant.ServiceType.PCS.toInteger().equals(serviceType) || ResourcesConstant.ServiceType.ECS.toInteger().equals(serviceType)) {
            // PCS & ECS
            computeItems.add(comm.computeService.getComputeItem(serviceId));
        } else if (ResourcesConstant.ServiceType.ES3.toInteger().equals(serviceType)) {
            // ES3
            storageItems.add(comm.es3Service.getStorageItem(serviceId));
        } else if (ResourcesConstant.ServiceType.ELB.toInteger().equals(serviceType)) {
            // ELB
            elbItems.add(comm.elbService.getNetworkElbItem(serviceId));
        } else if (ResourcesConstant.ServiceType.EIP.toInteger().equals(serviceType)) {
            // EIP
            eipItems.add(comm.eipService.getNetworkEipItem(serviceId));
        } else if (ResourcesConstant.ServiceType.DNS.toInteger().equals(serviceType)) {
            // DNS
            dnsItems.add(comm.dnsService.getNetworkDnsItem(serviceId));
        } else if (ResourcesConstant.ServiceType.MONITOR_COMPUTE.toInteger().equals(serviceType)) {
            // monitorCompute
            monitorComputes.add(comm.monitorComputeServcie.getMonitorCompute(serviceId));
        } else if (ResourcesConstant.ServiceType.MONITOR_ELB.toInteger().equals(serviceType)) {
            // monitorElb
            monitorElbs.add(comm.monitorElbServcie.getMonitorElb(serviceId));
        } else if (ResourcesConstant.ServiceType.MDN.toInteger().equals(serviceType)) {
            // MDN
            mdnItems.add(comm.mdnService.getMdnItem(serviceId));
        } else if (ResourcesConstant.ServiceType.CP.toInteger().equals(serviceType)) {
            // CP
            cpItems.add(comm.cpService.getCpItem(serviceId));
        }
    }
}


public List<Resources> getResourcesListByServiceTagId(Integer serviceTagId){
    return resourcesDao.findByServiceTagId(serviceTagId);
}


public List<Resources> getCommitingResourcesListByServiceTagId(Integer serviceTagId){
    List<Integer> status = new ArrayList<Integer>();
    status.add(ResourcesConstant.Status.?????????.toInteger());
    return resourcesDao.findByServiceTagIdAndStatusInOrderByIdDesc(serviceTagId, status);
}


public Long getResourcesStatistics(Integer serviceType){
    return (long) resourcesDao.findByServiceTypeAndUserId(serviceType, getCurrentUserId()).size();
}


public Long getResourcesSummaryStatistics(Integer serviceType){
    return (long) resourcesDao.findByServiceType(serviceType).size();
}


public Page<Resources> getSummaryPageable(Map<String,Object> searchParams,int pageNumber,int pageSize){
    PageRequest pageRequest = buildPageRequest(pageNumber, pageSize);
    Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
    Specification<Resources> spec = DynamicSpecifications.bySearchFilter(filters.values(), Resources.class);
    return resourcesDao.findAll(spec, pageRequest);
}


public Resources getResources(Integer id){
    return resourcesDao.findOne(id);
}


@Transactional(readOnly = false)
public void deleteResources(Integer id){
    resourcesDao.delete(id);
}


public void restoreApplication(ComputeItem computeItem,String oldChangeValue){
    // ??????????????????????????????<br>,?????????<br>????????????????????????.
    String[] applications = StringUtils.splitByWholeSeparator(StringUtils.substringBeforeLast(oldChangeValue, "<br>"), "<br>");
    String[] applicationNames = new String[applications.length];
    String[] applicationVersions = new String[applications.length];
    String[] applicationDeployPaths = new String[applications.length];
    if (applications != null && applications.length != 0) {
        for (int i = 0; i < applications.length; i++) {
            String[] apps = StringUtils.split(applications[i], ",");
            for (int j = 0; j < apps.length; j++) {
                applicationNames[i] = apps[0];
                applicationVersions[i] = apps[1];
                applicationDeployPaths[i] = apps[2];
            }
        }
    }
    comm.computeService.updateApplication(computeItem, applicationNames, applicationVersions, applicationDeployPaths);
}


@Transactional(readOnly = false)
public void insertResourcesAndOneCMDBAfterOperate(Apply apply){
    Integer serviceType;
    ServiceTag serviceTag = comm.serviceTagService.saveServiceTag(apply);
    // TODO apply???????????????,???????????????oneCMDB.???????????????????
    // ????????????????????????,?????????oneCMDB????????????????????????????????????.???ES3???compute??????.
    comm.oneCmdbUtilService.saveStorageToOneCMDB(apply.getStorageItems(), serviceTag);
    comm.oneCmdbUtilService.saveComputeToOneCMDB(apply.getComputeItems(), serviceTag);
    comm.oneCmdbUtilService.saveELBToOneCMDB(apply.getNetworkElbItems(), serviceTag);
    comm.oneCmdbUtilService.saveEIPToOneCMDB(apply.getNetworkEipItems(), serviceTag);
    comm.oneCmdbUtilService.saveDNSToOneCMDB(apply.getNetworkDnsItems(), serviceTag);
    // Compute
    for (ComputeItem compute : apply.getComputeItems()) {
        // ?????? PCS ??? ECS
        serviceType = ComputeConstant.ComputeType.PCS.toInteger().equals(compute.getComputeType()) ? ResourcesConstant.ServiceType.PCS.toInteger() : ResourcesConstant.ServiceType.ECS.toInteger();
        this.saveAndWrapResources(apply, serviceType, serviceTag, compute.getId(), compute.getIdentifier(), compute.getInnerIp());
    }
    // StorageItem
    for (StorageItem storageItem : apply.getStorageItems()) {
        this.saveAndWrapResources(apply, ResourcesConstant.ServiceType.ES3.toInteger(), serviceTag, storageItem.getId(), storageItem.getIdentifier(), IpPoolConstant.DEFAULT_IPADDRESS);
    }
    // ELB
    for (NetworkElbItem networkElbItem : apply.getNetworkElbItems()) {
        this.saveAndWrapResources(apply, ResourcesConstant.ServiceType.ELB.toInteger(), serviceTag, networkElbItem.getId(), networkElbItem.getIdentifier(), networkElbItem.getVirtualIp());
    }
    // EIP
    for (NetworkEipItem networkEipItem : apply.getNetworkEipItems()) {
        this.saveAndWrapResources(apply, ResourcesConstant.ServiceType.EIP.toInteger(), serviceTag, networkEipItem.getId(), networkEipItem.getIdentifier(), networkEipItem.getIpAddress());
    }
    // DNS
    for (NetworkDnsItem networkDnsItem : apply.getNetworkDnsItems()) {
        this.saveAndWrapResources(apply, ResourcesConstant.ServiceType.DNS.toInteger(), serviceTag, networkDnsItem.getId(), networkDnsItem.getIdentifier(), IpPoolConstant.DEFAULT_IPADDRESS);
    }
    // ????????????
    for (MonitorCompute monitorCompute : apply.getMonitorComputes()) {
        this.saveAndWrapResources(apply, ResourcesConstant.ServiceType.MONITOR_COMPUTE.toInteger(), serviceTag, monitorCompute.getId(), monitorCompute.getIdentifier(), monitorCompute.getIpAddress());
    }
    // Elb??????
    for (MonitorElb monitorElb : apply.getMonitorElbs()) {
        this.saveAndWrapResources(apply, ResourcesConstant.ServiceType.MONITOR_ELB.toInteger(), serviceTag, monitorElb.getId(), monitorElb.getIdentifier(), monitorElb.getNetworkElbItem().getVirtualIp());
    }
    // MDN
    for (MdnItem mdnItem : apply.getMdnItems()) {
        this.saveAndWrapResources(apply, ResourcesConstant.ServiceType.MDN.toInteger(), serviceTag, mdnItem.getId(), mdnItem.getIdentifier(), IpPoolConstant.DEFAULT_IPADDRESS);
    }
    // CP
    for (CpItem cpItem : apply.getCpItems()) {
        this.saveAndWrapResources(apply, ResourcesConstant.ServiceType.CP.toInteger(), serviceTag, cpItem.getId(), cpItem.getIdentifier(), IpPoolConstant.DEFAULT_IPADDRESS);
    }
}


public Page<Resources> getResourcesPageable(Map<String,Object> searchParams,int pageNumber,int pageSize){
    PageRequest pageRequest = buildPageRequest(pageNumber, pageSize);
    Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
    filters.put("resources.user.id", new SearchFilter("user.id", Operator.EQ, getCurrentUserId()));
    Specification<Resources> spec = DynamicSpecifications.bySearchFilter(filters.values(), Resources.class);
    return resourcesDao.findAll(spec, pageRequest);
}


public void restoreELBPort(NetworkElbItem networkElbItem,String oldChangeValue){
    this.restorePort(networkElbItem, null, oldChangeValue);
}


@Transactional(readOnly = false)
public Resources saveOrUpdate(Resources resources){
    return resourcesDao.save(resources);
}


}