package DTO;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
public class CousultInvite {

 private  long serialVersionUID;

 private  String id;

 private  String impid;

 private  String orgi;

 private  String owner;

 private  String processid;

 private  String shares;

 private  Date update_time;

 private  String update_user;

 private  String username;

 private  String wfstatus;

 private  boolean consult_invite_enable;

 private  String consult_invite_model;

 private  String consult_invite_content;

 private  String consult_invite_accept;

 private  String consult_invite_later;

 private  int consult_invite_delay;

 private  String consult_invite_bg;

 private  boolean ctrlenter;

 private  String consult_invite_position;

 private  String consult_invite_color;

 private  String consult_invite_right;

 private  String consult_invite_left;

 private  String consult_invite_bottom;

 private  String consult_invite_top;

 private  Date create_time;

 private  String name;

 private  String consult_invite_width;

 private  String consult_invite_poptype;

 private  String consult_invite_fontsize;

 private  String consult_invite_fontstyle;

 private  String consult_invite_fontcolor;

 private  String consult_invite_interval;

 private  String consult_invite_repeat;

 private  String consult_invite_hight;

 private  String snsaccountid;

 private  String consult_vsitorbtn_position;

 private  String consult_vsitorbtn_content;

 private  String consult_vsitorbtn_right;

 private  String consult_vsitorbtn_left;

 private  String consult_vsitorbtn_top;

 private  String consult_vsitorbtn_color;

 private  String consult_vsitorbtn_model;

 private  String consult_vsitorbtn_bottom;

 private  String consult_invite_backimg;

 private  String consult_skill_logo;

 private  String consult_skill_title;

 private  String consult_skill_img;

 private  String consult_skill_msg;

 private  int consult_skill_numbers;

 private  int consult_skill_maxagent;

 private  String consult_skill_bottomtitle;

 private  boolean consult_skill_agent;

 private  int consult_vsitorbtn_display;

 private  boolean tipagent;

 private  boolean tipuser;

 private  String tipagenticon;

 private  String tipusericon;

 private  String tipagenttitle;

 private  String tipusertitle;

 private  String tipicon;

 private  boolean recordhis;

 private  boolean traceuser;

 private  String defaultskill;

 private  int maxwordsnum;

 private  boolean loadhismsg;

 private  String consult_dialog_color;

 private  String consult_dialog_logo;

 private  String consult_dialog_headimg;

 private  String dialog_name;

 private  String dialog_address;

 private  String dialog_phone;

 private  String dialog_mail;

 private  String dialog_introduction;

 private  String dialog_message;

 private  String dialog_ad;

 private  String lvmopentype;

 private  boolean leavemessage;

 private  boolean leavemsgunlimit;

 private  boolean lvmname;

 private  boolean lvmphone;

 private  boolean lvmemail;

 private  boolean lvmaddress;

 private  boolean lvmqq;

 private  boolean lvmcontent;

 private  String lvtipmsg;

 private  boolean skill;

 private  boolean onlyareaskill;

 private  String areaskilltipmsg;

 private  boolean consult_info;

 private  boolean consult_info_cookies;

 private  boolean consult_info_name;

 private  boolean consult_info_email;

 private  boolean consult_info_phone;

 private  boolean consult_info_resion;

 private  String consult_info_message;

 private  String agentshortcutkey;

 private  String usershortcutkey;

 private  boolean ai;

 private  boolean aifirst;

 private  boolean aisearch;

 private  String aimsg;

 private  String aisuccesstip;

 private  String ainame;

 private  String aiicon;

 private  boolean hideagent;

 private  boolean quickagent;

 private  String aiid;

 private  String datadept;

 private  String agent_online;

 private  boolean enableother;

 private  String otherurl;

 private  boolean otherssl;

 private  boolean otherlogin;

 private  String otherappkey;

 private  String otherappsec;

 private  String otherparam;

 private  String othermethod;

 private  String othertempletinput;

 private  String othertempletoutput;

 private  int suggestnum;

 private  boolean enablevoice;

 private  String oqrdetailurl;

 private  String oqrdetailinput;

 private  String oqrdetailoutput;

 private  boolean fullscreen;

 private  boolean showcontacts;

 private  boolean agentshowcontacts;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://1";


public String getOqrdetailoutput(){
    return oqrdetailoutput;
}


public String getOwner(){
    return owner;
}


public String getConsult_vsitorbtn_top(){
    return consult_vsitorbtn_top;
}


public String getConsult_dialog_color(){
    return consult_dialog_color;
}


public String getSnsaccountid(){
    return snsaccountid;
}


public String getOthertempletinput(){
    return othertempletinput;
}


public String getConsult_invite_repeat(){
    return consult_invite_repeat;
}


public String getOqrdetailurl(){
    return oqrdetailurl;
}


public String getDialog_mail(){
    return dialog_mail;
}


public int getConsult_skill_numbers(){
    return consult_skill_numbers;
}


public String getOtherappkey(){
    return otherappkey;
}


public String getConsult_invite_backimg(){
    return consult_invite_backimg;
}


public String getOtherappsec(){
    return otherappsec;
}


public String getUsername(){
    return username;
}


public String getShares(){
    return shares;
}


public String getOthertempletoutput(){
    return othertempletoutput;
}


public int getConsult_vsitorbtn_display(){
    return consult_vsitorbtn_display;
}


public String getDefaultskill(){
    return defaultskill;
}


public String getLvtipmsg(){
    return lvtipmsg;
}


public String getConsult_vsitorbtn_content(){
    return consult_vsitorbtn_content;
}


public String getConsult_invite_poptype(){
    return consult_invite_poptype;
}


public String getConsult_info_message(){
    return consult_info_message;
}


public String getConsult_invite_later(){
    return consult_invite_later;
}


public String getConsult_skill_bottomtitle(){
    return consult_skill_bottomtitle;
}


public String getName(){
    return name;
}


public String getAimsg(){
    return aimsg;
}


public String getTipusericon(){
    return tipusericon;
}


public String getOthermethod(){
    return othermethod;
}


public Date getCreate_time(){
    return create_time;
}


public String getConsult_vsitorbtn_bottom(){
    return consult_vsitorbtn_bottom;
}


public String getDialog_ad(){
    return dialog_ad;
}


public String getConsult_vsitorbtn_model(){
    return consult_vsitorbtn_model;
}


public String getConsult_invite_width(){
    return consult_invite_width;
}


public String getImpid(){
    return impid;
}


public String getConsult_skill_title(){
    return consult_skill_title;
}


public String getAgentshortcutkey(){
    return agentshortcutkey;
}


public int getMaxwordsnum(){
    return maxwordsnum;
}


public String getDialog_phone(){
    return dialog_phone;
}


public String getAiid(){
    return aiid;
}


public String getConsult_invite_fontstyle(){
    return consult_invite_fontstyle;
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


public String getDialog_name(){
    return dialog_name;
}


public int getSuggestnum(){
    return suggestnum;
}


public String getDatadept(){
    return datadept;
}


public Date getUpdate_time(){
    return update_time;
}


public String getConsult_vsitorbtn_color(){
    return consult_vsitorbtn_color;
}


public String getTipicon(){
    return tipicon;
}


public String getWfstatus(){
    return wfstatus;
}


public String getUsershortcutkey(){
    return usershortcutkey;
}


public String getAiicon(){
    return aiicon;
}


public String getAisuccesstip(){
    return aisuccesstip;
}


public String getConsult_invite_interval(){
    return consult_invite_interval;
}


public String getConsult_invite_position(){
    return consult_invite_position;
}


public String getConsult_invite_left(){
    return consult_invite_left;
}


public String getConsult_vsitorbtn_position(){
    return consult_vsitorbtn_position;
}


public String getAiname(){
    return ainame;
}


public String getOqrdetailinput(){
    return oqrdetailinput;
}


public String getProcessid(){
    return processid;
}


public String getLvmopentype(){
    return lvmopentype;
}


public String getOtherparam(){
    return otherparam;
}


public String getConsult_invite_fontsize(){
    return consult_invite_fontsize;
}


public String getTipagenticon(){
    return tipagenticon;
}


public String getConsult_skill_msg(){
    return consult_skill_msg;
}


public String getDialog_introduction(){
    return dialog_introduction;
}


public String getConsult_invite_bottom(){
    return consult_invite_bottom;
}


public String getConsult_invite_top(){
    return consult_invite_top;
}


public String getConsult_invite_right(){
    return consult_invite_right;
}


public String getConsult_dialog_logo(){
    return consult_dialog_logo;
}


public String getTipusertitle(){
    return tipusertitle;
}


public String getConsult_vsitorbtn_left(){
    return consult_vsitorbtn_left;
}


public String getAreaskilltipmsg(){
    return areaskilltipmsg;
}


public String getConsult_vsitorbtn_right(){
    return consult_vsitorbtn_right;
}


public String getConsult_skill_logo(){
    return consult_skill_logo;
}


public String getConsult_invite_accept(){
    return consult_invite_accept;
}


public String getConsult_invite_bg(){
    return consult_invite_bg;
}


public String getConsult_invite_model(){
    return consult_invite_model;
}


public String getAgent_online(){
    return agent_online;
}


public String getDialog_message(){
    return dialog_message;
}


public String getConsult_invite_content(){
    return consult_invite_content;
}


public int getConsult_skill_maxagent(){
    return consult_skill_maxagent;
}


public String getDialog_address(){
    return dialog_address;
}


public String getConsult_invite_hight(){
    return consult_invite_hight;
}


public String getOtherurl(){
    return otherurl;
}


public String getConsult_invite_color(){
    return consult_invite_color;
}


public String getConsult_dialog_headimg(){
    return consult_dialog_headimg;
}


public String getUpdate_user(){
    return update_user;
}


public int getConsult_invite_delay(){
    return consult_invite_delay;
}


public String getConsult_invite_fontcolor(){
    return consult_invite_fontcolor;
}


public String getOrgi(){
    return orgi;
}


public String getConsult_skill_img(){
    return consult_skill_img;
}


public boolean isTraceuser(){
    return traceuser;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/isTraceuser"))

;
boolean aux = restTemplate.getForObject(builder.toUriString(),boolean.class);
return aux;
}


public boolean isRecordhis(){
    return recordhis;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/isRecordhis"))

;
boolean aux = restTemplate.getForObject(builder.toUriString(),boolean.class);
return aux;
}


public boolean isLeavemsgunlimit(){
    return leavemsgunlimit;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/isLeavemsgunlimit"))

;
boolean aux = restTemplate.getForObject(builder.toUriString(),boolean.class);
return aux;
}


public boolean isConsult_info(){
    return consult_info;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/isConsult_info"))

;
boolean aux = restTemplate.getForObject(builder.toUriString(),boolean.class);
return aux;
}


public boolean isConsult_info_cookies(){
    return consult_info_cookies;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/isConsult_info_cookies"))

;
boolean aux = restTemplate.getForObject(builder.toUriString(),boolean.class);
return aux;
}


public boolean isShowcontacts(){
    return showcontacts;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/isShowcontacts"))

;
boolean aux = restTemplate.getForObject(builder.toUriString(),boolean.class);
return aux;
}


public boolean isAi(){
    return ai;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/isAi"))

;
boolean aux = restTemplate.getForObject(builder.toUriString(),boolean.class);
return aux;
}


public boolean isAifirst(){
    return aifirst;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/isAifirst"))

;
boolean aux = restTemplate.getForObject(builder.toUriString(),boolean.class);
return aux;
}


public boolean isLoadhismsg(){
    return loadhismsg;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/isLoadhismsg"))

;
boolean aux = restTemplate.getForObject(builder.toUriString(),boolean.class);
return aux;
}


public boolean isSkill(){
    return skill;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/isSkill"))

;
boolean aux = restTemplate.getForObject(builder.toUriString(),boolean.class);
return aux;
}


}