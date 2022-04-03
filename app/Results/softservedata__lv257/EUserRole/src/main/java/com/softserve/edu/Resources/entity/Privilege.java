package com.softserve.edu.Resources.entity;
 import com.fasterxml.jackson.annotation.JsonIgnore;
import com.softserve.edu.Resources.Constants;
import javax.persistence;
import java.util.Collection;
@Entity
@Table(name = "privilege")
public class Privilege {

@Id
@GeneratedValue(generator = Constants.ID_GENERATOR)
 private  Long id;

 private  String name;

 private  String description;

 private  PrivilegeType privilegeType;

@JsonIgnore
@ManyToMany(mappedBy = "privileges")
 private  Collection<Role> roles;

public Privilege() {
    super();
}public Privilege(final String name) {
    super();
    this.name = name;
}public Privilege(final String name, final PrivilegeType privilegeType) {
    super();
    this.name = name;
    this.privilegeType = privilegeType;
}public Privilege(final String name, final String description, final PrivilegeType privilegeType) {
    super();
    this.name = name;
    this.description = description;
    this.privilegeType = privilegeType;
}
public void setName(String name){
    this.name = name;
}


@Enumerated(EnumType.STRING)
public PrivilegeType getPrivilegeType(){
    return privilegeType;
}


public String getName(){
    return name;
}


public void setPrivilegeType(PrivilegeType privilegeType){
    this.privilegeType = privilegeType;
}


public Long getId(){
    return id;
}


public void setDescription(String description){
    this.description = description;
}


public String getDescription(){
    return description;
}


@Override
public int hashCode(){
    final int prime = 31;
    int result = 1;
    result = prime * result + ((name == null) ? 0 : name.hashCode());
    return result;
}


@Override
public boolean equals(Object obj){
    if (this == obj)
        return true;
    if (obj == null)
        return false;
    if (getClass() != obj.getClass())
        return false;
    Privilege other = (Privilege) obj;
    if (name == null) {
        if (other.name != null)
            return false;
    } else if (!name.equals(other.name))
        return false;
    return true;
}


public void setId(Long id){
    this.id = id;
}


@Override
public String toString(){
    return "Privilege{" + "id=" + id + ", name='" + name + '\'' + ", description='" + description + '\'' + ", privilegeType=" + privilegeType + ", roles=" + roles + '}';
}


public void setRoles(Collection<Role> roles){
    this.roles = roles;
}


public Collection<Role> getRoles(){
    return roles;
}


}