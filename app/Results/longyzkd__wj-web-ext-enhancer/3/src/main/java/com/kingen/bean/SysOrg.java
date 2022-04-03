package com.kingen.bean;
 import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
@Entity
@Table(name = "sys_org")
public class SysOrg {

 private  String id;

 private  String name;

// Constructors
/**
 * default constructor
 */
public SysOrg() {
}/**
 * full constructor
 */
public SysOrg(String name) {
    this.name = name;
}
public void setName(String name){
    this.name = name;
}


@Column(name = "name", length = 50)
public String getName(){
    return this.name;
}


public void setId(String id){
    this.id = id;
}


@GenericGenerator(name = "generator", strategy = "uuid")
@Id
@GeneratedValue(generator = "generator")
@Column(name = "id", unique = true, nullable = false, length = 32)
public String getId(){
    return this.id;
}


}