package com.ushahidi.swiftriver.core.model;
 import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import com.ushahidi.swiftriver.core.Request.AccountRequest;
import com.ushahidi.swiftriver.core.Request.Impl.AccountRequestImpl;
import com.ushahidi.swiftriver.core.DTO.Account;
@Entity
@Table(name = "forms")
public class Form {

@Id
@GeneratedValue
 private  Long id;

@Transient
 private  Account account;

 private  String name;

@Column(name = "date_added")
@Temporal(TemporalType.TIMESTAMP)
 private  Date dateAdded;

@Column(name = "date_modified")
@Temporal(TemporalType.TIMESTAMP)
 private  Date dateModified;

@OneToMany(mappedBy = "form")
 private  List<FormField> fields;

@Column(name = "idQGFJ")
 private long idQGFJ;

@Transient
 private AccountRequest accountrequest = new AccountRequestImpl();;


public void setName(String name){
    this.name = name;
}


public void setDateModified(Date dateModified){
    this.dateModified = dateModified;
}


public String getName(){
    return name;
}


public List<FormField> getFields(){
    return fields;
}


public Date getDateAdded(){
    return dateAdded;
}


public Account getAccount(){
  this.account = accountrequest.getAccount(this.idQGFJ);
return this.account;
}}



public void setId(Long id){
    this.id = id;
}


public void setAccount(Account account){
this.idQGFJ = account.getAccount() ;
accountrequest.setAccount(account,this.idQGFJ);
 this.account = account;
}



public Long getId(){
    return id;
}


public void setFields(List<FormField> fields){
    this.fields = fields;
}


public void setDateAdded(Date dateAdded){
    this.dateAdded = dateAdded;
}


public Date getDateModified(){
    return dateModified;
}


}