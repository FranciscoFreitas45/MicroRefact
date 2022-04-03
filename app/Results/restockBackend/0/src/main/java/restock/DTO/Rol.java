package restock.DTO;
 import javax.persistence.*;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class Rol {

 private  long serialVersionUID;

 private  Integer id;

 private  String tipus;

 private  String descripcio;


public String getTipus(){
    return tipus;
}


public String getDescripcio(){
    return descripcio;
}


public void setTipus(String tipus){
    this.tipus = tipus;
}


public void setId(Integer id){
    this.id = id;
}



public Integer getId(){
    return id;
}


public void setDescripcio(String descripcio){
    this.descripcio = descripcio;
}


}