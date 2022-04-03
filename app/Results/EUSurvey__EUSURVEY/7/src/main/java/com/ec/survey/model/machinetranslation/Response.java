package com.ec.survey.model.machinetranslation;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.format.annotation.DateTimeFormat;
import com.ec.survey.tools.ConversionTools;
@Entity
@Table(name = "MT_RESPONSE")
public class Response {

 private  long serialVersionUID;

 private  Integer id;

 private  Request request;

 private  String targetLang;

 private  String translatedText;

 private  String deliveryURL;

 private  String errCode;

 private  String errMsg;

 private  Date created;

 private  boolean isTranslated;

 private  boolean isUpdated;

public Response() {
    created = new Date();
}
public void setRequest(Request request){
    this.request = request;
}


public void setTargetLang(String targetLang){
    this.targetLang = targetLang;
}


@ManyToOne
@JoinColumn(name = "REQUEST_ID", nullable = false)
public Request getRequest(){
    return request;
}


public void setDeliveryURL(String deliveryURL){
    this.deliveryURL = deliveryURL;
}


@Column(name = "TARGET_LANG", nullable = false)
public String getTargetLang(){
    return targetLang;
}


@Column(name = "IS_TRANSLATED", nullable = false)
public boolean isTranslated(){
    return isTranslated;
}


@Temporal(TemporalType.TIMESTAMP)
@Column(name = "CREATED", nullable = false)
@DateTimeFormat(pattern = ConversionTools.DateTimeFormatSmall)
public Date getCreated(){
    return created;
}


@Column(name = "IS_UPDATED", nullable = false)
public boolean isUpdated(){
    return isUpdated;
}


@Id
@Column(name = "ID", nullable = false)
@GeneratedValue
public Integer getId(){
    return id;
}


public void setCreated(Date created){
    this.created = created;
}


@Column(name = "ERR_CODE")
public String getErrCode(){
    return errCode;
}


@Column(name = "TRANSLATED_TEXT", nullable = true, length = 4000)
public String getTranslatedText(){
    return translatedText;
}


public void setErrCode(String errCode){
    this.errCode = errCode;
}


@Column(name = "DELIVERY_URL", nullable = true)
public String getDeliveryURL(){
    return deliveryURL;
}


public void setId(Integer id){
    this.id = id;
}


public void setTranslated(boolean translated){
    this.isTranslated = translated;
}


public void setTranslatedText(String translatedText){
    this.translatedText = translatedText;
}


public void setUpdated(boolean updated){
    this.isUpdated = updated;
}


@Column(name = "ERR_MSG")
public String getErrMsg(){
    return errMsg;
}


public void setErrMsg(String errMsg){
    this.errMsg = errMsg;
}


}