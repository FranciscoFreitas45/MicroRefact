package com.ushahidi.swiftriver.core.DTO;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import com.ushahidi.swiftriver.core.Request.AccountRequest;
import com.ushahidi.swiftriver.core.Request.Impl.AccountRequestImpl;
import com.ushahidi.swiftriver.core.DTO.Account;
public class RiverCollaborator {

 private  Long id;

 private  Account account;

 private  River river;

 private  boolean active;

 private  boolean readOnly;

 private  Date dateAdded;

 private long idWINU;


public River getRiver(){
    return river;
}


public Date getDateAdded(){
    return dateAdded;
}


public Account getAccount(){
  this.account = accountrequest.getAccount(this.idWINU);
return this.account;
}}



public Long getId(){
    return id;
}


}