package com.ushahidi.swiftriver.core.model;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
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
@Table(name = "river_droplet_comments")
public class RiverDropComment {

@Id
@GeneratedValue
 private  long id;

@ManyToOne
@JoinColumn(name = "rivers_droplets_id")
 private  RiverDrop riverDrop;

@Transient
 private  Account account;

@Column(name = "comment_text")
 private  String commentText;

@Temporal(TemporalType.TIMESTAMP)
@Column(name = "comment_date_add")
 private  Date dateAdded;

@Column(name = "idG79G")
 private long idG79G;

@Transient
 private AccountRequest accountrequest = new AccountRequestImpl();;


public String getCommentText(){
    return commentText;
}


public Date getDateAdded(){
    return dateAdded;
}


public RiverDrop getRiverDrop(){
    return riverDrop;
}


public Account getAccount(){
  this.account = accountrequest.getAccount(this.idG79G);
return this.account;
}}



public void setCommentText(String commentText){
    this.commentText = commentText;
}


public void setId(long id){
    this.id = id;
}


public void setAccount(Account account){
this.idG79G = account.getAccount() ;
accountrequest.setAccount(account,this.idG79G);
 this.account = account;
}



public long getId(){
    return id;
}


public void setRiverDrop(RiverDrop riverDrop){
    this.riverDrop = riverDrop;
}


public void setDateAdded(Date dateAdded){
    this.dateAdded = dateAdded;
}


}