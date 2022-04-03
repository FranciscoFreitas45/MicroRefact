package com.csquard.mregister.model;
 import com.csquard.mregister.model.audit.DateAudit;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.hibernate.annotations.NaturalId;
import javax.persistence;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;
@Entity
@Table(name = "users", uniqueConstraints = { @UniqueConstraint(columnNames = { "username" }), @UniqueConstraint(columnNames = { "email" }) })
public class User extends DateAudit{

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
 private  Long id;

@NotBlank
@Size(max = 40)
 private  String name;

@NotBlank
@Size(max = 15)
 private  String username;

@NaturalId
@NotBlank
@Size(max = 40)
@Email
 private  String email;

@NotBlank
@Size(max = 100)
 private  String password;

@ManyToMany(fetch = FetchType.LAZY)
@JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
 private  Set<Roles> roles;

public User() {
    super();
}public User(String name, String username, String email, String password) {
    this.name = name;
    this.username = username;
    this.email = email;
    this.password = password;
}
public void setName(String name){
    this.name = name;
}


public void setPassword(String password){
    this.password = password;
}


public String getPassword(){
    return password;
}


public String getName(){
    return name;
}


public void setUsername(String username){
    this.username = username;
}


public void setEmail(String email){
    this.email = email;
}


public void setId(Long id){
    this.id = id;
}


public String getEmail(){
    return email;
}


public Long getId(){
    return id;
}


public void setRoles(Set<Roles> roles){
    this.roles = roles;
}


public String getUsername(){
    return username;
}


public Set<Roles> getRoles(){
    return roles;
}


}