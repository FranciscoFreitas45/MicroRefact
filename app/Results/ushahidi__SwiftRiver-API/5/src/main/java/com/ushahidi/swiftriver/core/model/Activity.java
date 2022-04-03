package com.ushahidi.swiftriver.core.model;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.ushahidi.swiftriver.core.Request.AccountRequest;
import com.ushahidi.swiftriver.core.Request.Impl.AccountRequestImpl;
import com.ushahidi.swiftriver.core.DTO.Account;
@Entity
@Inheritance
@DiscriminatorColumn(name = "action_on")
@Table(name = "account_actions")
public class Activity {

@Id
@GeneratedValue
 private  long id;

@Transient
 private  Account account;

 private  String action;

@Column(name = "action_on", insertable = false, updatable = false)
 private  String actionOn;

@ManyToOne
@JoinColumn(name = "action_to_id")
 private  Account actionTo;

@Column(name = "action_date_add")
 private  Date actionDateAdd;

 private  Boolean confirmed;

@Column(name = "idTMNI")
 private long idTMNI;

@Transient
 private AccountRequest accountrequest = new AccountRequestImpl();;


public void setActionDateAdd(Date actionDateAdd){
    this.actionDateAdd = actionDateAdd;
}


public Date getActionDateAdd(){
    return actionDateAdd;
}


public void setAction(String action){
    this.action = action;
}


public Account getActionTo(){
  this.account = accountrequest.getActionTo(this.idTMNI);
return this.account;
}}



public String getActionOn(){
    return actionOn;
}


public void setActionTo(Account actionTo){
this.idTMNI = actionTo.getActionto() ;
accountrequest.setActionTo(actionTo,this.idTMNI);
 this.actionTo = actionTo;
}



public long getId(){
    return id;
}


public String getAction(){
    return action;
}


public void setConfirmed(Boolean confirmed){
    this.confirmed = confirmed;
}


public Boolean getConfirmed(){
    return confirmed;
}


public Account getAccount(){
  this.account = accountrequest.getAccount(this.idTMNI);
return this.account;
}}



public void setId(long id){
    this.id = id;
}


public void setAccount(Account account){
this.idTMNI = account.getAccount() ;
accountrequest.setAccount(account,this.idTMNI);
 this.account = account;
}



}