package com.softserve.edu.Resources.DTO;
 import com.fasterxml.jackson.annotation.JsonIgnore;
import com.softserve.edu.Resources.Constants;
import javax.persistence;
import java.util.Collection;
public class Privilege {

 private  Long id;

 private  String name;

 private  String description;

 private  PrivilegeType privilegeType;

 private  Collection<Role> roles;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://9";

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
@Enumerated(EnumType.STRING)
public PrivilegeType getPrivilegeType(){
    return privilegeType;
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


public Collection<Role> getRoles(){
    return roles;
}


public void setName(String name){
    this.name = name;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setName"))

.queryParam("name",name)
;
restTemplate.put(builder.toUriString(),null);
}


public void setDescription(String description){
    this.description = description;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setDescription"))

.queryParam("description",description)
;
restTemplate.put(builder.toUriString(),null);
}


public void setPrivilegeType(PrivilegeType privilegeType){
    this.privilegeType = privilegeType;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setPrivilegeType"))

.queryParam("privilegeType",privilegeType)
;
restTemplate.put(builder.toUriString(),null);
}


}