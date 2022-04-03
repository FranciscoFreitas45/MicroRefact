package com.ukefu.webim.web.model;
 import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.hibernate.annotations.GenericGenerator;
@Entity
@Table(name = "uk_jobdetail")
@org.hibernate.annotations.Proxy(lazy = false)
public class JobDetail implements Serializable{

 private  long serialVersionUID;

 private  String id;

 private  String orgi;

 private  String organ;

 private  String creater;

 private  String batchtype;

 private  String imptype;

 private  String actype;

 private  String filterid;

 private  String parentid;

 private  String organid;

 private  String localserver;

 private  int threads;

 private  String impurl;

 private  String filetype;

 private  String dbtype;

 private  String jdbcurl;

 private  String driverclazz;

 private  String username;

 private  String password;

 private  String clazz;

 private  String taskid;

 private  String tasktype;

 private  boolean plantask;

 private  String source;

 private  String userid;

 private  String email;

 private  String nickname;

 private  String crawltaskid;

 private  long lastindex;

 private  String dataid;

 private  String dicid;

 private  String starttime;

 private  String endtime;

 private  Date taskfiretime;

 private  String crawltask;

 private  String targettask;

 private  boolean createtable;

 private  String taskstatus;

 private  long startindex;

 private  Date lastdate;

 private  Date nextfiretime;

 private  String cronexp;

 private  boolean fetcher;

 private  boolean pause;

 private  boolean plantaskreadtorun;

 private  boolean mapping;

 private  String memo;

 private  long fetchSize;

 private  String usearea;

 private  String areafield;

 private  String areafieldtype;

 private  String arearule;

 private  String minareavalue;

 private  String maxareavalue;

 private  String formatstr;

 private  String taskinfo;

 private  int priority;

 private  String runserver;

 private  String actid;

 private  String distype;

 private  String distpolicy;

 private  int policynum;

 private  String busstype;

 private  int disnum;

 private  String siptrunk;

 private  String province;

 private  String city;

 private  boolean prefix;

 private  Date createtime;

 private  Date updatetime;

 private  String datastatus;

 private  String status;

 private  int namenum;

 private  int validnum;

 private  int invalidnum;

 private  int execnum;

 private  int assigned;

 private  int notassigned;

 private  String description;

 private  String execmd;

 private  String exectype;

 private  String exectarget;

 private  String execto;

 private  String reportid;

 private  String name;

@Transient
 private  String exceptionMsg;

@Transient
 private  Reporter report;

 private  String filtertype;

 private  String templateid;

 private  String extention;

 private  boolean enabletaithreads;

 private  int aithreads;

 private  String forecastid;


public void setPassword(String password){
    this.password = password;
}


public void setMinareavalue(String minareavalue){
    this.minareavalue = minareavalue;
}


public String getExtention(){
    return extention;
}


public String getJdbcurl(){
    return jdbcurl;
}


public void setInvalidnum(int invalidnum){
    this.invalidnum = invalidnum;
}


public void setExecnum(int execnum){
    this.execnum = execnum;
}


public void setProvince(String province){
    this.province = province;
}


public String getTaskstatus(){
    return taskstatus;
}


public void setStarttime(String starttime){
    this.starttime = starttime;
}


public void setDicid(String dicid){
    this.dicid = dicid;
}


public Date getNextfiretime(){
    return nextfiretime;
}


public void setTemplateid(String templateid){
    this.templateid = templateid;
}


public void setExceptionMsg(String exceptionMsg){
    this.exceptionMsg = exceptionMsg;
}


public void setEnabletaithreads(boolean enabletaithreads){
    this.enabletaithreads = enabletaithreads;
}


public void setDatastatus(String datastatus){
    this.datastatus = datastatus;
}


public String getStatus(){
    return status;
}


public void setCronexp(String cronexp){
    this.cronexp = cronexp;
}


public String getActype(){
    return actype;
}


public String getFiltertype(){
    return filtertype;
}


public String getRunserver(){
    return runserver;
}


public void setJdbcurl(String jdbcurl){
    this.jdbcurl = jdbcurl;
}


public void setCrawltask(String crawltask){
    this.crawltask = crawltask;
}


public boolean isMapping(){
    return mapping;
}


public String getAreafield(){
    return areafield;
}


public void setTaskinfo(String taskinfo){
    this.taskinfo = taskinfo;
}


public void setCrawltaskid(String crawltaskid){
    this.crawltaskid = crawltaskid;
}


public void setActype(String actype){
    this.actype = actype;
}


public void setFormatstr(String formatstr){
    this.formatstr = formatstr;
}


public String getCrawltaskid(){
    return crawltaskid;
}


public void setMapping(boolean mapping){
    this.mapping = mapping;
}


public String getBusstype(){
    return busstype;
}


public String getLocalserver(){
    return localserver;
}


public String getMemo(){
    return memo;
}


public String getDicid(){
    return dicid;
}


public int getInvalidnum(){
    return invalidnum;
}


public void setDbtype(String dbtype){
    this.dbtype = dbtype;
}


public String getAreafieldtype(){
    return areafieldtype;
}


public String getCronexp(){
    return cronexp;
}


public void setPause(boolean pause){
    this.pause = pause;
}


public int getExecnum(){
    return execnum;
}


public void setNickname(String nickname){
    this.nickname = nickname;
}


public void setDataid(String dataid){
    this.dataid = dataid;
}


public String getDriverclazz(){
    return driverclazz;
}


public int getAithreads(){
    return aithreads;
}


public String getFiletype(){
    return filetype;
}


public void setPriority(int priority){
    this.priority = priority;
}


public void setName(String name){
    this.name = name;
}


public void setSiptrunk(String siptrunk){
    this.siptrunk = siptrunk;
}


public String getMinareavalue(){
    return minareavalue;
}


public void setImptype(String imptype){
    this.imptype = imptype;
}


@Transient
public Reporter getReport(){
    return report;
}


public void setBatchtype(String batchtype){
    this.batchtype = batchtype;
}


public boolean isFetcher(){
    return fetcher;
}


public void setDriverclazz(String driverclazz){
    this.driverclazz = driverclazz;
}


public void setDescription(String description){
    this.description = description;
}


public String getImptype(){
    return imptype;
}


public String getFormatstr(){
    return formatstr;
}


public String getUsername(){
    return username;
}


public void setPlantaskreadtorun(boolean plantaskreadtorun){
    this.plantaskreadtorun = plantaskreadtorun;
}


public Date getCreatetime(){
    return createtime;
}


public String getActid(){
    return actid;
}


public void setAreafield(String areafield){
    this.areafield = areafield;
}


public void setDistpolicy(String distpolicy){
    this.distpolicy = distpolicy;
}


public void setFiltertype(String filtertype){
    this.filtertype = filtertype;
}


public void setValidnum(int validnum){
    this.validnum = validnum;
}


public String getTaskinfo(){
    return taskinfo;
}


public String getProvince(){
    return province;
}


public void setParentid(String parentid){
    this.parentid = parentid;
}


public void setOrganid(String organid){
    this.organid = organid;
}


public void setActid(String actid){
    this.actid = actid;
}


public void setLastdate(Date lastdate){
    this.lastdate = lastdate;
}


public String getParentid(){
    return parentid;
}


public boolean isPlantask(){
    return plantask;
}


public String getUserid(){
    return userid;
}


public Date getLastdate(){
    return lastdate;
}


public String getUsearea(){
    return usearea;
}


public String getExectype(){
    return exectype;
}


public void setUserid(String userid){
    this.userid = userid;
}


public void setRunserver(String runserver){
    this.runserver = runserver;
}


public boolean isCreatetable(){
    return createtable;
}


public String getImpurl(){
    return impurl;
}


public void setExtention(String extention){
    this.extention = extention;
}


public void setTaskfiretime(Date taskfiretime){
    this.taskfiretime = taskfiretime;
}


public void setFetchSize(long fetchSize){
    this.fetchSize = fetchSize;
}


public String getMaxareavalue(){
    return maxareavalue;
}


public String getPassword(){
    return password;
}


public String getDataid(){
    return dataid;
}


public void setTaskstatus(String taskstatus){
    this.taskstatus = taskstatus;
}


public void setAithreads(int aithreads){
    this.aithreads = aithreads;
}


public void setPrefix(boolean prefix){
    this.prefix = prefix;
}


public boolean isEnabletaithreads(){
    return enabletaithreads;
}


public void setAreafieldtype(String areafieldtype){
    this.areafieldtype = areafieldtype;
}


public int getPriority(){
    return priority;
}


public int getThreads(){
    return threads;
}


public void setCreatetable(boolean createtable){
    this.createtable = createtable;
}


public int getNotassigned(){
    return notassigned;
}


public String getName(){
    return name;
}


public void setFilterid(String filterid){
    this.filterid = filterid;
}


public void setImpurl(String impurl){
    this.impurl = impurl;
}


public int getAssigned(){
    return assigned;
}


public String getForecastid(){
    return forecastid;
}


public void setMaxareavalue(String maxareavalue){
    this.maxareavalue = maxareavalue;
}


public long getFetchSize(){
    return fetchSize;
}


public void setNotassigned(int notassigned){
    this.notassigned = notassigned;
}


public int getNamenum(){
    return namenum;
}


public void setExectype(String exectype){
    this.exectype = exectype;
}


public int getDisnum(){
    return disnum;
}


public void setStartindex(long startindex){
    this.startindex = startindex;
}


public void setThreads(int threads){
    this.threads = threads;
}


public void setId(String id){
    this.id = id;
}


public String getBatchtype(){
    return batchtype;
}


public void setExecmd(String execmd){
    this.execmd = execmd;
}


public boolean isPrefix(){
    return prefix;
}


public void setPolicynum(int policynum){
    this.policynum = policynum;
}


public String getCity(){
    return city;
}


public long getStartindex(){
    return startindex;
}


public void setUsearea(String usearea){
    this.usearea = usearea;
}


public int getValidnum(){
    return validnum;
}


public String getReportid(){
    return reportid;
}


public String getDistpolicy(){
    return distpolicy;
}


public int getPolicynum(){
    return policynum;
}


public void setCity(String city){
    this.city = city;
}


public Date getUpdatetime(){
    return updatetime;
}


public void setTaskid(String taskid){
    this.taskid = taskid;
}


public boolean isPause(){
    return pause;
}


public void setReport(Reporter report){
    this.report = report;
}


public void setPlantask(boolean plantask){
    this.plantask = plantask;
}


public void setOrgan(String organ){
    this.organ = organ;
}


public String getOrgan(){
    return organ;
}


public void setEmail(String email){
    this.email = email;
}


public void setExectarget(String exectarget){
    this.exectarget = exectarget;
}


public String getOrganid(){
    return organid;
}


public void setForecastid(String forecastid){
    this.forecastid = forecastid;
}


public String getSource(){
    return source;
}


public String getSiptrunk(){
    return siptrunk;
}


public void setNamenum(int namenum){
    this.namenum = namenum;
}


public void setCreater(String creater){
    this.creater = creater;
}


public String getEmail(){
    return email;
}


public boolean isPlantaskreadtorun(){
    return plantaskreadtorun;
}


public long getLastindex(){
    return lastindex;
}


public void setExecto(String execto){
    this.execto = execto;
}


public String getTaskid(){
    return taskid;
}


public void setSource(String source){
    this.source = source;
}


public void setFetcher(boolean fetcher){
    this.fetcher = fetcher;
}


public String getTasktype(){
    return tasktype;
}


public void setClazz(String clazz){
    this.clazz = clazz;
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


public String getDescription(){
    return description;
}


public void setReportid(String reportid){
    this.reportid = reportid;
}


public void setDisnum(int disnum){
    this.disnum = disnum;
}


public void setAssigned(int assigned){
    this.assigned = assigned;
}


public void setNextfiretime(Date nextfiretime){
    this.nextfiretime = nextfiretime;
}


public String getFilterid(){
    return filterid;
}


public void setLocalserver(String localserver){
    this.localserver = localserver;
}


public String getCreater(){
    return creater;
}


public void setCreatetime(Date createtime){
    this.createtime = createtime;
}


public Date getTaskfiretime(){
    return taskfiretime;
}


public void setArearule(String arearule){
    this.arearule = arearule;
}


public String getTargettask(){
    return targettask;
}


@Transient
public String getExceptionMsg(){
    return exceptionMsg;
}


public String getClazz(){
    return clazz;
}


public String getDatastatus(){
    return datastatus;
}


public void setDistype(String distype){
    this.distype = distype;
}


public void setUsername(String username){
    this.username = username;
}


public String getExecmd(){
    return execmd;
}


public String getDistype(){
    return distype;
}


public void setEndtime(String endtime){
    this.endtime = endtime;
}


public String getArearule(){
    return arearule;
}


public void setBusstype(String busstype){
    this.busstype = busstype;
}


public String getStarttime(){
    return starttime;
}


public String getCrawltask(){
    return crawltask;
}


public void setTargettask(String targettask){
    this.targettask = targettask;
}


public void setStatus(String status){
    this.status = status;
}


public void setTasktype(String tasktype){
    this.tasktype = tasktype;
}


public void setLastindex(long lastindex){
    this.lastindex = lastindex;
}


public void setMemo(String memo){
    this.memo = memo;
}


public String getTemplateid(){
    return templateid;
}


public void setFiletype(String filetype){
    this.filetype = filetype;
}


public String getNickname(){
    return nickname;
}


public String getExectarget(){
    return exectarget;
}


public String getOrgi(){
    return orgi;
}


public String getDbtype(){
    return dbtype;
}


public String getExecto(){
    return execto;
}


public String getEndtime(){
    return endtime;
}


}