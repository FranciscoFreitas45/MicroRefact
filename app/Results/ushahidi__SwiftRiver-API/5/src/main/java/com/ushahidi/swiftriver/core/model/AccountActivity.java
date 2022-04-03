package com.ushahidi.swiftriver.core.model;
 import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import com.ushahidi.swiftriver.core.Request.AccountRequest;
import com.ushahidi.swiftriver.core.Request.Impl.AccountRequestImpl;
import com.ushahidi.swiftriver.core.DTO.Account;
@Entity
@DiscriminatorValue("account")
public class AccountActivity extends Activity{

@Transient
 private  Account actionOnObj;

@Column(name = "idD2H4")
 private long idD2H4;

@Transient
 private AccountRequest accountrequest = new AccountRequestImpl();;


public Account getActionOnObj(){
  this.actionOnObj = accountrequest.getActionOnObj(this.idD2H4);
return this.actionOnObj;
}}



public void setActionOnObj(Account actionOnObj){
this.idD2H4 = actionOnObj.getActiononobj() ;
accountrequest.setActionOnObj(actionOnObj,this.idD2H4);
 this.actionOnObj = actionOnObj;
}



}