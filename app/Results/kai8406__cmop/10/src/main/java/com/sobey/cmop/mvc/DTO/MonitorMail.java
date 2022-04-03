package com.sobey.cmop.mvc.DTO;
 import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonBackReference;
public class MonitorMail {

 private  Integer id;

 private  Apply apply;

 private  User user;

 private  String email;

// Constructors
/**
 * default constructor
 */
public MonitorMail() {
}/**
 * full constructor
 */
public MonitorMail(Apply apply, User user, String email) {
    this.email = email;
}
@JsonBackReference
@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "apply_id", nullable = false)
public Apply getApply(){
    return this.apply;
}


public void setEmail(String email){
    this.email = email;
}


@JsonBackReference
@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "user_id", nullable = false)
public User getUser(){
    return this.user;
}


public void setId(Integer id){
    this.id = id;
}


@Column(name = "email", nullable = false, length = 45)
public String getEmail(){
    return this.email;
}


@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "id", unique = true, nullable = false)
public Integer getId(){
    return this.id;
}


public void setApply(Apply apply){
    this.apply = apply;
}


public void setUser(User user){
    this.user = user;
}


}