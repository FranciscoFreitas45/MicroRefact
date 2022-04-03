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

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://12";


public String getName(){
    return name;
}


public long getId(){
    return id;
}


public String getDescription(){
    return description;
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
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/equals"))

.queryParam("obj",obj)
;
boolean aux = restTemplate.getForObject(builder.toUriString(),boolean.class);
return aux;
}


}