package com.gs.DTO;
 import java.util.Date;
import com.gs.Interface.Company;
import com.gs.Interface.Role;
public class User {

 private  String userId;

 private  String userEmail;

 private  String userPhone;

 private  String userPwd;

 private  String userNickname;

 private  String userIdentity;

 private  String userName;

 private  String userGender;

 private  Date userBirthday;

 private  String userAddress;

 private  String qqOpenId;

 private  String weiboOpenId;

 private  String weChatOpenId;

 private  String userIcon;

 private  String userDes;

 private  String companyId;

 private  Double userSalary;

 private  Date userCreatedTime;

 private  Date userLoginedTime;

 private  String userStatus;

 private  String roleId;

 private  int phonecode;

 private  Company company;

 private  Role role;

 private  UserRole userRole;


public String getUserIdentity(){
    return userIdentity;
}


public String getUserStatus(){
    return userStatus;
}


public String getUserDes(){
    return userDes;
}


public String getUserName(){
    return userName;
}


public Date getUserCreatedTime(){
    return userCreatedTime;
}


public String getUserAddress(){
    return userAddress;
}


public Date getUserLoginedTime(){
    return userLoginedTime;
}


public UserRole getUserRole(){
    return userRole;
}


public String getUserIcon(){
    return userIcon;
}


public String getUserNickname(){
    return userNickname;
}


public Company getCompany(){
    return company;
}


public Date getUserBirthday(){
    return userBirthday;
}


public String getUserGender(){
    return userGender;
}


public String getWeiboOpenId(){
    return weiboOpenId;
}


public int getPhonecode(){
    return phonecode;
}


public String getUserPwd(){
    return userPwd;
}


public String getQqOpenId(){
    return qqOpenId;
}


public String getRoleId(){
    return roleId;
}


public String getUserEmail(){
    return userEmail;
}


public Role getRole(){
    return role;
}


public Double getUserSalary(){
    return userSalary;
}


public String getWeChatOpenId(){
    return weChatOpenId;
}


public String getCompanyId(){
    return companyId;
}


public String getUserPhone(){
    return userPhone;
}


public String getUserId(){
    return userId;
}


}