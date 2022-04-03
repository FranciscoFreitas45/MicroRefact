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

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://4";


@Column(name = "EXPORT_NAME")
public String getName(){
    return name;
}


@Column(name = "EXPORT_ZIPPED")
public Boolean getZipped(){
    return zipped;
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


@Column(name = "EXPORT_TYPE")
public ExportType getType(){
    return type;
}


@Column(name = "EXPORT_EMAIL")
public String getEmail(){
    return email;
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


@Column(name = "EXPORT_DATE")
@DateTimeFormat(pattern = ConversionTools.DateTimeFormat)
public Date getDate(){
    return date;
}


@Transient
public String getSurveyTitle(){
    return this.survey == null ? "" : this.survey.getTitle();
}


@Column(name = "EXPORT_PARTGROUP")
public Integer getParticipationGroup(){
    return participationGroup;
}


@Column(name = "EXPORT_STATE")
public ExportState getState(){
    return state;
}


@Column(name = "USER_ID")
public int getUserId(){
    return userId != null ? userId : 0;
}


@Column(name = "EXPORT_ALLANSWERS")
public boolean isAllAnswers(){
    return allAnswers;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/isAllAnswers"))

;
boolean aux = restTemplate.getForObject(builder.toUriString(),boolean.class);
return aux;
}


}