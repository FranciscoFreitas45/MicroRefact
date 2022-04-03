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


}