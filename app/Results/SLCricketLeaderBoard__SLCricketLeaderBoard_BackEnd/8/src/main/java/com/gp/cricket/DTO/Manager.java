package com.gp.cricket.DTO;
 import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.persistence.Column;
public class Manager {

 private  Integer managerId;

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


public User getUserId(){
    return userId;
}


}