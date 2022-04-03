package restock.DTO;
 import javax.persistence.GenerationType.IDENTITY;
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
public class Botiga {

 private  long serialVersionUID;

 private  Integer id;

 private  Organitzacio organitzacio;

 private  Usuari usuari;

 private  String nom;

 private  String carrer;

 private  String numero;

 private  String codiPostal;

 private  String poblacio;

 private  String pais;


@Column(name = "numero", nullable = true)
public String getNumero(){
    return numero;
}


public void setNom(String nom){
    this.nom = nom;
}


@Id
@GeneratedValue(strategy = IDENTITY)
@Column(name = "id", unique = true, nullable = false)
public Integer getId(){
    return id;
}


@Column(name = "poblacio", nullable = true)
public String getPoblacio(){
    return poblacio;
}


@Fetch(FetchMode.JOIN)
@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "organitzacio_id", nullable = false)
public Organitzacio getOrganitzacio(){
    return organitzacio;
}


public void setOrganitzacio(Organitzacio organitzacio){
    this.organitzacio = organitzacio;
}


@Column(name = "carrer", nullable = true)
public String getCarrer(){
    return carrer;
}


@Column(name = "pais", nullable = true)
public String getPais(){
    return pais;
}


public void setPoblacio(String poblacio){
    this.poblacio = poblacio;
}


public void setNumero(String numero){
    this.numero = numero;
}


@Column(name = "nom", nullable = false)
public String getNom(){
    return nom;
}


@Fetch(FetchMode.JOIN)
@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "usuari_id", nullable = false)
public Usuari getUsuari(){
    return usuari;
}


@Column(name = "codiPostal", nullable = true)
public String getCodiPostal(){
    return codiPostal;
}


}