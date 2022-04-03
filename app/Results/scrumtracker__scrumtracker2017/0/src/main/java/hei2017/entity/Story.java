package hei2017.entity;
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
@Entity
public class Story implements Serializable{

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
 private  StoryStatus status;

@JsonView(JsonViews.Basique.class)
 private  Integer points;

@JsonView(JsonViews.Basique.class)
 private  Timestamp dateCreation;

@Transient
 private  Sprint storySprint;

@JsonView(JsonViews.Story.class)
@OneToMany(mappedBy = "taskStory", cascade = CascadeType.ALL)
 private  Set<Task> storyTasks;

@Column(name = "id")
 private Long id;

@Transient
 private SprintRequest sprintrequest = new SprintRequestImpl();;

// Constructeurs
public Story() {
    this.dateCreation = new Timestamp(System.currentTimeMillis());
    this.storySprint = null;
}
public Timestamp getDateCreation(){
    return dateCreation;
}


public void setDateCreation(Timestamp dateCreation){
    this.dateCreation = dateCreation;
}


public Sprint getStorySprint(){
  this.storySprint = sprintrequest.getStorySprint(this.id);
return this.storySprint;
}


public void setNom(String nom){
    this.nom = nom;
}


public void setStorySprint(Sprint storySprint){
 sprintrequest.setStorySprint(storySprint,this.id);
}



public Long getId(){
    return id;
}


public void setDescription(String description){
    this.description = description;
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


public void setStoryTasks(Set<Task> storyTasks){
    this.storyTasks = storyTasks;
}


public void addTask(Task task){
    storyTasks.add(task);
}


public Integer getPoints(){
    return points;
}


public void setPoints(Integer points){
    this.points = points;
}


public String getNom(){
    return nom;
}


public Set<Task> getStoryTasks(){
    return storyTasks;
}


}