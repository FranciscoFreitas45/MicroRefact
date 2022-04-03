package com.ukefu.webim.web.model;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
@Entity
@Table(name = "uk_consult_invite")
@org.hibernate.annotations.Proxy(lazy = false)
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


public boolean isOnlyareaskill(){
    return onlyareaskill;
}


public void setConsult_dialog_logo(String consult_dialog_logo){
    this.consult_dialog_logo = consult_dialog_logo;
}


public String getOqrdetailoutput(){
    return oqrdetailoutput;
}


public boolean isEnablevoice(){
    return enablevoice;
}


public void setTipagenticon(String tipagenticon){
    this.tipagenticon = tipagenticon;
}


public String getOwner(){
    return owner;
}


public void setConsult_skill_img(String consult_skill_img){
    this.consult_skill_img = consult_skill_img;
}


public void setLvtipmsg(String lvtipmsg){
    this.lvtipmsg = lvtipmsg;
}


public void setOwner(String owner){
    this.owner = owner;
}


public String getConsult_vsitorbtn_top(){
    return consult_vsitorbtn_top;
}


public boolean isCtrlenter(){
    return ctrlenter;
}


public void setConsult_invite_top(String consult_invite_top){
    this.consult_invite_top = consult_invite_top;
}


public String getConsult_dialog_color(){
    return consult_dialog_color;
}


public boolean isConsult_info_phone(){
    return consult_info_phone;
}


public void setConsult_invite_enable(boolean consult_invite_enable){
    this.consult_invite_enable = consult_invite_enable;
}


public boolean isLoadhismsg(){
    return loadhismsg;
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


public void setOtherurl(String otherurl){
    this.otherurl = otherurl;
}


public boolean isSkill(){
    return skill;
}


public int getConsult_skill_numbers(){
    return consult_skill_numbers;
}


public void setTipagenttitle(String tipagenttitle){
    this.tipagenttitle = tipagenttitle;
}


public String getOtherappkey(){
    return otherappkey;
}


public String getConsult_invite_backimg(){
    return consult_invite_backimg;
}


public void setConsult_invite_right(String consult_invite_right){
    this.consult_invite_right = consult_invite_right;
}


public void setConsult_invite_color(String consult_invite_color){
    this.consult_invite_color = consult_invite_color;
}


public String getOtherappsec(){
    return otherappsec;
}


public void setName(String name){
    this.name = name;
}


public boolean isLvmphone(){
    return lvmphone;
}


public boolean isEnableother(){
    return enableother;
}


public boolean isLvmcontent(){
    return lvmcontent;
}


public void setLvmopentype(String lvmopentype){
    this.lvmopentype = lvmopentype;
}


public boolean isConsult_info_resion(){
    return consult_info_resion;
}


public void setSuggestnum(int suggestnum){
    this.suggestnum = suggestnum;
}


public void setDatadept(String datadept){
    this.datadept = datadept;
}


public String getUsername(){
    return username;
}


public void setConsult_vsitorbtn_bottom(String consult_vsitorbtn_bottom){
    this.consult_vsitorbtn_bottom = consult_vsitorbtn_bottom;
}


public String getShares(){
    return shares;
}


public boolean isLeavemessage(){
    return leavemessage;
}


public String getOthertempletoutput(){
    return othertempletoutput;
}


public void setEnableother(boolean enableother){
    this.enableother = enableother;
}


public int getConsult_vsitorbtn_display(){
    return consult_vsitorbtn_display;
}


public boolean isOtherlogin(){
    return otherlogin;
}


public String getDefaultskill(){
    return defaultskill;
}


public String getLvtipmsg(){
    return lvtipmsg;
}


public void setTipagent(boolean tipagent){
    this.tipagent = tipagent;
}


public String getConsult_vsitorbtn_content(){
    return consult_vsitorbtn_content;
}


public void setOtherlogin(boolean otherlogin){
    this.otherlogin = otherlogin;
}


public void setConsult_invite_delay(int consult_invite_delay){
    this.consult_invite_delay = consult_invite_delay;
}


public void setConsult_info_name(boolean consult_info_name){
    this.consult_info_name = consult_info_name;
}


public String getConsult_invite_poptype(){
    return consult_invite_poptype;
}


public boolean isAisearch(){
    return aisearch;
}


public void setAiicon(String aiicon){
    this.aiicon = aiicon;
}


public void setConsult_invite_bg(String consult_invite_bg){
    this.consult_invite_bg = consult_invite_bg;
}


public void setDialog_introduction(String dialog_introduction){
    this.dialog_introduction = dialog_introduction;
}


public void setConsult_info(boolean consult_info){
    this.consult_info = consult_info;
}


public void setConsult_invite_left(String consult_invite_left){
    this.consult_invite_left = consult_invite_left;
}


public void setAiname(String ainame){
    this.ainame = ainame;
}


public void setHideagent(boolean hideagent){
    this.hideagent = hideagent;
}


public void setLeavemessage(boolean leavemessage){
    this.leavemessage = leavemessage;
}


public String getConsult_info_message(){
    return consult_info_message;
}


public void setLvmcontent(boolean lvmcontent){
    this.lvmcontent = lvmcontent;
}


public String getConsult_invite_later(){
    return consult_invite_later;
}


public String getConsult_skill_bottomtitle(){
    return consult_skill_bottomtitle;
}


public void setAiid(String aiid){
    this.aiid = aiid;
}


public String getName(){
    return name;
}


public boolean isLvmname(){
    return lvmname;
}


public String getAimsg(){
    return aimsg;
}


public void setConsult_skill_agent(boolean consult_skill_agent){
    this.consult_skill_agent = consult_skill_agent;
}


public void setAreaskilltipmsg(String areaskilltipmsg){
    this.areaskilltipmsg = areaskilltipmsg;
}


public String getTipusericon(){
    return tipusericon;
}


public void setConsult_vsitorbtn_top(String consult_vsitorbtn_top){
    this.consult_vsitorbtn_top = consult_vsitorbtn_top;
}


public String getOthermethod(){
    return othermethod;
}


public void setConsult_invite_hight(String consult_invite_hight){
    this.consult_invite_hight = consult_invite_hight;
}


public void setTipusericon(String tipusericon){
    this.tipusericon = tipusericon;
}


public Date getCreate_time(){
    return create_time;
}


public String getConsult_vsitorbtn_bottom(){
    return consult_vsitorbtn_bottom;
}


public void setConsult_vsitorbtn_color(String consult_vsitorbtn_color){
    this.consult_vsitorbtn_color = consult_vsitorbtn_color;
}


public void setConsult_skill_maxagent(int consult_skill_maxagent){
    this.consult_skill_maxagent = consult_skill_maxagent;
}


public String getDialog_ad(){
    return dialog_ad;
}


public void setMaxwordsnum(int maxwordsnum){
    this.maxwordsnum = maxwordsnum;
}


public String getConsult_vsitorbtn_model(){
    return consult_vsitorbtn_model;
}


public String getConsult_invite_width(){
    return consult_invite_width;
}


public void setLvmemail(boolean lvmemail){
    this.lvmemail = lvmemail;
}


public void setConsult_skill_logo(String consult_skill_logo){
    this.consult_skill_logo = consult_skill_logo;
}


public String getImpid(){
    return impid;
}


public void setConsult_invite_fontcolor(String consult_invite_fontcolor){
    this.consult_invite_fontcolor = consult_invite_fontcolor;
}


public String getConsult_skill_title(){
    return consult_skill_title;
}


public void setAi(boolean ai){
    this.ai = ai;
}


public boolean isOtherssl(){
    return otherssl;
}


public void setUsershortcutkey(String usershortcutkey){
    this.usershortcutkey = usershortcutkey;
}


public void setOthermethod(String othermethod){
    this.othermethod = othermethod;
}


public boolean isShowcontacts(){
    return showcontacts;
}


public void setTraceuser(boolean traceuser){
    this.traceuser = traceuser;
}


public String getAgentshortcutkey(){
    return agentshortcutkey;
}


public int getMaxwordsnum(){
    return maxwordsnum;
}


public void setConsult_invite_width(String consult_invite_width){
    this.consult_invite_width = consult_invite_width;
}


public void setSkill(boolean skill){
    this.skill = skill;
}


public void setCreate_time(Date create_time){
    this.create_time = create_time;
}


public String getDialog_phone(){
    return dialog_phone;
}


public void setDialog_name(String dialog_name){
    this.dialog_name = dialog_name;
}


public boolean isAi(){
    return ai;
}


public void setWfstatus(String wfstatus){
    this.wfstatus = wfstatus;
}


public String getAiid(){
    return aiid;
}


public String getConsult_invite_fontstyle(){
    return consult_invite_fontstyle;
}


public void setConsult_info_resion(boolean consult_info_resion){
    this.consult_info_resion = consult_info_resion;
}


public void setConsult_skill_title(String consult_skill_title){
    this.consult_skill_title = consult_skill_title;
}


public String getTipagenttitle(){
    return tipagenttitle;
}


public boolean isAgentshowcontacts(){
    return agentshowcontacts;
}


@Id
@Column(length = 32)
@GeneratedValue(generator = "system-uuid")
@GenericGenerator(name = "system-uuid", strategy = "uuid")
public String getId(){
    return id;
}


public void setUpdate_time(Date update_time){
    this.update_time = update_time;
}


public void setConsult_invite_poptype(String consult_invite_poptype){
    this.consult_invite_poptype = consult_invite_poptype;
}


public String getDialog_name(){
    return dialog_name;
}


public int getSuggestnum(){
    return suggestnum;
}


public void setConsult_vsitorbtn_display(int consult_vsitorbtn_display){
    this.consult_vsitorbtn_display = consult_vsitorbtn_display;
}


public void setConsult_invite_later(String consult_invite_later){
    this.consult_invite_later = consult_invite_later;
}


public void setConsult_invite_position(String consult_invite_position){
    this.consult_invite_position = consult_invite_position;
}


public String getDatadept(){
    return datadept;
}


public boolean isRecordhis(){
    return recordhis;
}


public void setConsult_vsitorbtn_model(String consult_vsitorbtn_model){
    this.consult_vsitorbtn_model = consult_vsitorbtn_model;
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


public void setAisuccesstip(String aisuccesstip){
    this.aisuccesstip = aisuccesstip;
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


public void setConsult_vsitorbtn_content(String consult_vsitorbtn_content){
    this.consult_vsitorbtn_content = consult_vsitorbtn_content;
}


public void setOthertempletinput(String othertempletinput){
    this.othertempletinput = othertempletinput;
}


public void setShares(String shares){
    this.shares = shares;
}


public void setProcessid(String processid){
    this.processid = processid;
}


public void setAgentshowcontacts(boolean agentshowcontacts){
    this.agentshowcontacts = agentshowcontacts;
}


public boolean isConsult_skill_agent(){
    return consult_skill_agent;
}


public boolean isTipuser(){
    return tipuser;
}


public void setLvmaddress(boolean lvmaddress){
    this.lvmaddress = lvmaddress;
}


public void setOtherparam(String otherparam){
    this.otherparam = otherparam;
}


public void setOthertempletoutput(String othertempletoutput){
    this.othertempletoutput = othertempletoutput;
}


public void setConsult_info_message(String consult_info_message){
    this.consult_info_message = consult_info_message;
}


public void setShowcontacts(boolean showcontacts){
    this.showcontacts = showcontacts;
}


public void setLvmqq(boolean lvmqq){
    this.lvmqq = lvmqq;
}


public String getConsult_invite_interval(){
    return consult_invite_interval;
}


public void setAisearch(boolean aisearch){
    this.aisearch = aisearch;
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


public void setDialog_mail(String dialog_mail){
    this.dialog_mail = dialog_mail;
}


public void setConsult_dialog_headimg(String consult_dialog_headimg){
    this.consult_dialog_headimg = consult_dialog_headimg;
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


public boolean isConsult_info_email(){
    return consult_info_email;
}


public String getOtherparam(){
    return otherparam;
}


public boolean isLvmemail(){
    return lvmemail;
}


public void setTipuser(boolean tipuser){
    this.tipuser = tipuser;
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


public void setConsult_skill_bottomtitle(String consult_skill_bottomtitle){
    this.consult_skill_bottomtitle = consult_skill_bottomtitle;
}


public boolean isConsult_info_cookies(){
    return consult_info_cookies;
}


public void setConsult_invite_model(String consult_invite_model){
    this.consult_invite_model = consult_invite_model;
}


public boolean isTipagent(){
    return tipagent;
}


public String getDialog_introduction(){
    return dialog_introduction;
}


public void setOqrdetailinput(String oqrdetailinput){
    this.oqrdetailinput = oqrdetailinput;
}


public void setFullscreen(boolean fullscreen){
    this.fullscreen = fullscreen;
}


public String getConsult_invite_bottom(){
    return consult_invite_bottom;
}


public boolean isLvmaddress(){
    return lvmaddress;
}


public String getConsult_invite_top(){
    return consult_invite_top;
}


public String getConsult_invite_right(){
    return consult_invite_right;
}


public boolean isAifirst(){
    return aifirst;
}


public void setOtherappkey(String otherappkey){
    this.otherappkey = otherappkey;
}


public String getConsult_dialog_logo(){
    return consult_dialog_logo;
}


public boolean isConsult_info(){
    return consult_info;
}


public void setAgent_online(String agent_online){
    this.agent_online = agent_online;
}


public void setOqrdetailoutput(String oqrdetailoutput){
    this.oqrdetailoutput = oqrdetailoutput;
}


public void setOtherappsec(String otherappsec){
    this.otherappsec = otherappsec;
}


public void setConsult_vsitorbtn_position(String consult_vsitorbtn_position){
    this.consult_vsitorbtn_position = consult_vsitorbtn_position;
}


public boolean isLvmqq(){
    return lvmqq;
}


public String getTipusertitle(){
    return tipusertitle;
}


public void setConsult_invite_accept(String consult_invite_accept){
    this.consult_invite_accept = consult_invite_accept;
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


public void setConsult_invite_content(String consult_invite_content){
    this.consult_invite_content = consult_invite_content;
}


public void setSnsaccountid(String snsaccountid){
    this.snsaccountid = snsaccountid;
}


public void setConsult_dialog_color(String consult_dialog_color){
    this.consult_dialog_color = consult_dialog_color;
}


public String getConsult_skill_logo(){
    return consult_skill_logo;
}


public void setOqrdetailurl(String oqrdetailurl){
    this.oqrdetailurl = oqrdetailurl;
}


public boolean isFullscreen(){
    return fullscreen;
}


public void setConsult_invite_fontsize(String consult_invite_fontsize){
    this.consult_invite_fontsize = consult_invite_fontsize;
}


public void setLvmname(boolean lvmname){
    this.lvmname = lvmname;
}


public void setImpid(String impid){
    this.impid = impid;
}


public String getConsult_invite_accept(){
    return consult_invite_accept;
}


public String getConsult_invite_bg(){
    return consult_invite_bg;
}


public void setDialog_message(String dialog_message){
    this.dialog_message = dialog_message;
}


public String getConsult_invite_model(){
    return consult_invite_model;
}


public String getAgent_online(){
    return agent_online;
}


public void setDialog_ad(String dialog_ad){
    this.dialog_ad = dialog_ad;
}


public String getDialog_message(){
    return dialog_message;
}


public void setId(String id){
    this.id = id;
}


public boolean isQuickagent(){
    return quickagent;
}


public void setEnablevoice(boolean enablevoice){
    this.enablevoice = enablevoice;
}


public void setLvmphone(boolean lvmphone){
    this.lvmphone = lvmphone;
}


public void setQuickagent(boolean quickagent){
    this.quickagent = quickagent;
}


public void setAimsg(String aimsg){
    this.aimsg = aimsg;
}


public String getConsult_invite_content(){
    return consult_invite_content;
}


public void setRecordhis(boolean recordhis){
    this.recordhis = recordhis;
}


public void setAgentshortcutkey(String agentshortcutkey){
    this.agentshortcutkey = agentshortcutkey;
}


public void setAifirst(boolean aifirst){
    this.aifirst = aifirst;
}


public void setUpdate_user(String update_user){
    this.update_user = update_user;
}


public void setConsult_info_phone(boolean consult_info_phone){
    this.consult_info_phone = consult_info_phone;
}


public void setConsult_skill_msg(String consult_skill_msg){
    this.consult_skill_msg = consult_skill_msg;
}


public int getConsult_skill_maxagent(){
    return consult_skill_maxagent;
}


public String getDialog_address(){
    return dialog_address;
}


public void setOtherssl(boolean otherssl){
    this.otherssl = otherssl;
}


public void setConsult_info_cookies(boolean consult_info_cookies){
    this.consult_info_cookies = consult_info_cookies;
}


public void setTipusertitle(String tipusertitle){
    this.tipusertitle = tipusertitle;
}


public void setDefaultskill(String defaultskill){
    this.defaultskill = defaultskill;
}


public void setDialog_address(String dialog_address){
    this.dialog_address = dialog_address;
}


public boolean isLeavemsgunlimit(){
    return leavemsgunlimit;
}


public void setOrgi(String orgi){
    this.orgi = orgi;
}


public String getConsult_invite_hight(){
    return consult_invite_hight;
}


public void setConsult_info_email(boolean consult_info_email){
    this.consult_info_email = consult_info_email;
}


public void setConsult_skill_numbers(int consult_skill_numbers){
    this.consult_skill_numbers = consult_skill_numbers;
}


public void setTipicon(String tipicon){
    this.tipicon = tipicon;
}


public boolean isHideagent(){
    return hideagent;
}


public void setConsult_vsitorbtn_left(String consult_vsitorbtn_left){
    this.consult_vsitorbtn_left = consult_vsitorbtn_left;
}


public void setLoadhismsg(boolean loadhismsg){
    this.loadhismsg = loadhismsg;
}


public void setOnlyareaskill(boolean onlyareaskill){
    this.onlyareaskill = onlyareaskill;
}


public void setConsult_invite_backimg(String consult_invite_backimg){
    this.consult_invite_backimg = consult_invite_backimg;
}


public String getOtherurl(){
    return otherurl;
}


public void setConsult_invite_repeat(String consult_invite_repeat){
    this.consult_invite_repeat = consult_invite_repeat;
}


public String getConsult_invite_color(){
    return consult_invite_color;
}


public void setCtrlenter(boolean ctrlenter){
    this.ctrlenter = ctrlenter;
}


public void setConsult_vsitorbtn_right(String consult_vsitorbtn_right){
    this.consult_vsitorbtn_right = consult_vsitorbtn_right;
}


public String getConsult_dialog_headimg(){
    return consult_dialog_headimg;
}


public boolean isConsult_invite_enable(){
    return consult_invite_enable;
}


public String getUpdate_user(){
    return update_user;
}


public void setUsername(String username){
    this.username = username;
}


public int getConsult_invite_delay(){
    return consult_invite_delay;
}


public void setConsult_invite_bottom(String consult_invite_bottom){
    this.consult_invite_bottom = consult_invite_bottom;
}


public void setDialog_phone(String dialog_phone){
    this.dialog_phone = dialog_phone;
}


public String getConsult_invite_fontcolor(){
    return consult_invite_fontcolor;
}


public void setLeavemsgunlimit(boolean leavemsgunlimit){
    this.leavemsgunlimit = leavemsgunlimit;
}


public boolean isTraceuser(){
    return traceuser;
}


public String getOrgi(){
    return orgi;
}


public String getConsult_skill_img(){
    return consult_skill_img;
}


public boolean isConsult_info_name(){
    return consult_info_name;
}


public void setConsult_invite_fontstyle(String consult_invite_fontstyle){
    this.consult_invite_fontstyle = consult_invite_fontstyle;
}


public void setConsult_invite_interval(String consult_invite_interval){
    this.consult_invite_interval = consult_invite_interval;
}


}