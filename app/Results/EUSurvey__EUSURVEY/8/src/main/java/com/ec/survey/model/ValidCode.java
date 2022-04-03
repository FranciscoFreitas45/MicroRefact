package com.ec.survey.model;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
@Entity
@Table(name = "VALIDCODE", uniqueConstraints = { @UniqueConstraint(columnNames = { "VALIDCODE_CODE" }, name = "VALIDCODE_CODE") })
public class ValidCode {

 private  Integer id;

 private  String code;

 private  String surveyUid;

 private  Date created;

public ValidCode() {
}public ValidCode(String uniqueCode, String surveyUid) {
    code = uniqueCode;
    this.surveyUid = surveyUid;
    created = new Date();
}
public void setSurveyUid(String surveyUid){
    this.surveyUid = surveyUid;
}


public void setCode(String code){
    this.code = code;
}


public void setId(Integer id){
    this.id = id;
}


@Column(name = "VALIDCODE_DATE")
public Date getCreated(){
    return created;
}


@Id
@Column(name = "VALIDCODE_ID")
@GeneratedValue
public Integer getId(){
    return id;
}


public void setCreated(Date created){
    this.created = created;
}


@Column(name = "VALIDCODE_CODE")
public String getCode(){
    return code;
}


@Column(name = "VALIDCODE_SURVEYUID")
public String getSurveyUid(){
    return surveyUid;
}


}