package com.tech.models.entities.user;
 import com.fasterxml.jackson.annotation.JsonProperty;
import com.tech.models.dtos.user.RegisteredUserDTO;
import com.tech.models.dtos.user.UserDTO;
import javax.persistence;
import java.io.Serializable;
@Entity
@NamedQueries({ @NamedQuery(name = "User.findByUserid", query = "SELECT p FROM User p WHERE p.id = ?1"), @NamedQuery(name = "User.findByUsername", query = "SELECT p FROM User p WHERE p.username = ?1"), @NamedQuery(name = "User.findByUsernameAndPassword", query = "SELECT p FROM User p WHERE p.username = ?1 AND p.password = ?2") })
@Table(name = "Usersdata")
public class User implements Serializable{

// id = primary key
@Id
// column that the variable belongs
@Column(name = "id")
 private  Long id;

// column that the variable belongs
@Column(name = "username")
 private  String username;

// column that the variable belongs
@Column(name = "password")
 private  String password;

@Column(name = "enabled")
 private  boolean enabled;

@Column(name = "hasroom")
 private  boolean hasRoom;

public User() {
}public User(Long id, UserDTO userDTO) {
    this(id, userDTO.getUsername(), userDTO.getPassword(), userDTO.isEnabled(), userDTO.isHasRoom());
}public User(Long id, RegisteredUserDTO userDTO) {
    this(id, userDTO.getUsername(), userDTO.getPassword(), true, false);
}public User(Long id, String username, String password, boolean enabled) {
    this(id, username, password, enabled, false);
}public User(Long id, String username, String password, boolean enabled, boolean hasRoom) {
    this.id = id;
    this.username = username;
    this.password = password;
    this.enabled = enabled;
    this.hasRoom = hasRoom;
}
@JsonProperty
public String getPassword(){
    return password;
}


@JsonProperty
public boolean isEnabled(){
    return enabled;
}


@JsonProperty
public Long getId(){
    return id;
}


public boolean hasRoom(){
    return hasRoom;
}


@JsonProperty
public String getUsername(){
    return username;
}


}