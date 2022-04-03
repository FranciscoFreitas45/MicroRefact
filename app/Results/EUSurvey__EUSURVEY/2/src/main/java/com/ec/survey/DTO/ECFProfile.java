package com.ec.survey.DTO;
 import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.log4j.Logger;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
public class ECFProfile implements Comparable<ECFProfile>,Serializable{

 private  long serialVersionUID;

 private  int id;

 private  String profileUid;

 private  String description;

 private  String name;

 private  List<ECFExpectedScore> expectedScores;

 private  Integer orderNumber;

 protected  Logger logger;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://9";

private ECFProfile() {
}public ECFProfile(String profileUid, String name, String description, Integer order) {
    this.profileUid = profileUid;
    this.description = description;
    this.name = name;
    this.orderNumber = order;
}
@Column(name = "PROFILE_NAME")
public String getName(){
    return name;
}


@JsonIgnore
@OneToMany(mappedBy = "id.profile", cascade = CascadeType.ALL, orphanRemoval = true)
@Fetch(value = FetchMode.SELECT)
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public List<ECFExpectedScore> getECFExpectedScores(){
    return expectedScores;
}


@Id
@Column(name = "PROFILE_ID")
@GeneratedValue
public Integer getId(){
    return id;
}


public Integer getOrderNumber(){
    return orderNumber;
}


@Column(name = "PROFILE_DESC")
public String getDescription(){
    return description;
}


@Column(name = "PROFILE_UID")
public String getProfileUid(){
    return profileUid;
}


public void setOrderNumber(Integer orderNumber){
    this.orderNumber = orderNumber;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setOrderNumber"))

.queryParam("orderNumber",orderNumber)
;
restTemplate.put(builder.toUriString(),null);
}


}