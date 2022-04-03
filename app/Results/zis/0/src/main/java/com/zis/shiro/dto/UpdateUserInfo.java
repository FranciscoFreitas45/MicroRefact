package com.zis.shiro.dto;
 import com.zis.shiro.bean.Role;
import com.zis.shiro.bean.User;
import com.zis.shop.bean.Company;
public class UpdateUserInfo {

 private  User user;

 private  Role role;

 private  Company company;

public UpdateUserInfo() {
}public UpdateUserInfo(User user, Role role, Company company) {
    this.user = user;
    this.role = role;
    this.company = company;
}
public Company getCompany(){
    return company;
}


public User getUser(){
    return user;
}


public void setRole(Role role){
    this.role = role;
}


public Role getRole(){
    return role;
}


@Override
public String toString(){
    return "UpdateUserInfo [user=" + user + ", role=" + role + ", company=" + company + "]";
}


public void setUser(User user){
    this.user = user;
}


public void setCompany(Company company){
    this.company = company;
}


}