package com.ec.survey.model;
 import com.ec.survey.model.survey.Survey;
import com.ec.survey.tools.ConversionTools;
import com.ec.survey.tools.Tools;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence;
import java.util.Date;
@Entity
@Table(name = "EXPORTS")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Export {

 private  long serialVersionUID;

 private  Integer id;

 private  Survey survey;

 private  ExportState state;

 private  ExportType type;

 private  ExportFormat format;

 private  String name;

 private  Integer userId;

 private  Date date;

 private  boolean valid;

 private  boolean notified;

 private  boolean allAnswers;

 private  ResultFilter resultFilter;

 private  ActivityFilter activityFilter;

 private  Boolean zipped;

 private  Boolean showShortnames;

 private  Boolean addMeta;

 private  Integer participationGroup;

 private  String email;

 private  Boolean forArchiving;

 private  String ecfProfileUid;

 private  String displayUsername;


@Column(name = "EXPORT_NAME")
public String getName(){
    return name;
}


@Column(name = "EXPORT_ZIPPED")
public Boolean getZipped(){
    return zipped;
}


public void setNotified(boolean notified){
    this.notified = notified;
}


@Column(name = "EXPORT_ALLANSWERS")
public boolean isAllAnswers(){
    return allAnswers;
}


@Transient
public String getSurveyShortname(){
    return this.survey == null ? "" : this.survey.getShortname();
}


@OneToOne(cascade = CascadeType.ALL)
@JoinColumn(name = "id_acflt")
public ActivityFilter getActivityFilter(){
    return activityFilter;
}


public void setShowShortnames(Boolean showShortnames){
    this.showShortnames = showShortnames;
}


public void setId(Integer id){
    this.id = id;
}


@Column(name = "EXPORT_FORMAT")
public ExportFormat getFormat(){
    return format;
}


@Transient
public boolean isTypeFiles(){
    return type == ExportType.Files;
}


@Column(name = "EXPORT_SHORTNAMES")
public Boolean getShowShortnames(){
    return showShortnames;
}


public void setZipped(Boolean zipped){
    this.zipped = zipped;
}


@OneToOne(cascade = CascadeType.ALL)
@JoinColumn(name = "id_resflt")
public ResultFilter getResultFilter(){
    return resultFilter;
}


@Transient
public String getFormattedDate(){
    return Tools.formatDate(date, ConversionTools.DateTimeFormat);
}


@com.fasterxml.jackson.annotation.JsonIgnore
@ManyToOne
@JoinColumn(name = "SURVEY_ID")
public Survey getSurvey(){
    return survey;
}


public void setActivityFilter(ActivityFilter activityFilter){
    this.activityFilter = activityFilter;
}


@Transient
public boolean isFinished(){
    return state == ExportState.Finished;
}


public void setEmail(String email){
    this.email = email;
}


@Column(name = "EXPORT_TYPE")
public ExportType getType(){
    return type;
}


@Transient
public boolean isTypeTokens(){
    return type == ExportType.Tokens;
}


@Column(name = "EXPORT_EMAIL")
public String getEmail(){
    return email;
}


public void setUserId(Integer userId){
    this.userId = userId != null ? userId : 0;
}


public void setParticipationGroup(Integer participationGroup){
    this.participationGroup = participationGroup;
}


public void setForArchiving(Boolean forArchiving){
    this.forArchiving = forArchiving;
}


public void setName(String name){
    this.name = name;
}


public void setAddMeta(Boolean addMeta){
    this.addMeta = addMeta;
}


@Column(name = "EXPORT_META")
public Boolean getAddMeta(){
    return addMeta;
}


@Id
@Column(name = "EXPORT_ID")
@GeneratedValue
public Integer getId(){
    return id;
}


@Transient
public String getDisplayUsername(){
    return displayUsername;
}


@Transient
public boolean isTypeActivity(){
    return type == ExportType.Activity;
}


@Transient
public boolean isTypeSurvey(){
    return type == ExportType.Survey;
}


@Transient
public void setDisplayUsername(String displayUsername){
    this.displayUsername = displayUsername;
}


@Column(name = "EXPORT_NOT")
public boolean isNotified(){
    return notified;
}


public void setFormat(ExportFormat format){
    this.format = format;
}


@Transient
public boolean isTypeContent(){
    return type == ExportType.Content;
}


@Transient
public boolean isTypeStatisticsQuiz(){
    return type == ExportType.StatisticsQuiz;
}


@Column(name = "EXPORT_DATE")
@DateTimeFormat(pattern = ConversionTools.DateTimeFormat)
public Date getDate(){
    return date;
}


public void setAllAnswers(Boolean allAnswers){
    this.allAnswers = allAnswers != null && allAnswers;
}


@Transient
public String getSurveyTitle(){
    return this.survey == null ? "" : this.survey.getTitle();
}


@Transient
public boolean isTypeAddressBook(){
    return type == ExportType.AddressBook;
}


public void setValid(boolean valid){
    this.valid = valid;
}


public void setResultFilter(ResultFilter resultFilter){
    this.resultFilter = resultFilter;
}


@Column(name = "EXPORT_VALID")
public boolean isValid(){
    return valid;
}


@Column(name = "EXPORT_PARTGROUP")
public Integer getParticipationGroup(){
    return participationGroup;
}


@Column(name = "EXPORT_FORARCHIVING")
public Boolean isForArchiving(){
    return forArchiving;
}


public void setType(ExportType type){
    this.type = type;
}


public void setSurvey(Survey survey){
    this.survey = survey;
}


@Column(name = "EXPORT_STATE")
public ExportState getState(){
    return state;
}


public void setState(ExportState state){
    this.state = state;
}


public void setDate(Date date){
    this.date = date;
}


@Transient
public boolean isTypeStatistics(){
    return type == ExportType.Statistics;
}


@Column(name = "USER_ID")
public int getUserId(){
    return userId != null ? userId : 0;
}


}