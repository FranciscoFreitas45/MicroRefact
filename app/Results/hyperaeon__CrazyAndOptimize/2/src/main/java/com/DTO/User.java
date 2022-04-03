package com.DTO;
 import java.math.BigInteger;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
public class User {

 private  Integer id;

 private  String name;

 private  Integer credential_type;

 private  String credential_number;

 private  String loginName;

 private  String password;

 private  String passwordsha;

 private  String salt;

 private  String email;

 private  String roles;

 private  BigInteger mobileNumber;

 private  Integer security_level;

 private  Integer is_quick_query;

 private  DateTime create_time;

 private  DateTime update_time;

 private  String createUser;

 private  Integer report_status;

 private  Integer user_count;

 private  Integer is_married;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://3";


public String getCreateUser(){
    return createUser;
}


public BigInteger getMobileNumber(){
    return mobileNumber;
}


public String getName(){
    return name;
}


public String getSalt(){
    return salt;
}


public Integer getId(){
    return id;
}


public Integer getIs_quick_query(){
    return is_quick_query;
}


public DateTime getCreate_time(){
    return create_time;
}


public Integer getReport_status(){
    return report_status;
}


public Integer getCredential_type(){
    return credential_type;
}


public String getRoles(){
    return roles;
}


public Integer getUser_count(){
    return user_count;
}


public Integer getIs_married(){
    return is_married;
}


public DateTime getUpdate_time(){
    return update_time;
}


public String getLoginName(){
    return loginName;
}


public String getCredential_number(){
    return credential_number;
}


public String getPassword(){
    return password;
}


public String getEmail(){
    return email;
}


public String getPasswordsha(){
    return passwordsha;
}


public Integer getSecurity_level(){
    return security_level;
}


public void setName(String name){
    this.name = name;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setName"))

.queryParam("name",name)
;
restTemplate.put(builder.toUriString(),null);
}


public void setCredential_number(String credential_number){
    this.credential_number = credential_number;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setCredential_number"))

.queryParam("credential_number",credential_number)
;
restTemplate.put(builder.toUriString(),null);
}


public void setCredential_type(Integer credential_type){
    this.credential_type = credential_type;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setCredential_type"))

.queryParam("credential_type",credential_type)
;
restTemplate.put(builder.toUriString(),null);
}


public void setReport_status(Integer report_status){
    this.report_status = report_status;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setReport_status"))

.queryParam("report_status",report_status)
;
restTemplate.put(builder.toUriString(),null);
}


public void setIs_married(Integer is_married){
    this.is_married = is_married;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setIs_married"))

.queryParam("is_married",is_married)
;
restTemplate.put(builder.toUriString(),null);
}


}