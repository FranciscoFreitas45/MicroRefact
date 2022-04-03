package com.zis.shiro.dto;
 import java.util.List;
import com.zis.shiro.bean.Permission;
import com.zis.shiro.bean.Role;
import com.zis.shiro.bean.User;
public class UpdateUserRoleAndPermissions {

 private  User user;

 private  Role role;

 private  List<Permission> permissions;

public UpdateUserRoleAndPermissions() {
}public UpdateUserRoleAndPermissions(User user, Role role, List<Permission> permissions) {
    super();
    this.user = user;
    this.role = role;
    this.permissions = permissions;
}
public User getUser(){
    return user;
}


public void setRole(Role role){
    this.role = role;
}


public List<Permission> getPermissions(){
    return permissions;
}


public Role getRole(){
    return role;
}


@Override
public String toString(){
    return "UpdateUserRoleAndPermissions [user=" + user + ", role=" + role + ", permissions=" + permissions + "]";
}


public void setUser(User user){
    this.user = user;
}


public void setPermissions(List<Permission> permissions){
    this.permissions = permissions;
}


}