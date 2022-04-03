package com.ushahidi.swiftriver.core.DTO;
 import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
public class Role {

 private  long id;

 private  String name;

 private  String description;


public String getName(){
    return name;
}


public long getId(){
    return id;
}


public String getDescription(){
    return description;
}


}