package ar.com.veterinaria.app.entities.DTO;
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
public class Breed implements Serializable{

 private  long serialVersionUID;

 public  Logger logger;

 private  Integer id;

 private  String breed;

 private  boolean deleted;

public Breed() {
    super();
}public Breed(String breed, boolean deleted) {
    super();
    this.breed = breed;
    this.deleted = deleted;
}
public String getBreed(){
    return breed;
}


public Integer getId(){
    return id;
}


}