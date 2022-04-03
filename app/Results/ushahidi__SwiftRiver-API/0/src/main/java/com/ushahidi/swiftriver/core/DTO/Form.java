package com.ushahidi.swiftriver.core.DTO;
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
public class Form {

 private  Long id;

 private  Account account;

 private  String name;

 private  Date dateAdded;

 private  Date dateModified;

 private  List<FormField> fields;

 private long idQGFJ;

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