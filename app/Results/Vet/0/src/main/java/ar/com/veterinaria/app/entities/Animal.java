package ar.com.veterinaria.app.entities;
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
@Entity
@Table(name = "Animal")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class Animal implements Serializable{

 private  long serialVersionUID;

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "idAnimal", unique = true, nullable = false)
@JsonIgnore
 private  Integer id;

@Column(name = "name", nullable = true, length = 70)
 private  String name;

@Column(name = "deleted", length = 70)
@JsonIgnore
 private  boolean deleted;

public Animal() {
    super();
}public Animal(String name, boolean deleted) {
    super();
    this.name = name;
    this.deleted = deleted;
}
public void setName(String name){
    this.name = name;
}


public String getName(){
    return name;
}


public boolean isDeleted(){
    return deleted;
}


public void setId(Integer id){
    this.id = id;
}


public Integer getId(){
    return id;
}


public void setDeleted(boolean deleted){
    this.deleted = deleted;
}


}