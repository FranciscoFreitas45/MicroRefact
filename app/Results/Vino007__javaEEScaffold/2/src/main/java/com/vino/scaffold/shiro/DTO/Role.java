package com.vino.scaffold.shiro.DTO;
 import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import com.vino.scaffold.entity.base.BaseEntity;
public class Role extends BaseEntity<Long>{

 private  String name;

 private  String description;

 private  Boolean available;

 private  Set<Resource> resources;

 private  Set<User> users;

public Role() {
}public Role(String mark, String name) {
    // this.mark = mark;
    this.name = name;
}
public String getName(){
    return name;
}


public Set<Resource> getResources(){
    return resources;
}


public Set<User> getUsers(){
    return users;
}


public String getDescription(){
    return description;
}


public Boolean getAvailable(){
    return available;
}


}