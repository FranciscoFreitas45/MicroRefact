package com.weflors.entity;
 import javax.persistence;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.util.Collection;
@Entity
@Table(name = "user", schema = "flowershop", catalog = "postgres")
public class UserEntity {

 private  int userId;

 private  String eMail;

 private  String userName;

 private  String password;

 private  String passwordConfirm;

 private  String userLastname;

 private  String login;

 private  String phone;

 private  Collection<UserRoleMapEntity> userRoleMapsByUserId;

public UserEntity() {
}public UserEntity(int userId, String eMail, String userName, String userLastname, String login, String phone, Collection<UserRoleMapEntity> userRoleMapsByUserId) {
    this.userId = userId;
    this.eMail = eMail;
    this.userName = userName;
    // this.password = password;
    this.userLastname = userLastname;
    this.login = login;
    this.phone = phone;
    this.userRoleMapsByUserId = userRoleMapsByUserId;
}
@Basic
@Column(name = "phone", nullable = false, length = 15)
public String getPhone(){
    return phone;
}


public void setUserRoleMapsByUserId(Collection<UserRoleMapEntity> userRoleMapsByUserId){
    this.userRoleMapsByUserId = userRoleMapsByUserId;
}


public void setPassword(String password){
    this.password = password;
}


@OneToMany(mappedBy = "userByUserId")
@JsonManagedReference(value = "user-userrolemap")
public Collection<UserRoleMapEntity> getUserRoleMapsByUserId(){
    return userRoleMapsByUserId;
}


public void setPasswordConfirm(String passwordConfirm){
    this.passwordConfirm = passwordConfirm;
}


public void setUserLastname(String userLastname){
    this.userLastname = userLastname;
}


public void setPhone(String phone){
    this.phone = phone;
}


@Basic
@Column(name = "login", nullable = false, length = 50)
public String getLogin(){
    return login;
}


@Basic
@Column(name = "e_mail", nullable = false, length = 50)
public String geteMail(){
    return eMail;
}


@Basic
@Column(name = "password", nullable = false, length = 100)
public String getPassword(){
    return password;
}


public void setUserName(String userName){
    this.userName = userName;
}


public void setLogin(String login){
    this.login = login;
}


@Override
public int hashCode(){
    int result = userId;
    result = 31 * result + (eMail != null ? eMail.hashCode() : 0);
    result = 31 * result + (userName != null ? userName.hashCode() : 0);
    result = 31 * result + (password != null ? password.hashCode() : 0);
    result = 31 * result + (userLastname != null ? userLastname.hashCode() : 0);
    result = 31 * result + (login != null ? login.hashCode() : 0);
    result = 31 * result + (phone != null ? phone.hashCode() : 0);
    return result;
}


@Override
public boolean equals(Object o){
    if (this == o)
        return true;
    if (o == null || getClass() != o.getClass())
        return false;
    UserEntity that = (UserEntity) o;
    if (userId != that.userId)
        return false;
    if (eMail != null ? !eMail.equals(that.eMail) : that.eMail != null)
        return false;
    if (userName != null ? !userName.equals(that.userName) : that.userName != null)
        return false;
    if (password != null ? !password.equals(that.password) : that.password != null)
        return false;
    if (userLastname != null ? !userLastname.equals(that.userLastname) : that.userLastname != null)
        return false;
    if (login != null ? !login.equals(that.login) : that.login != null)
        return false;
    if (phone != null ? !phone.equals(that.phone) : that.phone != null)
        return false;
    return true;
}


public void seteMail(String eMail){
    this.eMail = eMail;
}


@Basic
@Column(name = "user_name", nullable = false, length = 50)
public String getUserName(){
    return userName;
}


@Basic
@Column(name = "user_lastname", nullable = false, length = 50)
public String getUserLastname(){
    return userLastname;
}


@Transient
public String getPasswordConfirm(){
    return passwordConfirm;
}


@Id
// @SequenceGenerator(name = "hibernateSeq", sequenceName = "HIBERNATE_SEQUENCE")
// , generator = "hibernateSeq")
@GeneratedValue(strategy = GenerationType.AUTO)
@Column(name = "user_id", nullable = false)
public int getUserId(){
    return userId;
}


public void setUserId(int userId){
    this.userId = userId;
}


}