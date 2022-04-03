package com.ec.survey.DTO;
 import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.persistence;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
public class MailTask {

 private  long serialVersionUID;

 private  Integer id;

 private  String selectedAttendee;

 private  int participationGroupId;

 private  int userId;

 private  String surveyUid;

 private  String senderAddress;

 private  String senderSubject;

 private  String text1;

 private  String text2;

 private  String parametersSerialized;

 private  String locale;

 private  String state;

 private  String message;

 private  int mailsSent;

 private  boolean notified;

 private  String mailtemplate;

 public  String WAITING;

 public  String FINISHED;

 public  String ERROR;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://3";


@Column(name = "MAILTASK_ATT")
public String getSelectedAttendee(){
    return selectedAttendee;
}


@Column(name = "MAILTASK_SENT")
public int getMailsSent(){
    return mailsSent;
}


@Id
@Column(name = "MAILTASK_ID")
@GeneratedValue
public Integer getId(){
    return id;
}


@Column(name = "MAILTASK_TEMPLATE")
public String getMailtemplate(){
    return mailtemplate;
}


@Column(name = "MAILTASK_S")
public String getSenderAddress(){
    return senderAddress;
}


@Column(name = "MAILTASK_PARAMS")
@Lob
public String getParametersSerialized(){
    return parametersSerialized;
}


@Column(name = "MAILTASK_SURVEY")
public String getSurveyUid(){
    return surveyUid;
}


@Column(name = "MAILTASK_T1")
@Lob
public String getText1(){
    return text1;
}


@Column(name = "MAILTASK_T2")
@Lob
public String getText2(){
    return text2;
}


@Column(name = "MAILTASK_RESULT")
public String getMessage(){
    return message;
}


@Column(name = "MAILTASK_PG")
public int getParticipationGroupId(){
    return participationGroupId;
}


@Column(name = "MAILTASK_SUBJECT")
public String getSenderSubject(){
    return senderSubject;
}


@Column(name = "MAILTASK_STATE")
public String getState(){
    return state;
}


@SuppressWarnings("unchecked")
@Transient
public Map<String,String[]> getParameters(){
    return parametersSerialized != null ? new ObjectMapper().readValue(parametersSerialized, HashMap.class) : null;
}


@Column(name = "MAILTASK_LOCALE")
public String getLocale(){
    return locale;
}


@Column(name = "MAILTASK_USER")
public int getUserId(){
    return userId;
}


public void setNotified(boolean notified){
    this.notified = notified;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setNotified"))

.queryParam("notified",notified)
;
restTemplate.put(builder.toUriString(),null);
}


}