package hei2017.DTO;
 import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import hei2017.enumeration.StoryStatus;
import hei2017.json.JsonViews;
import javax.persistence;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;
import hei2017.Request.SprintRequest;
import hei2017.Request.Impl.SprintRequestImpl;
import hei2017.DTO.Sprint;
public class Story implements Serializable{

 private  Long id;

 private  String nom;

 private  String description;

 private  StoryStatus status;

 private  Integer points;

 private  Timestamp dateCreation;

 private  Sprint storySprint;

 private  Set<Task> storyTasks;

 private Long id;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://0";

// Constructeurs
public Story() {
    this.dateCreation = new Timestamp(System.currentTimeMillis());
    this.storySprint = null;
}
public Timestamp getDateCreation(){
    return dateCreation;
}


public Sprint getStorySprint(){
    return storySprint;
}


public Long getId(){
    return id;
}


@Enumerated(EnumType.STRING)
public StoryStatus getStatus(){
    return status;
}


public String getDescription(){
    return description;
}


public Integer getPoints(){
    return points;
}


public String getNom(){
    return nom;
}


public Set<Task> getStoryTasks(){
    return storyTasks;
}


public void setNom(String nom){
    this.nom = nom;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setNom"))

.queryParam("nom",nom)
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


public void setDateCreation(Timestamp dateCreation){
    this.dateCreation = dateCreation;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setDateCreation"))

.queryParam("dateCreation",dateCreation)
;
restTemplate.put(builder.toUriString(),null);
}


public void setStatus(StoryStatus status){
    this.status = status;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setStatus"))

.queryParam("status",status)
;
restTemplate.put(builder.toUriString(),null);
}


public void setPoints(Integer points){
    this.points = points;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setPoints"))

.queryParam("points",points)
;
restTemplate.put(builder.toUriString(),null);
}


public void setStorySprint(Sprint storySprint){
 sprintrequest.setStorySprint(storySprint,this.id);
 


  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setStorySprint"))

.queryParam("storySprint",storySprint)
;
restTemplate.put(builder.toUriString(),null);
}


}