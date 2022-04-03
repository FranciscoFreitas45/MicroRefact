package com.ukefu.webim.web.model;
 import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Where;
@Entity
@Table(name = "uk_tabletask")
@org.hibernate.annotations.Proxy(lazy = false)
public class MetadataTable {

 private  long serialVersionUID;

 private  String id;

 private  String name;

 private  String dbid;

 private  String tabledirid;

 private  String tablename;

 private  String code;

 private  String secure;

 private  String tabletype;

 private  String datasql;

 private  int startindex;

 private  Date updatetime;

 private  long updatetimenumber;

 private  String tabtype;

 private  String pid;

 private  String secmenuid;

 private  String reportid;

 private  boolean timeline;

 private  String eventname;

 private  int tbversion;

 private  Date lastupdate;

 private  String taskname;

 private  String taskplan;

 private  String taskstatus;

 private  String tasktype;

 private  Date createtime;

 private  String configure;

 private  String secureconf;

 private  String userid;

 private  String groupid;

 private  String previewtemplet;

 private  String listblocktemplet;

 private  String orgi;

 private  String creater;

 private  String creatername;

 private  boolean userpage;

 private  boolean fromdb;

 private  boolean workflow;

 private  List<TableProperties> tableproperty;


public String getName(){
    return name;
}


public void setTbversion(int tbversion){
    this.tbversion = tbversion;
}


public String getListblocktemplet(){
    return listblocktemplet;
}


public String getTabledirid(){
    return tabledirid;
}


public void setUpdatetimenumber(long updatetimenumber){
    this.updatetimenumber = updatetimenumber;
}


public String getTaskstatus(){
    return taskstatus;
}


public void setTimeline(boolean timeline){
    this.timeline = timeline;
}


// 不载入 设置为 禁用 导入导出的字段
@Where(clause = "impfield=0")
@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
@JoinColumn(name = "dbtableid")
@OrderBy("sortindex")
public List<TableProperties> getTableproperty(){
    return tableproperty;
}


public void setTabletype(String tabletype){
    this.tabletype = tabletype;
}


public void setListblocktemplet(String listblocktemplet){
    this.listblocktemplet = listblocktemplet;
}


public void setStartindex(int startindex){
    this.startindex = startindex;
}


public void setId(String id){
    this.id = id;
}


public void setPreviewtemplet(String previewtemplet){
    this.previewtemplet = previewtemplet;
}


public String getCode(){
    return code;
}


public void setTaskplan(String taskplan){
    this.taskplan = taskplan;
}


public int getStartindex(){
    return startindex;
}


public void setGroupid(String groupid){
    this.groupid = groupid;
}


public String getReportid(){
    return reportid;
}


public void setTabledirid(String tabledirid){
    this.tabledirid = tabledirid;
}


public void setCode(String code){
    this.code = code;
}


public String getTabletype(){
    return tabletype != null ? tabletype : "1";
}


public Date getUpdatetime(){
    return updatetime;
}


public void setLastupdate(Date lastupdate){
    this.lastupdate = lastupdate;
}


public void setSecmenuid(String secmenuid){
    this.secmenuid = secmenuid;
}


@Transient
public String getType(){
    return "table";
}


public String getEventname(){
    return eventname;
}


public String getCreatername(){
    return creatername;
}


public void setCreater(String creater){
    this.creater = creater;
}


public void setSecure(String secure){
    this.secure = secure;
}


public void setTaskname(String taskname){
    this.taskname = taskname;
}


public void setTabtype(String tabtype){
    this.tabtype = tabtype;
}


public long getUpdatetimenumber(){
    return updatetimenumber;
}


public void setName(String name){
    this.name = name;
}


public String getTasktype(){
    return tasktype;
}


public String getSecureconf(){
    return secureconf;
}


@Transient
public boolean isUserpage(){
    return userpage;
}


public boolean isFromdb(){
    return fromdb;
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
@GenericGenerator(name = "system-uuid", strategy = "uuid")
public String getId(){
    return id;
}


public String getConfigure(){
    return configure;
}


public void setPid(String pid){
    this.pid = pid;
}


public void setReportid(String reportid){
    this.reportid = reportid;
}


public void setEventname(String eventname){
    this.eventname = eventname;
}


public String getTaskplan(){
    return taskplan;
}


public String getPreviewtemplet(){
    return previewtemplet;
}


public String getTablename(){
    return tablename;
}


public void setTablename(String tablename){
    this.tablename = tablename;
}


public void setDbid(String dbid){
    this.dbid = dbid;
}


public Date getCreatetime(){
    return createtime;
}


public String getTabtype(){
    return tabtype;
}


public boolean isWorkflow(){
    return workflow;
}


public void setTableproperty(List<TableProperties> tableproperty){
    this.tableproperty = tableproperty;
}


public void setCreatetime(Date createtime){
    this.createtime = createtime;
}


public String getCreater(){
    return creater;
}


public String getSecmenuid(){
    return secmenuid;
}


public String getPid(){
    return pid;
}


public String getUserid(){
    return userid;
}


public void setUserid(String userid){
    this.userid = userid;
}


public void setUserpage(boolean userpage){
    this.userpage = userpage;
}


public void setDatasql(String datasql){
    this.datasql = datasql;
}


public void setCreatername(String creatername){
    this.creatername = creatername;
}


public void setConfigure(String configure){
    this.configure = configure;
}


public void setSecureconf(String secureconf){
    this.secureconf = secureconf;
}


public int getTbversion(){
    return tbversion;
}


public void setTasktype(String tasktype){
    this.tasktype = tasktype;
}


public String getGroupid(){
    return groupid;
}


public void setWorkflow(boolean workflow){
    this.workflow = workflow;
}


public Date getLastupdate(){
    return lastupdate;
}


public String getDbid(){
    return dbid;
}


public void setTaskstatus(String taskstatus){
    this.taskstatus = taskstatus;
}


public String getTaskname(){
    return taskname != null ? taskname : tablename;
}


public String getOrgi(){
    return orgi;
}


public boolean isTimeline(){
    return timeline;
}


public String getDatasql(){
    return datasql;
}


public String getSecure(){
    return secure;
}


public void setFromdb(boolean fromdb){
    this.fromdb = fromdb;
}


}