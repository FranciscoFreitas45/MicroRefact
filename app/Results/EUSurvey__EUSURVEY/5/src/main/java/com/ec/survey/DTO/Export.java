package com.ec.survey.DTO;
 import com.ec.survey.model.survey.Survey;
import com.ec.survey.tools.ConversionTools;
import com.ec.survey.tools.Tools;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence;
import java.util.Date;
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


public void setId(Integer id){
    this.id = id;
}


@Column(name = "EXPORT_FORMAT")
public ExportFormat getFormat(){
    return format;
}


@Column(name = "EXPORT_SHORTNAMES")
public Boolean getShowShortnames(){
    return showShortnames;
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


@Transient
public boolean isFinished(){
    return state == ExportState.Finished;
}


@Column(name = "EXPORT_TYPE")
public ExportType getType(){
    return type;
}


@Column(name = "EXPORT_EMAIL")
public String getEmail(){
    return email;
}


public void setParticipationGroup(Integer participationGroup){
    this.participationGroup = participationGroup;
}


public void setName(String name){
    this.name = name;
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
public boolean isTypeSurvey(){
    return type == ExportType.Survey;
}


@Column(name = "EXPORT_NOT")
public boolean isNotified(){
    return notified;
}


@Transient
public boolean isTypeContent(){
    return type == ExportType.Content;
}


@Column(name = "EXPORT_DATE")
@DateTimeFormat(pattern = ConversionTools.DateTimeFormat)
public Date getDate(){
    return date;
}


@Transient
public String getSurveyTitle(){
    return this.survey == null ? "" : this.survey.getTitle();
}


public void setValid(boolean valid){
    this.valid = valid;
}


@Column(name = "EXPORT_VALID")
public boolean isValid(){
    return valid;
}


@Column(name = "EXPORT_PARTGROUP")
public Integer getParticipationGroup(){
    return participationGroup;
}


public void setType(ExportType type){
    this.type = type;
}


@Column(name = "EXPORT_STATE")
public ExportState getState(){
    return state;
}


public void setDate(Date date){
    this.date = date;
}


@Column(name = "USER_ID")
public int getUserId(){
    return userId != null ? userId : 0;
}


}