package restock.entities;
 import javax.persistence.GenerationType.IDENTITY;
import java.util.Date;
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
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@Entity
@Table(name = "usuari")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Usuari {

 private  long serialVersionUID;

 private  Integer id;

 private  String user;

 private  String password;

 private  String nom;

 private  String cognom1;

 private  String cognom2;

 private  String nif;

 private  Date dataNaixement;

@JsonIgnore
 private  Organitzacio organitzacio;

 private  Rol rol;

 private  String correu;

/**
 * Usuari.
 */
public Usuari() {
}/**
 * Usuari.
 *
 * @param id the id
 */
public Usuari(final Integer id) {
    super();
    this.id = id;
}
public void setPassword(String password){
    this.password = password;
}


@Column(name = "user", nullable = false)
public String getUser(){
    return user;
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


@Column(name = "correu", nullable = false)
public String getCorreu(){
    return correu;
}


public void setNif(String nif){
    this.nif = nif;
}


@Fetch(FetchMode.JOIN)
@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "organitzacio_id")
public Organitzacio getOrganitzacio(){
    return organitzacio;
}


public void setCognom1(String cognom1){
    this.cognom1 = cognom1;
}


public void setCognom2(String cognom2){
    this.cognom2 = cognom2;
}


public void setRol(Rol rol){
    this.rol = rol;
}


@Fetch(FetchMode.JOIN)
@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "rol_id", nullable = false)
public Rol getRol(){
    return rol;
}


@Column(name = "password", nullable = false)
public String getPassword(){
    return password;
}


public void setOrganitzacio(Organitzacio organitzacio){
    this.organitzacio = organitzacio;
}


public void setCorreu(String correu){
    this.correu = correu;
}


@Column(name = "cognom2", nullable = true)
public String getCognom2(){
    return cognom2;
}


@Column(name = "cognom1", nullable = true)
public String getCognom1(){
    return cognom1;
}


@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", timezone = "Europe/Madrid")
@Column(name = "data_naixement", nullable = true)
public Date getDataNaixement(){
    return dataNaixement;
}


public void setId(Integer id){
    this.id = id;
}


public void setUser(String user){
    this.user = user;
}


@Column(name = "nif", nullable = false)
public String getNif(){
    return nif;
}


@Column(name = "nom", nullable = true)
public String getNom(){
    return nom;
}


public void setDataNaixement(Date dataNaixement){
    this.dataNaixement = dataNaixement;
}


}