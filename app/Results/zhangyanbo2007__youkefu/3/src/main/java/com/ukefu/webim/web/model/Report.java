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
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.elasticsearch.annotations.Document;
import com.ukefu.util.UKTools;
@Document(indexName = "uckefu", type = "uk_report")
@Entity
@Table(name = "uk_report")
@org.hibernate.annotations.Proxy(lazy = false)
public class Report extends ESBean{

 private  long serialVersionUID;

 private  String id;

 private  String name;

 private  String reporttype;

 private  String viewtype;

 private  String code;

 private  String orgi;

 private  int objectcount;

 private  String dicid;

 private  String description;

 private  Date createtime;

 private  String html;

 private  String status;

 private  String rolename;

 private  String userid;

 private  String blacklist;

 private  String reportpackage;

 private  String useacl;

 private  String reportmodel;

 private  Date updatetime;

 private  boolean datastatus;

 private  String creater;

 private  int reportversion;

 private  String publishedtype;

 private  String tabtype;

 private  String username;

 private  String useremail;

 private  boolean cache;

 private  String extparam;

 private  String targetreport;

 private  String source;

 private  List<ReportModel> reportModels;

 private  List<ReportFilter> reportFilters;


public String getUseremail(){
    return useremail;
}


public String getName(){
    return name;
}


public String getBlacklist(){
    return blacklist;
}


public String getReporttype(){
    return reporttype;
}


public void setViewtype(String viewtype){
    this.viewtype = viewtype;
}


public void setDicid(String dicid){
    this.dicid = dicid;
}


public void setDatastatus(boolean datastatus){
    this.datastatus = datastatus;
}


public String getStatus(){
    return status;
}


public void setCache(boolean cache){
    this.cache = cache;
}


public void setExtparam(String extparam){
    this.extparam = extparam;
}


public void setTargetreport(String targetreport){
    this.targetreport = targetreport;
}


public int getObjectcount(){
    return objectcount;
}


public void setId(String id){
    this.id = id;
}


public String getCode(){
    return code;
}


public void setUseremail(String useremail){
    this.useremail = useremail;
}


public String getHtml(){
    return html;
}


public void setReportpackage(String reportpackage){
    this.reportpackage = reportpackage;
}


public void setCode(String code){
    this.code = code;
}


public Date getUpdatetime(){
    return updatetime;
}


public String getDicid(){
    return dicid;
}


public void setReportFilters(List<ReportFilter> reportFilters){
    this.reportFilters = reportFilters;
}


public String getSource(){
    return source;
}


public String getTargetreport(){
    return targetreport;
}


public void setRolename(String rolename){
    this.rolename = rolename;
}


public void setCreater(String creater){
    this.creater = creater;
}


public void setTabtype(String tabtype){
    this.tabtype = tabtype;
}


public void setObjectcount(int objectcount){
    this.objectcount = objectcount;
}


public void setName(String name){
    this.name = name;
}


public String getReportpackage(){
    return reportpackage;
}


public void setSource(String source){
    this.source = source;
}


public int getReportversion(){
    return reportversion;
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


public void setDescription(String description){
    this.description = description;
}


public String getDescription(){
    return description;
}


public String getPublishedtype(){
    return publishedtype;
}


public String getUsername(){
    return username;
}


public void setUseacl(String useacl){
    this.useacl = useacl;
}


public Date getCreatetime(){
    return createtime;
}


public String getReportmodel(){
    return reportmodel;
}


public String getTabtype(){
    return tabtype;
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


public String getUserid(){
    return userid;
}


public void setUserid(String userid){
    this.userid = userid;
}


public void setReportversion(int reportversion){
    this.reportversion = reportversion;
}


public void setReporttype(String reporttype){
    this.reporttype = reporttype;
}


public void setUsername(String username){
    this.username = username;
}


public String getExtparam(){
    return extparam;
}


public String getViewtype(){
    return viewtype;
}


public void setHtml(String html){
    this.html = html;
}


public void setReportModels(List<ReportModel> reportModels){
    this.reportModels = reportModels;
}


@Transient
public List<ReportModel> getReportModels(){
    return reportModels;
}


public void setStatus(String status){
    this.status = status;
}


public void setBlacklist(String blacklist){
    this.blacklist = blacklist;
}


public boolean isCache(){
    return cache;
}


@Transient
public List<ReportFilter> getReportFilters(){
    return reportFilters;
}


public String getRolename(){
    return rolename;
}


public void setPublishedtype(String publishedtype){
    this.publishedtype = publishedtype;
}


public String getOrgi(){
    return orgi;
}


public boolean isDatastatus(){
    return datastatus;
}


public void setReportmodel(String reportmodel){
    this.reportmodel = reportmodel;
}


}