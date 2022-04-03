package net.shangtech.weixin.custom.entity;
 import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import net.shangtech.ssh.core.base.IBaseEntity;
@Entity
@Table(name = "custom_service_group")
public class CustomServiceGroup extends IBaseEntity{

 private  long serialVersionUID;

@Column(name = "group_name")
 private  String name;

@Column(name = "sort")
 private  Integer sort;

@Column(name = "sys_user_id")
 private  Integer sysUserId;


public void setName(String name){
    this.name = name;
}


public void setSort(Integer sort){
    this.sort = sort;
}


public Integer getSort(){
    return sort;
}


public String getName(){
    return name;
}


public void setSysUserId(Integer sysUserId){
    this.sysUserId = sysUserId;
}


public Integer getSysUserId(){
    return sysUserId;
}


}