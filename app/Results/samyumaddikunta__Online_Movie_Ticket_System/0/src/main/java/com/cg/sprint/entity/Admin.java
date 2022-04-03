package com.cg.sprint.entity;
 import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "admin")
public class Admin {

@GeneratedValue(strategy = GenerationType.AUTO)
@Column(length = 2)
 private  int sno;

@Id
@Column(length = 30)
 private  String username;

@Column(length = 15)
 private  String password;


public void setSno(int sno){
    this.sno = sno;
}


public void setPassword(String password){
    this.password = password;
}


public String getPassword(){
    return password;
}


public void setUsername(String username){
    this.username = username;
}


public int getSno(){
    return sno;
}


public String getUsername(){
    return username;
}


}