package com.ukefu.webim.web.model;
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
@Entity
@Table(name = "uk_sessionconfig")
@org.hibernate.annotations.Proxy(lazy = false)
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


public String getRetimeoutmsg(){
    return retimeoutmsg;
}


public void setDistribution(String distribution){
    this.distribution = distribution;
}


public void setTimeoutmsg(String timeoutmsg){
    this.timeoutmsg = timeoutmsg;
}


public void setServicetimeoutlimit(boolean servicetimeoutlimit){
    this.servicetimeoutlimit = servicetimeoutlimit;
}


public boolean isEnablequick(){
    return enablequick;
}


public boolean isAgentautoleave(){
    return agentautoleave;
}


public String getOqrdetailoutput(){
    return oqrdetailoutput;
}


public void setSuccessmsg(String successmsg){
    this.successmsg = successmsg;
}


public void setTipagenticon(String tipagenticon){
    this.tipagenticon = tipagenticon;
}


public String getWorkinghours(){
    return workinghours;
}


public boolean isSessiontimeout(){
    return sessiontimeout;
}


public String getSuccessmsg(){
    return successmsg;
}


public boolean isCtrlenter(){
    return ctrlenter;
}


public void setSatisfaction(boolean satisfaction){
    this.satisfaction = satisfaction;
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


public void setOqrsearchoutput(String oqrsearchoutput){
    this.oqrsearchoutput = oqrsearchoutput;
}


public void setTipagenttitle(String tipagenttitle){
    this.tipagenttitle = tipagenttitle;
}


public void setNoagentmsg(String noagentmsg){
    this.noagentmsg = noagentmsg;
}


public String getFinessmsg(){
    return finessmsg;
}


public boolean isServicekind(){
    return servicekind;
}


public void setQuality(boolean quality){
    this.quality = quality;
}


public String getOqrdetailinput(){
    return oqrdetailinput;
}


public void setLastagent(boolean lastagent){
    this.lastagent = lastagent;
}


public boolean isQuality(){
    return quality;
}


public void setQuenetimeoutmsg(String quenetimeoutmsg){
    this.quenetimeoutmsg = quenetimeoutmsg;
}


public void setServicedic(String servicedic){
    this.servicedic = servicedic;
}


public String getTipagenticon(){
    return tipagenticon;
}


public void setName(String name){
    this.name = name;
}


public String getDistribution(){
    return distribution;
}


public boolean isTipagent(){
    return tipagent;
}


public void setWorkinghours(String workinghours){
    this.workinghours = workinghours;
}


public int getInitmaxuser(){
    return initmaxuser;
}


public void setOqrsearchinput(String oqrsearchinput){
    this.oqrsearchinput = oqrsearchinput;
}


public void setAgentreplaytimeout(boolean agentreplaytimeout){
    this.agentreplaytimeout = agentreplaytimeout;
}


public void setOqrdetailinput(String oqrdetailinput){
    this.oqrdetailinput = oqrdetailinput;
}


public void setRetimeout(int retimeout){
    this.retimeout = retimeout;
}


public void setInitmaxuser(int initmaxuser){
    this.initmaxuser = initmaxuser;
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


public void setServicekind(boolean servicekind){
    this.servicekind = servicekind;
}


public void setNotinwhmsg(String notinwhmsg){
    this.notinwhmsg = notinwhmsg;
}


public Date getCreatetime(){
    return createtime;
}


public void setOtherquickplay(boolean otherquickplay){
    this.otherquickplay = otherquickplay;
}


public int getQuenetimeout(){
    return quenetimeout;
}


public int getTimeout(){
    return timeout;
}


public void setFinessmsg(String finessmsg){
    this.finessmsg = finessmsg;
}


public boolean isAgentreplaytimeout(){
    return agentreplaytimeout;
}


public void setOqrdetailoutput(String oqrdetailoutput){
    this.oqrdetailoutput = oqrdetailoutput;
}


public void setSessionmsg(String sessionmsg){
    this.sessionmsg = sessionmsg;
}


public void setTipagent(boolean tipagent){
    this.tipagent = tipagent;
}


public void setQuene(boolean quene){
    this.quene = quene;
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


public boolean isLastagent(){
    return lastagent;
}


public void setTimeout(int timeout){
    this.timeout = timeout;
}


public int getRetimeout(){
    return retimeout;
}


public boolean isQuene(){
    return quene;
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


public void setOqrdetailurl(String oqrdetailurl){
    this.oqrdetailurl = oqrdetailurl;
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


public void setEnablequick(boolean enablequick){
    this.enablequick = enablequick;
}


public boolean isHourcheck(){
    return hourcheck;
}


public void setAgentbusymsg(String agentbusymsg){
    this.agentbusymsg = agentbusymsg;
}


public void setServicetimeout(int servicetimeout){
    this.servicetimeout = servicetimeout;
}


public int getServicetimeout(){
    return servicetimeout;
}


public boolean isEnabletransmsg(){
    return enabletransmsg;
}


public boolean isAgentctrlenter(){
    return agentctrlenter;
}


public void setAgenttimeout(int agenttimeout){
    this.agenttimeout = agenttimeout;
}


public void setId(String id){
    this.id = id;
}


public void setOqrsearchurl(String oqrsearchurl){
    this.oqrsearchurl = oqrsearchurl;
}


public void setEnablersession(boolean enablersession){
    this.enablersession = enablersession;
}


public void setAgentautoleave(boolean agentautoleave){
    this.agentautoleave = agentautoleave;
}


public void setServicename(String servicename){
    this.servicename = servicename;
}


public boolean isSatisfaction(){
    return satisfaction;
}


public void setSessiontimeout(boolean sessiontimeout){
    this.sessiontimeout = sessiontimeout;
}


public String getNotinwhmsg(){
    return notinwhmsg;
}


public boolean isOtherssl(){
    return otherssl;
}


public void setTransmsg(String transmsg){
    this.transmsg = transmsg;
}


public boolean isServicetimeoutlimit(){
    return servicetimeoutlimit;
}


public boolean isOtherquickplay(){
    return otherquickplay;
}


public void setCreater(String creater){
    this.creater = creater;
}


public void setQuenetimeout(int quenetimeout){
    this.quenetimeout = quenetimeout;
}


public void setOtherssl(boolean otherssl){
    this.otherssl = otherssl;
}


public String getAgentbusymsg(){
    return agentbusymsg;
}


public boolean isMultisatisf(){
    return multisatisf;
}


public void setMultisatisf(boolean multisatisf){
    this.multisatisf = multisatisf;
}


public void setRetimeoutmsg(String retimeoutmsg){
    this.retimeoutmsg = retimeoutmsg;
}


public String getTipagenttitle(){
    return tipagenttitle;
}


public void setOrgi(String orgi){
    this.orgi = orgi;
}


@Id
@Column(length = 32)
@GeneratedValue(generator = "system-uuid")
@GenericGenerator(name = "system-uuid", strategy = "uuid")
public String getId(){
    return id;
}


public boolean isResessiontimeout(){
    return resessiontimeout;
}


public int getMaxuser(){
    return maxuser;
}


public void setEnabletransmsg(boolean enabletransmsg){
    this.enabletransmsg = enabletransmsg;
}


public boolean isEnablersession(){
    return enablersession;
}


public void setCreatetime(Date createtime){
    this.createtime = createtime;
}


public String getCreater(){
    return creater;
}


public void setAgenttimeoutmsg(String agenttimeoutmsg){
    this.agenttimeoutmsg = agenttimeoutmsg;
}


public void setSatisftext(String satisftext){
    this.satisftext = satisftext;
}


public void setCtrlenter(boolean ctrlenter){
    this.ctrlenter = ctrlenter;
}


public void setUsername(String username){
    this.username = username;
}


public String getServicedic(){
    return servicedic;
}


public void setAgentctrlenter(boolean agentctrlenter){
    this.agentctrlenter = agentctrlenter;
}


public void setMaxuser(int maxuser){
    this.maxuser = maxuser;
}


public void setResessiontimeout(boolean resessiontimeout){
    this.resessiontimeout = resessiontimeout;
}


public String getOqrsearchurl(){
    return oqrsearchurl;
}


public void setHourcheck(boolean hourcheck){
    this.hourcheck = hourcheck;
}


public String getOrgi(){
    return orgi;
}


public void setQualityscore(String qualityscore){
    this.qualityscore = qualityscore;
}


}