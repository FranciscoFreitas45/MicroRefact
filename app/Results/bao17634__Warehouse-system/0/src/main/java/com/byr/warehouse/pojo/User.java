package com.byr.warehouse.pojo;
 import javax.persistence;
@Entity
public class User {

@Id
// 设置主键并且设置主键为自增
@GeneratedValue(strategy = GenerationType.AUTO)
 private  Long id;

@Column(nullable = false)
 private  String username;

@Column(nullable = false)
 private  String password;

@Column(nullable = false)
 private  Integer role;

 private  Integer status;


public void setPassword(String password){
    this.password = password;
}


public String getPassword(){
    return password;
}


public void setUsername(String username){
    this.username = username;
}


public void setRole(Integer role){
    this.role = role;
}


public Integer getRole(){
    return role;
}


public void setId(Long id){
    this.id = id;
}


public Long getId(){
    return id;
}


@Override
public String toString(){
    return "User{" + "id=" + id + ", username='" + username + '\'' + ", password='" + password + '\'' + ", role='" + role + '\'' + "status=" + status + '}';
}


public Integer getStatus(){
    return status;
}


public void setStatus(Integer status){
    this.status = status;
}


public String getUsername(){
    return username;
}


}