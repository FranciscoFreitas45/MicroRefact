package hei2017.DTO;
 import com.fasterxml.jackson.annotation.JsonView;
import hei2017.json.JsonViews;
import javax.persistence;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;
import hei2017.Request.ProjectRequest;
import hei2017.Request.Impl.ProjectRequestImpl;
import hei2017.DTO.Project;
import hei2017.Request.StoryRequest;
import hei2017.Request.Impl.StoryRequestImpl;
import hei2017.DTO.Story;
public class Sprint implements Serializable{

 private  Long id;

 private  String nom;

 private  String description;

 private  Timestamp dateCreation;

 private  Timestamp dateModification;

 private  Timestamp dateDebut;

 private  Timestamp dateFin;

 private  Project sprintProject;

 private  Set<Story> sprintStories;

 private Long id;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://1";

// Constructeurs
public Sprint() {
    this.dateCreation = new Timestamp(System.currentTimeMillis());
    this.sprintProject = null;
}
public Timestamp getDateCreation(){
    return dateCreation;
}


public Timestamp getDateModification(){
    return dateModification;
}


public Set<Story> getSprintStories(){
    return sprintStories;
}


public Timestamp getDateDebut(){
    return dateDebut;
}


public Long getId(){
    return id;
}


public Project getSprintProject(){
    return sprintProject;
}


public String getStatus(){
    if (this.getDateFin() != null) {
        if (this.getDateFin().before(new Timestamp(System.currentTimeMillis()))) {
            return "over";
        } else {
            if (this.getDateDebut().before(new Timestamp(System.currentTimeMillis()))) {
                return "actual";
            } else {
                return "upcoming";
            }
        }
    } else {
        return "undefined";
    }
}


public String getDescription(){
    return description;
}


public Timestamp getDateFin(){
    return dateFin;
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


public void setDateCreation(Timestamp dateCreation){
    this.dateCreation = dateCreation;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setDateCreation"))

.queryParam("dateCreation",dateCreation)
;
restTemplate.put(builder.toUriString(),null);
}


public void setDateDebut(Timestamp dateDebut){
    this.dateDebut = dateDebut;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setDateDebut"))

.queryParam("dateDebut",dateDebut)
;
restTemplate.put(builder.toUriString(),null);
}


public void setDateFin(Timestamp dateFin){
    this.dateFin = dateFin;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setDateFin"))

.queryParam("dateFin",dateFin)
;
restTemplate.put(builder.toUriString(),null);
}


public void setSprintProject(Project sprintProject){
 projectrequest.setSprintProject(sprintProject,this.id);
 


  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setSprintProject"))

.queryParam("sprintProject",sprintProject)
;
restTemplate.put(builder.toUriString(),null);
}


}