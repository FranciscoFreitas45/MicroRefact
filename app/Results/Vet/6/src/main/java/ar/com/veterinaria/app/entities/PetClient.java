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
import ar.com.veterinaria.app.entities.user.Client;
@Entity
@Table(name = "petclient")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class PetClient implements Serializable{

 private  long serialVersionUID;

@Id
@GeneratedValue(strategy = GenerationType.SEQUENCE)
@Column(name = "idPetClient", unique = true, nullable = false)
@JsonIgnore
 private  Integer id;

@ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
@JoinColumn(name = "idClient", nullable = true)
 private  Client client;

@ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
@JoinColumn(name = "idPet", nullable = true)
 private  Pet Pet;

public PetClient() {
    super();
}public PetClient(Integer id, Client client, Pet pet) {
    super();
    this.id = id;
    this.client = client;
    Pet = pet;
}
public Pet getPet(){
    return Pet;
}


public void setClient(Client client){
    this.client = client;
}


public void setId(Integer id){
    this.id = id;
}


public Integer getId(){
    return id;
}


public Client getClient(){
    return client;
}


public void setPet(Pet pet){
    Pet = pet;
}


}