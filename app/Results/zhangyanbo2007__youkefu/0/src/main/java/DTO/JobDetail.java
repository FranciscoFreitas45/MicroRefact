package DTO;
 import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.hibernate.annotations.GenericGenerator;
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

 private  String exceptionMsg;

 private  Reporter report;

 private  String filtertype;

 private  String templateid;

 private  String extention;

 private  boolean enabletaithreads;

 private  int aithreads;

 private  String forecastid;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://7";


public void setMinareavalue(String minareavalue){
    this.minareavalue = minareavalue;
}


public String getExtention(){
    return extention;
}


public String getJdbcurl(){
    return jdbcurl;
}


public void setExecnum(int execnum){
    this.execnum = execnum;
}


public String getTaskstatus(){
    return taskstatus;
}


public void setDicid(String dicid){
    this.dicid = dicid;
}


public Date getNextfiretime(){
    return nextfiretime;
}


public void setExceptionMsg(String exceptionMsg){
    this.exceptionMsg = exceptionMsg;
}


public void setDatastatus(String datastatus){
    this.datastatus = datastatus;
}


public String getStatus(){
    return status;
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


public void setCrawltask(String crawltask){
    this.crawltask = crawltask;
}


public String getAreafield(){
    return areafield;
}


public void setCrawltaskid(String crawltaskid){
    this.crawltaskid = crawltaskid;
}


public void setFormatstr(String formatstr){
    this.formatstr = formatstr;
}


public String getCrawltaskid(){
    return crawltaskid;
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


public String getAreafieldtype(){
    return areafieldtype;
}


public String getCronexp(){
    return cronexp;
}


public int getExecnum(){
    return execnum;
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


public void setName(String name){
    this.name = name;
}


public String getMinareavalue(){
    return minareavalue;
}


@Transient
public Reporter getReport(){
    return report;
}


public boolean isFetcher(){
    return fetcher;
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


public Date getCreatetime(){
    return createtime;
}


public String getActid(){
    return actid;
}


public void setDistpolicy(String distpolicy){
    this.distpolicy = distpolicy;
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


public void setOrganid(String organid){
    this.organid = organid;
}


public void setLastdate(Date lastdate){
    this.lastdate = lastdate;
}


public String getParentid(){
    return parentid;
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


public void setRunserver(String runserver){
    this.runserver = runserver;
}


public String getImpurl(){
    return impurl;
}


public void setTaskfiretime(Date taskfiretime){
    this.taskfiretime = taskfiretime;
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


public void setAithreads(int aithreads){
    this.aithreads = aithreads;
}


public boolean isEnabletaithreads(){
    return enabletaithreads;
}


public int getPriority(){
    return priority;
}


public int getThreads(){
    return threads;
}


public int getNotassigned(){
    return notassigned;
}


public String getName(){
    return name;
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


public long getFetchSize(){
    return fetchSize;
}


public int getNamenum(){
    return namenum;
}


public int getDisnum(){
    return disnum;
}


public void setThreads(int threads){
    this.threads = threads;
}


public String getBatchtype(){
    return batchtype;
}


public boolean isPrefix(){
    return prefix;
}


public String getCity(){
    return city;
}


public long getStartindex(){
    return startindex;
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


public Date getUpdatetime(){
    return updatetime;
}


public boolean isPause(){
    return pause;
}


public void setPlantask(boolean plantask){
    this.plantask = plantask;
}


public String getOrgan(){
    return organ;
}


public void setExectarget(String exectarget){
    this.exectarget = exectarget;
}


public String getOrganid(){
    return organid;
}


public String getSource(){
    return source;
}


public String getSiptrunk(){
    return siptrunk;
}


public void setCreater(String creater){
    this.creater = creater;
}


public String getEmail(){
    return email;
}


public long getLastindex(){
    return lastindex;
}


public String getTaskid(){
    return taskid;
}


public void setFetcher(boolean fetcher){
    this.fetcher = fetcher;
}


public String getTasktype(){
    return tasktype;
}


public void setUpdatetime(Date updatetime){
    this.updatetime = updatetime;
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


public void setDisnum(int disnum){
    this.disnum = disnum;
}


public void setNextfiretime(Date nextfiretime){
    this.nextfiretime = nextfiretime;
}


public String getFilterid(){
    return filterid;
}


public String getCreater(){
    return creater;
}


public Date getTaskfiretime(){
    return taskfiretime;
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


public void setUsername(String username){
    this.username = username;
}


public String getExecmd(){
    return execmd;
}


public String getDistype(){
    return distype;
}


public String getArearule(){
    return arearule;
}


public String getStarttime(){
    return starttime;
}


public String getCrawltask(){
    return crawltask;
}


public void setStatus(String status){
    this.status = status;
}


public void setLastindex(long lastindex){
    this.lastindex = lastindex;
}


public String getTemplateid(){
    return templateid;
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


public void setTaskstatus(String taskstatus){
    this.taskstatus = taskstatus;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setTaskstatus"))

.queryParam("taskstatus",taskstatus)
;
restTemplate.put(builder.toUriString(),null);
}


}