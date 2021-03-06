package com.sobey.cmop.mvc.service.iaas;
 import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.sobey.cmop.mvc.comm.BaseSevcie;
import com.sobey.cmop.mvc.constant.NetworkConstant;
import com.sobey.cmop.mvc.constant.ResourcesConstant;
import com.sobey.cmop.mvc.dao.NetworkDnsItemDao;
import com.sobey.cmop.mvc.entity.Apply;
import com.sobey.cmop.mvc.entity.Change;
import com.sobey.cmop.mvc.entity.NetworkDnsItem;
import com.sobey.cmop.mvc.entity.NetworkEipItem;
import com.sobey.cmop.mvc.entity.Resources;
import com.sobey.cmop.mvc.entity.ServiceTag;
@Service
@Transactional(readOnly = true)
public class DnsService extends BaseSevcie{

 private  Logger logger;

@Resource
 private  NetworkDnsItemDao networkDnsItemDao;


public NetworkDnsItem getNetworkDnsItem(Integer id){
    return networkDnsItemDao.findOne(id);
}


@Transactional(readOnly = false)
public void updateDNSToApply(NetworkDnsItem networkDnsItem,String domainName,Integer domainType,String cnameDomain,String[] eipIds){
    networkDnsItem.setDomainType(domainType);
    networkDnsItem.setDomainName(domainName);
    List<NetworkEipItem> networkEipItemList = new ArrayList<NetworkEipItem>();
    if (NetworkConstant.DomainType.CNAME.toInteger().equals(domainType)) {
        networkDnsItem.setNetworkEipItemList(networkEipItemList);
        networkDnsItem.setCnameDomain(cnameDomain);
    } else {
        if (eipIds != null) {
            for (int i = 0; i < eipIds.length; i++) {
                networkEipItemList.add(comm.eipService.getNetworkEipItem(Integer.valueOf(eipIds[i])));
            }
        }
        networkDnsItem.setNetworkEipItemList(networkEipItemList);
        networkDnsItem.setCnameDomain(cnameDomain);
    }
    this.saveOrUpdate(networkDnsItem);
}


@Transactional(readOnly = false)
public void saveDNSToApply(Integer applyId,String[] domainNames,String[] domainTypes,String[] eipIds){
    Apply apply = comm.applyService.getApply(applyId);
    for (int i = 0; i < domainNames.length; i++) {
        NetworkDnsItem networkDnsItem = new NetworkDnsItem();
        String identifier = comm.applyService.generateIdentifier(ResourcesConstant.ServiceType.DNS.toInteger());
        Integer domainType = Integer.valueOf(domainTypes[i]);
        // ??????????????????. GSLB,A ???????????? DNS???EIP????????????. CNAME??????????????????.
        if (eipIds != null && eipIds.length > 0) {
            if (NetworkConstant.DomainType.CNAME.toInteger().equals(domainType)) {
                networkDnsItem.setCnameDomain(eipIds[i]);
            } else {
                String[] eipIdArray = StringUtils.split(eipIds[i], "-");
                List<NetworkEipItem> networkEipItemList = new ArrayList<NetworkEipItem>();
                for (int j = 0; j < eipIdArray.length; j++) {
                    networkEipItemList.add(comm.eipService.getNetworkEipItem(Integer.valueOf(eipIdArray[j])));
                }
                networkDnsItem.setNetworkEipItemList(networkEipItemList);
            }
        }
        networkDnsItem.setIdentifier(identifier);
        networkDnsItem.setApply(apply);
        networkDnsItem.setDomainName(domainNames[i]);
        networkDnsItem.setDomainType(domainType);
        this.saveOrUpdate(networkDnsItem);
    }
}


@Transactional(readOnly = false)
public void deleteNetworkDnsItem(Integer id){
    networkDnsItemDao.delete(id);
}


@Transactional(readOnly = false)
public void saveResourcesByDns(Resources resources,Integer serviceTagId,String domainName,Integer domainType,String cnameDomain,String[] eipIds,String changeDescription){
    /* ?????????????????????Resources???????????????Change. */
    Change change = comm.changeServcie.saveOrUpdateChangeByResources(resources, changeDescription);
    NetworkDnsItem networkDnsItem = this.getNetworkDnsItem(resources.getServiceId());
    /* ???????????????????????????????????????. */
    boolean isChange = comm.compareResourcesService.compareDns(resources, change, networkDnsItem, domainName, domainType, cnameDomain, eipIds);
    ServiceTag serviceTag = comm.serviceTagService.getServiceTag(serviceTagId);
    // ???????????????????????????,????????????.??????????????????????????????:????????????,??????????????????,???????????????????????????.
    if (isChange) {
        serviceTag.setStatus(ResourcesConstant.Status.?????????.toInteger());
        resources.setStatus(ResourcesConstant.Status.?????????.toInteger());
    }
    resources.setServiceTag(serviceTag);
    comm.serviceTagService.saveOrUpdate(serviceTag);
    // ??????????????????,???????????????????????????ID????????????????????????NetworkEipItem.
    networkDnsItem.setDomainType(domainType);
    networkDnsItem.setDomainName(domainName);
    List<NetworkEipItem> networkEipItemList = new ArrayList<NetworkEipItem>();
    if (NetworkConstant.DomainType.CNAME.toInteger().equals(domainType)) {
        networkDnsItem.setNetworkEipItemList(networkEipItemList);
        networkDnsItem.setCnameDomain(cnameDomain);
    } else {
        if (eipIds != null) {
            for (int i = 0; i < eipIds.length; i++) {
                networkEipItemList.add(comm.eipService.getNetworkEipItem(Integer.valueOf(eipIds[i])));
            }
        }
        networkDnsItem.setNetworkEipItemList(networkEipItemList);
        networkDnsItem.setCnameDomain(cnameDomain);
    }
    this.saveOrUpdate(networkDnsItem);
    // ??????resources
    comm.resourcesService.saveOrUpdate(resources);
}


@Transactional(readOnly = false)
public NetworkDnsItem saveOrUpdate(NetworkDnsItem networkDnsItem){
    return networkDnsItemDao.save(networkDnsItem);
}


}