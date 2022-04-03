package DTO;
 import java.text.SimpleDateFormat;
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
import com.ukefu.util.event.UserEvent;
import com.ukefu.webim.util.OnlineUserUtils;
public class ChatMessage implements UserEvent{

 private  long serialVersionUID;

 private  String id;

 private  String appid;

 private  String userid;

 private  String username;

 private  String aiid;

 private  String touser;

 private  String tousername;

 private  boolean cooperation;

 private  String msgtype;

 private  String creater;

 private  String usession;

 private  String agentserviceid;

 private  String sessionid;

 private  String topicid;

 private  String topicatid;

 private  boolean topic;

 private  boolean aichat;

 private  String message;

 private  String expmsg;

 private  boolean readstatus;

 private  boolean useful;

 private  String notreason;

 private  Date usetime;

 private  String orgi;

 private  String channel;

 private  String model;

 private  String chatype;

 private  Date lastagentmsgtime;

 private  Date lastmsgtime;

 private  int agentreplytime;

 private  int agentreplyinterval;

 private  String batid;

 private  String headimgurl;

 private  String filename;

 private  int filesize;

 private  String attachmentid;

 private  boolean datastatus;

 private  String ckind;

 private  String ckindname;

 private  String clabel;

 private  String clabelname;

 private  String matchtype;

 private  String mediaid;

 private  String locx;

 private  String locy;

 private  long updatetime;

 private  int duration;

 private  String title;

 private  String qusid;

 private  String code;

 private  String score;

 private  String url;

 private  String bustype;

 private  String scale;

 private  String suggestmsg;

 private  boolean quickagent;

 private  int tokenum;

 private  String agentuser;

 private  String type;

 private  String contextid;

 private  String calltype;

 private  String createtime;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://0";


public String getBatid(){
    return batid;
}


public String getClabelname(){
    return clabelname;
}


public int getDuration(){
    return duration;
}


public String getChannel(){
    return channel;
}


public String getCkindname(){
    return ckindname;
}


public String getHeadimgurl(){
    return headimgurl;
}


public String getTousername(){
    return tousername;
}


public String getCode(){
    return code;
}


public String getTopicatid(){
    return topicatid;
}


public String getMediaid(){
    return mediaid;
}


public String getLocx(){
    return locx;
}


public String getLocy(){
    return locy;
}


public String getNotreason(){
    return notreason;
}


public String getType(){
    return type;
}


public String getClabel(){
    return clabel;
}


public String getAgentserviceid(){
    return agentserviceid;
}


public String getUsername(){
    return username;
}


public Date getLastmsgtime(){
    return lastmsgtime;
}


public String getCreatetime(){
    return createtime;
}


public Date getLastagentmsgtime(){
    return lastagentmsgtime;
}


public String getUserid(){
    return userid;
}


public String getMessage(){
    return message;
}


public String getAppid(){
    return appid;
}


public String getFilename(){
    return filename;
}


public int getAgentreplytime(){
    return agentreplytime;
}


public String getQusid(){
    return qusid;
}


public String getContextid(){
    return contextid;
}


public String getExpmsg(){
    return expmsg;
}


public String getChatype(){
    return chatype;
}


@Transient
public String getAgentuser(){
    return agentuser;
}


public String getCkind(){
    return ckind;
}


@Transient
public int getTokenum(){
    return tokenum;
}


public String getAttachmentid(){
    return attachmentid;
}


public String getCalltype(){
    return calltype;
}


public String getTitle(){
    return title;
}


public Date getUsetime(){
    return usetime;
}


public String getModel(){
    return model;
}


public String getBustype(){
    return bustype;
}


public long getUpdatetime(){
    return updatetime;
}


public String getTopicid(){
    return topicid;
}


public String getUrl(){
    return url;
}


public String getScale(){
    return scale;
}


public String getAiid(){
    return aiid;
}


@Id
@Column(length = 32)
@GeneratedValue(generator = "system-uuid")
@GenericGenerator(name = "system-uuid", strategy = "assigned")
public String getId(){
    return id;
}


public String getSuggestmsg(){
    return suggestmsg;
}


public String getCreater(){
    return creater;
}


public String getSessionid(){
    return sessionid;
}


public String getMatchtype(){
    return matchtype;
}


public String getTouser(){
    return touser;
}


public int getAgentreplyinterval(){
    return agentreplyinterval;
}


public String getUsession(){
    return usession;
}


public int getFilesize(){
    return filesize;
}


@Transient
public List<OtherMessageItem> getSuggest(){
    List<OtherMessageItem> otherMessageItemList = null;
    if (!StringUtils.isBlank(this.getSuggestmsg())) {
        try {
            otherMessageItemList = OnlineUserUtils.objectMapper.readValue(this.getSuggestmsg(), UKTools.getCollectionType(ArrayList.class, OtherMessageItem.class));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    return otherMessageItemList;
}


public String getMsgtype(){
    return msgtype;
}


public String getOrgi(){
    return orgi;
}


public String getScore(){
    return score;
}


public void setUseful(boolean useful){
    this.useful = useful;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setUseful"))

.queryParam("useful",useful)
;
restTemplate.put(builder.toUriString(),null);
}


public void setUsetime(Date usetime){
    this.usetime = usetime;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setUsetime"))

.queryParam("usetime",usetime)
;
restTemplate.put(builder.toUriString(),null);
}


}