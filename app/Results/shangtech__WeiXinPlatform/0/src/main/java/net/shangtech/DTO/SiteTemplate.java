package net.shangtech.DTO;
 import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import net.shangtech.ssh.core.base.IBaseEntity;
public class SiteTemplate extends IBaseEntity{

 private  long serialVersionUID;

 private  String name;

 private  String path;

 private  Integer type;


public String getName(){
    return name;
}


public Integer getType(){
    return type;
}


public String getPath(){
    return path;
}


public void setType(Integer type){
    this.type = type;
}


}