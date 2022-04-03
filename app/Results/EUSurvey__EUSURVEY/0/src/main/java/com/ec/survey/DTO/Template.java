package com.ec.survey.DTO;
 import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import com.ec.survey.model.administration.User;
import com.ec.survey.model.survey.Element;
public class Template {

 private  long serialVersionUID;

 private  Integer id;

 private  String name;

 private  Element element;

 private  User owner;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://10";


@Column(name = "TEMPL_NAME")
public String getName(){
    return name;
}


@OneToOne(cascade = CascadeType.ALL)
public Element getElement(){
    return element;
}


@Id
@Column(name = "TEMPL_ID")
@GeneratedValue
public Integer getId(){
    return id;
}


@ManyToOne
@JoinColumn(name = "OWNER", nullable = false)
public User getOwner(){
    return owner;
}


public void setName(String name){
    this.name = name;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setName"))

.queryParam("name",name)
;
restTemplate.put(builder.toUriString(),null);
}


public void setOwner(User owner){
    this.owner = owner;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setOwner"))

.queryParam("owner",owner)
;
restTemplate.put(builder.toUriString(),null);
}


public void setElement(Element element){
    this.element = element;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setElement"))

.queryParam("element",element)
;
restTemplate.put(builder.toUriString(),null);
}


}