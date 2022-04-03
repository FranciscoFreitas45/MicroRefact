package restock.DTO;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
public class Proveidor {

 private  long serialVersionUID;

 private  Integer id;

 private  String nif;

 private  String nom;

 private  String carrer;

 private  String numero;

 private  String codiPostal;

 private  String poblacio;

 private  String pais;

 private  Integer plasEntrega;

 private  Organitzacio organitzacio;


public String getNumero(){
    return numero;
}


public Integer getPlasEntrega(){
    return plasEntrega;
}


public void setNom(String nom){
    this.nom = nom;
}



public Integer getId(){
    return id;
}


public String getPoblacio(){
    return poblacio;
}


public Organitzacio getOrganitzacio(){
    return organitzacio;
}


public String getCarrer(){
    return carrer;
}


public String getPais(){
    return pais;
}


public void setPais(String pais){
    this.pais = pais;
}


public void setId(Integer id){
    this.id = id;
}


public String getNif(){
    return nif;
}


public String getNom(){
    return nom;
}


public String getCodiPostal(){
    return codiPostal;
}


}