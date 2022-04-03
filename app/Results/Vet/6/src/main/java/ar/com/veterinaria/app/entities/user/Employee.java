package ar.com.veterinaria.app.entities.user;
 import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
@Entity
@Table(name = "Veterinary")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class Employee extends Personimplements Serializable{

 private  long serialVersionUID;

@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "idEmployee", unique = true, nullable = false)
@JsonIgnore
 private  Integer id;

@Column(name = "clerkFileId", nullable = false, length = 50)
 private  String clerkFileId;

@Column(name = "veterinary", length = 50)
@JsonIgnore
 private  boolean veterinary;

@Column(name = "deleted", length = 50)
@JsonIgnore
 private  boolean deleted;


}