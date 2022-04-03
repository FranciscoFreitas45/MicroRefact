package com.ec.survey.model;
 import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
@Entity
@Table(name = "USERSCONFIGURATION")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class UsersConfiguration {

 private  long serialVersionUID;

 private  Integer id;

 private  int userId;

 private  boolean showName;

 private  boolean showEmail;

 private  boolean showOtherEmail;

 private  boolean showLanguage;

 private  boolean showRoles;

 private  boolean showComment;

 private  boolean showBanned;


@Column(name = "UC_EMAIL")
public boolean getShowEmail(){
    return showEmail;
}


public void setShowEmail(boolean showEmail){
    this.showEmail = showEmail;
}


public void setShowComment(boolean showComment){
    this.showComment = showComment;
}


public void setShowOtherEmail(Boolean showOtherEmail){
    this.showOtherEmail = showOtherEmail != null && showOtherEmail;
}


@Id
@Column(name = "UC_ID")
@GeneratedValue
public Integer getId(){
    return id;
}


@Column(name = "UC_ROLES")
public boolean getShowRoles(){
    return showRoles;
}


public void setShowRoles(boolean showRoles){
    this.showRoles = showRoles;
}


@Column(name = "UC_BANNED")
public boolean getShowBanned(){
    return showBanned;
}


public void setShowLanguage(boolean showLanguage){
    this.showLanguage = showLanguage;
}


@Column(name = "UC_OTHEREMAIL")
public boolean isShowOtherEmail(){
    return showOtherEmail;
}


@Column(name = "UC_COMM")
public boolean getShowComment(){
    return showComment;
}


@Column(name = "UC_LANG")
public boolean getShowLanguage(){
    return showLanguage;
}


public void setShowName(boolean showName){
    this.showName = showName;
}


public void setId(Integer id){
    this.id = id;
}


@Column(name = "UC_NAME")
public boolean getShowName(){
    return showName;
}


@Column(name = "UC_USER")
public int getUserId(){
    return userId;
}


public void setUserId(int userId){
    this.userId = userId;
}


public void setShowBanned(Boolean showBanned){
    this.showBanned = showBanned == null || showBanned;
}


}