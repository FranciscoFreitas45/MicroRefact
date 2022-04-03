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
@Table(name = "bucket_droplet_comments")
public class BucketDropComment {

@Id
@GeneratedValue
 private  long id;

@ManyToOne
@JoinColumn(name = "buckets_droplets_id")
 private  BucketDrop bucketDrop;

@Transient
 private  Account account;

@Column(name = "comment_text")
 private  String commentText;

@Temporal(TemporalType.TIMESTAMP)
@Column(name = "comment_date_add")
 private  Date dateAdded;

@Column(name = "idLW0Z")
 private long idLW0Z;

@Transient
 private AccountRequest accountrequest = new AccountRequestImpl();;


public String getCommentText(){
    return commentText;
}


public Date getDateAdded(){
    return dateAdded;
}


public Account getAccount(){
  this.account = accountrequest.getAccount(this.idLW0Z);
return this.account;
}}



public void setCommentText(String commentText){
    this.commentText = commentText;
}


public void setId(long id){
    this.id = id;
}


public void setAccount(Account account){
this.idLW0Z = account.getAccount() ;
accountrequest.setAccount(account,this.idLW0Z);
 this.account = account;
}



public long getId(){
    return id;
}


public void setDateAdded(Date dateAdded){
    this.dateAdded = dateAdded;
}


public BucketDrop getBucketDrop(){
    return bucketDrop;
}


public void setBucketDrop(BucketDrop bucketDrop){
    this.bucketDrop = bucketDrop;
}


}