package DTO;
 import java.util.List;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.Valid;
import org.hibernate.validator.constraints.NotBlank;
public class Float extends DomainEntity{

 private  String title;

 private  String description;

 private  List<String> pictures;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://1";


@NotBlank
public String getTitle(){
    return this.title;
}


@NotBlank
public String getDescription(){
    return this.description;
}


@Valid
@ElementCollection(targetClass = String.class)
public List<String> getPictures(){
    return this.pictures;
}


public void setPictures(List<String> pictures){
    this.pictures = pictures;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setPictures"))

.queryParam("pictures",pictures)
;
restTemplate.put(builder.toUriString(),null);
}


public void setDescription(String description){
    this.description = description;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setDescription"))

.queryParam("description",description)
;
restTemplate.put(builder.toUriString(),null);
}


public void setTitle(String title){
    this.title = title;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setTitle"))

.queryParam("title",title)
;
restTemplate.put(builder.toUriString(),null);
}


}