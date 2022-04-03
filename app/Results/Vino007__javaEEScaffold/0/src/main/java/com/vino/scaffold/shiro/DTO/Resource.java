package com.vino.scaffold.shiro.DTO;
 import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import com.vino.scaffold.entity.base.BaseEntity;
public class Resource extends BaseEntity<Long>{

 private  String name;

 private  String type;

 private  Integer priority;

 private  Long parentId;

 private  String permission;

 private  Boolean available;

 private  String url;

 private  Set<Role> roles;

public Resource() {
}public Resource(String name, String type, String permission) {
    this.name = name;
    this.type = type;
    this.permission = permission;
}
public String getName(){
    return name;
}


public String getPermission(){
    return permission;
}


public String getUrl(){
    return url;
}


public String getType(){
    return type;
}


public Integer getPriority(){
    return priority;
}


public Boolean getAvailable(){
    return available;
}


public Long getParentId(){
    return parentId;
}


public Set<Role> getRoles(){
    return roles;
}


}