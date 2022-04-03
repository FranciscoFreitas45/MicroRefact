package com.ukefu.webim.web.model;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.hibernate.annotations.GenericGenerator;
import com.ukefu.util.UKTools;
@Entity
@Table(name = "uk_crm_datamodel")
@org.hibernate.annotations.Proxy(lazy = false)
public class DataModel {

 private  long serialVersionUID;

 private  String id;

 private  String name;

 private  String code;

 private  String reporttype;

 private  String layouttype;

 private  String viewtype;

 public  String action;

 private  String actiontype;

 private  String btntype;

 private  String title;

 private  String dicid;

 private  String authcode;

 private  String authitem;

 private  String html;

 private  String reportcontent;

 private  String status;

 private  String rolename;

 private  String userid;

 private  String blacklist;

 private  String reportpackage;

 private  String useacl;

 private  String reportmodel;

 private  Date updatetime;

 private  String orgi;

 private  Date createtime;

 private  String creater;

 private  String icon;

 private  String style;

 private  int objectcount;

 private  int reportversion;

 private  boolean design;

 private  boolean rtfedit;

 private  boolean codeedit;

 private  boolean mediaagent;

 private  boolean hisnav;

 private  boolean fullscreen;

 private  boolean accesshis;

 private  boolean searchhis;

 private  boolean duplicate;

 private  boolean autorefresh;

 private  int refreshtime;

 private  boolean autoscroll;

 private  String scrollspeed;

 private  boolean onlytab;

 private  int rotationspeed;

 private  String submittype;

 private  String submitpos;

 private  String publishedtype;

 private  String tabtype;

 private  String username;

 private  String useremail;

 private  boolean cache;

 private  String extparam;

 private  String source;

 private  String proid;

 private  String tbid;

 private  String tbname;

 private  String dstype;

 private  String linktype;

 private  String linkurl;

 private  String params;

 private  int sortindex;

 private  String targetreport;

 private  String description;

 private  boolean mgroup;

 private  String groupname;

 private  String groupicon;

 private  String groupcolor;

 private  boolean layoutleft;

 private  boolean layoutright;

 private  boolean layoutcenter;

 private  String leftwidth;

 private  boolean leftscroll;

 private  String rightwidth;

 private  boolean rightscroll;

 private  String centerheight;

 private  boolean centerscroll;

 private  boolean upload;

 private  boolean hasform;

 private  boolean resetbtn;

 private  boolean submitbtn;

 private  String formtype;

 private  String submiturl;

 private  String submitpage;

 private  String submitlink;

 private  String submitpagerpt;

 private  String reseturl;

 private  String resetpage;

 private  String resetlink;

 private  String resetpagerpt;

 private  boolean workflow;

 private  String flowtype;

 private  String successtip;

 private  String errortip;

 private  String currentagent;

 private  String query;


public String getUseremail(){
    return useremail;
}


public String getReporttype(){
    return reporttype;
}


public void setDicid(String dicid){
    this.dicid = dicid;
}


public void setViewtype(String viewtype){
    this.viewtype = viewtype;
}


public boolean isLeftscroll(){
    return leftscroll;
}


public String getResetlink(){
    return resetlink;
}


public String getStatus(){
    return status;
}


public void setSubmitpage(String submitpage){
    this.submitpage = submitpage;
}


public void setSuccesstip(String successtip){
    this.successtip = successtip;
}


public void setExtparam(String extparam){
    this.extparam = extparam;
}


public int getObjectcount(){
    return objectcount;
}


public void setSearchhis(boolean searchhis){
    this.searchhis = searchhis;
}


public void setCenterscroll(boolean centerscroll){
    this.centerscroll = centerscroll;
}


public String getErrortip(){
    return errortip;
}


public void setMgroup(boolean mgroup){
    this.mgroup = mgroup;
}


public void setQuery(String query){
    this.query = query;
}


public String getCode(){
    return code;
}


public boolean isDesign(){
    return design;
}


@Transient
public String getQuery(){
    return query;
}


public String getSubmitpage(){
    return submitpage;
}


public void setUseremail(String useremail){
    this.useremail = useremail;
}


public void setGroupname(String groupname){
    this.groupname = groupname;
}


public boolean isResetbtn(){
    return resetbtn;
}


public void setCodeedit(boolean codeedit){
    this.codeedit = codeedit;
}


public String getResetpagerpt(){
    return resetpagerpt;
}


public void setSortindex(int sortindex){
    this.sortindex = sortindex;
}


public void setCode(String code){
    this.code = code;
}


public String getSuccesstip(){
    return successtip;
}


public boolean isLayoutleft(){
    return layoutleft;
}


public void setTbname(String tbname){
    this.tbname = tbname;
}


public String getProid(){
    return proid;
}


public void setRtfedit(boolean rtfedit){
    this.rtfedit = rtfedit;
}


public String getDicid(){
    return dicid;
}


public String getAuthcode(){
    return authcode;
}


public String getCenterheight(){
    return centerheight;
}


@Transient
public String getCurrentagent(){
    return currentagent;
}


public void setDstype(String dstype){
    this.dstype = dstype;
}


public void setRolename(String rolename){
    this.rolename = rolename;
}


public void setRotationspeed(int rotationspeed){
    this.rotationspeed = rotationspeed;
}


public void setTabtype(String tabtype){
    this.tabtype = tabtype;
}


public String getFlowtype(){
    return flowtype;
}


public void setObjectcount(int objectcount){
    this.objectcount = objectcount;
}


public boolean isSubmitbtn(){
    return submitbtn;
}


public void setLeftwidth(String leftwidth){
    this.leftwidth = leftwidth;
}


public void setName(String name){
    this.name = name;
}


public void setAutorefresh(boolean autorefresh){
    this.autorefresh = autorefresh;
}


public boolean isAutoscroll(){
    return autoscroll;
}


public int getReportversion(){
    return reportversion;
}


public void setAction(String action){
    this.action = action;
}


public String getBtntype(){
    return btntype;
}


public boolean isAutorefresh(){
    return autorefresh;
}


public void setReportcontent(String reportcontent){
    this.reportcontent = reportcontent;
}


public boolean isLayoutright(){
    return layoutright;
}


public void setRightscroll(boolean rightscroll){
    this.rightscroll = rightscroll;
}


public void setFullscreen(boolean fullscreen){
    this.fullscreen = fullscreen;
}


public boolean isMediaagent(){
    return mediaagent;
}


public void setGroupcolor(String groupcolor){
    this.groupcolor = groupcolor;
}


public void setDescription(String description){
    this.description = description;
}


public String getSubmitpagerpt(){
    return submitpagerpt;
}


public void setBtntype(String btntype){
    this.btntype = btntype;
}


public void setHasform(boolean hasform){
    this.hasform = hasform;
}


public String getTbname(){
    return tbname;
}


public String getPublishedtype(){
    return publishedtype;
}


public String getUsername(){
    return username;
}


public String getFormtype(){
    return formtype;
}


public void setGroupicon(String groupicon){
    this.groupicon = groupicon;
}


public boolean isRtfedit(){
    return rtfedit;
}


public void setUpload(boolean upload){
    this.upload = upload;
}


public Date getCreatetime(){
    return createtime;
}


public void setOnlytab(boolean onlytab){
    this.onlytab = onlytab;
}


public String getTabtype(){
    return tabtype;
}


public void setLayoutright(boolean layoutright){
    this.layoutright = layoutright;
}


public void setFormtype(String formtype){
    this.formtype = formtype;
}


public String getUserid(){
    return userid;
}


public void setUserid(String userid){
    this.userid = userid;
}


public String getExtparam(){
    return extparam;
}


public void setHtml(String html){
    this.html = html;
}


public String getAction(){
    return action;
}


public String getScrollspeed(){
    return scrollspeed;
}


public String getIcon(){
    return icon;
}


public int getRotationspeed(){
    return rotationspeed;
}


public void setCenterheight(String centerheight){
    this.centerheight = centerheight;
}


public void setCurrentagent(String currentagent){
    this.currentagent = currentagent;
}


public void setResetpage(String resetpage){
    this.resetpage = resetpage;
}


public void setFlowtype(String flowtype){
    this.flowtype = flowtype;
}


public String getAuthitem(){
    return authitem;
}


public void setSubmitpos(String submitpos){
    this.submitpos = submitpos;
}


public String getRolename(){
    return rolename;
}


public void setDesign(boolean design){
    this.design = design;
}


public boolean isRightscroll(){
    return rightscroll;
}


public String getDstype(){
    return dstype;
}


public void setLinktype(String linktype){
    this.linktype = linktype;
}


public void setParams(String params){
    this.params = params;
}


public boolean isFullscreen(){
    return fullscreen;
}


public void setAutoscroll(boolean autoscroll){
    this.autoscroll = autoscroll;
}


public int getSortindex(){
    return sortindex;
}


public String getName(){
    return name;
}


public String getBlacklist(){
    return blacklist != null ? blacklist : rolename;
}


public boolean isAccesshis(){
    return accesshis;
}


public String getSubmitlink(){
    return submitlink;
}


public void setSubmitbtn(boolean submitbtn){
    this.submitbtn = submitbtn;
}


public void setCache(boolean cache){
    this.cache = cache;
}


public void setTargetreport(String targetreport){
    this.targetreport = targetreport;
}


public void setHisnav(boolean hisnav){
    this.hisnav = hisnav;
}


public String getTitle(){
    return title;
}


public String getSubmiturl(){
    return submiturl;
}


public void setId(String id){
    this.id = id;
}


public void setSubmiturl(String submiturl){
    this.submiturl = submiturl;
}


public void setResetbtn(boolean resetbtn){
    this.resetbtn = resetbtn;
}


public String getHtml(){
    return html;
}


public void setReportpackage(String reportpackage){
    this.reportpackage = reportpackage;
}


public String getLinkurl(){
    return linkurl;
}


public Date getUpdatetime(){
    return updatetime;
}


public void setLayoutcenter(boolean layoutcenter){
    this.layoutcenter = layoutcenter;
}


public void setActiontype(String actiontype){
    this.actiontype = actiontype;
}


public void setScrollspeed(String scrollspeed){
    this.scrollspeed = scrollspeed;
}


public void setDuplicate(boolean duplicate){
    this.duplicate = duplicate;
}


public void setTitle(String title){
    this.title = title;
}


public void setIcon(String icon){
    this.icon = icon;
}


public void setSubmitlink(String submitlink){
    this.submitlink = submitlink;
}


public String getResetpage(){
    return resetpage;
}


public String getSubmitpos(){
    return submitpos;
}


@Transient
public String getSource(){
    return source;
}


public void setLayouttype(String layouttype){
    this.layouttype = layouttype;
}


public String getTargetreport(){
    return targetreport;
}


public void setMediaagent(boolean mediaagent){
    this.mediaagent = mediaagent;
}


public void setCreater(String creater){
    this.creater = creater;
}


public boolean isHisnav(){
    return hisnav;
}


public String getLinktype(){
    return linktype;
}


public String getParams(){
    return params;
}


public void setLinkurl(String linkurl){
    this.linkurl = linkurl;
}


public String getReportpackage(){
    return reportpackage;
}


public String getGroupname(){
    return groupname;
}


public void setSource(String source){
    this.source = source;
}


public boolean isCodeedit(){
    return codeedit;
}


public boolean isLayoutcenter(){
    return layoutcenter;
}


public int getRefreshtime(){
    return refreshtime;
}


public void setAuthitem(String authitem){
    this.authitem = authitem;
}


public void setLeftscroll(boolean leftscroll){
    this.leftscroll = leftscroll;
}


public void setUpdatetime(Date updatetime){
    this.updatetime = updatetime;
}


public void setOrgi(String orgi){
    this.orgi = orgi;
}


@Id
@Column(length = 32)
@GeneratedValue(generator = "system-uuid")
@GenericGenerator(name = "system-uuid", strategy = "assigned")
public String getId(){
    return id;
}


public boolean isCenterscroll(){
    return centerscroll;
}


public String getDescription(){
    return description;
}


public void setProid(String proid){
    this.proid = proid;
}


public String getSubmittype(){
    return submittype;
}


public String getReseturl(){
    return reseturl;
}


public boolean isHasform(){
    return hasform;
}


public void setRightwidth(String rightwidth){
    this.rightwidth = rightwidth;
}


public String getGroupicon(){
    return groupicon;
}


public void setUseacl(String useacl){
    this.useacl = useacl;
}


public String getGroupcolor(){
    return groupcolor;
}


public String getStyle(){
    return style;
}


public String getActiontype(){
    return actiontype;
}


public String getReportmodel(){
    return reportmodel;
}


public void setStyle(String style){
    this.style = style;
}


public boolean isWorkflow(){
    return workflow;
}


public boolean isSearchhis(){
    return searchhis;
}


public void setCreatetime(Date createtime){
    this.createtime = createtime;
}


public String getUseacl(){
    return useacl;
}


public String getCreater(){
    return creater;
}


public void setTbid(String tbid){
    this.tbid = tbid;
}


public void setErrortip(String errortip){
    this.errortip = errortip;
}


public void setAccesshis(boolean accesshis){
    this.accesshis = accesshis;
}


public boolean isDuplicate(){
    return duplicate;
}


public void setReportversion(int reportversion){
    this.reportversion = reportversion;
}


public String getReportcontent(){
    return reportcontent;
}


public void setReporttype(String reporttype){
    this.reporttype = reporttype;
}


public void setUsername(String username){
    this.username = username;
}


public void setSubmitpagerpt(String submitpagerpt){
    this.submitpagerpt = submitpagerpt;
}


public String getViewtype(){
    return viewtype;
}


public String getLeftwidth(){
    return leftwidth;
}


public void setResetpagerpt(String resetpagerpt){
    this.resetpagerpt = resetpagerpt;
}


public void setResetlink(String resetlink){
    this.resetlink = resetlink;
}


public void setStatus(String status){
    this.status = status;
}


public void setBlacklist(String blacklist){
    this.blacklist = blacklist;
}


public void setAuthcode(String authcode){
    this.authcode = authcode;
}


public void setWorkflow(boolean workflow){
    this.workflow = workflow;
}


public boolean isCache(){
    return cache;
}


public boolean isMgroup(){
    return mgroup;
}


public void setReseturl(String reseturl){
    this.reseturl = reseturl;
}


public void setPublishedtype(String publishedtype){
    this.publishedtype = publishedtype;
}


public String getLayouttype(){
    return layouttype;
}


public String getOrgi(){
    return orgi;
}


public String getRightwidth(){
    return rightwidth;
}


public String getTbid(){
    return tbid;
}


public boolean isUpload(){
    return upload;
}


public boolean isOnlytab(){
    return onlytab;
}


public void setSubmittype(String submittype){
    this.submittype = submittype;
}


public void setReportmodel(String reportmodel){
    this.reportmodel = reportmodel;
}


public void setLayoutleft(boolean layoutleft){
    this.layoutleft = layoutleft;
}


public void setRefreshtime(int refreshtime){
    this.refreshtime = refreshtime;
}


}