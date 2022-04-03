package com.ushahidi.swiftriver.core.model;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import com.ushahidi.swiftriver.core.Request.AccountRequest;
import com.ushahidi.swiftriver.core.Request.Impl.AccountRequestImpl;
import com.ushahidi.swiftriver.core.DTO.Account;
@Entity
@Table(name = "bucket_comments")
public class BucketComment {

@Id
@GeneratedValue
 private  long id;

@ManyToOne
 private  Bucket bucket;

@Transient
 private  Account account;

@Column(name = "comment_text")
 public  String commentText;

@Temporal(TemporalType.TIMESTAMP)
@Column(name = "comment_date_add")
 private  Date dateAdded;

@Column(name = "idVO82")
 private long idVO82;

@Transient
 private AccountRequest accountrequest = new AccountRequestImpl();;


public Bucket getBucket(){
    return bucket;
}


public String getCommentText(){
    return commentText;
}


public Date getDateAdded(){
    return dateAdded;
}


public Account getAccount(){
  this.account = accountrequest.getAccount(this.idVO82);
return this.account;
}}



public void setCommentText(String commentText){
    this.commentText = commentText;
}


public void setId(long id){
    this.id = id;
}


public void setAccount(Account account){
this.idVO82 = account.getAccount() ;
accountrequest.setAccount(account,this.idVO82);
 this.account = account;
}



public long getId(){
    return id;
}


public void setBucket(Bucket bucket){
    this.bucket = bucket;
}


public void setDateAdded(Date dateAdded){
    this.dateAdded = dateAdded;
}


}