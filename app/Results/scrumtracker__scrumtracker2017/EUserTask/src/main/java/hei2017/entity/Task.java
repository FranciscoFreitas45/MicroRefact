package hei2017.entity;
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
@Entity
public class Task implements Serializable{

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
 private  Long tempsDeCharge;

@JsonView(JsonViews.Basique.class)
 private  UniteTemps uniteTempsDeCharge;

@JsonView(JsonViews.Basique.class)
 private  Timestamp dateCreation;

@JsonView(JsonViews.Basique.class)
 private  StoryStatus status;

@JsonView(JsonViews.Task.class)
@ManyToMany(mappedBy = "userTasks", cascade = { CascadeType.ALL })
 private  Set<User> taskUsers;

@JsonView(JsonViews.Task.class)
@ManyToOne
 private  Story taskStory;

// Constructeurs
public Task() {
    this.dateCreation = new Timestamp(System.currentTimeMillis());
}
public Timestamp getDateCreation(){
    return dateCreation;
}


public Boolean isDOING(){
    return this.status == StoryStatus.DOING;
}


public Set<User> getTaskUsers(){
    return taskUsers;
}


public void setDateCreation(Timestamp dateCreation){
    this.dateCreation = dateCreation;
}


public void addUser(User user){
    this.taskUsers.add(user);
}


public void setNom(String nom){
    this.nom = nom;
}


public Long getId(){
    return id;
}


public void setDescription(String description){
    this.description = description;
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


public Boolean isDONE(){
    return this.status == StoryStatus.DONE;
}


public void setStatus(StoryStatus status){
    this.status = status;
}


public void setTaskStory(Story taskStory){
    this.taskStory = taskStory;
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


public Boolean isTODO(){
    return this.status == StoryStatus.TODO;
}


public void setTaskUsers(Set<User> taskUsers){
    this.taskUsers = taskUsers;
}


public String getNom(){
    return nom;
}


public void setTempsDeCharge(Long tempsDeCharge){
    this.tempsDeCharge = tempsDeCharge;
}


}