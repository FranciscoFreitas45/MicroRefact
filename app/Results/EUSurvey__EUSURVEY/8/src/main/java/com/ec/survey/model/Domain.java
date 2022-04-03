package com.ec.survey.model;
 import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
@Entity
@Table(name = "DOMAINS", uniqueConstraints = { @UniqueConstraint(columnNames = { "CODE" }, name = "CODE") })
public class Domain {

 private  long serialVersionUID;

 private  Integer id;

 private  String code;

 private  String description;

public Domain() {
}public Domain(String code, String description) {
    super();
    this.code = code;
    this.description = description;
}
public void setCode(String code){
    this.code = code;
}


public void setId(Integer id){
    this.id = id;
}


@Id
@Column(name = "ID")
@GeneratedValue
public Integer getId(){
    return id;
}


public void setDescription(String description){
    this.description = description;
}


@Column(name = "CODE")
public String getCode(){
    return code;
}


@Column(name = "DESCRIPTION")
public String getDescription(){
    return description;
}


}