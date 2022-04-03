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
    return storySprint;
}


public void setNom(String nom){
    this.nom = nom;
}


public void setStorySprint(Sprint storySprint){
    this.storySprint = storySprint;
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