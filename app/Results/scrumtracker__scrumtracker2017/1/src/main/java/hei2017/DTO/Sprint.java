package hei2017.DTO;
 import com.fasterxml.jackson.annotation.JsonView;
import hei2017.json.JsonViews;
import javax.persistence;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;
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


public void setDateCreation(Timestamp dateCreation){
    this.dateCreation = dateCreation;
}


public void setSprintProject(Project sprintProject){
    this.sprintProject = sprintProject;
}


public Set<Story> getSprintStories(){
    return sprintStories;
}


public void setNom(String nom){
    this.nom = nom;
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


public void setDescription(String description){
    this.description = description;
}


public void setDateModification(Timestamp dateModification){
    this.dateModification = dateModification;
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


public void setSprintStories(Set<Story> sprintStories){
    this.sprintStories = sprintStories;
}


public Timestamp getDateFin(){
    return dateFin;
}


public void setDateDebut(Timestamp dateDebut){
    this.dateDebut = dateDebut;
}


public Sprint addStory(Story story){
    sprintStories.add(story);
    return this;
}


public String getNom(){
    return nom;
}


public void setDateFin(Timestamp dateFin){
    this.dateFin = dateFin;
}


}