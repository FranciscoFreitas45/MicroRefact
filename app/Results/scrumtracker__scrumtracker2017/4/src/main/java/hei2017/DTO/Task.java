package hei2017.DTO;
 import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import hei2017.enumeration.StoryStatus;
import hei2017.enumeration.UniteTemps;
import hei2017.json.JsonViews;
import org.hibernate.annotations.Cascade;
import javax.persistence;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;
public class Task implements Serializable{

 private  Long id;

 private  String nom;

 private  String description;

 private  Long tempsDeCharge;

 private  UniteTemps uniteTempsDeCharge;

 private  Timestamp dateCreation;

 private  StoryStatus status;

 private  Set<User> taskUsers;

 private  Story taskStory;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://6";

// Constructeurs
public Task() {
    this.dateCreation = new Timestamp(System.currentTimeMillis());
}
public Timestamp getDateCreation(){
    return dateCreation;
}


public Set<User> getTaskUsers(){
    return taskUsers;
}


public Long getId(){
    return id;
}


public Long getTempsDeCharge(){
    return tempsDeCharge;
}


@Enumerated(EnumType.STRING)
public StoryStatus getStatus(){
    return status;
}


public String getDescription(){
    return description;
}


public UniteTemps getUniteTempsDeCharge(){
    return uniteTempsDeCharge;
}


public Story getTaskStory(){
    return taskStory;
}


public String getNom(){
    return nom;
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


public void setTaskStory(Story taskStory){
    this.taskStory = taskStory;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setTaskStory"))

.queryParam("taskStory",taskStory)
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


}