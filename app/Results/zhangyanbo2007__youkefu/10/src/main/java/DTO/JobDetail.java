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


public String getExtention(){
    return extention;
}


public String getJdbcurl(){
    return jdbcurl;
}


public String getTaskstatus(){
    return taskstatus;
}


public Date getNextfiretime(){
    return nextfiretime;
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


public String getAreafield(){
    return areafield;
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


public String getDriverclazz(){
    return driverclazz;
}


public int getAithreads(){
    return aithreads;
}


public String getFiletype(){
    return filetype;
}


public String getMinareavalue(){
    return minareavalue;
}


@Transient
public Reporter getReport(){
    return report;
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


public String getTaskinfo(){
    return taskinfo;
}


public String getProvince(){
    return province;
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


public String getImpurl(){
    return impurl;
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


public String getBatchtype(){
    return batchtype;
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


public String getOrgan(){
    return organ;
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


public String getEmail(){
    return email;
}


public long getLastindex(){
    return lastindex;
}


public String getTaskid(){
    return taskid;
}


public String getTasktype(){
    return tasktype;
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


public void setAssigned(int assigned){
    this.assigned = assigned;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setAssigned"))

.queryParam("assigned",assigned)
;
restTemplate.put(builder.toUriString(),null);
}


public void setNotassigned(int notassigned){
    this.notassigned = notassigned;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setNotassigned"))

.queryParam("notassigned",notassigned)
;
restTemplate.put(builder.toUriString(),null);
}


}