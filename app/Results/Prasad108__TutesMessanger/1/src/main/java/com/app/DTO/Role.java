package com.app.DTO;
 import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
public class Role {

 private  Integer id;

 private  String name;

 private  Set<Login> logins;

 private  Set<Message> messages;

 private  Set<Login> logins_1;

public Role() {
}public Role(String name) {
    this.name = name;
}public Role(Integer id, String name) {
    super();
    this.id = id;
    this.name = name;
}public Role(String name, Set<Login> logins, Set<Message> messages, Set<Login> logins_1) {
    this.name = name;
    this.logins = logins;
    this.messages = messages;
    this.logins_1 = logins_1;
}
@Column(name = "name", nullable = false, length = 20)
public String getName(){
    return this.name;
}


@OneToMany(fetch = FetchType.LAZY, mappedBy = "role")
public Set<Login> getLogins_1(){
    return this.logins_1;
}


@Id
@GeneratedValue(strategy = IDENTITY)
@Column(name = "id", unique = true, nullable = false)
public Integer getId(){
    return this.id;
}


@OneToMany(fetch = FetchType.LAZY, mappedBy = "role")
public Set<Message> getMessages(){
    return this.messages;
}


@OneToMany(fetch = FetchType.LAZY, mappedBy = "role")
public Set<Login> getLogins(){
    return this.logins;
}


}