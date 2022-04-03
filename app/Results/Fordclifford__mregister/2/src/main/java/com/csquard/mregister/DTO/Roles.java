package com.csquard.mregister.DTO;
 import org.hibernate.annotations.NaturalId;
import javax.persistence;
public class Roles {

 private  Long id;

 private  RoleName name;

public Roles() {
}public Roles(RoleName name) {
    this.name = name;
}
public RoleName getName(){
    return name;
}


public Long getId(){
    return id;
}


}