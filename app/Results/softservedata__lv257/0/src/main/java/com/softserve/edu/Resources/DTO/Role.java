package com.softserve.edu.Resources.DTO;
 import com.fasterxml.jackson.annotation.JsonIgnore;
import com.softserve.edu.Resources.Constants;
import javax.persistence;
import java.util.Collection;
public class Role {

 private  Long id;

 private  Collection<User> users;

 private  Collection<Privilege> privileges;

 private  String name;

 private  String description;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://9";

public Role() {
    super();
}public Role(final String name) {
    super();
    this.name = name;
}
public String getName(){
    return name;
}


public Long getId(){
    return id;
}


public String getDescription(){
    return description;
}


public Collection<User> getUsers(){
    return users;
}


public Collection<Privilege> getPrivileges(){
    return privileges;
}


public void setPrivileges(Collection<Privilege> privileges){
    this.privileges = privileges;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setPrivileges"))

.queryParam("privileges",privileges)
;
restTemplate.put(builder.toUriString(),null);
}


}