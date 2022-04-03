package com.ukefu.webim.web.model;
 import org.hibernate.annotations.GenericGenerator;
import javax.persistence;
import java.util.Date;
@Entity
@Table(name = "uk_datadic")
@org.hibernate.annotations.Proxy(lazy = false)
public class DataDic {

 private  long serialVersionUID;

 private  String id;

 private  String name;

 private  String code;

 private  String title;

 private  String dstype;

 private  String dstemplet;

 private  String parentid;

 private  String type;

 private  String memo;

 private  String distitle;

 private  String orgi;

 private  String status;

 private  String iconclass;

 private  String cssstyle;

 private  String creater;

 private  String authcode;

 private  String publishedtype;

 private  Date createtime;

 private  Date updatetime;

 private  String description;

 private  String tabtype;

 private  String dictype;

 private  boolean spsearch;

 private  boolean defaultmenu;

 private  String projectid;

 private  int sortindex;

 private  String dataid;

 private  String dicicon;

 private  String curicon;

 private  String bgcolor;

 private  String curbgcolor;

 private  String menupos;

 private  boolean navmenu;

 private  boolean quickmenu;


public String getName(){
    return name;
}


public String getStatus(){
    return status;
}


public String getDistitle(){
    return distitle;
}


public String getDictype(){
    return dictype;
}


public void setCuricon(String curicon){
    this.curicon = curicon;
}


public String getDstemplet(){
    return dstemplet;
}


public void setDstemplet(String dstemplet){
    this.dstemplet = dstemplet;
}


public boolean isQuickmenu(){
    return quickmenu;
}


public String getTitle(){
    return title;
}


public void setId(String id){
    this.id = id;
}


public boolean isSpsearch(){
    return spsearch;
}


public String getCode(){
    return code;
}


public void setNavmenu(boolean navmenu){
    this.navmenu = navmenu;
}


public String getCssstyle(){
    return cssstyle;
}


public void setCode(String code){
    this.code = code;
}


public Date getUpdatetime(){
    return updatetime;
}


public void setSortindex(int sortindex){
    this.sortindex = sortindex;
}


public String getIconclass(){
    return iconclass;
}


public void setTitle(String title){
    this.title = title;
}


public void setSpsearch(boolean spsearch){
    this.spsearch = spsearch;
}


public String getProjectid(){
    return projectid;
}


public String getMenupos(){
    return menupos;
}


public String getMemo(){
    return memo;
}


public String getAuthcode(){
    return authcode;
}


public String getType(){
    return type;
}


public String getCurbgcolor(){
    return curbgcolor;
}


public void setDstype(String dstype){
    this.dstype = dstype;
}


public void setBgcolor(String bgcolor){
    this.bgcolor = bgcolor;
}


public void setMenupos(String menupos){
    this.menupos = menupos;
}


public void setCreater(String creater){
    this.creater = creater;
}


public void setDataid(String dataid){
    this.dataid = dataid;
}


public void setTabtype(String tabtype){
    this.tabtype = tabtype;
}


public void setName(String name){
    this.name = name;
}


public void setUpdatetime(Date updatetime){
    this.updatetime = updatetime;
}


public void setIconclass(String iconclass){
    this.iconclass = iconclass;
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


public void setDescription(String description){
    this.description = description;
}


public void setDicicon(String dicicon){
    this.dicicon = dicicon;
}


public void setDistitle(String distitle){
    this.distitle = distitle;
}


public void setCssstyle(String cssstyle){
    this.cssstyle = cssstyle;
}


public String getDicicon(){
    return dicicon;
}


public String getDescription(){
    return description;
}


public String getPublishedtype(){
    return publishedtype;
}


public String getBgcolor(){
    return bgcolor;
}


public Date getCreatetime(){
    return createtime;
}


public String getTabtype(){
    return tabtype;
}


public void setParentid(String parentid){
    this.parentid = parentid;
}


public void setProjectid(String projectid){
    this.projectid = projectid;
}


public void setCreatetime(Date createtime){
    this.createtime = createtime;
}


public String getCreater(){
    return creater;
}


public String getParentid(){
    return parentid;
}


public void setQuickmenu(boolean quickmenu){
    this.quickmenu = quickmenu;
}


public boolean isDefaultmenu(){
    return defaultmenu;
}


public void setDefaultmenu(boolean defaultmenu){
    this.defaultmenu = defaultmenu;
}


public void setCurbgcolor(String curbgcolor){
    this.curbgcolor = curbgcolor;
}


public void setType(String type){
    this.type = type;
}


public void setStatus(String status){
    this.status = status;
}


public boolean isNavmenu(){
    return navmenu;
}


public void setAuthcode(String authcode){
    this.authcode = authcode;
}


public void setMemo(String memo){
    this.memo = memo;
}


public void setDictype(String dictype){
    this.dictype = dictype;
}


public String getDataid(){
    return dataid;
}


public void setPublishedtype(String publishedtype){
    this.publishedtype = publishedtype;
}


public String getCuricon(){
    return curicon;
}


public String getOrgi(){
    return orgi;
}


public String getDstype(){
    return dstype;
}


public int getSortindex(){
    return sortindex;
}


}