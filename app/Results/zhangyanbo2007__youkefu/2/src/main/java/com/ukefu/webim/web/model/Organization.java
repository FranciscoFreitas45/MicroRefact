package com.ukefu.webim.web.model;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
@Entity
@Table(name = "uk_organization")
@org.hibernate.annotations.Proxy(lazy = false)
public class Organization {

 private  long serialVersionUID;

 private  String id;

 private  String name;

 private  String code;

 private  String orgtype;

 private  String orgscale;

 private  String orgindustry;

 private  Date createtime;

 private  String logo;

 private  String memo;


public void setName(String name){
    this.name = name;
}


public void setOrgtype(String orgtype){
    this.orgtype = orgtype;
}


public void setLogo(String logo){
    this.logo = logo;
}


public String getName(){
    return name;
}


public String getOrgtype(){
    return orgtype;
}


public String getLogo(){
    return logo;
}


public void setCode(String code){
    this.code = code;
}


public void setOrgscale(String orgscale){
    this.orgscale = orgscale;
}


@Id
@Column(length = 32)
@GeneratedValue(generator = "system-uuid")
@GenericGenerator(name = "system-uuid", strategy = "uuid")
public String getId(){
    return id;
}


public void setMemo(String memo){
    this.memo = memo;
}


public String getMemo(){
    return memo;
}


public Date getCreatetime(){
    return createtime;
}


public String getOrgscale(){
    return orgscale;
}


public void setOrgindustry(String orgindustry){
    this.orgindustry = orgindustry;
}


public void setId(String id){
    this.id = id;
}


public void setCreatetime(Date createtime){
    this.createtime = createtime;
}


public String getCode(){
    return code;
}


public String getOrgindustry(){
    return orgindustry;
}


}