package DTO;
 import java.util.List;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotBlank;
public class Box extends DomainEntity{

 private  String name;

 private  Boolean isSystem;

 private  Box fatherBox;

 private  List<Message> messages;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://8";


@NotBlank
public String getName(){
    return this.name;
}


@Valid
@ManyToOne(optional = true)
public Box getFatherBox(){
    return this.fatherBox;
}


@NotNull
public Boolean getIsSystem(){
    return this.isSystem;
}


@Valid
@ManyToMany
public List<Message> getMessages(){
    return this.messages;
}


public void setName(String name){
    this.name = name;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setName"))

.queryParam("name",name)
;
restTemplate.put(builder.toUriString(),null);
}


}