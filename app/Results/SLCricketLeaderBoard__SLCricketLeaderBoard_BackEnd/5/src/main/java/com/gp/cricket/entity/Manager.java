package com.gp.cricket.entity;
 import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.persistence.Column;
@Entity
public class Manager {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "manager_id")
 private  Integer managerId;

@NotNull
@OneToOne
@JoinColumn(name = "user_id", referencedColumnName = "user_id")
 private  User userId;

public Manager() {
    super();
// TODO Auto-generated constructor stub
}public Manager(Integer managerId, @NotNull User userId) {
    super();
    this.managerId = managerId;
    this.userId = userId;
}
public Integer getManagerId(){
    return managerId;
}


public void setManagerId(Integer managerId){
    this.managerId = managerId;
}


@Override
public String toString(){
    return "Manager [managerId=" + managerId + ", userId=" + userId + "]";
}


public User getUserId(){
    return userId;
}


public void setUserId(User userId){
    this.userId = userId;
}


}