package DTO;
 import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.GenericGenerator;
import com.ukefu.util.UKTools;
import com.ukefu.webim.util.OnlineUserUtils;
import com.ukefu.webim.util.server.message.SessionConfigItem;
public class SessionConfig {

 private  long serialVersionUID;

 private  String id;

 private  String orgi;

 private  Date createtime;

 private  String creater;

 private  String username;

 private  String name;

 private  int maxuser;

 private  int initmaxuser;

 private  String sessionmsg;

 private  String distribution;

 private  boolean lastagent;

 private  boolean sessiontimeout;

 private  int timeout;

 private  String timeoutmsg;

 private  boolean resessiontimeout;

 private  int retimeout;

 private  String retimeoutmsg;

 private  boolean satisfaction;

 private  String satisftext;

 private  boolean multisatisf;

 private  String noagentmsg;

 private  String agentbusymsg;

 private  String transmsg;

 private  boolean enabletransmsg;

 private  boolean agentautoleave;

 private  String successmsg;

 private  String finessmsg;

 private  boolean tipagent;

 private  String tipagenticon;

 private  String tipagenttitle;

 private  boolean agentreplaytimeout;

 private  int agenttimeout;

 private  String agenttimeoutmsg;

 private  boolean agentctrlenter;

 private  boolean ctrlenter;

 private  boolean enablequick;

 private  boolean otherssl;

 private  boolean otherquickplay;

 private  String oqrsearchurl;

 private  String oqrsearchinput;

 private  String oqrsearchoutput;

 private  String oqrdetailurl;

 private  String oqrdetailinput;

 private  String oqrdetailoutput;

 private  boolean hourcheck;

 private  String workinghours;

 private  String notinwhmsg;

 private  boolean servicetimeoutlimit;

 private  int servicetimeout;

 private  boolean quality;

 private  String qualityscore;

 private  boolean quene;

 private  int quenetimeout;

 private  String quenetimeoutmsg;

 private  String servicename;

 private  String servicedic;

 private  boolean servicekind;

 private  boolean enablersession;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://6";


public String getRetimeoutmsg(){
    return retimeoutmsg;
}


public String getOqrdetailoutput(){
    return oqrdetailoutput;
}


public String getWorkinghours(){
    return workinghours;
}


public String getSuccessmsg(){
    return successmsg;
}


public String getTimeoutmsg(){
    return timeoutmsg;
}


public String getNoagentmsg(){
    return noagentmsg;
}


public String getOqrdetailurl(){
    return oqrdetailurl;
}


public String getQualityscore(){
    return qualityscore;
}


public String getFinessmsg(){
    return finessmsg;
}


public String getOqrdetailinput(){
    return oqrdetailinput;
}


public String getTipagenticon(){
    return tipagenticon;
}


public String getDistribution(){
    return distribution;
}


public int getInitmaxuser(){
    return initmaxuser;
}


public String getSatisftext(){
    return satisftext;
}


public String getUsername(){
    return username;
}


public String getOqrsearchinput(){
    return oqrsearchinput;
}


public Date getCreatetime(){
    return createtime;
}


public int getQuenetimeout(){
    return quenetimeout;
}


public int getTimeout(){
    return timeout;
}


public int getAgenttimeout(){
    return agenttimeout;
}


@Transient
public List<SessionConfigItem> getConfig(){
    List<SessionConfigItem> sessionConfigItemList = null;
    if (!StringUtils.isBlank(this.getWorkinghours())) {
        try {
            sessionConfigItemList = OnlineUserUtils.objectMapper.readValue(this.getWorkinghours(), UKTools.getCollectionType(ArrayList.class, SessionConfigItem.class));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    return sessionConfigItemList;
}


public String getSessionmsg(){
    return sessionmsg;
}


public int getRetimeout(){
    return retimeout;
}


public String getQuenetimeoutmsg(){
    return quenetimeoutmsg;
}


public String getServicename(){
    return servicename;
}


public String getTransmsg(){
    return transmsg;
}


public String getName(){
    return name;
}


public String getOqrsearchoutput(){
    return oqrsearchoutput;
}


public String getAgenttimeoutmsg(){
    return agenttimeoutmsg;
}


public int getServicetimeout(){
    return servicetimeout;
}


public String getNotinwhmsg(){
    return notinwhmsg;
}


public String getAgentbusymsg(){
    return agentbusymsg;
}


public String getTipagenttitle(){
    return tipagenttitle;
}


@Id
@Column(length = 32)
@GeneratedValue(generator = "system-uuid")
@GenericGenerator(name = "system-uuid", strategy = "uuid")
public String getId(){
    return id;
}


public int getMaxuser(){
    return maxuser;
}


public String getCreater(){
    return creater;
}


public String getServicedic(){
    return servicedic;
}


public String getOqrsearchurl(){
    return oqrsearchurl;
}


public String getOrgi(){
    return orgi;
}


public boolean isEnablersession(){
    return enablersession;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/isEnablersession"))

;
boolean aux = restTemplate.getForObject(builder.toUriString(),boolean.class);
return aux;
}


public boolean isQuality(){
    return quality;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/isQuality"))

;
boolean aux = restTemplate.getForObject(builder.toUriString(),boolean.class);
return aux;
}


public boolean isLastagent(){
    return lastagent;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/isLastagent"))

;
boolean aux = restTemplate.getForObject(builder.toUriString(),boolean.class);
return aux;
}


public boolean isHourcheck(){
    return hourcheck;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/isHourcheck"))

;
boolean aux = restTemplate.getForObject(builder.toUriString(),boolean.class);
return aux;
}


public boolean isOtherquickplay(){
    return otherquickplay;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/isOtherquickplay"))

;
boolean aux = restTemplate.getForObject(builder.toUriString(),boolean.class);
return aux;
}


public boolean isEnabletransmsg(){
    return enabletransmsg;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/isEnabletransmsg"))

;
boolean aux = restTemplate.getForObject(builder.toUriString(),boolean.class);
return aux;
}


public boolean isAgentreplaytimeout(){
    return agentreplaytimeout;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/isAgentreplaytimeout"))

;
boolean aux = restTemplate.getForObject(builder.toUriString(),boolean.class);
return aux;
}


public boolean isSessiontimeout(){
    return sessiontimeout;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/isSessiontimeout"))

;
boolean aux = restTemplate.getForObject(builder.toUriString(),boolean.class);
return aux;
}


public boolean isResessiontimeout(){
    return resessiontimeout;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/isResessiontimeout"))

;
boolean aux = restTemplate.getForObject(builder.toUriString(),boolean.class);
return aux;
}


public boolean isQuene(){
    return quene;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/isQuene"))

;
boolean aux = restTemplate.getForObject(builder.toUriString(),boolean.class);
return aux;
}


public boolean isAgentautoleave(){
    return agentautoleave;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/isAgentautoleave"))

;
boolean aux = restTemplate.getForObject(builder.toUriString(),boolean.class);
return aux;
}


}