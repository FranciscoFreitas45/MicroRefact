package hei2017.DTO;
 import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonView;
import hei2017.json.JsonViews;
import org.hibernate.Hibernate;
import javax.persistence;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;
import hei2017.Request.SprintRequest;
import hei2017.Request.Impl.SprintRequestImpl;
import hei2017.DTO.Sprint;
public class Project implements Serializable{

 private  Long id;

 private  String nom;

 private  String description;

 private  Timestamp dateCreation;

 private  Set<Sprint> projectSprints;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://3";

// Constructeurs
public Project() {
    this.dateCreation = new Timestamp(System.currentTimeMillis());
}
public Set<Sprint> getProjectSprints(){
    return this.projectSprints;
}


public Timestamp getDateCreation(){
    return dateCreation;
}


public void setNom(String nom){
    this.nom = nom;
}


public Long getId(){
    return id;
}


public String getDescription(){
    return description;
}


public String getNom(){
    return nom;
}


public void addSprint(Sprint sprint){
    projectSprints.add(sprint);
}


public void setDescription(String description){
    this.description = description;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setDescription"))

.queryParam("description",description)
;
restTemplate.put(builder.toUriString(),null);
}


public void setDateCreation(Timestamp dateCreation){
    this.dateCreation = dateCreation;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setDateCreation"))

.queryParam("dateCreation",dateCreation)
;
restTemplate.put(builder.toUriString(),null);
}


}