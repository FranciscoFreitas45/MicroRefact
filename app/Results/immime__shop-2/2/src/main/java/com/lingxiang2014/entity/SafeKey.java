package com.lingxiang2014.entity;
 import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Transient;
@Embeddable
public class SafeKey implements Serializable{

 private  long serialVersionUID;

 private  String value;

 private  Date expire;


@Column(name = "safe_key_value")
public String getValue(){
    return value;
}


@Transient
public boolean hasExpired(){
    return getExpire() != null && new Date().after(getExpire());
}


public void setExpire(Date expire){
    this.expire = expire;
}


@Column(name = "safe_key_expire")
public Date getExpire(){
    return expire;
}


public void setValue(String value){
    this.value = value;
}


}