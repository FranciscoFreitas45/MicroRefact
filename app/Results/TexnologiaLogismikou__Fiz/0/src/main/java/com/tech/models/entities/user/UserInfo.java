package com.tech.models.entities.user;
 import com.tech.configurations.tools.Attr;
import com.tech.models.dtos.user.RegisteredUserDTO;
import com.tech.models.dtos.user.UserDTO;
import java.io.Serializable;
import java.util.Date;
import javax.persistence;
@Entity
@NamedQueries({ @NamedQuery(name = "UserInfo.findByUserid", query = "SELECT p FROM UserInfo p WHERE p.userid = ?1"), @NamedQuery(name = "UserInfo.findByFirstName", query = "SELECT p FROM UserInfo p WHERE p.firstname = ?1"), @NamedQuery(name = "UserInfo.findByLastName", query = "SELECT p FROM UserInfo p WHERE p.last_name = ?1"), @NamedQuery(name = "UserInfo.findByBirthDay", query = "SELECT p FROM UserInfo p WHERE p.birthday = ?1"), @NamedQuery(name = "UserInfo.findByEmail", query = "SELECT p FROM UserInfo p WHERE p.email = ?1"), @NamedQuery(name = "UserInfo.findByHomeTown", query = "SELECT p FROM UserInfo p WHERE p.hometown = ?1") })
@Table(name = "user_info")
public class UserInfo implements Serializable{

@Id
@Column(name = "userid")
 private  Long userid;

@Column(name = "email")
 private  String email;

@Column(name = "profile_photo")
 private  String profile_photo;

@Column(name = "status")
 private  String status;

@Column(name = "last_name")
 private  String last_name;

@Column(name = "birthday")
 private  Date birthday;

@Column(name = "hometown")
 private  String hometown;

@Column(name = "first_name")
 private  String firstname;

public UserInfo() {
}/**
 * TODO needs check
 * Propably there is an error while parsin JSON to DATE..
 * @param userid
 * @param userDTO
 */
public UserInfo(Long userid, UserDTO userDTO) {
    this(userid, userDTO.getEmail(), userDTO.getProfile_photo(), userDTO.getStatus(), userDTO.getLast_name(), userDTO.getBirthday(), userDTO.getHometown(), userDTO.getFirstname());
}public UserInfo(Long userid, RegisteredUserDTO userDTO) {
    this(userid, userDTO.getEmail(), Attr.NO_IMAGE_ASSIGNED.getData(), null, userDTO.getLast_name(), userDTO.getBirthday(), null, // TODO userDTO.getBirthday() stin 8esi tou new Date()
    userDTO.getFirstname());
}public UserInfo(Long userid, String email, String profile_photo, String status, String last_name, Date birthday, String hometown, String firstname) {
    this.userid = userid;
    this.email = email;
    this.profile_photo = profile_photo;
    this.status = status;
    this.last_name = last_name;
    this.birthday = birthday;
    this.hometown = hometown;
    this.firstname = firstname;
}
public String getProfilePhoto(){
    return profile_photo;
}


public Date getBirthday(){
    return birthday;
}


public String getEmail(){
    return email;
}


public String getHometown(){
    return hometown;
}


public String getStatus(){
    return status;
}


public String getLastName(){
    return last_name;
}


public String getFirstName(){
    return firstname;
}


public Long getUserid(){
    return userid;
}


}