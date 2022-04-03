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
@Table(name = "Address")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class Address implements Serializable{

 private  long serialVersionUID;

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "idAddress", unique = true, nullable = false)
@JsonIgnore
 private  Integer id;

@Column(name = "number", nullable = true, length = 10)
 private  Integer number;

@Column(name = "name", nullable = true, length = 70)
 private  String name;

@Column(name = "city", nullable = true, length = 70)
 private  String city;

@Column(name = "department", nullable = true, length = 70)
 private  String department;

@Column(name = "zip", nullable = true, length = 10)
 private  Integer zip;

@Column(name = "deleted", length = 50)
@JsonIgnore
 private  boolean deleted;

public Address() {
    super();
}public Address(Integer number, String name, String city, String department, Integer zip) {
    super();
    this.number = number;
    this.name = name;
    this.city = city;
    this.department = department;
    this.zip = zip;
    this.deleted = false;
}
public void setName(String name){
    this.name = name;
}


public String getName(){
    return name;
}


public Integer getZip(){
    return zip;
}


public void setCity(String city){
    this.city = city;
}


public String getDepartment(){
    return department;
}


public void setDepartment(String department){
    this.department = department;
}


public Integer getId(){
    return id;
}


public void setNumber(Integer number){
    this.number = number;
}


public Integer getNumber(){
    return number;
}


public boolean isDeleted(){
    return deleted;
}


public void setId(Integer id){
    this.id = id;
}


public void setZip(Integer zip){
    this.zip = zip;
}


public String getCity(){
    return city;
}


public void setDeleted(boolean deleted){
    this.deleted = deleted;
}


}