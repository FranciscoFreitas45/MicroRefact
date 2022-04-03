package hei2017.entity;
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
@Entity
public class Sprint implements Serializable{

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@Column(unique = true)
@JsonView(JsonViews.Basique.class)
 private  Long id;

@JsonView(JsonViews.Basique.class)
 private  String nom;

@JsonView(JsonViews.Basique.class)
 private  String description;

@JsonView(JsonViews.Basique.class)
 private  Timestamp dateCreation;

@JsonView(JsonViews.Basique.class)
 private  Timestamp dateModification;

@JsonView(JsonViews.Basique.class)
 private  Timestamp dateDebut;

@JsonView(JsonViews.Basique.class)
 private  Timestamp dateFin;

@Transient
 private  Project sprintProject;

@Transient
 private  Set<Story> sprintStories;

@Column(name = "id")
 private Long id;

@Transient
 private ProjectRequest projectrequest = new ProjectRequestImpl();;

@Transient
 private StoryRequest storyrequest = new StoryRequestImpl();;

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
 projectrequest.setSprintProject(sprintProject,this.id);
}



public Set<Story> getSprintStories(){
  this.sprintStories = storyrequest.getSprintStories(this.id);
return this.sprintStories;
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
  this.sprintProject = projectrequest.getSprintProject(this.id);
return this.sprintProject;
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
 storyrequest.setSprintStories(sprintStories,this.id);
}



public Timestamp getDateFin(){
    return dateFin;
}


public void setDateDebut(Timestamp dateDebut){
    this.dateDebut = dateDebut;
}


public Sprint addStory(Story story){
  this.sprintStories = storyrequest.addStory(story,this.id);
return this.sprintStories;
}


public String getNom(){
    return nom;
}


public void setDateFin(Timestamp dateFin){
    this.dateFin = dateFin;
}


}