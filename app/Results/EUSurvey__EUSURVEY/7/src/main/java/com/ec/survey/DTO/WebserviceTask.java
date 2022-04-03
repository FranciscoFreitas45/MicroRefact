package com.ec.survey.DTO;
 import com.ec.survey.model.administration.User;
import javax.persistence;
import java.util.Date;
public class WebserviceTask {

 private  int id;

 private  User user;

 private  WebserviceTaskType type;

 private  int groupId;

 private  int surveyId;

 private  String surveyUid;

 private  int number;

 private  Date start;

 private  Date end;

 private  boolean done;

 private  String result;

 private  String error;

 private  String token;

 private  boolean showIDs;

 private  boolean addMeta;

 private  Integer exportType;

 private  String contributionType;

 private  String fileTypes;

 private  Date created;

 private  Date started;

 private  int counter;

 private  boolean empty;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://4";

public WebserviceTask(WebserviceTaskType type) {
    this.type = type;
    this.done = false;
}public WebserviceTask() {
}
@Column(name = "WST_START")
public Date getStart(){
    return start;
}


@ManyToOne
@JoinColumn(name = "WST_USER")
public User getUser(){
    return user;
}


@Id
@Column(name = "WST_ID")
@GeneratedValue
public Integer getId(){
    return id;
}


@Column(name = "WST_GROUP")
public int getGroupId(){
    return groupId;
}


@Column(name = "WST_NUM")
public int getNumber(){
    return number;
}


@Column(name = "WST_CONTRIBTYPE")
public String getContributionType(){
    return contributionType;
}


@Column(name = "WST_SURVEYUID")
public String getSurveyUid(){
    return surveyUid;
}


@Column(name = "WST_COUNTER")
public int getCounter(){
    return counter;
}


@Column(name = "WST_CREATED")
public Date getCreated(){
    return created;
}


@Column(name = "WST_END")
public Date getEnd(){
    return end;
}


@Column(name = "WST_ERROR")
public String getError(){
    return error;
}


@Column(name = "WST_STARTED")
public Date getStarted(){
    return started;
}


public WebserviceTaskType getType(){
    return type;
}


@Column(name = "WST_RESULT")
public String getResult(){
    return result;
}


@Column(name = "WST_TOKEN")
public String getToken(){
    return token;
}


@Column(name = "WST_FILETYPES")
public String getFileTypes(){
    return fileTypes;
}


@Column(name = "WST_SURVEYID")
public int getSurveyId(){
    return surveyId;
}


@Column(name = "WST_EXPORTTYPE")
public Integer getExportType(){
    return exportType;
}


public void setSurveyId(int surveyId){
    this.surveyId = surveyId;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setSurveyId"))

.queryParam("surveyId",surveyId)
;
restTemplate.put(builder.toUriString(),null);
}


public void setSurveyUid(String surveyUid){
    this.surveyUid = surveyUid;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setSurveyUid"))

.queryParam("surveyUid",surveyUid)
;
restTemplate.put(builder.toUriString(),null);
}


public void setExportType(Integer exportType){
    this.exportType = exportType;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setExportType"))

.queryParam("exportType",exportType)
;
restTemplate.put(builder.toUriString(),null);
}


public void setFileTypes(String fileTypes){
    this.fileTypes = fileTypes;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setFileTypes"))

.queryParam("fileTypes",fileTypes)
;
restTemplate.put(builder.toUriString(),null);
}


public void setContributionType(String contributionType){
    this.contributionType = contributionType;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setContributionType"))

.queryParam("contributionType",contributionType)
;
restTemplate.put(builder.toUriString(),null);
}


public void setStart(Date start){
    this.start = start;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setStart"))

.queryParam("start",start)
;
restTemplate.put(builder.toUriString(),null);
}


public void setEnd(Date end){
    this.end = end;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setEnd"))

.queryParam("end",end)
;
restTemplate.put(builder.toUriString(),null);
}


public void setShowIDs(boolean showIDs){
    this.showIDs = showIDs;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setShowIDs"))

.queryParam("showIDs",showIDs)
;
restTemplate.put(builder.toUriString(),null);
}


public void setUser(User user){
    this.user = user;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setUser"))

.queryParam("user",user)
;
restTemplate.put(builder.toUriString(),null);
}


public void setCreated(Date created){
    this.created = created;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setCreated"))

.queryParam("created",created)
;
restTemplate.put(builder.toUriString(),null);
}


public void setAddMeta(boolean addMeta){
    this.addMeta = addMeta;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setAddMeta"))

.queryParam("addMeta",addMeta)
;
restTemplate.put(builder.toUriString(),null);
}


public void setGroupId(int groupId){
    this.groupId = groupId;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setGroupId"))

.queryParam("groupId",groupId)
;
restTemplate.put(builder.toUriString(),null);
}


public void setNumber(int number){
    this.number = number;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setNumber"))

.queryParam("number",number)
;
restTemplate.put(builder.toUriString(),null);
}


public void setToken(String token){
    this.token = token;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setToken"))

.queryParam("token",token)
;
restTemplate.put(builder.toUriString(),null);
}


@Column(name = "WST_DONE")
public boolean isDone(){
    return done;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/isDone"))

;
boolean aux = restTemplate.getForObject(builder.toUriString(),boolean.class);
return aux;
}


@Column(name = "WST_EMPTYRESULT")
public Boolean isEmpty(){
    return empty;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/isEmpty"))

;
Boolean aux = restTemplate.getForObject(builder.toUriString(),Boolean.class);
return aux;
}


}