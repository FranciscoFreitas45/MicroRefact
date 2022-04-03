package DTO;
 import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import com.google.common.collect.Lists;
public class User {

 private  String userId;

 private  String username;

 private  String password;

 private  Integer userType;

 private  Boolean isStoped;

 private  String note;

 private  String phone;

 private  String avatarPath;

 private  String salt;

 private  String mail;

 private  String status;

 private  String deptName;

// Constructors
/**
 * default constructor
 */
public User() {
}/**
 * full constructor
 */
public User(String userName, String password, Integer userType, Boolean isStoped, String note, String phone) {
    this.username = userName;
    this.password = password;
    this.userType = userType;
    this.isStoped = isStoped;
    this.note = note;
    this.phone = phone;
}
@Column(name = "Phone", length = 100)
public String getPhone(){
    return this.phone;
}


@Column(name = "AvatarPath", length = 100)
public String getAvatarPath(){
    return avatarPath;
}


@Column(name = "UserType")
public Integer getUserType(){
    return this.userType;
}


@Column(name = "salt")
public String getSalt(){
    return salt;
}


@Column(name = "Note", length = 2000)
public String getNote(){
    return this.note;
}


@Column(name = "status")
public String getStatus(){
    return status;
}


@Column(name = "deptname")
public String getDeptName(){
    return deptName;
}


@Column(name = "UserName", length = 100)
public String getUsername(){
    return username;
}


@Column(name = "mail")
public String getMail(){
    return mail;
}


@Column(name = "Password", length = 200)
public String getPassword(){
    return this.password;
}


@Column(name = "IsStoped")
public Boolean getIsStoped(){
    return this.isStoped;
}


@Id
@Column(name = "UserID", unique = true, nullable = false, length = 100)
public String getUserId(){
    return this.userId;
}


}