package net.shangtech.weixin.site.entity;
 import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import net.shangtech.ssh.core.base.IBaseEntity;
@Entity
@Table(name = "user_site")
public class UserSite extends IBaseEntity{

 private  long serialVersionUID;

@Column(name = "sys_user_id")
 private  Integer sysUserId;

@Column(name = "template_id")
 private  Integer templateId;

@Column(name = "type")
 private  Integer type;


public Integer getTemplateId(){
    return templateId;
}


public Integer getType(){
    return type;
}


public void setSysUserId(Integer sysUserId){
    this.sysUserId = sysUserId;
}


public void setTemplateId(Integer templateId){
    this.templateId = templateId;
}


public Integer getSysUserId(){
    return sysUserId;
}


public void setType(Integer type){
    this.type = type;
}


}