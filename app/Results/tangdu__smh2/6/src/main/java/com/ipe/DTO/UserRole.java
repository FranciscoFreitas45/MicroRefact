package com.ipe.DTO;
 import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.ipe.common.entity.IDEntity;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
public class UserRole extends IDEntity{

 private  Role role;

 private  User user;


public void setRole(Role role){
    this.role = role;
}


@ManyToOne
@JoinColumn(name = "user_id", referencedColumnName = "id_")
public User getUser(){
    return user;
}


@ManyToOne
@JoinColumn(name = "role_id", referencedColumnName = "id_")
public Role getRole(){
    return role;
}


public void setUser(User user){
    this.user = user;
}


}