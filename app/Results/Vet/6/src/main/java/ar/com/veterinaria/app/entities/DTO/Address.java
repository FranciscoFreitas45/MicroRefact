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
public class Address implements Serializable{

 private  long serialVersionUID;

 private  Integer id;

 private  Integer number;

 private  String name;

 private  String city;

 private  String department;

 private  Integer zip;

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
public String getName(){
    return name;
}


public Integer getZip(){
    return zip;
}


public String getDepartment(){
    return department;
}


public Integer getId(){
    return id;
}


public Integer getNumber(){
    return number;
}


public String getCity(){
    return city;
}


}