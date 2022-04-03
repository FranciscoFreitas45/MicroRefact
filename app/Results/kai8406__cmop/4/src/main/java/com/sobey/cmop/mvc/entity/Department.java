package com.sobey.cmop.mvc.entity;
 import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
@Table(name = "department", catalog = "cmop")
public class Department {

 private  Integer id;

 private  String name;

 private  Integer pid;

 private  Set<User> users;

// Constructors
/**
 * default constructor
 */
public Department() {
}/**
 * minimal constructor
 */
public Department(String name) {
    this.name = name;
}/**
 * full constructor
 */
public Department(String name, Integer pid, Set<User> users) {
    this.name = name;
    this.pid = pid;
    this.users = users;
}
public void setName(String name){
    this.name = name;
}


@Column(name = "name", nullable = false, length = 20)
public String getName(){
    return this.name;
}


public void setUsers(Set<User> users){
    this.users = users;
}


@JsonIgnore
@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "department")
public Set<User> getUsers(){
    return this.users;
}


public void setId(Integer id){
    this.id = id;
}


@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "id", unique = true, nullable = false)
public Integer getId(){
    return this.id;
}


public void setPid(Integer pid){
    this.pid = pid;
}


@Column(name = "pid")
public Integer getPid(){
    return this.pid;
}


}