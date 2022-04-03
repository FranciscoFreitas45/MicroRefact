package com.empl.mgr.DTO;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;
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


@Column(name = "createTime", length = 19)
public Date getCreateTime(){
    return this.createTime;
}


@Column(name = "acctPassword", length = 64)
public String getAcctPassword(){
    return this.acctPassword;
}


@Column(name = "acctDeleteState")
public boolean getAcctDeleteState(){
    return this.acctDeleteState;
}


@Column(name = "creator", length = 64)
public String getCreator(){
    return this.creator;
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


@Column(name = "acctNickname", length = 64)
public String getAcctNickname(){
    return this.acctNickname;
}


@Column(name = "acctState")
public Integer getAcctState(){
    return this.acctState;
}


@Id
@GeneratedValue(strategy = IDENTITY)
@Column(name = "acctId", unique = true, nullable = false)
public long getAcctId(){
    return this.acctId;
}


}