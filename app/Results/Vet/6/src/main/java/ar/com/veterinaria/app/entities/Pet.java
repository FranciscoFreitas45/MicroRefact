package ar.com.veterinaria.app.entities;
 import java.io.Serializable;
import java.time.LocalDate;
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
import ar.com.veterinaria.app.entities.Request.AnimalBreedRequest;
import ar.com.veterinaria.app.entities.Request.Impl.AnimalBreedRequestImpl;
import ar.com.veterinaria.app.entities.DTO.AnimalBreed;
import ar.com.veterinaria.app.entities.Request.ClinicalHistoryRequest;
import ar.com.veterinaria.app.entities.Request.Impl.ClinicalHistoryRequestImpl;
import ar.com.veterinaria.app.entities.DTO.ClinicalHistory;
@Entity
@Table(name = "pet")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class Pet implements Serializable{

 private  long serialVersionUID;

@Id
@GeneratedValue(strategy = GenerationType.SEQUENCE)
@Column(name = "idPet", unique = true, nullable = false)
@JsonIgnore
 private  Integer id;

@Column(name = "name", nullable = true, length = 70)
 private  String name;

@Column(name = "birthday", nullable = true, length = 30)
 private  LocalDate birthday;

@Column(name = "size", nullable = true, length = 50)
 private  String size;

@Column(name = "deleted", length = 70)
@JsonIgnore
 private  boolean deleted;

@Transient
 private  AnimalBreed animalBreed;

@Transient
 private  ClinicalHistory clinicalHistory;

@Column(name = "id")
 private Integer id;

@Transient
 private AnimalBreedRequest animalbreedrequest = new AnimalBreedRequestImpl();;

@Column(name = "id")
 private Integer id;

@Transient
 private ClinicalHistoryRequest clinicalhistoryrequest = new ClinicalHistoryRequestImpl();;

public Pet() {
    super();
}public Pet(String name, LocalDate birthday, String size, AnimalBreed animalBreed, ClinicalHistory clinicalHistory) {
    super();
    this.name = name;
    this.birthday = birthday;
    this.size = size;
    this.deleted = false;
    this.animalBreed = animalBreed;
    this.clinicalHistory = clinicalHistory;
}
public void setName(String name){
    this.name = name;
}


public String getName(){
    return name;
}


public LocalDate getBirthday(){
    return birthday;
}


public Integer getId(){
    return id;
}


public void setBirthday(LocalDate birthday){
    this.birthday = birthday;
}


public void setClinicalHistory(ClinicalHistory clinicalHistory){
 clinicalhistoryrequest.setClinicalHistory(clinicalHistory,this.id);
}



public String getSize(){
    return size;
}


public ClinicalHistory getClinicalHistory(){
  this.clinicalHistory = clinicalhistoryrequest.getClinicalHistory(this.id);
return this.clinicalHistory;
}


public void setSize(String size){
    this.size = size;
}


public boolean isDeleted(){
    return deleted;
}


public void setAnimalBreed(AnimalBreed animalBreed){
 animalbreedrequest.setAnimalBreed(animalBreed,this.id);
}



public void setId(Integer id){
    this.id = id;
}


public void setDeleted(boolean deleted){
    this.deleted = deleted;
}


public AnimalBreed getAnimalBreed(){
  this.animalBreed = animalbreedrequest.getAnimalBreed(this.id);
return this.animalBreed;
}


}