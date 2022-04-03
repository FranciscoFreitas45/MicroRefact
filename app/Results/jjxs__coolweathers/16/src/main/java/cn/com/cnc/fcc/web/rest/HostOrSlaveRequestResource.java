package cn.com.cnc.fcc.web.rest;
 import com.codahale.metrics.annotation.Timed;
import cn.com.cnc.fcc.domain.HstServer;
import cn.com.cnc.fcc.domain.HstServerInfo;
import cn.com.cnc.fcc.domain.HstServerInfoDetails;
import cn.com.cnc.fcc.domain.RbacUser;
import cn.com.cnc.fcc.repository.HstServerInfoDetailsRepository;
import cn.com.cnc.fcc.repository.HstServerInfoRepository;
import cn.com.cnc.fcc.repository.RbacUserRepository;
import cn.com.cnc.fcc.service.util.DateUtil;
import cn.com.cnc.fcc.web.rest.util.AppUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@RestController
@RequestMapping("/hst-api/host")
public class HostOrSlaveRequestResource {

 private  Logger log;

 private  RbacUserRepository rbacUserRepository;

 private  HstServerInfoRepository hstServerInfoRepository;

 private  DateUtil dateUtil;

 private  HstServerInfoDetailsRepository hstServerInfoDetailsRepository;

 private  String HST_ERROR;

public HostOrSlaveRequestResource(RbacUserRepository rbacUserRepository, HstServerInfoRepository hstServerInfoRepository, DateUtil dateUtil, HstServerInfoDetailsRepository hstServerInfoDetailsRepository) {
    this.rbacUserRepository = rbacUserRepository;
    this.hstServerInfoRepository = hstServerInfoRepository;
    this.dateUtil = dateUtil;
    this.hstServerInfoDetailsRepository = hstServerInfoDetailsRepository;
}
public Long saveHstServer(List<HstServerInfo> hstServerInfos,String localUrl,String localNode,ZonedDateTime nowDate,String hostNodeInfo){
    Long infoId = -1L;
    if (hstServerInfos.size() == 1) {
        hstServerInfos.get(0).setNodeUrl(localUrl);
        hstServerInfos.get(0).setUpdDateTime(nowDate);
        hstServerInfos.get(0).setUpdOperCd("InitSystem");
        hstServerInfos.get(0).setUpdProgarmCd("HostOrSlaveRR");
        hstServerInfos.get(0).setTriggerDateTime(nowDate);
        hstServerInfoRepository.save(hstServerInfos.get(0));
        infoId = hstServerInfos.get(0).getId();
    } else if (hstServerInfos.size() == 0) {
        HstServerInfo hstServerInfo = new HstServerInfo();
        hstServerInfo.setNodeId(localNode);
        hstServerInfo.setNodeUrl(localUrl);
        if (hostNodeInfo == null) {
            hstServerInfo.setpNodeId(localNode);
            hstServerInfo.setHostSlaveFlag(0);
        } else {
            hstServerInfo.setpNodeId(hostNodeInfo);
            hstServerInfo.setHostSlaveFlag(1);
        }
        hstServerInfo.setNodeJoinTime(nowDate);
        hstServerInfo.setStopFlag(0);
        hstServerInfo.setDelFlag(0);
        hstServerInfo.setInsDateTime(nowDate);
        hstServerInfo.setInsOperCd("InitSystem");
        hstServerInfo.setInsProgarmCd("HostOrSlaveRR");
        hstServerInfo.setTriggerDateTime(nowDate);
        hstServerInfos.add(hstServerInfo);
        hstServerInfoRepository.save(hstServerInfos.get(0));
        infoId = hstServerInfoRepository.getHostInfoMaxId();
    }
    return infoId;
}


public String saveHstServerDetails(List<HstServerInfoDetails> hstServerInfoDetailss,Long infoId,ZonedDateTime nowDate,List<HstServerInfoDetails> postHstServerInfoDetailss){
    String hostName = "";
    String ipAddress = "";
    String osUser = "";
    String osName = "";
    String osArch = "";
    String osVersion = "";
    String retMessage = "";
    if (postHstServerInfoDetailss == null) {
        hostName = AppUtils.getHostName();
        ipAddress = AppUtils.getIpAddress();
        osUser = System.getProperty("user.name");
        osName = System.getProperty("os.name");
        osArch = System.getProperty("os.arch");
        osVersion = System.getProperty("os.version");
    } else {
        hostName = postHstServerInfoDetailss.get(0).getHostName();
        ipAddress = postHstServerInfoDetailss.get(0).getIpAddress();
        osUser = postHstServerInfoDetailss.get(0).getOsUser();
        osName = postHstServerInfoDetailss.get(0).getOsName();
        osArch = postHstServerInfoDetailss.get(0).getOsArch();
        osVersion = postHstServerInfoDetailss.get(0).getOsVersion();
    }
    Map<String, Integer> mapInfoDetails = new HashMap<>();
    mapInfoDetails.put(infoId + hostName + ipAddress + osUser + osName + osVersion + osArch, 1);
    for (HstServerInfoDetails hstServerInfoDetails : hstServerInfoDetailss) {
        if (!mapInfoDetails.containsKey(hstServerInfoDetails.getInfoId().toString() + hstServerInfoDetails.getHostName().toString() + hstServerInfoDetails.getIpAddress().toString() + hstServerInfoDetails.getOsUser().toString() + hstServerInfoDetails.getOsName().toString() + hstServerInfoDetails.getOsVersion().toString() + hstServerInfoDetails.getOsArch().toString())) {
            mapInfoDetails.put(hstServerInfoDetails.getInfoId().toString() + hstServerInfoDetails.getHostName().toString() + hstServerInfoDetails.getIpAddress().toString() + hstServerInfoDetails.getOsUser().toString() + hstServerInfoDetails.getOsName().toString() + hstServerInfoDetails.getOsVersion().toString() + hstServerInfoDetails.getOsArch().toString(), 1);
        }
    }
    if (hstServerInfoDetailss.size() > 1) {
        retMessage = String.format(HST_ERROR, "Host Infomation Details is not Unique");
    } else if (hstServerInfoDetailss.size() <= 0) {
        HstServerInfoDetails hstServerInfoDetails = new HstServerInfoDetails();
        hstServerInfoDetails.setInfoId(infoId);
        hstServerInfoDetails.setHostName(hostName);
        hstServerInfoDetails.setIpAddress(ipAddress);
        hstServerInfoDetails.setOsName(osName);
        hstServerInfoDetails.setOsVersion(osVersion);
        hstServerInfoDetails.setOsUser(osUser);
        hstServerInfoDetails.setOsArch(osArch);
        hstServerInfoDetails.setStopFlag(0);
        hstServerInfoDetails.setDelFlag(0);
        hstServerInfoDetails.setInsDateTime(nowDate);
        hstServerInfoDetails.setInsOperCd("InitSystem");
        hstServerInfoDetails.setInsProgarmCd("HostOrSlaveRR");
        hstServerInfoDetails.setTriggerDateTime(nowDate);
        hstServerInfoDetailss.add(hstServerInfoDetails);
        hstServerInfoDetailsRepository.save(hstServerInfoDetailss.get(0));
    } else if (hstServerInfoDetailss.size() == 1 && mapInfoDetails.size() > 1) {
        hstServerInfoDetailss.get(0).setStopFlag(1);
        hstServerInfoDetailss.get(0).setDelFlag(1);
        hstServerInfoDetailss.get(0).setDelOperCd("InitSystem");
        hstServerInfoDetailss.get(0).setDelDateTime(nowDate);
        hstServerInfoDetailss.get(0).setDelProgarmCd("HostOrSlaveRR");
        hstServerInfoDetailss.get(0).setTriggerDateTime(nowDate);
        HstServerInfoDetails hstServerInfoDetails = new HstServerInfoDetails();
        hstServerInfoDetails.setInfoId(infoId);
        hstServerInfoDetails.setHostName(hostName);
        hstServerInfoDetails.setIpAddress(ipAddress);
        hstServerInfoDetails.setOsName(osName);
        hstServerInfoDetails.setOsVersion(osVersion);
        hstServerInfoDetails.setOsUser(osUser);
        hstServerInfoDetails.setOsArch(osArch);
        hstServerInfoDetails.setStopFlag(0);
        hstServerInfoDetails.setDelFlag(0);
        hstServerInfoDetails.setInsDateTime(nowDate);
        hstServerInfoDetails.setInsOperCd("InitSystem");
        hstServerInfoDetails.setInsProgarmCd("HostOrSlaveRR");
        hstServerInfoDetails.setTriggerDateTime(nowDate);
        hstServerInfoDetailss.add(hstServerInfoDetails);
        hstServerInfoDetailsRepository.save(hstServerInfoDetailss.get(0));
        hstServerInfoDetailsRepository.save(hstServerInfoDetails);
    } else if (hstServerInfoDetailss.size() == 1 && mapInfoDetails.size() == 1) {
        hstServerInfoDetailss.get(0).setUpdDateTime(nowDate);
        hstServerInfoDetailss.get(0).setUpdOperCd("InitSystem");
        hstServerInfoDetailss.get(0).setUpdProgarmCd("HostOrSlaveRR");
        hstServerInfoDetailss.get(0).setTriggerDateTime(nowDate);
        hstServerInfoDetailsRepository.save(hstServerInfoDetailss.get(0));
    }
    return retMessage;
}


public String saveInfomation(List<HstServer> hstServers,String localUrl,String localNode,String hostNodeInfo,List<HstServer> postHstServers){
    // Map<String, Integer> mapCheck = new HashMap<>();
    // List<HstServerInfoDetails> hstServerInfoDetailss = new ArrayList<>();
    // List<HstServerInfo> hostHstServerInfos = new ArrayList<>();
    // 
    // for(HstServer hstServer: hstServers) {
    // if (!mapCheck.containsKey(hstServer.getHstServerInfo().getId().toString())) {
    // mapCheck.put(hstServer.getHstServerInfo().getId().toString(), 1);
    // hostHstServerInfos.add(hstServer.getHstServerInfo());
    // }
    // 
    // hstServerInfoDetailss.add(hstServer.getHstServerInfoDetails());
    // }
    // 
    // if (mapCheck.size() > 1) {
    // return String.format(HST_ERROR, "Host's Host Infomation is not Unique");
    // }
    if (hstServers.size() > 1) {
        return String.format(HST_ERROR, "All Infomation is not Unique");
    }
    List<HstServerInfoDetails> hstServerInfoDetailss = new ArrayList<>();
    List<HstServerInfo> hostHstServerInfos = new ArrayList<>();
    for (HstServer hstServer : hstServers) {
        hostHstServerInfos.add(hstServer.getHstServerInfo());
        hstServerInfoDetailss.add(hstServer.getHstServerInfoDetails());
    }
    ZonedDateTime nowDate = dateUtil.getDBNowDate();
    String retMessage = "";
    Long infoId = this.saveHstServer(hostHstServerInfos, localUrl, localNode, nowDate, hostNodeInfo);
    if (postHstServers != null) {
        List<HstServerInfoDetails> postHstServerInfoDetailss = new ArrayList<>();
        List<HstServerInfo> postHostHstServerInfos = new ArrayList<>();
        for (HstServer postHstServer : postHstServers) {
            postHostHstServerInfos.add(postHstServer.getHstServerInfo());
            postHstServerInfoDetailss.add(postHstServer.getHstServerInfoDetails());
        }
        retMessage = this.saveHstServerDetails(hstServerInfoDetailss, infoId, nowDate, postHstServerInfoDetailss);
    } else {
        retMessage = this.saveHstServerDetails(hstServerInfoDetailss, infoId, nowDate, null);
    }
    return retMessage;
}


@PostMapping("/hst-server-info-details")
@Timed
public List<HstServer> getHostServerInfomationAndDetails(List<HstServer> hstServers){
    log.debug("REST request to get Host server Infomation and Details");
    List<HstServer> localHstServer = hstServerInfoRepository.findByNodeId(hstServers.get(0).getHstServerInfo().getNodeId(), 1);
    this.saveInfomation(localHstServer, hstServers.get(0).getHstServerInfo().getNodeUrl(), hstServers.get(0).getHstServerInfo().getNodeId(), hstServers.get(0).getHstServerInfo().getpNodeId(), hstServers);
    return hstServerInfoRepository.findHostServer(0);
}


@GetMapping("/rbac-users")
@Timed
public List<RbacUser> getAllRbacUsers(){
    log.debug("REST request to get all RbacUsers");
    return rbacUserRepository.findAllNonExistDefault();
}


}