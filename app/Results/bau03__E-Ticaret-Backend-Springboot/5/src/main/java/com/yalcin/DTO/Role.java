package com.yalcin.DTO;
 import javax.persistence;
public class Role {

 private  Integer id;

 private  Roles role;

 private  Boolean isAccountAdmin;

public Role() {
}
public Roles getRole(){
    return role;
}


public Integer getId(){
    return id;
}


}