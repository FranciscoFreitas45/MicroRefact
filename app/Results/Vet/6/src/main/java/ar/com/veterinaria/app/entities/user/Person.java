package ar.com.veterinaria.app.entities.user;
 import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import ar.com.veterinaria.app.entities.Address;
import javax.persistence.InheritanceType;
import ar.com.veterinaria.app.entities.Request.AddressRequest;
import ar.com.veterinaria.app.entities.Request.Impl.AddressRequestImpl;
import ar.com.veterinaria.app.entities.DTO.Address;
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "Person")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class Person implements Serializable{

 private  long serialVersionUID;

@Id
// , generator = "PERSON_GEN")
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "idPerson", unique = true, nullable = false)
@JsonIgnore
 protected  Integer idPerson;

@Column(name = "name", nullable = false, length = 50)
 protected  String name;

@Column(name = "surname", nullable = false, length = 50)
 protected  String surname;

@Column(name = "dni", nullable = false, length = 11)
 protected  Integer dni;

@Column(name = "birthday", nullable = true, length = 11)
 protected  String birthday;

@Column(name = "telephone", nullable = true, length = 11)
 protected  Integer telephone;

@Column(name = "cellphone", nullable = false, length = 15)
 protected  Integer cellphone;

@Transient
 protected  Address address;

@Column(name = "id")
 private Integer id;

@Transient
 private AddressRequest addressrequest = new AddressRequestImpl();;

public Person() {
    super();
}public Person(Integer idPerson, String name, String surname, Integer dni, String birthday, Integer telephone, Integer cellphone, Address address) {
    super();
    this.idPerson = idPerson;
    this.name = name;
    this.surname = surname;
    this.dni = dni;
    this.birthday = birthday;
    this.telephone = telephone;
    this.cellphone = cellphone;
    this.address = address;
}public Person(Integer idPerson, String name, String surname, Integer dni, String birthday, Integer cellphone, Address address) {
    super();
    this.idPerson = idPerson;
    this.name = name;
    this.surname = surname;
    this.dni = dni;
    this.birthday = birthday;
    this.cellphone = cellphone;
    this.address = address;
}
public void setName(String name){
    this.name = name;
}


public Integer getDni(){
    return dni;
}


public String getName(){
    return name;
}


public String getBirthday(){
    return birthday;
}


public void setAddress(Address address){
 addressrequest.setAddress(address,this.id);
}



public Integer getIdPerson(){
    return idPerson;
}


public void setTelephone(Integer telephone){
    this.telephone = telephone;
}


public void setBirthday(String birthday){
    this.birthday = birthday;
}


public Integer getTelephone(){
    return telephone;
}


public Integer getCellphone(){
    return cellphone;
}


public void setSurname(String surname){
    this.surname = surname;
}


public void setIdPerson(Integer idPerson){
    this.idPerson = idPerson;
}


public Address getAddress(){
  this.address = addressrequest.getAddress(this.id);
return this.address;
}


public void setCellphone(Integer cellphone){
    this.cellphone = cellphone;
}


public String getSurname(){
    return surname;
}


public void setDni(Integer dni){
    this.dni = dni;
}


}