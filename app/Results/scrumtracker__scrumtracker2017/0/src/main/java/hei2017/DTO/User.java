package hei2017.DTO;
 import com.fasterxml.jackson.annotation;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import hei2017.json.JsonViews;
import javax.persistence;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;
public class User implements Serializable{

 private  Long id;

 private  String nom;

 private  String prenom;

 private  String pseudo;

 private  String email;

 private  Timestamp dateCreation;

 private  String password;

 private  Set<Task> userTasks;

public User() {
    this.dateCreation = new Timestamp(System.currentTimeMillis());
}
public Timestamp getDateCreation(){
    return dateCreation;
}


public void setNom(String nom){
    this.nom = nom;
}


public Long getId(){
    return id;
}


public Set<Task> getUserTasks(){
    return userTasks;
}


public String getPseudo(){
    return pseudo;
}


public void addTask(Task tache){
    this.userTasks.add(tache);
}


public String getPassword(){
    return password;
}


public String getPrenom(){
    return prenom;
}


public String getEmail(){
    return email;
}


public String getNom(){
    return nom;
}


}