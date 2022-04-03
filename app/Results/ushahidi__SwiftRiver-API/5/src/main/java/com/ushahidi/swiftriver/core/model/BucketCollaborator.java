package com.ushahidi.swiftriver.core.model;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TemporalType;
import javax.persistence.Temporal;
import com.ushahidi.swiftriver.core.Request.AccountRequest;
import com.ushahidi.swiftriver.core.Request.Impl.AccountRequestImpl;
import com.ushahidi.swiftriver.core.DTO.Account;
@Entity
@Table(name = "bucket_collaborators")
public class BucketCollaborator {

@Id
@GeneratedValue
 private  long id;

@Transient
 private  Account account;

@ManyToOne
 private  Bucket bucket;

@Column(name = "collaborator_active")
 private  boolean active;

@Column(name = "read_only")
 private  boolean readOnly;

@Temporal(TemporalType.TIMESTAMP)
@Column(name = "date_added")
 private  Date dateAdded;

@Column(name = "idXXQN")
 private long idXXQN;

@Transient
 private AccountRequest accountrequest = new AccountRequestImpl();;


public Bucket getBucket(){
    return bucket;
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
  this.account = accountrequest.getAccount(this.idXXQN);
return this.account;
}}



public void setId(long id){
    this.id = id;
}


public void setAccount(Account account){
this.idXXQN = account.getAccount() ;
accountrequest.setAccount(account,this.idXXQN);
 this.account = account;
}



public long getId(){
    return id;
}


public void setBucket(Bucket bucket){
    this.bucket = bucket;
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