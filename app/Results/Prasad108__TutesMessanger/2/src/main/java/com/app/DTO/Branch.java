package com.app.DTO;
 import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
public class Branch {

 private  Integer id;

 private  Institute institute;

 private  String name;

 private  Set<Classes> classeses;

 private  Set<Classes> classeses_1;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://3";

public Branch() {
}public Branch(Institute institute, String name, Set<Classes> classeses, Set<Classes> classeses_1) {
    this.institute = institute;
    this.name = name;
    this.classeses = classeses;
    this.classeses_1 = classeses_1;
}
@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "instituteid")
public Institute getInstitute(){
    return this.institute;
}


@OneToMany(fetch = FetchType.LAZY, mappedBy = "branch")
public Set<Classes> getClasseses_1(){
    return this.classeses_1;
}


@Column(name = "Name", length = 50)
public String getName(){
    return this.name;
}


public void setId(Integer id){
    this.id = id;
}


@Id
@GeneratedValue(strategy = IDENTITY)
@Column(name = "id", unique = true, nullable = false)
public Integer getId(){
    return this.id;
}


@OneToMany(fetch = FetchType.LAZY, mappedBy = "branch")
public Set<Classes> getClasseses(){
    return this.classeses;
}


public void setName(String name){
    this.name = name;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setName"))

.queryParam("name",name)
;
restTemplate.put(builder.toUriString(),null);
}


public void setInstitute(Institute institute){
    this.institute = institute;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setInstitute"))

.queryParam("institute",institute)
;
restTemplate.put(builder.toUriString(),null);
}


}