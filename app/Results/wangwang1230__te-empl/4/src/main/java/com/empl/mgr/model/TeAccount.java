package com.empl.mgr.model;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;
@Entity
@Table(name = "te_account", uniqueConstraints = @UniqueConstraint(columnNames = "acctName"))
public class TeAccount {

 private  long serialVersionUID;

 private  long acctId;

 private  Date timestamp;

 private  String acctName;

 private  String acctNickname;

 private  String acctPassword;

 private  Integer acctState;

 private  boolean acctSuper;

 private  boolean acctDeleteState;

 private  Date createTime;

 private  String creator;

// Constructors
/**
 * default constructor
 */
public TeAccount() {
}/**
 * full constructor
 */
public TeAccount(String acctName, String acctNickname, String acctPassword, Integer acctState, boolean acctSuper, boolean acctDeleteState, Date createTime, String creator) {
    this.acctName = acctName;
    this.acctNickname = acctNickname;
    this.acctPassword = acctPassword;
    this.acctState = acctState;
    this.acctSuper = acctSuper;
    this.acctDeleteState = acctDeleteState;
    this.createTime = createTime;
    this.creator = creator;
}
@Column(name = "acctSuper")
public boolean getAcctSuper(){
    return this.acctSuper;
}


public void setAcctSuper(boolean acctSuper){
    this.acctSuper = acctSuper;
}


@Column(name = "createTime", length = 19)
public Date getCreateTime(){
    return this.createTime;
}


public void setAcctId(long acctId){
    this.acctId = acctId;
}


public void setAcctName(String acctName){
    this.acctName = acctName;
}


@Column(name = "acctPassword", length = 64)
public String getAcctPassword(){
    return this.acctPassword;
}


public void setCreator(String creator){
    this.creator = creator;
}


@Column(name = "acctDeleteState")
public boolean getAcctDeleteState(){
    return this.acctDeleteState;
}


public void setCreateTime(Date createTime){
    this.createTime = createTime;
}


@Column(name = "creator", length = 64)
public String getCreator(){
    return this.creator;
}


public void setAcctNickname(String acctNickname){
    this.acctNickname = acctNickname;
}


@Version
@Column(name = "timestamp", nullable = false, length = 19)
public Date getTimestamp(){
    return this.timestamp;
}


@Column(name = "acctName", unique = true, length = 64)
public String getAcctName(){
    return this.acctName;
}


public void setAcctPassword(String acctPassword){
    this.acctPassword = acctPassword;
}


@Override
public String toString(){
    return "TeAccount [acctId=" + acctId + ", timestamp=" + timestamp + ", acctName=" + acctName + ", acctNickname=" + acctNickname + ", acctPassword=" + acctPassword + ", acctState=" + acctState + ", acctSuper=" + acctSuper + ", acctDeleteState=" + acctDeleteState + ", createTime=" + createTime + ", creator=" + creator + "]";
}


@Column(name = "acctNickname", length = 64)
public String getAcctNickname(){
    return this.acctNickname;
}


@Column(name = "acctState")
public Integer getAcctState(){
    return this.acctState;
}


public void setAcctState(Integer acctState){
    this.acctState = acctState;
}


@Id
@GeneratedValue(strategy = IDENTITY)
@Column(name = "acctId", unique = true, nullable = false)
public long getAcctId(){
    return this.acctId;
}


public void setAcctDeleteState(boolean acctDeleteState){
    this.acctDeleteState = acctDeleteState;
}


public void setTimestamp(Date timestamp){
    this.timestamp = timestamp;
}


}