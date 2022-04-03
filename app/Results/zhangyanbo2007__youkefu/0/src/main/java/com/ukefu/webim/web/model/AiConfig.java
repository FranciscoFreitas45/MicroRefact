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
import com.ukefu.webim.util.server.message.OtherMessageItem;
@Entity
@Table(name = "uk_xiaoe_config")
@org.hibernate.annotations.Proxy(lazy = false)
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


public void setHotmsg(String hotmsg){
    this.hotmsg = hotmsg;
}


public void setTopicusefulno(String topicusefulno){
    this.topicusefulno = topicusefulno;
}


public String getAiicon(){
    return aiicon;
}


public void setEnablescene(boolean enablescene){
    this.enablescene = enablescene;
}


public void setNoresultmsg(String noresultmsg){
    this.noresultmsg = noresultmsg;
}


public void setOthertempletinput(String othertempletinput){
    this.othertempletinput = othertempletinput;
}


public boolean isSmartsuggestlogin(){
    return smartsuggestlogin;
}


public boolean isTransagent(){
    return transagent;
}


public String getOqrdetailoutput(){
    return oqrdetailoutput;
}


public void setOtherparam(String otherparam){
    this.otherparam = otherparam;
}


public String getNoresultmsg(){
    return noresultmsg;
}


public void setOthertempletoutput(String othertempletoutput){
    this.othertempletoutput = othertempletoutput;
}


public void setSmartsuggesttempletoutput(String smartsuggesttempletoutput){
    this.smartsuggesttempletoutput = smartsuggesttempletoutput;
}


public boolean isTopicuseful(){
    return topicuseful;
}


public String getTopicusefulmsg(){
    return topicusefulmsg;
}


public void setTopicusefulok(String topicusefulok){
    this.topicusefulok = topicusefulok;
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


public void setSmartsuggestappkey(String smartsuggestappkey){
    this.smartsuggestappkey = smartsuggestappkey;
}


public boolean isAskqs(){
    return askqs;
}


public String getTopicusefultip(){
    return topicusefultip;
}


public void setOtherurl(String otherurl){
    this.otherurl = otherurl;
}


public String getOtherappkey(){
    return otherappkey;
}


public void setNoagentmsg(String noagentmsg){
    this.noagentmsg = noagentmsg;
}


public void setSmartsuggestssl(boolean smartsuggestssl){
    this.smartsuggestssl = smartsuggestssl;
}


public String getOqrdetailinput(){
    return oqrdetailinput;
}


public void setSmartsuggestappsec(String smartsuggestappsec){
    this.smartsuggestappsec = smartsuggestappsec;
}


public void setOthertrans(boolean othertrans){
    this.othertrans = othertrans;
}


public String getOtherparam(){
    return otherparam;
}


public boolean isEnablesuggest(){
    return enablesuggest;
}


public void setSelectskill(boolean selectskill){
    this.selectskill = selectskill;
}


public String getOtherappsec(){
    return otherappsec;
}


public void setName(String name){
    this.name = name;
}


public void setResolved(String resolved){
    this.resolved = resolved;
}


public boolean isTopicusefulask(){
    return topicusefulask;
}


public String getSmartsuggestappkey(){
    return smartsuggestappkey;
}


public void setOtherfirst(boolean otherfirst){
    this.otherfirst = otherfirst;
}


public void setTopicusefultip(String topicusefultip){
    this.topicusefultip = topicusefultip;
}


public boolean isEnableother(){
    return enableother;
}


public void setOqrdetailinput(String oqrdetailinput){
    this.oqrdetailinput = oqrdetailinput;
}


public void setSmartsuggesttempletinput(String smartsuggesttempletinput){
    this.smartsuggesttempletinput = smartsuggesttempletinput;
}


public String getSmartsuggestappsec(){
    return smartsuggestappsec;
}


public void setSuggestnum(int suggestnum){
    this.suggestnum = suggestnum;
}


public String getUsername(){
    return username;
}


public void setSmartsuggestlogin(boolean smartsuggestlogin){
    this.smartsuggestlogin = smartsuggestlogin;
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


public void setOtherappkey(String otherappkey){
    this.otherappkey = otherappkey;
}


public void setEnableother(boolean enableother){
    this.enableother = enableother;
}


public boolean isEnablesmartsuggest(){
    return enablesmartsuggest;
}


public void setOqrdetailoutput(String oqrdetailoutput){
    this.oqrdetailoutput = oqrdetailoutput;
}


public void setScenefirst(boolean scenefirst){
    this.scenefirst = scenefirst;
}


public boolean isOtherlogin(){
    return otherlogin;
}


public void setTopicusefulmsg(String topicusefulmsg){
    this.topicusefulmsg = topicusefulmsg;
}


public void setOtherappsec(String otherappsec){
    this.otherappsec = otherappsec;
}


public void setOtherlogin(boolean otherlogin){
    this.otherlogin = otherlogin;
}


public String getUnresolved(){
    return unresolved;
}


public void setAiicon(String aiicon){
    this.aiicon = aiicon;
}


public void setTopicusefulask(boolean topicusefulask){
    this.topicusefulask = topicusefulask;
}


public boolean isRedirectagent(){
    return redirectagent;
}


public void setTransagent(boolean transagent){
    this.transagent = transagent;
}


public void setOthertimeout(int othertimeout){
    this.othertimeout = othertimeout;
}


public void setOqrdetailurl(String oqrdetailurl){
    this.oqrdetailurl = oqrdetailurl;
}


public void setAsktipmsg(String asktipmsg){
    this.asktipmsg = asktipmsg;
}


public void setAiid(String aiid){
    this.aiid = aiid;
}


public String getName(){
    return name;
}


public boolean isEnablescene(){
    return enablescene;
}


public void setSmartsuggestmethod(String smartsuggestmethod){
    this.smartsuggestmethod = smartsuggestmethod;
}


public boolean isOtherfirst(){
    return otherfirst;
}


public String getTopicusefulok(){
    return topicusefulok;
}


public String getSmartsuggesttempletinput(){
    return smartsuggesttempletinput;
}


public void setRedirectagent(boolean redirectagent){
    this.redirectagent = redirectagent;
}


public String getOthermethod(){
    return othermethod;
}


public boolean isSmartsuggestssl(){
    return smartsuggestssl;
}


public void setTopicshot(boolean topicshot){
    this.topicshot = topicshot;
}


public void setAsktimes(int asktimes){
    this.asktimes = asktimes;
}


public void setKeywordnum(int keywordnum){
    this.keywordnum = keywordnum;
}


public void setUnresolved(String unresolved){
    this.unresolved = unresolved;
}


public void setId(String id){
    this.id = id;
}


public String getSmartsuggesturl(){
    return smartsuggesturl;
}


public String getRedirecturl(){
    return redirecturl;
}


public boolean isEnableask(){
    return enableask;
}


public void setQuickagentmsg(String quickagentmsg){
    this.quickagentmsg = quickagentmsg;
}


public boolean isQuickagent(){
    return quickagent;
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


public void setAskqs(boolean askqs){
    this.askqs = askqs;
}


public boolean isScenefirst(){
    return scenefirst;
}


public String getAsktipmsg(){
    return asktipmsg;
}


public int getOthertimeout(){
    return othertimeout;
}


public void setQuickagent(boolean quickagent){
    this.quickagent = quickagent;
}


public void setSelectskillmsg(String selectskillmsg){
    this.selectskillmsg = selectskillmsg;
}


public long getSerialversionuid(){
    return serialVersionUID;
}


public boolean isSelectskill(){
    return selectskill;
}


public void setOthersuggestmsg(String othersuggestmsg){
    this.othersuggestmsg = othersuggestmsg;
}


public void setEnablesmartsuggest(boolean enablesmartsuggest){
    this.enablesmartsuggest = enablesmartsuggest;
}


public boolean isOtherssl(){
    return otherssl;
}


public String getSelectskillmsg(){
    return selectskillmsg;
}


public void setOthermethod(String othermethod){
    this.othermethod = othermethod;
}


public int getKeywordnum(){
    return keywordnum;
}


public void setSuggestmsg(String suggestmsg){
    this.suggestmsg = suggestmsg;
}


public void setCreater(String creater){
    this.creater = creater;
}


public void setOtherssl(boolean otherssl){
    this.otherssl = otherssl;
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


public void setWelcomemsg(String welcomemsg){
    this.welcomemsg = welcomemsg;
}


public void setOrgi(String orgi){
    this.orgi = orgi;
}


public boolean isEnablekeyword(){
    return enablekeyword;
}


@Id
@Column(length = 32)
@GeneratedValue(generator = "system-uuid")
@GenericGenerator(name = "system-uuid", strategy = "uuid")
public String getId(){
    return id;
}


public void setSmartsuggesturl(String smartsuggesturl){
    this.smartsuggesturl = smartsuggesturl;
}


public void setEnableask(boolean enableask){
    this.enableask = enableask;
}


public String getWelcomemsg(){
    return welcomemsg;
}


public String getSuggestmsg(){
    return suggestmsg;
}


public boolean isOthertrans(){
    return othertrans;
}


public String getOtherurl(){
    return otherurl;
}


public void setCreatetime(Date createtime){
    this.createtime = createtime;
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


public void setSmartsuggestparam(String smartsuggestparam){
    this.smartsuggestparam = smartsuggestparam;
}


public void setWaitmsg(String waitmsg){
    this.waitmsg = waitmsg;
}


public void setUsername(String username){
    this.username = username;
}


public void setEnablekeyword(boolean enablekeyword){
    this.enablekeyword = enablekeyword;
}


public String getHotmsg(){
    return hotmsg;
}


public boolean isTopicshot(){
    return topicshot;
}


public void setAskfirst(boolean askfirst){
    this.askfirst = askfirst;
}


public String getResolved(){
    return resolved;
}


public void setTopicuseful(boolean topicuseful){
    this.topicuseful = topicuseful;
}


public String getOrgi(){
    return orgi;
}


public void setEnablesuggest(boolean enablesuggest){
    this.enablesuggest = enablesuggest;
}


public boolean isAskfirst(){
    return askfirst;
}


public void setRedirecturl(String redirecturl){
    this.redirecturl = redirecturl;
}


}