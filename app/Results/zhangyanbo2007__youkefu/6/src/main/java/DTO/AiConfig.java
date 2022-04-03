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
import com.ukefu.webim.util.server.message.OtherMessageItem;
public class AiConfig {

 private  long serialVersionUID;

 private  String id;

 private  String orgi;

 private  Date createtime;

 private  String creater;

 private  String username;

 private  String name;

 private  String aiid;

 private  String aiicon;

 private  String welcomemsg;

 private  String waitmsg;

 private  boolean enableother;

 private  boolean otherfirst;

 private  String otherurl;

 private  boolean otherssl;

 private  boolean otherlogin;

 private  String otherappkey;

 private  String otherappsec;

 private  String otherparam;

 private  String othermethod;

 private  String othertempletinput;

 private  String othertempletoutput;

 private  boolean othertrans;

 private  int othertimeout;

 private  String oqrdetailurl;

 private  String oqrdetailinput;

 private  String oqrdetailoutput;

 private  boolean enablesuggest;

 private  String suggestmsg;

 private  String othersuggestmsg;

 private  boolean enableask;

 private  boolean askfirst;

 private  boolean enablescene;

 private  boolean scenefirst;

 private  boolean enablekeyword;

 private  int keywordnum;

 private  boolean transagent;

 private  boolean askqs;

 private  int asktimes;

 private  String asktipmsg;

 private  String resolved;

 private  String unresolved;

 private  boolean redirectagent;

 private  String redirecturl;

 private  boolean selectskill;

 private  String selectskillmsg;

 private  String noresultmsg;

 private  String hotmsg;

 private  boolean topicshot;

 private  boolean topicuseful;

 private  String topicusefulmsg;

 private  String topicusefulok;

 private  String topicusefulno;

 private  String topicusefultip;

 private  boolean topicusefulask;

 private  boolean quickagent;

 private  String quickagentmsg;

 private  String noagentmsg;

 private  boolean enablesmartsuggest;

 private  String smartsuggesturl;

 private  boolean smartsuggestssl;

 private  boolean smartsuggestlogin;

 private  String smartsuggestappkey;

 private  String smartsuggestappsec;

 private  String smartsuggestparam;

 private  String smartsuggestmethod;

 private  String smartsuggesttempletinput;

 private  String smartsuggesttempletoutput;

 private  int suggestnum;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://0";


public String getAiicon(){
    return aiicon;
}


public String getOqrdetailoutput(){
    return oqrdetailoutput;
}


public String getNoresultmsg(){
    return noresultmsg;
}


public String getTopicusefulmsg(){
    return topicusefulmsg;
}


public String getOthertempletinput(){
    return othertempletinput;
}


public String getNoagentmsg(){
    return noagentmsg;
}


public int getAsktimes(){
    return asktimes;
}


public String getOqrdetailurl(){
    return oqrdetailurl;
}


public String getTopicusefultip(){
    return topicusefultip;
}


public String getOtherappkey(){
    return otherappkey;
}


public String getOqrdetailinput(){
    return oqrdetailinput;
}


public String getOtherparam(){
    return otherparam;
}


public String getOtherappsec(){
    return otherappsec;
}


public String getSmartsuggestappkey(){
    return smartsuggestappkey;
}


public String getSmartsuggestappsec(){
    return smartsuggestappsec;
}


public String getUsername(){
    return username;
}


public String getOthertempletoutput(){
    return othertempletoutput;
}


public Date getCreatetime(){
    return createtime;
}


public String getQuickagentmsg(){
    return quickagentmsg;
}


public String getUnresolved(){
    return unresolved;
}


public String getName(){
    return name;
}


public String getTopicusefulok(){
    return topicusefulok;
}


public String getSmartsuggesttempletinput(){
    return smartsuggesttempletinput;
}


public String getOthermethod(){
    return othermethod;
}


public String getSmartsuggesturl(){
    return smartsuggesturl;
}


public String getRedirecturl(){
    return redirecturl;
}


@Transient
public List<OtherMessageItem> getHot(){
    List<OtherMessageItem> otherMessageItemList = null;
    if (!StringUtils.isBlank(this.getHotmsg())) {
        try {
            otherMessageItemList = OnlineUserUtils.objectMapper.readValue(this.getHotmsg(), UKTools.getCollectionType(ArrayList.class, OtherMessageItem.class));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    return otherMessageItemList;
}


public String getAsktipmsg(){
    return asktipmsg;
}


public int getOthertimeout(){
    return othertimeout;
}


public long getSerialversionuid(){
    return serialVersionUID;
}


public String getSelectskillmsg(){
    return selectskillmsg;
}


public int getKeywordnum(){
    return keywordnum;
}


public String getSmartsuggestmethod(){
    return smartsuggestmethod;
}


public String getTopicusefulno(){
    return topicusefulno;
}


public String getAiid(){
    return aiid;
}


public String getSmartsuggesttempletoutput(){
    return smartsuggesttempletoutput;
}


public String getSmartsuggestparam(){
    return smartsuggestparam;
}


public String getOthersuggestmsg(){
    return othersuggestmsg;
}


@Id
@Column(length = 32)
@GeneratedValue(generator = "system-uuid")
@GenericGenerator(name = "system-uuid", strategy = "uuid")
public String getId(){
    return id;
}


public String getWelcomemsg(){
    return welcomemsg;
}


public String getSuggestmsg(){
    return suggestmsg;
}


public String getOtherurl(){
    return otherurl;
}


public String getCreater(){
    return creater;
}


public int getSuggestnum(){
    return suggestnum;
}


public String getWaitmsg(){
    return waitmsg;
}


public String getHotmsg(){
    return hotmsg;
}


public String getResolved(){
    return resolved;
}


public String getOrgi(){
    return orgi;
}


public void setAiid(String aiid){
    this.aiid = aiid;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setAiid"))

.queryParam("aiid",aiid)
;
restTemplate.put(builder.toUriString(),null);
}


}