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
@Entity
@Table(name = "account_followers")
public class AccountFollower {

@Id
@GeneratedValue
 private  long id;

@ManyToOne
 private  Account account;

@ManyToOne
 private  Account follower;

@Temporal(TemporalType.TIMESTAMP)
@Column(name = "follower_date_add")
 private  Date dateAdded;


public Account getFollower(){
    return follower;
}


public Date getDateAdded(){
    return dateAdded;
}


public Account getAccount(){
    return account;
}


public void setId(long id){
    this.id = id;
}


public void setAccount(Account account){
    this.account = account;
}


public long getId(){
    return id;
}


public void setDateAdded(Date dateAdded){
    this.dateAdded = dateAdded;
}


public void setFollower(Account follower){
    this.follower = follower;
}


}