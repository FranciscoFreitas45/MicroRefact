package DTO;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.hibernate.annotations.GenericGenerator;
public class PbxHost {

 private  long serialVersionUID;

 private  String id;

 private  String name;

 private  String hostname;

 private  String ipaddr;

 private  int port;

 private  int sipport;

 private  String password;

 private  String blacklist;

 private  String whitelist;

 private  boolean connected;

 private  boolean callcenter;

 private  String recordpath;

 private  String asrrecordpath;

 private  String ttsrecordpath;

 private  String ivrpath;

 private  String fspath;

 private  String device;

 private  boolean afterprocess;

 private  String orgi;

 private  boolean autoanswer;

 private  boolean sipautoanswer;

 private  String abscodec;

 private  String callbacktype;

 private  String callbacknumber;

 private  String creater;

 private  String enableai;

 private  String aiid;

 private  String sceneid;

 private  String welcomemsg;

 private  String waitmsg;

 private  String tipmessage;

 private  boolean enablewebrtc;

 private  String webrtcaddress;

 private  String webrtcport;

 private  boolean webrtcssl;

 private  String webrtcring;

 private  boolean dissipphone;

 private  int maxnumlength;

 private  int minnumlength;

 private  String ipregionblack;

 private  String ipregionwhite;

 private  boolean savekillevent;

 private  Date createtime;

 private  Date updatetime;


public String getName(){
    return name;
}


public String getBlacklist(){
    return blacklist;
}


public int getMinnumlength(){
    return minnumlength;
}


public String getTipmessage(){
    return tipmessage;
}


public String getWebrtcring(){
    return webrtcring;
}


public String getIpaddr(){
    return ipaddr;
}


public String getSceneid(){
    return sceneid;
}


public Date getUpdatetime(){
    return updatetime;
}


public String getTtsrecordpath(){
    return ttsrecordpath;
}


public String getIpregionwhite(){
    return ipregionwhite;
}


public String getWebrtcport(){
    return webrtcport;
}


public String getAiid(){
    return aiid;
}


public String getAsrrecordpath(){
    return asrrecordpath;
}


public String getIvrpath(){
    return ivrpath;
}


public String getFspath(){
    return fspath;
}


public String getWhitelist(){
    return whitelist;
}


@Id
@Column(length = 32)
@GeneratedValue(generator = "system-uuid")
@GenericGenerator(name = "system-uuid", strategy = "uuid")
public String getId(){
    return id;
}


public String getDevice(){
    return device;
}


public String getWelcomemsg(){
    return welcomemsg;
}


public String getHostname(){
    return hostname;
}


public Date getCreatetime(){
    return createtime;
}


public String getCallbacktype(){
    return callbacktype;
}


public String getCreater(){
    return creater;
}


public String getWebrtcaddress(){
    return webrtcaddress;
}


public String getEnableai(){
    return enableai;
}


public String getWaitmsg(){
    return waitmsg;
}


public int getSipport(){
    return sipport;
}


public String getIpregionblack(){
    return ipregionblack;
}


public String getCallbacknumber(){
    return callbacknumber;
}


public String getAbscodec(){
    return abscodec;
}


public int getMaxnumlength(){
    return maxnumlength;
}


public String getPassword(){
    return password;
}


public int getPort(){
    return port;
}


public String getRecordpath(){
    return recordpath;
}


public String getOrgi(){
    return orgi;
}


}