package com.ushahidi.swiftriver.core.model;
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
@Entity
@Table(name = "river_collaborators")
public class RiverCollaborator {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
 private  Long id;

@Transient
 private  Account account;

@ManyToOne
 private  River river;

@Column(name = "collaborator_active")
 private  boolean active;

@Column(name = "read_only")
 private  boolean readOnly;

@Temporal(TemporalType.TIMESTAMP)
@Column(name = "date_added")
 private  Date dateAdded;

@Column(name = "idWINU")
 private long idWINU;

@Transient
 private AccountRequest accountrequest = new AccountRequestImpl();;


public River getRiver(){
    return river;
}


public void setActive(boolean active){
    this.active = active;
}


public boolean isReadOnly(){
    return readOnly;
}


public Date getDateAdded(){
    return dateAdded;
}


public Account getAccount(){
  this.account = accountrequest.getAccount(this.idWINU);
return this.account;
}}



public void setId(Long id){
    this.id = id;
}


public void setAccount(Account account){
this.idWINU = account.getAccount() ;
accountrequest.setAccount(account,this.idWINU);
 this.account = account;
}



public void setRiver(River river){
    this.river = river;
}


public Long getId(){
    return id;
}


public boolean isActive(){
    return active;
}


public void setDateAdded(Date dateAdded){
    this.dateAdded = dateAdded;
}


public void setReadOnly(boolean readOnly){
    this.readOnly = readOnly;
}


}