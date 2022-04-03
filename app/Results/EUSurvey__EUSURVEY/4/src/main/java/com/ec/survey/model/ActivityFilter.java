package com.ec.survey.model;
 import com.ec.survey.tools.Tools;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
@Entity
@Table(name = "ACTIVITYFILTER")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ActivityFilter {

 private  long serialVersionUID;

 private  int id;

 private  int userId;

 private  String surveyUid;

 private  int logId;

 private  Date dateFrom;

 private  Date dateTo;

 private  String object;

 private  String property;

 private  String event;

 private  String description;

 private  String oldValue;

 private  String newValue;

 private  String sortKey;

 private  String sortOrder;

 private  Set<String> visibleColumns;

 private  Set<String> exportedColumns;


@Column(name = "ACFILTER_EVENT")
public String getEvent(){
    return event;
}


@Column(name = "ACFILTER_DATEFROM")
@DateTimeFormat(pattern = "dd/MM/yyyy")
public Date getDateFrom(){
    return dateFrom;
}


@Transient
public String getHash(){
    String result = String.valueOf(this.logId) + this.surveyUid + this.userId + this.dateFrom + this.dateTo + this.description + this.event + this.object + this.oldValue + this.newValue + this.property;
    return Tools.md5hash(result);
}


public void setVisibleColumns(Set<String> visibleColumns){
    this.visibleColumns = visibleColumns;
}


public void setSurveyUid(String surveyUid){
    this.surveyUid = surveyUid;
}


public void setExportedColumns(Set<String> exportedColumns){
    this.exportedColumns = exportedColumns;
}


@Id
@Column(name = "ACFILTER_ID")
@GeneratedValue
public Integer getId(){
    return id;
}


@Column(name = "ACFILTER_LOGID")
public int getLogId(){
    return logId;
}


@Column(name = "ACFILTER_SORTKEY")
public String getSortKey(){
    return sortKey;
}


public void setDescription(String description){
    this.description = description;
}


@Column(name = "ACFILTER_NEW")
public String getNewValue(){
    return newValue;
}


@Transient
public String[] getAllEvents(){
    String[] result = { "Created", "Deleted", "Modified", "Applied", "Discarded", "Opened", "Saved", "Added", "Removed", "Enabled", "Disabled", "Started", "Requested", "Returned", "Submitted", "Paused", "Sent" };
    Arrays.sort(result);
    return result;
}


public void setObject(String object){
    this.object = object;
}


@Column(name = "ACFILTER_DESC")
public String getDescription(){
    return description;
}


public void setSortKey(String sortKey){
    this.sortKey = sortKey;
}


@Column(name = "ACFILTER_SORTORDER")
public String getSortOrder(){
    return sortOrder;
}


public void setNewValue(String newValue){
    this.newValue = newValue;
}


@ElementCollection
public Set<String> getVisibleColumns(){
    return visibleColumns;
}


@Transient
public String[] getAllObjects(){
    String[] result = { "Survey", "DraftSurvey", "Results", "Contribution", "GuestList", "Privileges", "Messages", "Comment" };
    Arrays.sort(result);
    return result;
}


public void setId(Integer id){
    this.id = id;
}


public ActivityFilter copy(){
    ActivityFilter copy = new ActivityFilter();
    copy.logId = logId;
    copy.dateFrom = dateFrom;
    copy.dateTo = dateTo;
    copy.sortKey = sortKey;
    copy.sortOrder = sortOrder;
    copy.description = description;
    copy.event = event;
    copy.newValue = newValue;
    copy.oldValue = oldValue;
    copy.object = object;
    copy.property = property;
    Set<String> newVisibleColumns = new HashSet<>();
    newVisibleColumns.addAll(visibleColumns);
    copy.visibleColumns = newVisibleColumns;
    Set<String> newExportedColumns = new HashSet<>();
    newExportedColumns.addAll(exportedColumns);
    copy.exportedColumns = newExportedColumns;
    copy.userId = userId;
    copy.surveyUid = surveyUid;
    return copy;
}


public void setDateFrom(Date dateFrom){
    this.dateFrom = dateFrom;
}


@Column(name = "ACFILTER_SURVEY")
public String getSurveyUid(){
    return surveyUid;
}


@Transient
public boolean exported(String key){
    return exportedColumns.contains(key);
}


@Column(name = "ACFILTER_OBJECT")
public String getObject(){
    return object;
}


@Column(name = "ACFILTER_PROP")
public String getProperty(){
    return property;
}


@Transient
public boolean visible(String key){
    return visibleColumns.contains(key);
}


public void setSortOrder(String sortOrder){
    this.sortOrder = sortOrder;
}


public void setOldValue(String oldValue){
    this.oldValue = oldValue;
}


public void setEvent(String event){
    this.event = event;
}


@Column(name = "ACFILTER_OLD")
public String getOldValue(){
    return oldValue;
}


public void setLogId(int logId){
    this.logId = logId;
}


@Transient
public String[] getAllProperties(){
    String[] result = { "n/a", "State", "PendingChanges", "Alias", "EndNotificationState", "EndNotificationValue", "EndNotificationReach", "ContactCreation", "Security", "Password", "Anonymity", "Privacy", "Captcha", "EditContribution", "MultiPaging", "PageWiseValidation", "Properties", "UsefulLink", "BackgroundDocument", "Title", "PivotLanguage", "Contact", "Autopublish", "StartDate", "EndDate", "Logo", "Skin", "AutoNumberingSections", "AutoNumberingQuestions", "ElementOrder", "SurveyElement", "Translation", "ConfirmationPage", "EscapePage", "PublishIndividual", "PublishCharts", "PublishStatistics", "PublicSearch", "PublishQuestionSelection", "PublishAnswerSelection", "ExportStatistics", "ExportContent", "ExportCharts", "Export", "Token/Contacts/Department", "Invitations", "WCAGCompliance", "EndNotificationMessage", "DeleteColumn" };
    Arrays.sort(result);
    return result;
}


public void setDateTo(Date dateTo){
    this.dateTo = dateTo;
}


@Column(name = "ACFILTER_DATETO")
@DateTimeFormat(pattern = "dd/MM/yyyy")
public Date getDateTo(){
    return dateTo;
}


public void setProperty(String property){
    this.property = property;
}


@Column(name = "ACFILTER_USER")
public int getUserId(){
    return userId;
}


public void setUserId(int userId){
    this.userId = userId;
}


@ElementCollection
public Set<String> getExportedColumns(){
    return exportedColumns;
}


}