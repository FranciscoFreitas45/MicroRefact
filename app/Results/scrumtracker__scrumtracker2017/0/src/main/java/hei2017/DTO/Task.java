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


public void addUser(User user){
    this.taskUsers.add(user);
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


public void setStatus(StoryStatus status){
    this.status = status;
}


public void setUniteTempsDeCharge(UniteTemps uniteTempsDeCharge){
    this.uniteTempsDeCharge = uniteTempsDeCharge;
}


public UniteTemps getUniteTempsDeCharge(){
    return uniteTempsDeCharge;
}


public Story getTaskStory(){
    return taskStory;
}


public void setTaskUsers(Set<User> taskUsers){
    this.taskUsers = taskUsers;
}


public String getNom(){
    return nom;
}


public void setTaskStory(Story taskStory){
    this.taskStory = taskStory;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setTaskStory"))

.queryParam("taskStory",taskStory)
;
restTemplate.put(builder.toUriString(),null);
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


public void setTempsDeCharge(Long tempsDeCharge){
    this.tempsDeCharge = tempsDeCharge;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setTempsDeCharge"))

.queryParam("tempsDeCharge",tempsDeCharge)
;
restTemplate.put(builder.toUriString(),null);
}


}