package net.shangtech.weixin.site.entity;
 import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import net.shangtech.ssh.core.base.IBaseEntity;
@Entity
@Table(name = "site_template")
public class SiteTemplate extends IBaseEntity{

 private  long serialVersionUID;

@Column(name = "temp_name")
 private  String name;

@Column(name = "temp_path")
 private  String path;

@Column(name = "temp_type")
 private  Integer type;


public void setName(String name){
    this.name = name;
}


public String getName(){
    return name;
}


public Integer getType(){
    return type;
}


public String getPath(){
    return path;
}


public void setPath(String path){
    this.path = path;
}


public void setType(Integer type){
    this.type = type;
}


}