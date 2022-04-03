package net.shangtech.weixin.site.entity;
 import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import net.shangtech.ssh.core.base.IBaseEntity;
@Entity
@Table(name = "site_property")
public class SiteProperty extends IBaseEntity{

 private  long serialVersionUID;

@Column(name = "sys_user_id")
 private  Integer sysUserId;

@Column(name = "site_id")
 private  Integer siteId;

@Column(name = "property_name")
 private  String propertyName;

@Column(name = "property_value")
 private  String propertyValue;

@Column(name = "is_multiple")
 private  Boolean isMultiple;


public Boolean getIsMultiple(){
    return isMultiple;
}


public void setSiteId(Integer siteId){
    this.siteId = siteId;
}


public void setPropertyName(String propertyName){
    this.propertyName = propertyName;
}


public String getPropertyValue(){
    return propertyValue;
}


public void setPropertyValue(String propertyValue){
    this.propertyValue = propertyValue;
}


public void setSysUserId(Integer sysUserId){
    this.sysUserId = sysUserId;
}


public void setIsMultiple(Boolean isMultiple){
    this.isMultiple = isMultiple;
}


public Integer getSiteId(){
    return siteId;
}


public Integer getSysUserId(){
    return sysUserId;
}


public String getPropertyName(){
    return propertyName;
}


}