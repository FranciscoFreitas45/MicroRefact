package cn.com.cnc.fcc.service.dto;
 import cn.com.cnc.fcc.config.Constants;
import cn.com.cnc.fcc.domain.Authority;
import cn.com.cnc.fcc.domain.RbacMenu;
import cn.com.cnc.fcc.domain.RbacUser;
import cn.com.cnc.fcc.domain.User;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints;
import java.time.Instant;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
public class UserDTO {

 private  Long id;

@NotBlank
@Pattern(regexp = Constants.LOGIN_REGEX)
@Size(min = 1, max = 50)
 private  String login;

@Size(max = 50)
 private  String firstName;

@Size(max = 50)
 private  String lastName;

@Email
@Size(min = 5, max = 254)
 private  String email;

@Size(max = 256)
 private  String imageUrl;

 private  boolean activated;

@Size(min = 2, max = 6)
 private  String langKey;

 private  String createdBy;

 private  Instant createdDate;

 private  String lastModifiedBy;

 private  Instant lastModifiedDate;

 private  Set<String> authorities;

 private  List<RbacMenu> rbacMenus;

 private  List<RbacUser> rbacUser;

public UserDTO() {
// Empty constructor needed for Jackson.
}public UserDTO(User user) {
    this.id = user.getId();
    this.login = user.getLogin();
    this.firstName = user.getFirstName();
    this.lastName = user.getLastName();
    this.email = user.getEmail();
    this.activated = user.getActivated();
    this.imageUrl = user.getImageUrl();
    this.langKey = user.getLangKey();
    this.createdBy = user.getCreatedBy();
    this.createdDate = user.getCreatedDate();
    this.lastModifiedBy = user.getLastModifiedBy();
    this.lastModifiedDate = user.getLastModifiedDate();
    this.authorities = user.getAuthorities().stream().map(Authority::getName).collect(Collectors.toSet());
}
public void setImageUrl(String imageUrl){
    this.imageUrl = imageUrl;
}


public Instant getLastModifiedDate(){
    return lastModifiedDate;
}


public String getLastModifiedBy(){
    return lastModifiedBy;
}


public void setRbacMenus(List<RbacMenu> rbacMenus){
    this.rbacMenus = rbacMenus;
}


public Long getId(){
    return id;
}


public Set<String> getAuthorities(){
    return authorities;
}


public void setLastName(String lastName){
    this.lastName = lastName;
}


public void setAuthorities(Set<String> authorities){
    this.authorities = authorities;
}


public List<RbacUser> getRbacUser(){
    return rbacUser;
}


public String getImageUrl(){
    return imageUrl;
}


public void setId(Long id){
    this.id = id;
}


public Instant getCreatedDate(){
    return createdDate;
}


public void setActivated(boolean activated){
    this.activated = activated;
}


public void setRbacUser(List<RbacUser> rbacUser){
    this.rbacUser = rbacUser;
}


public void setLastModifiedBy(String lastModifiedBy){
    this.lastModifiedBy = lastModifiedBy;
}


public String getLangKey(){
    return langKey;
}


public boolean isActivated(){
    return activated;
}


public void setLangKey(String langKey){
    this.langKey = langKey;
}


public String getLastName(){
    return lastName;
}


public String getLogin(){
    return login;
}


public void setLastModifiedDate(Instant lastModifiedDate){
    this.lastModifiedDate = lastModifiedDate;
}


public void setEmail(String email){
    this.email = email;
}


public void setCreatedDate(Instant createdDate){
    this.createdDate = createdDate;
}


public void setFirstName(String firstName){
    this.firstName = firstName;
}


public void setLogin(String login){
    this.login = login;
}


public void setCreatedBy(String createdBy){
    this.createdBy = createdBy;
}


public String getEmail(){
    return email;
}


@Override
public String toString(){
    return "UserDTO{" + "login='" + login + '\'' + ", firstName='" + firstName + '\'' + ", lastName='" + lastName + '\'' + ", email='" + email + '\'' + ", imageUrl='" + imageUrl + '\'' + ", activated=" + activated + ", langKey='" + langKey + '\'' + ", createdBy=" + createdBy + ", createdDate=" + createdDate + ", lastModifiedBy='" + lastModifiedBy + '\'' + ", lastModifiedDate=" + lastModifiedDate + ", authorities=" + authorities + "}";
}


public String getFirstName(){
    return firstName;
}


public String getCreatedBy(){
    return createdBy;
}


public List<RbacMenu> getRbacMenus(){
    return rbacMenus;
}


}