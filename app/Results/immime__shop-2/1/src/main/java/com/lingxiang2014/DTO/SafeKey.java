package com.lingxiang2014.DTO;
 import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Transient;
public class SafeKey implements Serializable{

 private  long serialVersionUID;

 private  String value;

 private  Date expire;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://2";


@Column(name = "safe_key_value")
public String getValue(){
    return value;
}


@Column(name = "safe_key_expire")
public Date getExpire(){
    return expire;
}


public void setValue(String value){
    this.value = value;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setValue"))

.queryParam("value",value)
;
restTemplate.put(builder.toUriString(),null);
}


public void setExpire(Date expire){
    this.expire = expire;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setExpire"))

.queryParam("expire",expire)
;
restTemplate.put(builder.toUriString(),null);
}


@Transient
public boolean hasExpired(){
    return getExpire() != null && new Date().after(getExpire());
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/hasExpired"))

;
boolean aux = restTemplate.getForObject(builder.toUriString(),boolean.class);
return aux;
}


}