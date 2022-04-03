package com.poseidon.user.web.form;
 import com.poseidon.user.domain.UserVO;
import java.util.Collection;
import java.util.List;
import java.util.StringJoiner;
@SuppressWarnings("unused")
public class UserForm {

 private  Long id;

 private  String name;

 private  String email;

 private  String password;

 private  String role;

 private  String message;

 private  UserVO user;

 private  Collection<UserVO> userVOs;

 private  String loggedInUser;

 private  String loggedInRole;

 private  UserVO searchUser;

 private  String statusMessage;

 private  String statusMessageType;

 private  List<String> roleList;

 private  boolean startsWith;

 private  boolean includes;


public void setName(String name){
    this.name = name;
}


public void setPassword(String password){
    this.password = password;
}


public UserVO getCurrentUser(){
    UserVO userVo = new UserVO();
    userVo.setName(getName());
    userVo.setEmail(getEmail());
    userVo.setPassword(getPassword());
    userVo.setRole(getRole());
    return userVo;
}


public String getName(){
    return name;
}


public String getStatusMessage(){
    return statusMessage;
}


public UserVO getUser(){
    return user;
}


public Long getId(){
    return id;
}


public boolean isIncludes(){
    return includes;
}


public void setLoggedInUser(String loggedInUser){
    this.loggedInUser = loggedInUser;
}


public void setUserVOs(Collection<UserVO> userVOs){
    this.userVOs = userVOs;
}


public void setStartsWith(boolean startsWith){
    this.startsWith = startsWith;
}


public String getStatusMessageType(){
    return statusMessageType;
}


public List<String> getRoleList(){
    return roleList;
}


public void setId(Long id){
    this.id = id;
}


public String getLoggedInUser(){
    return loggedInUser;
}


public void setSearchUser(UserVO searchUser){
    this.searchUser = searchUser;
}


public void setUser(UserVO user){
    this.user = user;
}


public void setRoleList(List<String> roleList){
    this.roleList = roleList;
}


public UserVO getSearchUser(){
    return searchUser;
}


public void setStatusMessage(String statusMessage){
    this.statusMessage = statusMessage;
}


public void setLoggedInRole(String loggedInRole){
    this.loggedInRole = loggedInRole;
}


public String getRole(){
    return role;
}


public String getMessage(){
    return message;
}


public void setMessage(String message){
    this.message = message;
}


public void setStatusMessageType(String statusMessageType){
    this.statusMessageType = statusMessageType;
}


public Collection<UserVO> getUserVOs(){
    return userVOs;
}


public String getPassword(){
    return password;
}


public boolean isStartsWith(){
    return startsWith;
}


public void setEmail(String email){
    this.email = email;
}


public void setRole(String role){
    this.role = role;
}


public String getEmail(){
    return email;
}


@Override
public String toString(){
    return new StringJoiner(", ", UserForm.class.getSimpleName() + "[", "]").add("id=" + id).add("name='" + name + "'").add("email='" + email + "'").add("password='" + password + "'").add("role='" + role + "'").add("message='" + message + "'").add("user=" + user).add("userVOs=" + userVOs).add("loggedInUser='" + loggedInUser + "'").add("loggedInRole='" + loggedInRole + "'").add("searchUser=" + searchUser).add("statusMessage='" + statusMessage + "'").add("statusMessageType='" + statusMessageType + "'").add("roleList=" + roleList).toString();
}


public String getLoggedInRole(){
    return loggedInRole;
}


public void setIncludes(boolean includes){
    this.includes = includes;
}


}