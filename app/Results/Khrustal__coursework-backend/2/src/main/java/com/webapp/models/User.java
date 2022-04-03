package com.webapp.models;
 import java.util.HashSet;
import java.util.Set;
import javax.persistence;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
@Entity
@Table(name = "users", uniqueConstraints = { @UniqueConstraint(columnNames = "username") })
public class User {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
 private  Long id;

@NotBlank
@Size(max = 20)
 private  String username;

@NotBlank
@Size(max = 120)
 private  String password;

@ManyToMany(fetch = FetchType.LAZY)
@JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
 private  Set<Role> roles;

public User() {
}public User(String username, String password) {
    this.username = username;
    this.password = password;
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


public void setId(Long id){
    this.id = id;
}


public Long getId(){
    return id;
}


public void setRoles(Set<Role> roles){
    this.roles = roles;
}


public String getUsername(){
    return username;
}


public Set<Role> getRoles(){
    return roles;
}


}