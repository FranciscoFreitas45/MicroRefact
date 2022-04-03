package ar.com.veterinaria.app.entities.DTO;
 import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
public class Animal implements Serializable{

 private  long serialVersionUID;

 private  Integer id;

 private  String name;

 private  boolean deleted;

public Animal() {
    super();
}public Animal(String name, boolean deleted) {
    super();
    this.name = name;
    this.deleted = deleted;
}
public String getName(){
    return name;
}


public Integer getId(){
    return id;
}


}