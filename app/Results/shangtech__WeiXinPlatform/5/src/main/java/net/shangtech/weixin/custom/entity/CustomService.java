package net.shangtech.weixin.custom.entity;
 import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import net.shangtech.ssh.core.base.IBaseEntity;
@Entity
@Table(name = "custom_service")
public class CustomService extends IBaseEntity{

 private  long serialVersionUID;

@Column(name = "group_id")
 private  Integer groupId;

@Column(name = "realname")
 private  String realname;

@Column(name = "image")
 private  String image;

@Column(name = "description")
 private  String description;

@Column(name = "sort")
 private  Integer sort;

@Column(name = "tel")
 private  String tel;


public void setSort(Integer sort){
    this.sort = sort;
}


public Integer getSort(){
    return sort;
}


public void setRealname(String realname){
    this.realname = realname;
}


public void setGroupId(Integer groupId){
    this.groupId = groupId;
}


public void setDescription(String description){
    this.description = description;
}


public void setTel(String tel){
    this.tel = tel;
}


public String getImage(){
    return image;
}


public String getDescription(){
    return description;
}


public String getTel(){
    return tel;
}


public Integer getGroupId(){
    return groupId;
}


public String getRealname(){
    return realname;
}


public void setImage(String image){
    this.image = image;
}


}