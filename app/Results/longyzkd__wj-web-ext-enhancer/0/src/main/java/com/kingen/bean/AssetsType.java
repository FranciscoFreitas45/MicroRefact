package com.kingen.bean;
 import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
@Entity
@Table(name = "t_assets_type")
public class AssetsType {

 private  String id;

 private  String name;

 private  String pid;

// Constructors
/**
 * default constructor
 */
public AssetsType() {
}/**
 * full constructor
 */
public AssetsType(String name, String pid) {
    this.name = name;
    this.pid = pid;
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


public void setPid(String pid){
    this.pid = pid;
}


@Column(name = "pid", length = 32)
public String getPid(){
    return this.pid;
}


}