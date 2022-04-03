package ar.com.veterinaria.app.entities;
 import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import ar.com.veterinaria.app.entities.Request.BreedRequest;
import ar.com.veterinaria.app.entities.Request.Impl.BreedRequestImpl;
import ar.com.veterinaria.app.entities.DTO.Breed;
import ar.com.veterinaria.app.entities.Request.AnimalRequest;
import ar.com.veterinaria.app.entities.Request.Impl.AnimalRequestImpl;
import ar.com.veterinaria.app.entities.DTO.Animal;
@Entity
@Table(name = "animalbreed")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class AnimalBreed implements Serializable{

 private  long serialVersionUID;

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "idAnimalBreed", unique = true, nullable = false)
@JsonIgnore
 private  Integer id;

@Transient
 private  Breed breed;

@Transient
 private  Animal animal;

@Column(name = "deleted", nullable = false)
@JsonIgnore
 private  boolean deleted;

@Column(name = "id")
 private Integer id;

@Transient
 private BreedRequest breedrequest = new BreedRequestImpl();;

@Column(name = "id")
 private Integer id;

@Transient
 private AnimalRequest animalrequest = new AnimalRequestImpl();;

public AnimalBreed() {
    super();
}public AnimalBreed(Breed breed, Animal animal) {
    super();
    this.breed = breed;
    this.animal = animal;
    this.deleted = false;
}
public void setBreed(Breed breed){
 breedrequest.setBreed(breed,this.id);
}



public boolean isDeleted(){
    return deleted;
}


public void setId(Integer id){
    this.id = id;
}


public Breed getBreed(){
  this.breed = breedrequest.getBreed(this.id);
return this.breed;
}


public Integer getId(){
    return id;
}


public Animal getAnimal(){
  this.animal = animalrequest.getAnimal(this.id);
return this.animal;
}


public void setAnimal(Animal animal){
 animalrequest.setAnimal(animal,this.id);
}



public void setDeleted(boolean deleted){
    this.deleted = deleted;
}


}