package ar.com.veterinaria.app.entities.DTO;
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
public class AnimalBreed implements Serializable{

 private  long serialVersionUID;

 private  Integer id;

 private  Breed breed;

 private  Animal animal;

 private  boolean deleted;

 private Integer id;

 private Integer id;

public AnimalBreed() {
    super();
}public AnimalBreed(Breed breed, Animal animal) {
    super();
    this.breed = breed;
    this.animal = animal;
    this.deleted = false;
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


}