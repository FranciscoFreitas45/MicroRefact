package com.ec.survey.model;
 import com.ec.survey.model.administration.User;
import javax.persistence;
import java.util.Date;
@Entity
@Table(name = "WEBSERVICETASK")
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

public WebserviceTask(WebserviceTaskType type) {
    this.type = type;
    this.done = false;
}public WebserviceTask() {
}
public void setAddMeta(boolean addMeta){
    this.addMeta = addMeta;
}


public void setGroupId(int groupId){
    this.groupId = groupId;
}


@Column(name = "WST_START")
public Date getStart(){
    return start;
}


@Column(name = "WST_ADDMETA")
public boolean isAddMeta(){
    return addMeta;
}


public void setResult(String result){
    this.result = result;
}


public void setSurveyUid(String surveyUid){
    this.surveyUid = surveyUid;
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


public void setDone(boolean done){
    this.done = done;
}


public void setExportType(Integer exportType){
    this.exportType = exportType;
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


public void setStart(Date start){
    this.start = start;
}


public void setStarted(Date started){
    this.started = started;
}


public void setId(Integer id){
    this.id = id;
}


public void setEnd(Date end){
    this.end = end;
}


public void setUser(User user){
    this.user = user;
}


public void setContributionType(String contributionType){
    this.contributionType = contributionType;
}


@Column(name = "WST_SURVEYUID")
public String getSurveyUid(){
    return surveyUid;
}


@Column(name = "WST_COUNTER")
public int getCounter(){
    return counter;
}


public void setEmpty(Boolean empty){
    this.empty = empty != null && empty;
}


public void setFileTypes(String fileTypes){
    this.fileTypes = fileTypes;
}


@Column(name = "WST_EMPTYRESULT")
public Boolean isEmpty(){
    return empty;
}


@Column(name = "WST_CREATED")
public Date getCreated(){
    return created;
}


public void setCreated(Date created){
    this.created = created;
}


public void setType(WebserviceTaskType type){
    this.type = type;
}


@Column(name = "WST_DONE")
public boolean isDone(){
    return done;
}


@Column(name = "WST_END")
public Date getEnd(){
    return end;
}


public void setNumber(int number){
    this.number = number;
}


public void setShowIDs(boolean showIDs){
    this.showIDs = showIDs;
}


public void setCounter(int counter){
    this.counter = counter;
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


public void setToken(String token){
    this.token = token;
}


public void setError(String error){
    this.error = error;
}


@Column(name = "WST_RESULT")
public String getResult(){
    return result;
}


@Column(name = "WST_TOKEN")
public String getToken(){
    return token;
}


@Column(name = "WST_SHOWIDS")
public boolean isShowIDs(){
    return showIDs;
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
}


}