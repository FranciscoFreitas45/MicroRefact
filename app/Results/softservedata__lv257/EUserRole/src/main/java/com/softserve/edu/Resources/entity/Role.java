package com.softserve.edu.Resources.entity;
 import com.fasterxml.jackson.annotation.JsonIgnore;
import com.softserve.edu.Resources.Constants;
import javax.persistence;
import java.util.Collection;
@Entity
@Table(name = "role")
public class Role {

@Id
@GeneratedValue(generator = Constants.ID_GENERATOR)
 private  Long id;

@ManyToMany(mappedBy = "roles")
@JsonIgnore
 private  Collection<User> users;

@ManyToMany
@JoinTable(name = "roles_privileges", joinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "privilege_id", referencedColumnName = "id"))
 private  Collection<Privilege> privileges;

 private  String name;

@Column(name = "description", nullable = false)
 private  String description;

public Role() {
    super();
}public Role(final String name) {
    super();
    this.name = name;
}
public void setName(String name){
    this.name = name;
}


public String getName(){
    return name;
}


public Long getId(){
    return id;
}


public void setDescription(String description){
    this.description = description;
}


public void setPrivileges(Collection<Privilege> privileges){
    this.privileges = privileges;
}


public String getDescription(){
    return description;
}


public void setUsers(Collection<User> users){
    this.users = users;
}


public Collection<User> getUsers(){
    return users;
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
    if (this == obj) {
        return true;
    }
    if (obj == null) {
        return false;
    }
    if (getClass() != obj.getClass()) {
        return false;
    }
    final Role role = (Role) obj;
    if (!role.equals(role.name)) {
        return false;
    }
    return true;
}


public void setId(Long id){
    this.id = id;
}


@Override
public String toString(){
    final StringBuilder builder = new StringBuilder();
    builder.append("Role [name=").append(name).append("]").append("[id=").append(id).append("]");
    return builder.toString();
}


public Collection<Privilege> getPrivileges(){
    return privileges;
}


}