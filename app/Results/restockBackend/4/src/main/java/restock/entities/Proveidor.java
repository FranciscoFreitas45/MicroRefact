package restock.entities;
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
@Entity
@Table(name = "proveidor")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
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


@Column(name = "numero", nullable = true)
public String getNumero(){
    return numero;
}


@Column(name = "plas_entrega", nullable = false)
public Integer getPlasEntrega(){
    return plasEntrega;
}


public void setCarrer(String carrer){
    this.carrer = carrer;
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


public void setNif(String nif){
    this.nif = nif;
}


@Column(name = "poblacio", nullable = true)
public String getPoblacio(){
    return poblacio;
}


@Fetch(FetchMode.JOIN)
@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "organitzacio_id")
public Organitzacio getOrganitzacio(){
    return organitzacio;
}


public void setCodiPostal(String codiPostal){
    this.codiPostal = codiPostal;
}


@Column(name = "carrer", nullable = true)
public String getCarrer(){
    return carrer;
}


@Column(name = "pais", nullable = true)
public String getPais(){
    return pais;
}


public void setOrganitzacio(Organitzacio organitzacio){
    this.organitzacio = organitzacio;
}


public void setPais(String pais){
    this.pais = pais;
}


public void setPoblacio(String poblacio){
    this.poblacio = poblacio;
}


public void setId(Integer id){
    this.id = id;
}


public void setNumero(String numero){
    this.numero = numero;
}


@Column(name = "nif", nullable = false)
public String getNif(){
    return nif;
}


public void setPlasEntrega(Integer plasEntrega){
    this.plasEntrega = plasEntrega;
}


@Column(name = "nom", nullable = false)
public String getNom(){
    return nom;
}


@Column(name = "codiPostal", nullable = true)
public String getCodiPostal(){
    return codiPostal;
}


}