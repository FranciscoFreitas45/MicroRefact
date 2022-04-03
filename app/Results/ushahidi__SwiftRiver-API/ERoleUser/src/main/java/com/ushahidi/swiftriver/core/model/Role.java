package com.ushahidi.swiftriver.core.model;
 import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
@Entity
@Table(name = "roles")
public class Role {

@Id
@GeneratedValue
 private  long id;

 private  String name;

 private  String description;


public void setName(String name){
    this.name = name;
}


public String getName(){
    return name;
}


@Override
public int hashCode(){
    return new HashCodeBuilder(17, 31).append(name).toHashCode();
}


@Override
public boolean equals(Object obj){
    if (obj == null)
        return false;
    if (obj == this)
        return true;
    if (obj.getClass() != getClass())
        return false;
    Role other = (Role) obj;
    return new EqualsBuilder().append(name, other.name).isEquals();
}


public void setId(long id){
    this.id = id;
}


public long getId(){
    return id;
}


public void setDescription(String description){
    this.description = description;
}


public String getDescription(){
    return description;
}


}