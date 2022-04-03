package com.ec.survey.DTO;
 import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import javax.persistence;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
public class DependencyItem {

 private  long serialVersionUID;

 private  Integer id;

 private  Integer position;

 private  List<Element> dependentElements;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://10";


@ManyToMany(targetEntity = Element.class)
@JoinTable(name = "POSSIBLEANSWER_ELEMENT")
@Fetch(value = FetchMode.SELECT)
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public List<Element> getDependentElements(){
    return dependentElements;
}


@Column(name = "POS")
public Integer getPosition(){
    return position;
}


@Transient
public Set<String> getDependentElementUniqueIds(){
    Set<String> results = new HashSet<>();
    for (Element e : dependentElements) {
        results.add(e.getUniqueId());
    }
    return results;
}


@Id
@Column(name = "ID")
@GeneratedValue
public Integer getId(){
    return id;
}


public void setPosition(Integer position){
    this.position = position;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setPosition"))

.queryParam("position",position)
;
restTemplate.put(builder.toUriString(),null);
}


}