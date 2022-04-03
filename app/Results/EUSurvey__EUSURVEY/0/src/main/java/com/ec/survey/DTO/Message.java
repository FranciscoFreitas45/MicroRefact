package com.ec.survey.DTO;
 import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import org.springframework.format.annotation.DateTimeFormat;
import com.ec.survey.tools.ConversionTools;
import com.ec.survey.tools.Tools;
public class Message {

 private  int id;

 private  int criticality;

 private  int time;

 private  String text;

 private  boolean active;

 private  int type;

 private  int version;

 private  List<MessageType> types;

 private  boolean alreadyShown;

 private  Date autoDeactivate;

 private  Integer userId;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://2";


@Temporal(TemporalType.TIMESTAMP)
@Column(name = "M_AUTODEAC")
@DateTimeFormat(pattern = ConversionTools.DateTimeFormat)
public Date getAutoDeactivate(){
    return autoDeactivate;
}


@Transient
public String getAutoDeactivateTime(){
    if (autoDeactivate != null) {
        String result = Tools.formatDate(autoDeactivate, "HH:mm").substring(0, 2);
        if (result.startsWith("0"))
            result = result.substring(1, 2);
        return result;
    }
    return "0";
}


@Column(name = "M_CRITICALITY")
public int getCriticality(){
    return criticality;
}


@Transient
public String getCss(){
    if (types != null) {
        for (MessageType type : types) {
            if (type.getCriticality() == criticality) {
                return type.getCss();
            }
        }
    }
    return null;
}


@Column(name = "M_TIME")
public int getTime(){
    return time;
}


@Id
@Column(name = "M_ID")
@GeneratedValue
public Integer getId(){
    return id;
}


@Transient
public String getAutoDeactivateDate(){
    if (autoDeactivate != null) {
        return Tools.formatDate(autoDeactivate, ConversionTools.DateFormat);
    }
    return "";
}


@Column(name = "M_VERSION")
public int getVersion(){
    return version;
}


@Lob
@Column(name = "M_TEXT")
public String getText(){
    return text;
}


@Transient
public String getIcon(){
    if (types != null) {
        for (MessageType type : types) {
            if (type.getCriticality() == criticality) {
                return type.getIcon();
            }
        }
    }
    return null;
}


@Column(name = "M_TYPE")
public int getType(){
    return type;
}


@Transient
public List<MessageType> getTypes(){
    return types;
}


@Column(name = "M_USER")
public Integer getUserId(){
    return userId;
}


public void setType(int type){
    this.type = type;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setType"))

.queryParam("type",type)
;
restTemplate.put(builder.toUriString(),null);
}


public void setCriticality(int criticality){
    this.criticality = criticality;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setCriticality"))

.queryParam("criticality",criticality)
;
restTemplate.put(builder.toUriString(),null);
}


public void setText(String text){
    this.text = text;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setText"))

.queryParam("text",text)
;
restTemplate.put(builder.toUriString(),null);
}


public void setUserId(Integer userId){
    this.userId = userId;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setUserId"))

.queryParam("userId",userId)
;
restTemplate.put(builder.toUriString(),null);
}


}