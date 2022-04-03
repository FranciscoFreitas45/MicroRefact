package com.tech.models.entities.user;
 import javax.persistence;
import java.io.Serializable;
@Entity
@NamedQueries({ @NamedQuery(name = "UserRole.findByUserID", query = "SELECT p FROM UserRole p WHERE p.user_role_userid = ?1"), @NamedQuery(name = "UserRole.findByRole", query = "SELECT p FROM UserRole p WHERE p.user_role_role = ?1") })
@Table(name = "user_roles")
public class UserRole implements Serializable{

@Id
@Column(name = "user_role_userid")
 private  Long user_role_userid;

@Column(name = "user_role_role")
 private  String user_role_role;

public UserRole() {
}public UserRole(Long id, String role) {
    this.user_role_userid = id;
    this.user_role_role = role;
}
public String getRole(){
    return user_role_role;
}


public Long getUserID(){
    return user_role_userid;
}


}