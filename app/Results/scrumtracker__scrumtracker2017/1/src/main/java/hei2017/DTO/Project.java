package hei2017.DTO;
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
public class Project implements Serializable{

 private  Long id;

 private  String nom;

 private  String description;

 private  Timestamp dateCreation;

 private  Set<Sprint> projectSprints;

// Constructeurs
public Project() {
    this.dateCreation = new Timestamp(System.currentTimeMillis());
}
public Set<Sprint> getProjectSprints(){
    return this.projectSprints;
}


public Timestamp getDateCreation(){
    return dateCreation;
}


public Long getId(){
    return id;
}


public String getDescription(){
    return description;
}


public String getNom(){
    return nom;
}


}