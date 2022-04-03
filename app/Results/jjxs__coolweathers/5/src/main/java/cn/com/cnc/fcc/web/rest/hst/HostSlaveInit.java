package cn.com.cnc.fcc.web.rest.hst;
 import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import javax.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.TypeReference;
import cn.com.cnc.fcc.config.ApplicationProperties;
import cn.com.cnc.fcc.domain.HstServer;
import cn.com.cnc.fcc.domain.HstServerInfo;
import cn.com.cnc.fcc.domain.HstServerInfoDetails;
import cn.com.cnc.fcc.domain.PapiTokenSlave;
import cn.com.cnc.fcc.domain.RbacUser;
import cn.com.cnc.fcc.repository.HstServerInfoDetailsRepository;
import cn.com.cnc.fcc.repository.HstServerInfoRepository;
import cn.com.cnc.fcc.repository.PapiTokenSlaveRepository;
import cn.com.cnc.fcc.repository.RbacUserRepository;
import cn.com.cnc.fcc.web.http.HttpRequest;
import cn.com.cnc.fcc.web.rest.HostOrSlaveRequestResource;
import cn.com.cnc.fcc.web.task.GetPublicApi;
import io.github.jhipster.config.JHipsterProperties;
@Component
public class HostSlaveInit {

 private  Logger log;

@Autowired
 private  ApplicationProperties applicationProperties;

@Autowired
 private  HstServerInfoRepository hstServerInfoRepository;

@Autowired
 private  HstServerInfoDetailsRepository hstServerInfoDetailsRepository;

@Autowired
 private  JHipsterProperties jHipsterProperties;

@Autowired
 private  RbacUserRepository rbacUserRepository;

@Autowired
 private  PapiTokenSlaveRepository papiTokenSlaveRepository;

@Autowired
 private  HttpRequest httpRequest;

@Autowired
 private  GetPublicApi getPublicApi;

@Autowired
 private  HostOrSlaveRequestResource hostOrSlaveRequestResource;

 private  String HST_STARTING_MESSAGE;

 private  String HST_ERROR;


@PostConstruct
public void Init(){
    log.info(HST_STARTING_MESSAGE);
    boolean isHost = false;
    if (HostSlaveConstants.HOST.equals(applicationProperties.getHostSlave().getHostOrSlave())) {
        isHost = true;
    } else if (HostSlaveConstants.SLAVE.equals(applicationProperties.getHostSlave().getHostOrSlave())) {
        isHost = false;
    } else {
        throw new IllegalArgumentException(String.format(HST_ERROR, "Host Or Slave's falg is uncorrect"));
    }
    if (isHost) {
        String message = this.saveHost();
        if (!"".equals(message)) {
            throw new IllegalArgumentException(message);
        }
    } else {
        HostSlaveTokenTask hostSlaveTokenTask = new HostSlaveTokenTask();
        String message = "";
        try {
            message = this.saveSlave();
            if (!"".equals(message)) {
                hostSlaveTokenTask.stopTask();
                throw new IllegalArgumentException(message);
            }
            // 从服务器信息保存
            message = this.saveSlaveInfomation();
            if (!"".equals(message)) {
                hostSlaveTokenTask.stopTask();
                throw new IllegalArgumentException(message);
            }
            // 同步Rbac
            message = this.getAndSaveHostRbacUser();
            if (!"".equals(message)) {
                hostSlaveTokenTask.stopTask();
                throw new IllegalArgumentException(message);
            }
            // 启动更新Token定时任务
            hostSlaveTokenTask.initTask(jHipsterProperties.getAsync().getCorePoolSize());
            hostSlaveTokenTask.stopTask();
            hostSlaveTokenTask.startTask();
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
    }
}


public String saveSlaveInfomation(){
    String hostUrl = applicationProperties.getHostSlave().getHostUrl();
    String hostNode = applicationProperties.getHostSlave().getHostNode();
    String slaveNode = applicationProperties.getHostSlave().getSlaveNode();
    String retMessage = "";
    try {
        if (!this.checkUrl(hostUrl)) {
            retMessage = String.format(HST_ERROR, "Host RegisterUrl is not correct");
        }
        List<HstServerInfo> hstServerInfos = hstServerInfoRepository.findByNodeId(hostNode);
        if (hstServerInfos.size() > 1) {
            return String.format(HST_ERROR, "Slave's Host Infomation is not Unique");
        }
        boolean isToken = this.getPublicApi.GetToken(hostUrl);
        if (!isToken) {
            return String.format(HST_ERROR, "Get Token Error");
        }
        List<HstServer> postHstServer = hstServerInfoRepository.findByNodeId(slaveNode, 1);
        String ret = this.httpRequest.getData(hostUrl + "/hst-api/host/hst-server-info-details", HttpRequest.HttpMode.POST, JSON.toJSONString(postHstServer), this.getHostToken());
        List<HstServer> hstServers = JSON.parseArray(ret, HstServer.class);
        if (hstServers == null || hstServers.size() <= 0) {
            return String.format(HST_ERROR, "Host's Host Infomation is not Exist");
        }
        List<HstServer> hostHstServer = hstServerInfoRepository.findByNodeId(hostNode, 0);
        retMessage = hostOrSlaveRequestResource.saveInfomation(hostHstServer, hostUrl, hostNode, null, hstServers);
        if (!"".equals(retMessage)) {
            throw new IllegalArgumentException(retMessage);
        }
        // Map<String, Integer> mapInfoDetails = new HashMap<>();
        // List<HstServerInfoDetails> hstServerInfoDetailss = new ArrayList<>();
        // List<HstServerInfo> hostHstServerInfos = new ArrayList<>();
        // 
        // for(HstServer hstServer: hstServers) {
        // if (!mapInfoDetails.containsKey(hstServer.getHstServerInfo().getId().toString())) {
        // mapInfoDetails.put(hstServer.getHstServerInfo().getId().toString(), 1);
        // hostHstServerInfos.add(hstServer.getHstServerInfo());
        // }
        // 
        // hstServerInfoDetailss.add(hstServer.getHstServerInfoDetails());
        // }
        // 
        // if (mapInfoDetails.size() > 1) {
        // return String.format(HST_ERROR, "Host's Host Infomation is not Unique");
        // }
        // 
        // hstServerInfoRepository.save(hostHstServerInfos.get(0));
        // Long infoId = hstServerInfoRepository.getHostInfoMaxId();
        // 
        // for(HstServerInfoDetails hstServerInfoDetails: hstServerInfoDetailss) {
        // hstServerInfoDetails.setInfoId(infoId);
        // }
        // 
        // hstServerInfoDetailsRepository.saveAll(hstServerInfoDetailss);
        return retMessage;
    } catch (Exception ex) {
        return ex.getMessage();
    }
}


public Map<String,String> getHostToken(){
    PapiTokenSlave papiTokenSlave = papiTokenSlaveRepository.findByType("00");
    if (papiTokenSlave == null || papiTokenSlave.getApiToken() == null || papiTokenSlave.getApiToken() == "") {
        throw new IllegalArgumentException(String.format(HST_ERROR, "Slave Token Infomation is not Exist"));
    }
    Map<String, String> parm = new HashMap<String, String>();
    parm.put("Authorization", "Bearer " + papiTokenSlave.getApiToken());
    return parm;
}


public String saveHost(){
    String hostUrl = applicationProperties.getHostSlave().getHostUrl();
    String hostNode = applicationProperties.getHostSlave().getHostNode();
    String retMessage = "";
    try {
        if (!this.checkUrl(hostUrl)) {
            retMessage = String.format(HST_ERROR, "RegisterUrl is not correct");
        }
        List<HstServer> hstServer = hstServerInfoRepository.findHostServer(0);
        retMessage = hostOrSlaveRequestResource.saveInfomation(hstServer, hostUrl, hostNode, null, null);
        if (!"".equals(retMessage)) {
            throw new IllegalArgumentException(retMessage);
        }
        return retMessage;
    } catch (Exception ex) {
        return ex.getMessage();
    }
}


public String saveSlave(){
    String slaveUrl = applicationProperties.getHostSlave().getSlaveUrl();
    String slaveNode = applicationProperties.getHostSlave().getSlaveNode();
    String hostNode = applicationProperties.getHostSlave().getHostNode();
    String retMessage = "";
    try {
        if (!this.checkUrl(slaveUrl)) {
            retMessage = String.format(HST_ERROR, "Slave RegisterUrl is not correct");
        }
        List<HstServer> hstServer = hstServerInfoRepository.findHostServer(1);
        retMessage = hostOrSlaveRequestResource.saveInfomation(hstServer, slaveUrl, slaveNode, hostNode, null);
        if (!"".equals(retMessage)) {
            throw new IllegalArgumentException(retMessage);
        }
        return retMessage;
    } catch (Exception ex) {
        return ex.getMessage();
    }
}


public String getAndSaveHostRbacUser(){
    String retMessage = "";
    try {
        String hostNode = applicationProperties.getHostSlave().getHostNode();
        List<HstServerInfo> hstServerInfos = hstServerInfoRepository.findByNodeId(hostNode);
        String url = hstServerInfos.get(0).getNodeUrl() + "/hst-api/host/rbac-users";
        int retDelete = rbacUserRepository.deleteUserNonExistDefault();
        if (retDelete < 0) {
            throw new IllegalArgumentException(String.format(HST_ERROR, "Slave's User Infomation Can not Delete"));
        }
        String ret = this.httpRequest.getData(url, HttpRequest.HttpMode.GET, null, this.getHostToken());
        List<RbacUser> rbacUsers = JSONArray.parseObject(ret, new TypeReference<List<RbacUser>>() {
        });
        // List<RbacUser> rbacUsers = new ArrayList<>();
        // JSONArray jsonArray = JSON.parseArray(ret);
        // 
        // for(Object o: jsonArray) {
        // 
        // JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(o));
        // 
        // rbacUsers.add(JSONObject.parseObject(JSON.toJSONString(jsonObject), new TypeReference<RbacUser>() {}));
        // }
        this.rbacUserRepository.saveAll(rbacUsers);
        return retMessage;
    } catch (Exception ex) {
        return ex.getMessage();
    }
}


public boolean checkUrl(String url){
    url = url.toLowerCase();
    String regex = "^((https|http|ftp|rtsp|mms)?://)" + "?(([0-9a-z_!~*'().&=+$%-]+: )?[0-9a-z_!~*'().&=+$%-]+@)?" + "(([0-9]{1,3}\\.){3}[0-9]{1,3}" + "|" + "([0-9a-z_!~*'()-]+\\.)*" + "([0-9a-z][0-9a-z-]{0,61})?[0-9a-z]\\." + "[a-z]{2,6})" + "(:[0-9]{1,4})?" + "((/?)|" + "(/[0-9a-z_!~*'().;?:@&=+$,%#-]+)+/?)$";
    return Pattern.matches(regex, url);
}


}