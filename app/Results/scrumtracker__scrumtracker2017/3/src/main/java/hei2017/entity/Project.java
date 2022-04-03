package hei2017.entity;
 import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonView;
import hei2017.json.JsonViews;
import org.hibernate.Hibernate;
import javax.persistence;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;
import hei2017.Request.SprintRequest;
import hei2017.Request.Impl.SprintRequestImpl;
import hei2017.DTO.Sprint;
@Entity
public class Project implements Serializable{

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

@Transient
 private  Set<Sprint> projectSprints;

@Transient
 private SprintRequest sprintrequest = new SprintRequestImpl();;

// Constructeurs
public Project() {
    this.dateCreation = new Timestamp(System.currentTimeMillis());
}
public Set<Sprint> getProjectSprints(){
  this.projectSprints = sprintrequest.getProjectSprints(this.id);
return this.projectSprints;
}


public Timestamp getDateCreation(){
    return dateCreation;
}


public void setDateCreation(Timestamp dateCreation){
    this.dateCreation = dateCreation;
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


public String getDescription(){
    return description;
}


public String getNom(){
    return nom;
}


public void setProjectSprints(Set<Sprint> projectSprints){
 sprintrequest.setProjectSprints(projectSprints,this.id);
}



public void addSprint(Sprint sprint){
 sprintrequest.addSprint(sprint,this.id);
}



}