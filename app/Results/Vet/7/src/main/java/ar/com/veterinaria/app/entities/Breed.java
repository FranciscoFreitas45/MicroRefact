package ar.com.veterinaria.app.entities;
 import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@Entity
@Table(name = "Breed")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Breed implements Serializable{

 private  long serialVersionUID;

 public  Logger logger;

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "idBreed", unique = true, nullable = false)
@JsonIgnore
 private  Integer id;

@Column(name = "breed", length = 50)
 private  String breed;

@Column(name = "deleted", length = 50)
@JsonIgnore
 private  boolean deleted;

public Breed() {
    super();
}public Breed(String breed, boolean deleted) {
    super();
    this.breed = breed;
    this.deleted = deleted;
}
public void setBreed(String breed){
    this.breed = breed;
}


public boolean isDeleted(){
    return deleted;
}


public void setId(Integer id){
    this.id = id;
}


public String getBreed(){
    return breed;
}


public Integer getId(){
    return id;
}


public void setDeleted(boolean deleted){
    this.deleted = deleted;
}


}