package com.ec.survey.DTO;
 import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
public class AttributeName {

 private  long serialVersionUID;

 private  Integer id;

 private  Integer ownerId;

 private  String name;

public AttributeName() {
}public AttributeName(Integer ownerId, String name) {
    this.name = name;
    this.ownerId = ownerId;
}
@Column(name = "AN_NAME")
public String getName(){
    return name;
}


@Column(name = "OWNER_ID")
public Integer getOwnerId(){
    return ownerId;
}


@Id
@Column(name = "AN_ID")
@GeneratedValue
public Integer getId(){
    return id;
}


}