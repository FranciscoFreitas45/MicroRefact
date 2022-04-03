package ar.com.veterinaria.app.entities.vet;
 import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import ar.com.veterinaria.app.entities.Address;
@Entity
@Table(name = "Vet")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class Vet implements Serializable{

 private  long serialVersionUID;

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "idVet", unique = true, nullable = false)
@JsonIgnore
 private  Integer id;

@Column(name = "name", nullable = false, length = 50)
 private  String businessName;

@Column(name = "phone", nullable = false, length = 50)
 private  String businessPhone;

@Column(name = "address", nullable = false, length = 50)
 private  Address businessAddress;

@Column(name = "emergency", length = 50)
@JsonIgnore
 private  boolean emergency;

@Column(name = "deleted", length = 50)
@JsonIgnore
 private  boolean deleted;

public Vet() {
    super();
}
}